package com.excilys.computerdatabase.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet implementation class AbstractServlet
 */
public class AbstractServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config ) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
