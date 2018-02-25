<%@ page import="java.io.PrintWriter" %>
<!doctype html>
<head>
    <title>Clients</title>
    <style>
        table{
            width: 100%;
        }
    </style>
</head>
<body>
<table border="1">
    <caption>Clients</caption>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Bank account</th>
        <th>Amount of money</th>
    </tr>
    <tr><td>1</td><td>Thomas</td><td>qwerty123</td><td>11111</td><td>200$</td></tr>
    <%{
        PrintWriter pw = response.getWriter();
        if (request.getAttribute("id") != null) {
            pw.println("<tr><td>" + request.getAttribute("id").toString() + "</td>");
        }
        if (request.getAttribute("name") != null) {
            pw.println("<td>" + request.getAttribute("name").toString() + "</td>");
        }
        if (request.getAttribute("pass") != null) {
            pw.println("<td>" + request.getAttribute("pass").toString() + "</td>");
        }
        if (request.getAttribute("ba") != null) {
            pw.println("<td>" + request.getAttribute("ba").toString() + "</td>");
        }
        if (request.getAttribute("money") != null) {
            pw.println("<td>" + request.getAttribute("money").toString() + "</td></tr>");
        }
    }%>
</table>
<form action="newClient.jsp"><button>Add client</button></form>
</body>
</html>