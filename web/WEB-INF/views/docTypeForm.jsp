<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar_docType.jsp"/>
    <div id="content">
        <h2>Добавить тип документа</h2>
        <form:form method="POST" action="/doctype/" modelAttribute="docType">
            <form:hidden path="id"/>
            <table>
                <tr>
                    <td><form:label path="documentType">Тип документа</form:label></td>
                    <td><form:input path="documentType"/></td>
                </tr>
                <tr>
                    <td><form:label path="description">Описание</form:label></td>
                    <td><form:input path="description"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="submit"/></td>
                    <td><input type="submit" name="delete" value="delete"/></td>
                </tr>
            </table>
        </form:form>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>