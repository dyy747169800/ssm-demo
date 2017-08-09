<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户列表</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/angular-1.4.6.min.js"></script>
</head>
<body ng-controller="myCtrl">
<nav class="navbar navbar-inverse">
   <a class="navbar-brand hidden-sm active">Hello,{{ loginName }}</a>
</nav>
<div class="container">
    <div class="alert alert-info">
        <h3 style="display:inline" >用户列表</h3>
        <label style="float: right;cursor: pointer;margin-top: 3px">
            <input style="vertical-align: text-bottom; cursor: pointer;margin: 0px 3px 1px 0px;" type="checkbox" ng-model="realTime">实时更新数据
        </label>
    </div>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>Firstname</th>
            <th>Phone</th>
            <th>Email</th>
            <th>CreateAt</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
            <tr ng-repeat="user in userList | orderBy:user.createDate:false">
                <td>{{user.id}}</td>
                <td>{{user.username}}</td>
                <td>{{user.phone}}</td>
                <td><a href="mailto:{{user.email}}">{{user.email}}</a></td>
                <td>{{user.createDate | date:"yyyy-MM-dd HH:mm:ss" }}</td>
                <td class="text-center">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#adduser-modal" ng-click="edit(this)">编辑</button>
                        <button type="button" class="btn btn-danger" ng-click="delete(this)">删除</button>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="text-right">
        <button type="button" class="btn btn-primary small" data-toggle="modal" data-target="#adduser-modal" ng-click="openModal()">新增</button>
    </div>
</div>
<div class="modal fade" id="adduser-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <%--data-dismiss="modal" 关闭对话框--%>
                <button class="close" data-dismiss="modal">&times;</button>
                <h4>{{ editOrAdd }}用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="add-user-form">
                    <input type="hidden" class="form-control" name="id" ng-value="id"/>
                    <div class="row">
                        <div class="form-group col-sm-10">
                            <label class="col-sm-2 control-label" for="username">
                                姓名
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="username" id="username" ng-model="username">
                            </div>
                        </div>
                        <div class="col-sm-10 form-group">
                            <label class="col-sm-2 control-label" for="phone">
                                电话
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="phone" id="phone" ng-model="phone">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-10 form-group">
                            <label class="col-sm-2 control-label" for="email">
                                邮箱
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="email" id="email" ng-model="email">
                            </div>
                        </div>
                        <div class="col-sm-10 form-group">
                            <label class="col-sm-2 control-label" for="password">
                                密码
                            </label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" name="password" id="password" ng-model="password">
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
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" ng-click="addUser()" data-dismiss="modal">{{ editOrAdd }}</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    var app = angular.module('myApp', []);

    app.controller('myCtrl', function($scope,$http,$interval) {
        $scope.loginName = getCookie('loginUserName');;
        renderView($http,$scope)
        $scope.openModal = function(){
            $scope.editOrAdd = "新增";
            $scope.username = "";
            $scope.phone = "";
            $scope.email = "";
            $scope.password = "";
            $scope.id = "";
        }

        $scope.edit = function(data) {
            $scope.editOrAdd = "修改";
            var user = data.user;
            $scope.username = user.username;
            $scope.phone = user.phone;
            $scope.email = user.email;
            $scope.password = user.password;
            $scope.id = user.id;
        };

        $scope.addUser = function () {
            var data  = $("#add-user-form").serializeArray();
            data = arrayToJson(data)
            $http.post('/addOrEditUser', data, null).then(
                    function successCallback(response) {
                        renderView($http,$scope);
                 });
        }

        $scope.delete = function (obj) {
            if(!confirm("确定删除?")){
                return false;
            }
            $http.post('/deleteUser', {id:obj.user.id}, null).then(
                    function successCallback(response) {
                        renderView($http,$scope);
                    });
        }

        $scope.realTime = false;
        $interval(function () {
            if($scope.realTime){
                renderView($http,$scope);
            }
        },1000);
    });

    function arrayToJson(formArray){
        var dataArray = {};
        $.each(formArray,function(){
            if(dataArray[this.name]){
                if(!dataArray[this.name].push){
                    dataArray[this.name] = [dataArray[this.name]];
                }
                dataArray[this.name].push(this.value || '');
            }else{
                dataArray[this.name] = this.value || '';
            }
        });
        return JSON.stringify(dataArray);
    }

    function renderView($http,$scope) {
        $http.get("/getUsers").then(function (response) {
            $scope.userList = response.data;
        });
    }

    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }
</script>
</html>