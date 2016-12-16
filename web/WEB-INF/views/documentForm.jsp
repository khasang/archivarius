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
                <option value="Приказ">Приказ</option>
                <option value="Распоряжение">Распоряжение</option>
                <option value="Служебная записка">Служебная записка</option>
                <option value="Рапорт">Рапорт</option>
                <option value="Предписание">Предписание</option>
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
                <option value="Бухгалтерия">Бухгалтерия</option>
                <option value="Служба сервиса">Служба сервиса</option>
                <option value="Ай Ти депратамент">Ай Ти депратамент</option>
                <option value="Канцелярия">Канцелярия</option>
                <option value="Отдел кадров">Отдел кадров</option>
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