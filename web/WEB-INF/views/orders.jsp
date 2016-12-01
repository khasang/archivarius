<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar.jsp"/>
    <div id="content">
        <h2>Распоряжения</h2>
        <form action="" method="post" name="outboxForm" id="form" class="contact_form">
            <label for="orderTheme">Тема распорядительного документа:</label>
            <input type="text" name="orderTheme" placeholder="Введите название документа" id="orderTheme" />
            <br>
            <br>
            <br>
            <br>
            <label for="documentType">Тип распорядительного документа:</label>
            <select name="documentType" id="documentType" >
                <option value="A">Распоряжение</option>
                <option value="B">Приказ</option>
                <option value="C">Указание</option>
                <option value="D">Решение</option>
                <option value="E">Постановление</option>
            </select>
            <br>
            <br>
            <br>
            <label for="datepicker">Дата распоряжения:</label>
            <input type="date" id="datepicker">
            <br>
            <br>
            <label for="additional">Текст распоряжения:</label>
            <textarea rows="8" cols="50" name="additional"  id="additional"></textarea>
            <br>
            <br>
            <span>Получатели:</span>
            <br>
            <br>
            <input type="radio" name="send" id="all" value="sendToWholeOrg" /> <label for="all">  - вся организация</label>
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
    <jsp:include page="include/right_sidebar.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>