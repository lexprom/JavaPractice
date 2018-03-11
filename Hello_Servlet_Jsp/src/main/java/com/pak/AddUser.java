package com.pak;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddUser extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = new PrintWriter("D:\\Program Files\\Java\\apache-tomcat-8.5.28\\webapps\\HelloServlet\\WEB-INF\\classes\\input.txt");
        out.println(request.getParameter("name") + " " + request.getParameter("pass") + " " + request.getParameter("bc" + " " + request.getParameter("money")));
        request.getRequestDispatcher("/hello").forward(request,response);
    }
}
