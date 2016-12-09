<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/css/styleLogin.css"/>
</head>

<body>
<div class="container">
    <div class="login">
        <h1 class="login-heading">
            <strong>Archivarius Login Form</strong>
        </h1>
        <c:url var="loginUrl" value="/login"/>
        <form action="${loginUrl}" method="post">
            <input type="text" class="input-txt" id="username" name="ssoId" placeholder="Enter Username" required>
            <input type="password" class="input-txt" id="password" name="password" placeholder="Enter Password"
                   required>
            <div class="login-footer">
                <a href="#" class="lnk">
                    <span class="icon icon--min">Забыли пароль?</span>
                </a>
                <button type="submit"
                        class="btn btn--right">Войти
                </button>
            </div>
        </form>
    </div>
</div>

</body>
</html>