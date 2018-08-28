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
			<h1>推送设置</h1>
		</div>
		</section>
		
		<section>
			<div class="layui-form" lay-filter="pushSetForm">
				<div class="layui-form-item">
					<label class="layui-form-label">自动推送</label>
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="checkbox" name="stat" lay-skin="switch">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">推送方式</label>
					<div class="layui-inline">
						<div class="layui-input-inline" style="margin-right: 0px;">
							<input type="checkbox" name="pushType" title="短信">
							<input type="checkbox" name="like[email]" title="邮件" disabled>
						</div>
						<div class="layui-input-inline">
							<input type="checkbox" name="like[qq]" title="QQ" disabled>
							<input type="checkbox" name="like[wechat]" title="微信" disabled>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">提前推送时间</label>
					<div class="layui-inline">
						<input type="text" name="forwardDays" lay-verify="required" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-inline">
						<div class="layui-form-mid layui-word-aux">提前推送时间和生日当天上午8:00，会推送通知给用户</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<button class="layui-btn" lay-submit lay-filter="save">保存</button>
					</div>
				</div>
			</div>
			<input type="hidden" id="gco" name="gco" value=<%=request.getAttribute("gco") == null ? "" : request.getAttribute("gco") %>>
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
		var sms = data.field.pushType;
		var stat = data.field.stat;
		var gco = $("#gco").val();
		$.ajax({
			url: '../user/savePushSet.do',
			method: 'post',
			data: {
				forwardDays: data.field.forwardDays,
				pushType: sms != null ? 1 : 0, // 目前只有短信推送类型
				stat: stat != null ? 1 : 0,
				gco: gco
			},
			success: function(result) {
				if (result.success) {
					layer.msg("保存出错");
				}
		  		layer.msg("保存成功");
		  		$("#gco").val(result.msg);
			},
			error: function() {
				layer.msg("调用服务失败");
			}
		});
	});
	
	form.val("pushSetForm", <%= request.getAttribute("pushSet")%>);
});
</script>
</body>
</html>