<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Archivarius</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- styles -->
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<body>
<div id="container">
    <div id="header">
        <h1><a href="">Archivarius</a></h1>
        <h2>Documents Workflow System</h2>
    </div>

    <div id="navigation">
        <ul>
            <li><a href="/inbox.html">Входящие документы</a></li>
            <li><a href="/outbox.html">Исходящие документы</a></li>
            <li><a href="/orders.html">Распоряжения</a></li>
            <li><a href="/control.html">Контроль исполнений</a></li>
            <li class="selected"><a href="/help.html">Справка</a></li>
        </ul>
    </div>
