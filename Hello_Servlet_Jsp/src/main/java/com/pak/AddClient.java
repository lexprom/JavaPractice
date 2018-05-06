package com.pak;

import com.pak.Model.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class AddClient extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("createClient.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int account = Integer.parseInt(req.getParameter("account"));
        int money = Integer.parseInt(req.getParameter("money"));

        Client bankClient = new Client(username, password, account, money);

        FileOutputStream fout = new FileOutputStream("\\WEB-INF\\input.txt",true);
        fout.write((bankClient.toString()).getBytes());
        fout.close();

        resp.sendRedirect("hello.jsp");
    }
}
