<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar.jsp"/>
    <div id="content">
        <h2>Добавить новый документ</h2>
        <br>
        <br>
        <form:form method="POST" action="/document/" modelAttribute="document" class="contact_form">
            <form:hidden path="id"/>
            <form:label path="title">Title:</form:label>
            <form:input type="text" path="title" placeholder="Введите название документа" id="title" />
            <br>
            <br>
            <br>
            <form:label path="author">Автор:</form:label>
            <form:input path="author" placeholder="Введите автора документа" />
            <br>
            <br>
            <br>
            <label>Тип документа:</label>
            <form:select path="documentType">
                <form:option value="NONE" label="Выбрать"/>
                <form:options items="${docTypeDropBox}"/>
            </form:select>
            <br>
            <br>
            <br>
            <form:label path="dateOfReceive">Дата получения:</form:label>
            <form:input fmt:formatDate = "dd.mm.yyyy" type="date"  path="dateOfReceive" />
            <br>
            <br>
            <br>

            <label for="destination">Получатели:</label>
            <select name="destination" id="destination" >
                <option value="Бухгалтерия">Бухгалтерия</option>
                <option value="Служба сервиса">Служба сервиса</option>
                <option value="Ай Ти депратамент">Ай Ти депратамент</option>
                <option value="Канцелярия">Канцелярия</option>
                <option value="Отдел кадров">Отдел кадров</option>
            </select>
            <br>
            <br>
            <br>
            <form:label path="deadline">Контрольный срок:</form:label>
            <form:input fmt:formatDate = "dd.mm.yyyy" type="date"  path="deadline" />
            <br>
            <br>
            <br>
            <label for="status">Состояние документа:</label>
            <select name="status" id="status" >
                <option value="Получен">Получен</option>
                <option value="Подписан">Подписан</option>
                <option value="Требуется подпись">Требуется подпись</option>
                <option value="Отказано в согласновании">Отказано в согласновании</option>
                <option value="Ошибка в документе">Ошибка в документе</option>
                <option value="Документооборот завершен">Документооборот завершен</option>
            </select>
            <br>
            <br>
            <br>

            <tr>
                <td><input type="submit" value="Submit"/></td>
                <td><input type="submit" name="delete" value="Delete"/></td>
            </tr>
        </form:form>
        <form:form method="POST" action="/document/" modelAttribute="document">
            <label for="encloseFile">Прикрепить файл:</label>
            <br>
            <br>
            <input type="file" name="encloseFile" class="formbutton" />
            <br>
            <br>
            <input type="button" onclick="valid(document.getElementById('form'))" name="submit" value="Отправить" class="formbutton" />
        </form:form>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar_inside_inbox.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>