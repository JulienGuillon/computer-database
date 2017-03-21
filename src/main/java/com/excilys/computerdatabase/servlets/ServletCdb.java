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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdatabase.dao.PaginationComputer;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.springConfig.AppConfig;
import com.excilys.computerdatabase.utils.MapperComputerDTO;
import com.excilys.computerdatabase.validations.DateValidation;

/**
 * Servlet implementation class servletCdb.
 */
@WebServlet(name = "CdbServlet", urlPatterns = "/computerdatabase")
public class ServletCdb extends AbstractServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletCdb.class);

    private static final long serialVersionUID = 1L;

    private String pageToForward = "/views/dashboard.jsp";

    private ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    
    private ServiceComputer serviceComputer = context.getBean(ServiceComputer.class);
 
    private PaginationComputer paginationComputer = context.getBean(PaginationComputer.class);
    
    //private ServiceComputer serviceComputer = ServiceComputer.INSTANCE;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCdb() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PaginationComputer paginationComputer = getPage(request);
        List<ComputerDTO> computers = new ArrayList<>();
        int size = paginationComputer.getSize();
        int numOfPage = 0;
        String filter = "";
        
        if (request.getParameter("nextPage") != null) {
            try {
                numOfPage = Integer.parseInt(request.getParameter("numOfPage"));
            } catch (NumberFormatException e) {
            }
        }
        if (request.getParameter("previousPage") != null) {
            try {
                numOfPage = Integer.parseInt(request.getParameter("numOfPage"));
            } catch (NumberFormatException e) {
            }
        }
        if (request.getParameter("numOfPage") != null) {
            try {
                numOfPage = Integer.parseInt(request.getParameter("numOfPage"));
            } catch (NumberFormatException e) {
            }
        }
        if (request.getParameter("limit") != null) {
            try {
                size = Integer.parseInt(request.getParameter("limit"));
                paginationComputer.setSize(size);
            } catch (NumberFormatException e) {
            }
        }
        if (request.getParameter("filter") != null) {
            try {
                filter = request.getParameter("filter");
                paginationComputer.setFilter(filter);
            } catch (NumberFormatException e) {
            }
        }
        if (request.getParameter("action") != null && request.getParameter("action").equals("add")) {
            response.sendRedirect(request.getContextPath() + "/addComputer");
            return;
        }
        if (request.getParameter("action") != null && request.getParameter("action").equals("edit")) {
            request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/editComputer");
            return;
        }
        if (pageToForward.equals("/views/dashboard.jsp")) {
            computers = MapperComputerDTO.computersToComputersDTO(paginationComputer.getPageNumber(numOfPage));

            request.getSession().setAttribute("computers", computers);
            request.getSession().setAttribute("numberOfPages", paginationComputer.getNumberOfPages());
            request.getSession().setAttribute("currentPage", paginationComputer.getPageIndex());
            request.getSession().setAttribute("numberOfComputers", paginationComputer.getNumberOfComputers());
            request.getSession().setAttribute("size", size);
            request.getSession().setAttribute("filter", filter);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(pageToForward);
            rd.forward(request, response);            
        }
    }

    /**
     * @return
     **/
    private PaginationComputer getPage(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getSession().getAttribute("paginationComputer") == null) {
            PaginationComputer paginationComputer = new PaginationComputer();
            httpServletRequest.getSession().setAttribute("paginationComputer", paginationComputer);
        }   
        return (PaginationComputer) httpServletRequest.getSession().getAttribute("paginationComputer");
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
            case "delete":
                String selection = request.getParameter("selection");
                String[] selections = selection.split(",");
                for (String select : selections) {                    
                    serviceComputer.delete(Long.parseLong(select)); 
                }
                response.sendRedirect(request.getContextPath() + "/computerdatabase");
                break;
            default:
                break;
            }
        }
    }
}