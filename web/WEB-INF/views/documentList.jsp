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
        <h2>Все документы в базе</h2>

            <table class="table">
                    <tr>
                        <th width="2%"></th>
                        <th width="6%">Вх. №</th>
                        <th width="10%">Дата получения</th>
                        <th width="12%">Отправитель</th>
                        <th width="25%">Название документа</th>
                        <th width="12%">Тип документа</th>
                        <th width="10%">Статус</th>
                        <th width="12%">Получатель</th>
                        <th width="11%">Срок ответа</th>
                    </tr>
                <c:forEach items="${documentList}" var="document">
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td>${document.id}</td>
                        <td>${document.dateOfReceive}</td>
                        <td>${document.author}</td>
                        <td>${document.title}</td>
                        <td>${document.documentType}</td>
                        <td>${document.status}</td>
                        <td>${document.destination}</td>
                        <td>${document.deadline}</td>
                    </tr>
                </c:forEach>
                </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar_inbox.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>