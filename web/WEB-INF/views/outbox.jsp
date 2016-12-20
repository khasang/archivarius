<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar.jsp"/>
    <div id="content">
        <h2>Исходящие документы</h2>
        <form action="" method="post" name="outboxForm" id="form" class="contact_form">
            <label for="documentName">Название документа:</label>
            <input type="text" name="documentName" placeholder="Введите название документа" id="documentName" />
            <br>
            <br>
            <label for="documentType">Тип документа:</label>
            <select name="documentType" id="documentType" >
                <option value="A">По умолчанию</option>
                <option value="B">Предложение</option>
                <option value="C">Заявка</option>
                <option value="D">Письмо</option>
                <option value="E">Справка</option>
                <option value="F">Обзор</option>
                <option value="G">Договор</option>
                <option value="H">Служебная записка</option>
                <option value="I">Добровольное сообщение</option>
            </select>
            <br>
            <br>
            <label for="description">Краткое описание:</label>
            <textarea rows="8" cols="50" name="description"  id="description"></textarea>
            <br>
            <br>
            <label for="additional">Текст сопровождения (по необходимости):</label>
            <textarea rows="8" cols="50" name="additional"  id="additional"></textarea>
            <br>
            <br>
            <span>Получатели:</span>
            <br>
            <br>
            <input type="radio" name="send" id="all" value="sendToWholeOrg"> <label for="all">  - вся организация</label>
            <br>
            <br>
            <input type="radio" name="send" id="management" value="sendToManagement" /> <label for="management">  - руководство</label>
            <br>
            <br>
            <input type="radio" name="send" id="group" value="sendToGroup" /> <label for="group">  - выбранная группа</label>
            <br>
            <br>
            <input type="radio" name="send" id="executor" value="sendToExecutor" /> <label for="executor">  - конкретное лицо</label>
            <br>
            <br>
            <br>
        </form>
        <form action="" method="post" name="submitForm" id="subForm">
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
    <jsp:include page="include/right_sidebar_inbox.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>