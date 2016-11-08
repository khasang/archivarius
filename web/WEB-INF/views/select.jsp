<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees list</title>
</head>
<body>
<h1>All employees: </h1>
<c:forEach items="${list}" var="item">
    ${item}<br/>
</c:forEach>
</body>
</html>
