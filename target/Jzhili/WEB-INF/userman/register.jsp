<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="../include.jsp" %>
<title>简治理注册</title>
<style type="text/css">
.main{
    text-align: left; /*让div内部文字居中*/
    background-color: #FFFACD;
    border-radius: 20px;
    width: 30%;
    height: 50%;
    margin: auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
}
</style>
</head>
<body ng-app="register">
	<div class="main">
		<ul class="nav nav-tabs">
		  <li role="presentation"><a href="<%=path%>/user/toLogin.do">登录</a></li>
		  <li role="presentation"  class="active"><a href="<%=path%>/user/toRegister.do">注册</a></li>
		</ul>
		<form ng-controller="register">
			<div class="form-group"></div>
			<div class="form-group">
				<input class="form-control" id="userName" ng-model="userName" placeholder="请输入用户名">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" id="password" ng-model="password" placeholder="密码">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" id="passwordConfirm" ng-model="passwordConfirm" placeholder="再次输入密码">
			</div>
			<button type="submit" class="btn btn-default" ng-click="register()">注册</button>
		</form>
	</div>
	<script>
	
		var app = angular.module('register', []);
		
		app.controller('register', function($scope, $http) {
			$scope.register = function() {
				if ($scope.password != $scope.passwordConfirm) {
					alert("两次密码输入不一致!");
					$scope.password = "";
					$scope.passwordConfirm = "";
					return;
				}
				$http.post("<%=path%>/user/register",{
					userName : $scope.userName,
					password : $scope.password
				}).then(function(result){
					if (result.data.success) {
						window.location.href = "<%=path%>/birthday/index.do";
					} else {
						alert("注册失败：" + result.data.msg);
					}
				});
			}
		});
		
	</script>
</body>
</html>