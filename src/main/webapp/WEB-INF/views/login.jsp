<%--
  Created by IntelliJ IDEA.
  User: DuanYangYu
  Date: 2017/5/3 0003
  Time: 上午 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>请登录后操作</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/angular-1.4.6.min.js"></script>
</head>
<body>
    <div class="container" style="padding-top: 18%">
        <form class="form-horizontal" role="form" id="form">
            <div class="form-group">
                <div class="text-center"><h3>请登录后访问</h3></div>
                <div class="col-sm-3"></div>
                <label for="username" class="col-sm-2 control-label">账号</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="username" placeholder="请输入账号" name="username">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3"></div>
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="password" placeholder="请输入密码" name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-3"></div>
                <div class="col-sm-offset-2 col-sm-3 text-right">
                    <button type="button" class="btn btn-default" id="login-btn">登录</button>
                </div>
            </div>
        </form>
    </div>
</body>
<script>
    var callBackUrl = "${requestUrl}";
    $(function () {
        $("button[type=button]").click(function () {
            $.ajax({
                url:"/userLogin",
                method:"post",
                data:$("#form").serialize(),
                success:function(data){
                    if (data){
                        alert("登录成功")
                        location.href = callBackUrl;
                    }else {
                        alert("账号或密码不正确!")
                    }
                }
            })
        })
    })
    $(function(){
        $(document).on('keydown',function(e){
            switch (e.which){
                case 13:$("#login-btn").click();
            }

        })

        $(document).on('keydown',function(e){
//           按下不同的键e.which会有不同的值
            console.log(e.which)
        })
    })
</script>
</html>
