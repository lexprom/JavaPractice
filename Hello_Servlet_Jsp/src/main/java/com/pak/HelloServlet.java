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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Scanner in = new Scanner("D:\\Program Files\\Java\\apache-tomcat-8.5.28\\webapps\\HelloServlet\\WEB-INF\\classes\\com\\pak\\input.txt");
        if(in.hasNext()) {
            String input = in.next();
            String[] inputs = input.split(" ");
            try
            {
                Client client = new Client(inputs[0], inputs[1], Double.valueOf(inputs[2]), Double.valueOf(inputs[3]));
                List<Client> clients = new ArrayList<>();
                clients.add(client);
                request.setAttribute("clientList",clients);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
