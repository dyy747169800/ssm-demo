<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户列表</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/angular-1.4.6.min.js"></script>
</head>
<body>

<div class="container">
    <h1 class="text-center">用户列表</h1> <div class="text-right"><button type="button" class="btn btn-primary" onclick="location.href='/toAddUserPage'">添加用户</button></div>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>Firstname</th>
            <th>Phone</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.phone}</td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>