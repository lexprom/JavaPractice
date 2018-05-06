package com.pak;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String filename = "\\WEB-INF\\input.txt";

        ServletContext context = getServletContext();
        PrintWriter writer = response.getWriter();
        InputStream is = context.getResourceAsStream(filename);
        if (is != null)
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text;

            while ((text = reader.readLine()) != null) {
                writer.println(text + "</br>");
            }
        }

        writer.println("<form action=hello.jsp>" +
                "                        <button type=submit>Back</button>" +
                "                    </form>");

    }
}