package com.pak.servlets;

import com.pak.model.Item;
import com.pak.service.ItemDAO;
import com.pak.service.ItemDBQuery;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddItem extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        dispatcher.forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
    {
        ItemDAO itemDAO = new ItemDAO("//resources//input.txt");
        ItemDBQuery itemDBQuery = null;
        try
        {
            itemDBQuery = new ItemDBQuery();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        String title = req.getParameter("title");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("desc");

        Item item = new Item(title,price,description);

        try {
            itemDBQuery.addItem(item);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("/items");
    }
}
