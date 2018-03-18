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
    public void writeFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (FileWriter writer = new FileWriter("D:\\Program Files\\Java\\apache-tomcat-8.5.28\\webapps\\HelloServlet\\WEB-INF\\classes\\com\\pak\\input.txt",false))
        {
            writer.write(request.getParameter("name") + " " + request.getParameter("pass") + " " + request.getParameter("bc") + " " + request.getParameter("money") + "\n");
            this.getServletContext().getRequestDispatcher("/hello").forward(request, response);
            PrintWriter pw = new PrintWriter(writer);
            pw.print("");
            pw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {
        writeFile(req,res);
    }
}