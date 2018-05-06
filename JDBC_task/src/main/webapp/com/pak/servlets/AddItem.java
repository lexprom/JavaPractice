package main.webapp.com.pak.servlets;

import main.webapp.com.pak.model.Item;
import main.webapp.com.pak.service.ItemDAO;
import main.webapp.com.pak.service.ItemDBQuery;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddItem extends HttpServlet
{
    private ItemDAO itemDAO = new ItemDAO("//resource//input.txt");
    private ItemDBQuery itemDBQuery = new ItemDBQuery();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        dispatcher.forward(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
    {
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
