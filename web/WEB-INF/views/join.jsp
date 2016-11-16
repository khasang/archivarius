<%--
  Created by IntelliJ IDEA.
  User: VKanunnikov
  Date: 16.11.2016
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Join</title>
</head>
<body>
<h1>Join Table</h1>
<div class="container">
    <div th:if="${not #list.isEmpty(employers)}">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Company</th>
            </tr>
            <tr th:each="employer : ${employers}">
                <td th:text="${employer.employerName}"></td>
                <td th:text="${employer.companyName}"></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
