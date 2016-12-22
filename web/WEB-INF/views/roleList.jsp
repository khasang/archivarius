<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar_role.jsp"/>
    <div id="content">
        <h2>Роли</h2>
        <table class="table">
            <tr>
                <th>Название</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${roleList}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td><a href="/role/${item.id}/edit">Редактировать</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>