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
            <form:label path="title">Title:</form:label>
            <form:input type="text" path="title" placeholder="Введите название документа" id="title" />
            <br>
            <br>
            <br>
            <label for="author">Автор:</label>
            <input type="text" name="author" placeholder="Введите автора документа" id="author" />
            <br>
            <br>
            <br>
            <label for="documentType">Тип документа:</label>
            <select name="documentType" id="documentType" >
                <option value="Сюда">Сюда</option>
                <option value="нужно">нужно</option>
                <option value="добавить">добавить</option>
                <option value="получение">получение типов докуменов</option>
                <option value="базы">из базы данных</option>
            </select>
            <br>
            <br>
            <br>
            <label for="datepicker">Дата получения:</label>
            <input fmt:formatDate type="date" name="dateOfReceive" id="datepicker">
            <br>
            <br>
            <br>
            <label for="destination">Получатели:</label>
            <select name="destination" id="destination" >
                <option value="сюда">А вот сюда</option>
                <option value="нужно">нужно добавить</option>
                <option value="отделы">отделы или должности</option>
                <option value="получателей">получателей документа</option>
                <option value="сущности">из сущности БД</option>
            </select>
            <br>
            <br>
            <br>
            <label for="deadline">Контрольный срок:</label>
            <input type="date" name="deadline" id="deadline">
            <br>
            <br>
            <br>
            <label for="status">Состояние документа:</label>
            <select name="status" id="status" >
                <option value="Получен">Получен</option>
                <option value="Подписан">Подписан</option>
                <option value="Требуется подпись">Требуется подпись</option>
                <option value="ООтказано в согласновании">Отказано в согласновании</option>
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