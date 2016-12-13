<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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
        <br>
        <br>
        <h3>Новый документ был успешно добавлен!</h3>
        <br>
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
                <tr>
                    <td></td>
                    <td>${id}</td>
                    <td>${dateOfReceive}</td>
                    <td>${author}</td>
                    <td>${title}</td>
                    <td>${documentType}</td>
                    <td>${status}</td>
                    <td>${destination}</td>
                    <td>${deadline}</td>
                </tr>
            </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>