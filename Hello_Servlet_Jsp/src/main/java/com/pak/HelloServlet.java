package com.pak;

import com.pak.Model.Client;
import org.codehaus.jackson.map.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = new Client(request.getParameter("name"),request.getParameter("pass"),Double.valueOf(request.getParameter("bc")),Double.valueOf(request.getParameter("money")));
        request.setAttribute("id",client.getID_client());
        request.setAttribute("name",client.getName());
        request.setAttribute("pass",client.getPassword());
        request.setAttribute("ba",client.getBankAccount());
        request.setAttribute("money",client.getAmountMoney());
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
