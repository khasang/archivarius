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
    <jsp:include page="include/left_sidebar_comp.jsp"/>
    <div id="content">
        <h2>Изменение параметров организации</h2>
        <form:form method="POST" action="/company/" modelAttribute="company">
            <form:hidden path="id"/>
            <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td><form:label path="address">Address</form:label></td>
                    <td><form:input path="address"/></td>
                </tr>
                <tr>
                    <td><form:label path="innNumber">Inn Number</form:label></td>
                    <td><form:input path="innNumber"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                    <td><input type="submit" name="delete" value="Delete"/></td>
                </tr>
            </table>
        </form:form>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>