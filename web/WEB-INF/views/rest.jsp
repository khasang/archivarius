<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Company API</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.css" />"/>
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
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
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
<h1>REST API</h1>
<div class="panel-body">
    <div class="form-group">
        <label class="control-label col-lg-2">Get all companies</label>
        <div class="col-lg-10">
            <div class="row">
                <div class="col-xs-1">GET</div>
                <div class="col-xs-2"> /api/company/all</div>
                <form class="form-inline">
                    <div class="col-xs-5">
                    </div>
                    <div class="col-xs-4">
                        <button class="btn btn-primary" type="button" onclick="RestGet('all')">
                            GET ALL
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-lg-2">Get company by id</label>
        <div class="col-lg-10">
            <div class="row">
                <div class="col-xs-1">GET</div>
                <div class="col-xs-2"> /api/company/{id}</div>
                <form class="form-inline">
                    <div class="col-xs-5">
                        <input type="number" class="form-control" placeholder="Id" required id="getCompanyID">
                    </div>
                    <div class="col-xs-4">
                        <button class="btn btn-primary" type="button" onclick="RestGet($('#getCompanyID').val())">
                            GET
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-lg-2">Add company</label>
        <div class="col-lg-10">
            <div class="row">
                <div class="col-xs-1">POST</div>
                <div class="col-xs-2"> /api/company</div>
                <form class="form-inline">
                    <div class="col-xs-5">
                        <input type="number" class="form-control" placeholder="Id" required id="post_id">
                        <input type="text" id="post_name" class="form-control" placeholder="Enter name" required>
                    </div>
                    <div class="col-xs-4">
                        <button class="btn btn-primary" type="button" onclick="RestPost()">POST</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-lg-2">Update company</label>
        <div class="col-lg-10">
            <div class="row">
                <div class="col-xs-1">PUT</div>
                <div class="col-xs-2"> /api/company</div>
                <form class="form-inline">
                    <div class="col-xs-5">
                        <input type="number" class="form-control" placeholder="Id" required id="put_id">
                        <input type="text" id="put_name" class="form-control" placeholder="Enter name" required>
                    </div>
                    <div class="col-xs-4">
                        <button class="btn btn-primary" type="button" onclick="RestPut()">PUT</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-lg-2">Delete company by id</label>
        <div class="col-lg-10">
            <div class="row">
                <div class="col-xs-1">DELETE</div>
                <div class="col-xs-2">/api/company/{id}</div>
                <form class="form-inline">
                    <div class="col-xs-5">
                        <input type="number" class="form-control" placeholder="Id" required id="deleteQuestionId">
                    </div>
                    <div class="col-xs-4">
                        <button class="btn btn-primary" type="button" onclick="RestDelete()">DELETE</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
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