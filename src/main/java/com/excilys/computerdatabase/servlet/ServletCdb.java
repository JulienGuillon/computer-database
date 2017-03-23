package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.service.impl.ServiceComputerImpl;
import com.excilys.computerdatabase.springConfig.AppConfig;

/**
 * Servlet implementation class servletCdb.
 */

@WebServlet(name = "CdbServlet", urlPatterns = "/computerdatabasesssss")
public class ServletCdb extends AbstractServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletCdb.class);

    private static final long serialVersionUID = 1L;

    private String pageToForward = "/views/dashboard.jsp";
    
    @Autowired
    private ServiceComputer serviceComputer;
     
    private Page<Computer> page;
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
        page = getPage(request);
        List<ComputerDTO> computers = new ArrayList<>();
        int size = page.getElementsByPage();
        int numOfPage = page.getPage();
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
                page.setElementsByPage(size);
            } catch (NumberFormatException e) {
            }
        }
        if (request.getParameter("filter") != null) {
            try {
                filter = request.getParameter("filter");
                page.setFilter(filter);
                page.setPage(0);
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
        	page.setPage(numOfPage);
        	page = serviceComputer.getPage(page);
            computers = MapperComputerDto.toComputersDTO(page.getElements());

            request.getSession().setAttribute("computers", computers);
            request.getSession().setAttribute("numberOfPages", page.getTotalPages());
            request.getSession().setAttribute("currentPage", page.getPage());
            request.getSession().setAttribute("numberOfComputers", page.getTotalElements());
            request.getSession().setAttribute("size", size);
            request.getSession().setAttribute("filter", filter);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(pageToForward);
            rd.forward(request, response);            
        }
    }
    
    private Page getPage(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getSession().getAttribute("paginationComputer") == null) {
            Page page = new Page();
            httpServletRequest.getSession().setAttribute("paginationComputer", page);
        }
        
        return (Page) httpServletRequest.getSession().getAttribute("paginationComputer");
        
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
                serviceComputer.multipleDelete(selection);
                response.sendRedirect(request.getContextPath() + "/computerdatabase");
                break;
            default:
                break;
            }
        }
    }
}