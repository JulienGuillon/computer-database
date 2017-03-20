package com.excilys.computerdatabase.servlets;

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

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.services.ServiceCompany;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.utils.MapperComputerDTO;
import com.excilys.computerdatabase.validations.DateValidation;

/**
 * Servlet implementation class ServletEditComputer.
 */
@WebServlet(name = "/ServletEditComputer", urlPatterns = "/editComputer")
public class ServletEditComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletEditComputer.class);

    private String pageToForward = "/views/editComputer.jsp";

    private ServiceComputer serviceComputer = ServiceComputer.INSTANCE;

    private ServiceCompany serviceCompany = ServiceCompany.INSTANCE;

    private ComputerDTO computerDto;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEditComputer() {
        super();
        // TODO Auto-generated constructor stub
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
            computerDto = MapperComputerDTO.computerToComputerDTO(optionalComputer.get());
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
        try {
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
            Optional<Computer> computer = MapperComputerDTO.computerDtoToComputer(Optional.ofNullable(computerDto));
            serviceComputer.update(computer);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        response.sendRedirect(request.getContextPath() + "/computerdatabase");
    }

}
