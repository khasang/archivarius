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
                        <th>Вх. №</th>
                        <th>Дата получения</th>
                        <th>Отправитель</th>
                        <th>Название документа</th>
                        <th>Тип документа</th>
                        <th>Статус</th>
                        <th>Получатель</th>
                        <th>Срок ответа</th>
                    </tr>
                <c:forEach items="${documentList}" var="document">
                    <tr>
                        <td><a href="/document/${document.id}/edit">${document.id}</a></td>
                        <td>${document.dateOfReceive}</td>
                        <td>${document.author}</td>
                        <td>${document.title}</td>
                        <td>${document.name}</td>
                        <td>${document.documentType.name}</td>
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