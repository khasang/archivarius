<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar_user.jsp"/>
    <div id="content">
        <h2>Пользователи</h2>
        <table class="table">
            <tr>
                <th>Login</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${userList}" var="item">
                <tr>
                    <td>${item.login}</td>
                    <td><a href="/user/${item.id}/edit">Редактировать</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>