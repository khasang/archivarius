<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar.jsp"/>
    <div id="content">
        <h2>Нарушители режима</h2>
        <table class="table">
            <tr>
                <th width="5%"></th>
                <th>Сотрудник</th>
                <th>Время в сети</th>
            </tr>
            <c:forEach items="${vklist}" var="item">
                <tr>
                    <td><input type="checkbox" /></td>
                    <td>${item[1]}</td>
                    <td>${item[0]}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>