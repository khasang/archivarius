<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${vklist}" var="item">
    ${item[0]} - ${item[1]}<br/>
</c:forEach>
</body>
</html>
