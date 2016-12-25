<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar_docType.jsp"/>
    <div id="content">
        <h2>Типы документов</h2>
        <table class="table">
            <tr>
                <th>Тип документа</th>
                <th>Описание</th>
                <th>&nbsp;</th>
            </tr>
            <tr>
                <td>${docTypeGetId.name}</td>
                <td>${docTypeGetId.description}</td>
                <td><a href="/company/${docTypeGetId.id}/edit">Редактировать</a></td>
            </tr>
        </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>