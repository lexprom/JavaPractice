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
    <c:forEach items="${clientList}" var="item">
        ${item}
    </c:forEach>

</table>
<a href="/HelloServlet/add">add new client</a>
<form action="newClient.jsp"><button>Add client</button></form>
</body>
</html>