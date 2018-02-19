package com.pak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        PrintWriter writer = response.getWriter();
        writer.println("<HTML>");
        writer.println("<HEAD>");
        writer.println("<TITLE>Servlet Testing</TITLE>");
        writer.println("</HEAD>");
        writer.println("<BODY>");
        writer.println("Welcome to the Servlet Testing Center");
        writer.println("</BODY>");
        writer.println("</HTML>");
    }
}
