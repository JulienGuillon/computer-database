package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.services.ServiceCompany;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.validation.DateValidation;

/**
 * Servlet implementation class ServletEditComputer.
 */
@WebServlet(name = "/ServletEditComputer", urlPatterns = "/editComputer")
public class ServletEditComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletEditComputer.class);

    private String pageToForward = "/views/editComputer.jsp";

    @Autowired
    private ServiceComputer serviceComputer;

    @Autowired
    private ServiceCompany serviceCompany;

    private ComputerDTO computerDto;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditComputer() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = (int) request.getSession().getAttribute("id");
        request.getSession().setAttribute("id", id);
        Optional<Computer> optionalComputer = serviceComputer.find(id);
        if (optionalComputer.isPresent()) {
            computerDto = MapperComputerDto.toComputerDTO(optionalComputer.get());
            request.getSession().setAttribute("computer", computerDto);
            List<Company> companies = serviceCompany.findAll();
            request.getSession().setAttribute("companies", companies);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(pageToForward);
            rd.forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/computerdatabase");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("computerName");
        if (StringUtils.isNotBlank(name)) {
            computerDto.setName(name);
        }
        LocalDate introduced = null;
        if (DateValidation.formatIsValid(Optional.of(request.getParameter("introduced")))) {
            computerDto.setIntroduced(request.getParameter("introduced"));
        }

        LocalDate discontinued = null;
        if (DateValidation.formatIsValid(Optional.of(request.getParameter("discontinued")))) {
            if (DateValidation.dateIsValid(Optional.ofNullable(introduced),
                    Optional.ofNullable(LocalDate.parse(request.getParameter("discontinued"))))) {
                computerDto.setDiscontinued(request.getParameter("discontinued"));
            }
        }
        int companyId = Integer.parseInt(request.getParameter("companyId"));

        if (companyId != 0) {
            computerDto.setManufacturerId(Long.parseLong(request.getParameter("companyId")));
        }
        Optional<Computer> computer = MapperComputerDto.fromDTOComputer(Optional.ofNullable(computerDto));
        serviceComputer.update(computer);

        response.sendRedirect(request.getContextPath() + "/computerdatabase");
    }

}
