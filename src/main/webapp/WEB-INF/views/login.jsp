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
    <title>请登录后操作!</title>
    <script src="/js/jquery-1.10.2.min.js"></script>
</head>
<body>
    <h1>请登录后操作!</h1>
    <form action="/login" id="form">
        <label>
            <input type="text" name="username">
        </label>账号<br>
        <label>
            <input type="text" name="password">
        </label>密码<br>
        <input type="button" value="登录"><br>
    </form>
</body>
<script>
    var callBackUrl = "${requestUrl}";
    $(function () {
        $("input[type=button]").click(function () {
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
</script>
</html>
