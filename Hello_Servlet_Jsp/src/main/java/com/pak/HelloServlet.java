package com.pak;

import com.pak.Model.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Scanner in = new Scanner("C:\\Users\\pakas\\Downloads\\apache-tomcat-8.5.29\\webapps\\HelloServlet\\input.txt");
        //if(in.hasNext())
        //{
        List<Client> clients = new ArrayList<>();
        String input = in.next();
        String[] inputs = input.split(" ");
        System.out.println(Arrays.toString(inputs));
        try {
            Client client = new Client(inputs[0], inputs[1], Double.valueOf(inputs[2]), Double.valueOf(inputs[3]));

            clients.add(client);
            request.setAttribute("clientList", clients);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        PrintWriter pw = response.getWriter();
        pw.print(clients.size());
        request.getRequestDispatcher("index.jsp").forward(request,response);
        // this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
