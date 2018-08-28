<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>简治理</title>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="../js/layui/css/layui.css" media="all">
<script src="../js/layui/layui.js" charset="utf-8"></script>

<style type="text/css">
.container {
	margin: 0 auto;
	padding: 0 30px;
}
section {
	padding: 30px 0 0 0;
}
section h1 {
	font-weight: 700;
/* 	margin-bottom: 5px; */
}
section.color {
	background-color: #3cb5f9;
	color: white;
}


.task-list-item {
	list-style-type:none;
}
.task-list-item-checkbox {
	display:inline-block;
	margin-left:-20px;
	margin-right:3px;
	vertical-align:1px;
}
</style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

	<div class="layui-header">
		<%@ include file="../element.jsp" %>
	</div>
	
	
	<div class="layui-body" style="width:70%;">
	 	<section>
		<div class="container">
			<h1>用户信息</h1>
		</div>
		</section>
		
		<section>
			<div class="layui-form" lay-filter="userForm">
				<div class="layui-form-item">
					<label class="layui-form-label">昵称</label>
					<div class="layui-inline">
						<input type="text" name="nickName" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机号</label>
					<div class="layui-inline">
						<input type="text" name="phone" lay-verify="required|phone|number" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-inline">
						<div class="layui-form-mid layui-word-aux">接收推送生日消息的手机号</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-inline">
						<input type="text" name="email"  autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">微信号</label>
					<div class="layui-inline">
						<input type="text" name="wechatcode" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">qq号</label>
					<div class="layui-inline">
						<input type="text" name="qqcode" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<button class="layui-btn" lay-submit lay-filter="save">保存</button>
					</div>
				</div>
			</div>
		</section>
	</div>
	
	<div class="layui-footer" style="left:0px; text-align:center;">
	    © justzhili.com
	</div>
</div>


<script>
layui.use(['form','element'], function(){
	var form = layui.form;
	// 初始化表单
	
	// 保存
	form.on('submit(save)', function(data) {
		console.log(data.field);
		$.ajax({
			url: '../user/saveUserInfo.do',
			method: 'post',
			data: data.field,
			success: function(result) {
				if (result.success) {
					layer.msg("保存出错");
				}
		  		layer.msg("保存成功");
			},
			error: function() {
				layer.msg("调用服务失败");
			}
		});
	});
	
	form.val("userForm", <%= request.getAttribute("user")%>);
});
</script>
</body>
</html>