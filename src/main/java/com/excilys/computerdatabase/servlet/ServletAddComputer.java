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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.service.impl.ServiceCompanyImpl;
import com.excilys.computerdatabase.service.impl.ServiceComputerImpl;
import com.excilys.computerdatabase.springConfig.AppConfig;
import com.excilys.computerdatabase.validation.DateValidation;

/**
 * Servlet implementation class ServletAddComputer.
 */
@WebServlet(name = "/ServletAddComputer", urlPatterns = "/addComputer")
public class ServletAddComputer extends AbstractServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletAddComputer.class);

    private String pageToForward = "/views/addComputer.jsp";
    
    private ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Autowired
    private ServiceComputer serviceComputer;

    @Autowired
    private ServiceCompany serviceCompany;

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
        List<Company> companies = serviceCompany.findAll();
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
