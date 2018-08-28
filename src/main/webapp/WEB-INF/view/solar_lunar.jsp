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
			<h1> 公历农历转换</h1>
		</div>
		</section>
		
		<!-- 农历展示 -->
		<section>
			<form class="layui-form layui-form-pane" action="">
				<div class="layui-form-item" pane>
					<label class="layui-form-label">农历日期</label>
					<div class="layui-input-block">
						<div class="layui-form-mid"><%=request.getAttribute("lunarStr") %></div>						
					</div>
				</div>
			</form>
		</section>
		
		<!-- 农历转公历 -->
		<section>
			<div class="layui-form layui-form-pane">
				<div class="layui-form-item" style="margin-bottom: 0px;" pane>
					<label class="layui-form-label">农历日期</label>
					<div class="layui-input-block">
						<div class="layui-inline">
							<div class="layui-input-inline" style="width: 100px; margin-left: 10px;">
						    	<input type="text" name="year" autocomplete="off" class="layui-input">
						    </div>
							<div class="layui-form-mid">年</div>
						    <div class="layui-input-inline" style="width: 100px; margin-left: 10px;">
						    	<input type="text" name="month" autocomplete="off" class="layui-input">
						    </div>
						 	<div class="layui-form-mid">月</div>
						    <div class="layui-input-inline" style="width: 100px; margin-left: 10px;">
						    	<input type="text" name="day" autocomplete="off" class="layui-input">
						    </div>
							<div class="layui-form-mid">日</div>
						    <div class="layui-input-inline" style="width: 100px; margin-left: 10px;">
						    	<input type="checkbox" name="leapMonthFlag" title="闰月">
						    </div>
						</div>
						<button class="layui-btn" lay-submit lay-filter="lunar2Solar">转换</button>
					</div>
				</div>
				<div class="layui-form-item" pane>
					<label class="layui-form-label">公历日期</label>
					<div class="layui-input-block">
						<div class="layui-form-mid layui-word-aux" id = "solarValue"></div>						
					</div>
				</div>
			</div>
		</section>
		
		<!-- 公历转农历 -->
		<section>
			<div class="layui-form layui-form-pane">
				<div class="layui-form-item" style="margin-bottom: 0px;" pane>
					<label class="layui-form-label">公历日期</label>
					<div class="layui-input-block">
						<div class="layui-inline">
							<div class="layui-input-inline" style="width: 100px; margin-left: 10px;">
							  	<input type="text" name="year" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid">年</div>
							<div class="layui-input-inline" style="width: 100px; margin-left: 10px;">
								<input type="text" name="month" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid">月</div>
							<div class="layui-input-inline" style="width: 100px; margin-left: 10px;">
								<input type="text" name="day" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid">日</div>
						</div>
						<button class="layui-btn" lay-submit lay-filter="solar2Lunar" onclick="">转换</button>
					</div>
				</div>
				<div class="layui-form-item" pane>
					<label class="layui-form-label">农历日期</label>
					<div class="layui-input-block">
						<div class="layui-form-mid layui-word-aux" id="lunarValue"></div>						
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
		form.on('submit(solar2Lunar)', function(data) {
			$.ajax({
				url: '../dateConvert/solar2Lunar.do',
				method: 'post',
				data: {
					year: data.field.year,
					month: data.field.month,
					day: data.field.day,
					leapMonthFlag: data.field.leapMonthFlag
				},
				success: function(result) {
			  		$("#lunarValue").html(result);
				},
				error: function() {
					layer.msg("调用转换服务失败");
				}
			});
		});
		form.on('submit(lunar2Solar)', function(data) {
			console.log("lunar2Solar");
			$.ajax({
				url: '../dateConvert/lunar2Solar.do',
				method: 'post',
				data: {
					year: data.field.year,
					month: data.field.month,
					day: data.field.day,
					leapMonthFlag: data.field.leapMonthFlag
				},
				success: function(result) {
			  		$("#solarValue").html(result);
				},
				error: function() {
					layer.msg("调用转换服务失败");
				}
			});
		});
	});
</script>
</body>
</html>