
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>New client</title>
    <style type="text/css">
        tr{
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <form action="add" method="post">
        <table border="1px black">
            <tr>
                <td>Name</td>
                <td>
                    <input type="text" name="username" required pattern="^[a-zA-Z]+$">
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td>
                    <input type="password" name="password" required>
                </td>
            </tr>
            <tr>
                <td>Account</td>
                <td>
                    <input type="number" name="account" required pattern="^[ 0-9]+$">
                </td>
            </tr>
            <tr>
                <td>Money</td>
                <td>
                    <input type="number" name="money" required pattern="^[ 0-9]+$">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">Create</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
