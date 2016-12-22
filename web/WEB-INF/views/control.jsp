<!-- Include HEADER-->
<jsp:include page="include/header.jsp"/>
<!--PAGE CONTENT -->
<div id="body">
    <!-- Include NAVBAR-->
    <jsp:include page="include/navbar.jsp"/>
    <!-- Include LEFT SIDEBAR -->
    <jsp:include page="include/left_sidebar.jsp"/>
    <div id="content">
        <h2>Контроль исполнений</h2>
        <table class="table">
            <tr>
                <th width="2%"></th>
                <th width="8%">Вх. №</th>
                <th width="10%">Дата получения</th>
                <th width="15%">Отправитель</th>
                <th width="30%">Название документа</th>
                <th width="15%">Тип документа</th>
                <th width="10%">Статус</th>
                <th width="10%">Срок ответа</th>
            </tr>
            <!-- удалить ОТСЮДА - это хардкод для прмиера отображения -->
            <tr>
                <td><input type="checkbox" /></td>
                <td>16-001</td>
                <td>27.11.2016</td>
                <td>ОАО "Вторчермет"</td>
                <td>Расходный ордер №81754</td>
                <td>Расходный ордер</td>
                <td>Получен</td>
                <td>Не требуется</td>
            </tr>
            <tr>
                <td><input type="checkbox" /></td>
                <td>16-002</td>
                <td>27.11.2016</td>
                <td>ПАО "Торг Инвест"</td>
                <td>Счет фактура №18414</td>
                <td>Счет фактура </td>
                <td>На утверждении</td>
                <td>12.12.2016</td>
            </tr>
            <tr>
                <td><input type="checkbox" /></td>
                <td>16-003</td>
                <td>28.11.2016</td>
                <td>Налоговая инспекция</td>
                <td>Извещение о взыскании</td>
                <td>Гос. органы</td>
                <td>На утверждении</td>
                <td>10.02.2017</td>
            </tr>
            <tr>
                <td><input type="checkbox" /></td>
                <td>16-004</td>
                <td>29.11.2016</td>
                <td>Профсоюз</td>
                <td>Новогодние подарки детям</td>
                <td>Информационное письмо</td>
                <td>Разослан по организации</td>
                <td>Не требуется</td>
            </tr>
            <tr>
                <td><input type="checkbox" /></td>
                <td>16-005</td>
                <td>29.11.2016</td>
                <td>ЗАО "Алюминий"</td>
                <td>Счет фактура №55212</td>
                <td>Счет фактура </td>
                <td>На утверждении</td>
                <td>20.01.2017</td>
            </tr>
            <!-- удалить ДОСЮДА =))) -->
        </table>
    </div>
    <!-- Include RIGHT SIDEBAR-->
    <jsp:include page="include/right_sidebar_inbox.jsp"/>
    <!-- Include FOOTER-->
    <jsp:include page="include/footer.jsp"/>
</div>