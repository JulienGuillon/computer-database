package com.excilys.computerdatabase;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class test.
 */
@WebServlet("/test")
public class Test extends HttpServlet {

    private static final long serialVersionUID = 1L;

    int count;

    @Override
    protected void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException, IOException {
            // Set a cookie for the user, so that the counter does not increase
            // every time the user press refresh
            HttpSession session = request.getSession(true);
            // Set the session valid for 5 secs
            session.setMaxInactiveInterval(5);
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            if (session.isNew()) {
                    count++;
            }
            out.println("This site has been accessed " + count + " times.");
    }
}
