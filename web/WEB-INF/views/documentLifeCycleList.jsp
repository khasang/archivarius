<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar_comp.jsp"/>
    <div id="content">
        <h2>Движение документа</h2>
        <table class="table">
            <tr>
                <th>Название документа</th>
                <th>Подразделение</th>
                <th>Дата получения</th>
                <th>Дата окончания</th>
                <th>Статус</th>
            </tr>
            <c:forEach items="${documentLifeCycleList}" var="item">
                <tr>
                    <td>${item.document.name}</td>
                    <td>${item.department.name}</td>
                    <td>${item.startDate}</td>
                    <td>${item.finishDate}</td>
                    <td>${item.lifeCycle}</td>
                    <td><a href="/document/life_cycle/${item.id}/edit">Редактировать</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>