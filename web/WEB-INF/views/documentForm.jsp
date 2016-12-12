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
        <form action="" method="post" name="documentForm" id="form" class="contact_form">
            <label for="title">Название:</label>
            <input type="text" name="title" placeholder="Введите название документа" id="title" />
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
                <option value="A">Сюда</option>
                <option value="B">нужно</option>
                <option value="C">добавить</option>
                <option value="D">получение типов докуменов</option>
                <option value="E">из базы данных</option>
            </select>
            <br>
            <br>
            <br>
            <label for="datepicker">Дата получения:</label>
            <input type="date" id="datepicker">
            <br>
            <br>
            <br>
            <label for="destination">Получатели:</label>
            <select name="destination" id="destination" >
                <option value="A">А вот сюда</option>
                <option value="B">нужно добавить</option>
                <option value="C">отделы или должности</option>
                <option value="D">получателей документа</option>
                <option value="E">из сущности БД</option>
            </select>
            <br>
            <br>
            <br>
            <label for="deadline">Контрольный срок:</label>
            <input type="date" id="deadline">
            <br>
            <br>
            <br>
            <label for="status">Состояние документа:</label>
            <select name="status" id="status" >
                <option value="A">Получен</option>
                <option value="B">Подписан</option>
                <option value="C">Требуется подпись</option>
                <option value="D">Отказано в согласновании</option>
                <option value="E">Ошибка в документе</option>
                <option value="F">Документооборот завершен</option>
            </select>
            <br>
            <br>
            <br>
        </form>
        <form action="" method="post" name="submitForm" id="SubForm">
            <label for="encloseFile">Прикрепить файл:</label>
            <br>
            <br>
            <input type="file" name="encloseFile" class="formbutton" />
            <br>
            <br>
            <input type="button" onclick="valid(document.getElementById('form'))" name="submit" value="Отправить" class="formbutton" />
        </form>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar_inside_inbox.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>