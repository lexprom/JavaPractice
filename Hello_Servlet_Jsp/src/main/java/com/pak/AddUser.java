package com.pak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddUser extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (FileWriter writer = new FileWriter("C:\\Users\\pakas\\Downloads\\apache-tomcat-8.5.28\\webapps\\HelloServlet\\WEB-INF\\classes\\input.txt",true))
        {
            writer.write(request.getParameter("name") + " " + request.getParameter("pass") + " " + request.getParameter("bc") + " " + request.getParameter("money") + "\n");
            writer.flush();
            request.getRequestDispatcher("/hello").forward(request, response);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}