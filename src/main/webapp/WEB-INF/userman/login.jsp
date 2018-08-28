<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="../include.jsp" %>
<title>简治理登录</title>
<style type="text/css">
	body {
    padding-top: 90px;
}
.panel-login {
	border-color: #ccc;
	-webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	-moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
}
.panel-login>.panel-heading {
	color: #00415d;
	background-color: #fff;
	border-color: #fff;
	text-align:center;
}
.panel-login>.panel-heading a{
	text-decoration: none;
	color: #666;
	font-weight: bold;
	font-size: 15px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login>.panel-heading a.active{
	color: #029f5b;
	font-size: 18px;
}
.panel-login>.panel-heading hr{
	margin-top: 10px;
	margin-bottom: 0px;
	clear: both;
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
}
.panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"] {
	height: 45px;
	border: 1px solid #ddd;
	font-size: 16px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login input:hover,
.panel-login input:focus {
	outline:none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	border-color: #ccc;
}
.btn-login {
	background-color: #59B2E0;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #59B2E6;
}
.btn-login:hover,
.btn-login:focus {
	color: #fff;
	background-color: #53A3CD;
	border-color: #53A3CD;
}
.forgot-password {
	text-decoration: underline;
	color: #888;
}
.forgot-password:hover,
.forgot-password:focus {
	text-decoration: underline;
	color: #666;
}

.btn-register {
	background-color: #1CB94E;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #1CB94A;
}
.btn-register:hover,
.btn-register:focus {
	color: #fff;
	background-color: #1CA347;
	border-color: #1CA347;
}

</style>
</head>
<body ng-app="login">
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								
								<!-- Login begin  -->
								<form ng-controller="login" id="login-form" style="display: block;">
									<div class="form-group">
										<input type="text" ng-model="userName" tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" ng-model="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group text-center">
										<input type="checkbox" ng-model="rememberme" tabindex="3" class="">
										<label for="remember"> Remember Me</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" ng-click="login()"  id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
<!-- 													<a href="https://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a> -->
												</div>
											</div>
										</div>
									</div>
								</form>
								<!-- Login end -->
								
								<!-- 	Register  begin	 -->
								<form ng-controller="register" id="register-form" style="display: none;">
									<div class="form-group">
										<input type="text" ng-model="userName"tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<!-- <div class="form-group">
										<input type="email"  tabindex="1" class="form-control" placeholder="Email Address" value="">
									</div> -->
									<div class="form-group">
										<input type="password" ng-model="password"" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<input type="password" ng-model="passwordConfirm" tabindex="2" class="form-control" placeholder="Confirm Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" ng-click="register()" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form>
								<!-- Register end -->
								
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<script>
	
		$(function() {
		    $('#login-form-link').click(function(e) {
				$("#login-form").delay(100).fadeIn(100);
		 		$("#register-form").fadeOut(100);
				$('#register-form-link').removeClass('active');
				$(this).addClass('active');
				e.preventDefault();
			});
			$('#register-form-link').click(function(e) {
				$("#register-form").delay(100).fadeIn(100);
		 		$("#login-form").fadeOut(100);
				$('#login-form-link').removeClass('active');
				$(this).addClass('active');
				e.preventDefault();
			});
			// 提醒Cookie被盗
			var sheftError = "<%=request.getParameter("sheftError")%>";
			if (sheftError) {
// 				alert("Cookie值不一致，账号有被盗取的危险!");
			}
		});

	
		var app = angular.module('login', []);
		
		app.controller('login', function($scope, $http) {
			$scope.login = function() {
				$http.post("<%=path%>/login",{
					userName : $scope.userName,
					password : $scope.password,
					rememberme : $scope.rememberme
				}).then(function(result){
					if (result.data.success) {
						window.location.href = "<%=path%>/birthday/index";
					} else {
						alert("登录失败：" + result.data.msg);
					}
				});
			}
			
		});
		
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