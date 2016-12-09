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
        <h2>Организации</h2>
        <table class="table">
            <tr>
                <th>ИНН</th>
                <th>Название</th>
                <th>Адрес</th>
                <th>&nbsp;</th>
            </tr>
                <tr>
                    <td>${companygetId.innNumber}</td>
                    <td>${companygetId.name}</td>
                    <td>${companygetId.address}</td>
                    <td><a href="/company/${companygetId.id}/edit">Редактировать</a></td>
                </tr>
        </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>