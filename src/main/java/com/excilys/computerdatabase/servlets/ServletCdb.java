package com.excilys.computerdatabase.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

import com.excilys.computerdatabase.dao.PaginationComputer;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.services.ServiceCompany;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.utils.MapperComputerDTO;
import com.excilys.computerdatabase.validations.DateValidation;

/**
 * Servlet implementation class servletCdb.
 */
@WebServlet(name = "CdbServlet", urlPatterns = "/computerdatabase")
public class ServletCdb extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletCdb.class);

    private static final long serialVersionUID = 1L;

    private String pageToForward = "/views/dashboard.jsp";

    private ServiceComputer serviceComputer = ServiceComputer.INSTANCE;

    private ServiceCompany serviceCompany = ServiceCompany.INSTANCE;
    
    private PaginationComputer paginationComputer = new PaginationComputer();


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCdb() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ComputerDTO> computers = new ArrayList<>();

        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
            case "numOfPage":
                if (request.getParameter("numOfPage") != null) {
                    try {
                        int numOfPage = Integer.parseInt(request.getParameter("numOfPage"));
                        computers = MapperComputerDTO.computersToComputersDTO(paginationComputer.getPageNumber(numOfPage));
                    } catch (NumberFormatException e) {
                    }
                }
                break;
            case "nextPage":
                computers = MapperComputerDTO.computersToComputersDTO(paginationComputer.nextPage());
                break;
            case "previousPage":
                computers = MapperComputerDTO.computersToComputersDTO(paginationComputer.previousPage());
                break;
            case "size":
                if (request.getParameter("size") != null) {
                    try {
                        int size = Integer.parseInt(request.getParameter("size"));
                        paginationComputer.setSize(size);
                    } catch (NumberFormatException e) {
                    }
                }
                break;
            case "add":
                List<Company> companies = MapperComputerDTO
                        .optionalListOfCompaniesToListOfCompanies(serviceCompany.findAll());
                request.getSession().setAttribute("companies", companies);
                pageToForward = "/views/addComputer.jsp";
                break;
            default:
                // TODO Log..
                break;
            }
        }
        if (request.getParameter("numOfPage") != null) {
            try {
                int numOfPage = Integer.parseInt(request.getParameter("numOfPage"));
                computers = MapperComputerDTO.computersToComputersDTO(paginationComputer.getPageNumber(numOfPage));
            } catch (NumberFormatException e) {
            }
        }
        if (pageToForward.equals("/views/dashboard.jsp")) {
            if (computers.isEmpty()) {
                computers = MapperComputerDTO.computersToComputersDTO(paginationComputer.getPageNumber(1));
            }

            request.getSession().setAttribute("computers", computers);
            request.getSession().setAttribute("numberOfPages", paginationComputer.getNumberOfPages());
            request.getSession().setAttribute("currentPage", paginationComputer.getPageIndex());
            request.getSession().setAttribute("numberOfComputers", paginationComputer.getNumberOfComputers());
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageToForward);
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
            case "add":
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

            default:
                break;
            }
        }
        doGet(request, response);
    }
}