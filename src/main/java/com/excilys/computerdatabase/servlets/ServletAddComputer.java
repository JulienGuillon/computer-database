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

import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.services.ServiceCompany;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.utils.MapperComputerDTO;
import com.excilys.computerdatabase.validations.DateValidation;

/**
 * Servlet implementation class ServletAddComputer.
 */
@WebServlet(name = "/ServletAddComputer", urlPatterns = "/addComputer")
public class ServletAddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletAddComputer.class);

    private String pageToForward = "/views/addComputer.jsp";

    private ServiceComputer serviceComputer = ServiceComputer.INSTANCE;

    private ServiceCompany serviceCompany = ServiceCompany.INSTANCE;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Company> companies = MapperComputerDTO.optionalListOfCompaniesToListOfCompanies(serviceCompany.findAll());
        request.getSession().setAttribute("companies", companies);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageToForward);
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("computerName");
            Computer.Builder computerBuilder = new Computer.Builder().withName(name);
            LocalDate introduced = null;
            if (DateValidation.formatIsValid(Optional.of(request.getParameter("introduced")))
                    && StringUtils.isNotBlank(request.getParameter("introduced"))) {
                introduced = LocalDate.parse(request.getParameter("introduced"));
                computerBuilder.withIntroduced(introduced);
            }

            LocalDate discontinued = null;
            if (DateValidation.formatIsValid(Optional.of(request.getParameter("discontinued")))
                    && StringUtils.isNotBlank(request.getParameter("discontinued"))) {
                if (DateValidation.dateIsValid(Optional.ofNullable(introduced),
                        Optional.ofNullable(LocalDate.parse(request.getParameter("discontinued"))))) {
                    discontinued = LocalDate.parse(request.getParameter("discontinued"));
                    computerBuilder.withDiscontinued(discontinued);
                }
            }
            int companyId = Integer.parseInt(request.getParameter("companyId"));

            Company company = null;

            if (companyId != 0) {
                company = new Company.Builder().withId(companyId).build();
                computerBuilder.withManufacturer(company);
            }
            serviceComputer.create(Optional.ofNullable(computerBuilder.build()));

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        response.sendRedirect(request.getContextPath() + "/computerdatabase");
    }

}
