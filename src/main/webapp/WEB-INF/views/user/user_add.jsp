<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加用户</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1 class="text-center">添加用户</h1>
    <form class="form-horizontal" role="form">
        <div class="row">
            <div class="form-group col-sm-6">
                <label class="col-sm-2 control-label" for="username">
                    姓名
                </label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="username">
                </div>
            </div>
            <div class="col-sm-6 form-group">
                    <label class="col-sm-2 control-label" for="phone">
                        电话
                    </label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="phone">
                    </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 form-group">
                <label class="col-sm-2 control-label" for="email">
                    邮箱
                </label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="email">
                </div>
            </div>
            <div class="col-sm-6 form-group">
                <label class="col-sm-2 control-label" for="password">
                    密码
                </label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="password">
                </div>
            </div>
        </div>
        <%--<div class="form-group has-warning">--%>
            <%--<label class="col-sm-2 control-label" for="inputWarning">--%>
                <%--输入警告--%>
            <%--</label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<input type="text" class="form-control" id="inputWarning">--%>
            <%--</div>--%>
        <%--</div>--%>
</form>
    <div class="text-right"><button type="button" class="btn btn-primary" onclick="location.href='/toAddUserPage'">添加</button></div>
</div>
</body>
</html>
