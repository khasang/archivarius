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
    <jsp:include page="include/left_sidebar_user.jsp"/>
    <div id="content">
        <h2>Изменение параметров пользователя</h2>
        <form:form method="POST" action="/user/" modelAttribute="user">
            <form:hidden path="id"/>
            <table>
                <tr>
                    <td><form:label path="login">Login</form:label></td>
                    <td><form:input path="login"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td><form:label path="">ROLE</form:label></td>
                    <td>
                        <form:select path="roles">
                            <form:options items="${roles}"/>
                        </form:select>
                    </td>
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