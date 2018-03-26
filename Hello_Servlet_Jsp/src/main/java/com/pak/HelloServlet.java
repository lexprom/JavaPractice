package com.pak;

import com.pak.Model.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            FileReader fr = new FileReader("C:\\Users\\pakas\\Downloads\\apache-tomcat-8.5.29\\webapps\\HelloServlet\\input.txt");
//            Scanner in = new Scanner(fr);
            List<Client> clients = new ArrayList<>();
//            String input = in.nextLine();
//            String[] inputs = input.split(" ");
            try
            {
                //Client client = new Client(inputs[0], inputs[1], Double.valueOf(inputs[2]), Double.valueOf(inputs[3]));
                Client client = new Client("Tom", "sada",4543,543);
                clients.add(client);
                request.setAttribute("clientList", clients);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
        }
    }
}
