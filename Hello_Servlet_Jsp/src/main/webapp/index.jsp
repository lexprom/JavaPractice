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
        PrintWriter pw = ;
        if (request.getAttribute("id") != null) {
            pw.println("<p>" + request.getAttribute("id").toString() + "</p>");
        }
        if (request.getAttribute("name") != null) {
            pw.println("<p>" + request.getAttribute("name").toString() + "</p>");
        }
        if (request.getAttribute("pass") != null) {
            pw.println("<p>" + request.getAttribute("pass").toString() + "</p>");
        }
        if (request.getAttribute("ba") != null) {
            pw.println("<p>" + request.getAttribute("ba").toString() + "</p>");
        }
        if (request.getAttribute("money") != null) {
            pw.println("<p>" + request.getAttribute("money").toString() + "</p>");
        }
    }%>
</table>
<form action="newClient.jsp"><button>Add client</button></form>
</body>
</html>