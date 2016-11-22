<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Company API</title>
</head>
<body>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
    var service = '/api/company';
    var RestGet = function (id) {
        $.ajax({
            type: 'GET',
            url: service + "/" + id,
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(JSON.stringify(jqXHR));
            }
        });
    };
    var RestPost = function () {
        var JSONObject = {
            'id': $('#post_id').val(),
            'name': $('#post_name').val()
        };
        $.ajax({
            type: 'POST',
            url: service,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function(result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(JSON.stringify(jqXHR));
            }
        });
    };
    var RestPut = function () {
        var JSONObject = {
            'id': $('#put_id').val(),
            'name': $('#put_name').val()
        };
        $.ajax({
            type: 'PUT',
            url: service,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(JSON.stringify(jqXHR));
            }
        });
    };
    var RestDelete = function () {
        $.ajax({
            type: 'DELETE',
            url: service + "/" + $('#deleteQuestionId').val(),
            dataType: 'json',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(JSON.stringify(jqXHR));
            }
        });
    };
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        REST API
    </div>
    <div class="panel-body">

        <table class="table" border="1">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Get all companies</td>
                <td><code><strong>GET</strong> /api/company/all</code></td>
                <td>
                    <button type="button" onclick="RestGet('all')">GET ALL</button>
                </td>
            </tr>
            <tr>
                <td>Get company by id</td>
                <td><code><strong>GET</strong> /api/company/{id}</code></td>
                <td>
                    Id: <input type="number" required id="getCompanyID">
                    <br/>
                    <button type="button" onclick="RestGet($('#getCompanyID').val())">GET</button>
                </td>
            </tr>
            <tr>
                <td>Add company</td>
                <td><code><strong>POST</strong> /api/company</code></td>
                <td>
                    <form class="form-inline">
                        id: <input type="number" required id="post_id">
                        <br>
                        name: <input type="text" id="post_name">
                        <br/>
                        <button type="button" onclick="RestPost()">POST</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>Update question</td>
                <td><code><strong>PUT</strong> /api/question</code></td>
                <td>
                    <form class="form-inline">
                        Id: <input type="number" required id="put_id">
                        <br>
                        name: <input type="text" id="put_name" required>
                        <br/>
                        <button type="button" onclick="RestPut()">PUT</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>Delete question by id</td>
                <td><code><strong>DELETE</strong> /api/company/{id}</code></td>
                <td>
                    Id: <input type="number" required id="deleteQuestionId">
                    <br/>
                    <button type="button" onclick="RestDelete()">DELETE</button>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <strong>RESPONSE</strong>
    </div>
    <div class="panel-body" id="response">
    </div>
</div>
</body>
</html>