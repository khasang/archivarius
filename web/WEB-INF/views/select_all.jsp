<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees with tasks</title>
</head>
<body>
<h1>Task of employees: </h1>
<c:forEach items="${list}" var="item">
    ${item}<br/>
</c:forEach>
</body>
</html>
