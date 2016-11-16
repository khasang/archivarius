<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${not empty joinResponses}">
    <table>
        <tr>
            <th>Name</th>
            <th>Company</th>
        </tr>
        <c:forEach var="j" items="${joinResponses}">
            <tr>
                <td>${j.workerName}</td>
                <td>${j.companyName}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</body>
</html>
