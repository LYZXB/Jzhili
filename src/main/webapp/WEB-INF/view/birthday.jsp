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
			<h1> 生日数据管理</h1>
		</div>
		</section>
		
		<section>
		<div class="layui-form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="name" lay-verify="required"
							placeholder="请输入姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" id="birthday" name="birthday" lay-verify="datetime"
							placeholder="请输入公历生日" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn" lay-submit lay-filter="insertBirthday">添加</button>
<!-- 					<button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
				</div>
			</div>
		</div>
		
		<table class="layui-hide" id="birthdayTable" lay-filter="birthday"></table>
		</section>
	</div>
	
	<div class="layui-footer" style="left:0px; text-align:center;">
	    © justzhili.com
	</div>
</div>


<script type="text/html" id="barDemo">
  	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
	layui.use(['form', 'table', 'element', 'laydate', 'layer', 'laytpl'], function(){
		var form = layui.form;
	  	var element = layui.element;
	  	var table = layui.table;
	  	var laydate = layui.laydate;
	  	var laytpl = layui.laytpl;

	  	var tableIns = table.render({
	    	elem: '#birthdayTable',url:'../birthday/showBirthday',method:'post', //全局定义常规单元格的最小宽度，layui 2.2.1 新增
	    	height:465,
	    	page: true, where: {sort:'lunar_days', order: 'asc'},
	    	cols: [[
		    	{ field:'name', fixed:'left', title:'姓名',edit:"text", width: 120 },
	           	{ field:'age', title:'年龄', sort: true, width: 90, align: "center"},
	           	{ field:'birthday', title:'公历生日',sort: true, event:'check'},
	           	{ field:'lunar_birthday', title:'农历生日', sort: true },
	           	{ field:'next_lunar', title:'下一次生日（农历）', sort: true},
	           	{ field:'lunar_days', title:'还剩天数', sort: true, width: 130 },
	            {fixed: 'right', align:'center', toolbar: '#barDemo'}
	    	]],
	    	done: function(res, curr, count){
	    	   // 编辑编程日历模式
	   		},
	  	});
	  	table.on('tool(birthday)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data //获得当前行数据
	  	    ,layEvent = obj.event; //获得 lay-event 对应的值
	  	    // 删除
	  	    if(layEvent === 'del'){
	  	    	layer.confirm('确定要删除此生日记录吗?', function(index){
		  	      	$.ajax({
						url: '../birthday/delBirthday.do',
						method: 'post',
						data: {
							gcos: data.gco
						},
						success: function(result) {
							layer.msg("删除生日数据成功");
				  	        obj.del(); //删除对应行（tr）的DOM结构
						},
						error: function() {
							layer.msg("调用删除生日数据服务出错");
						}
					});
	  	    	});
	  	    } 
	  	   	// 修改
	  		else if(layEvent === 'edit'){
	  		 	var updateList = [];
	  		 	updateList.push(data);
	  	    	$.ajax({
					url: '../birthday/updateBirthday.do',
					method: 'post',
					data: {
						birthdays: JSON.stringify(updateList)
					},
					success: function(result) {
						console.log(result);
						if (result != null) {
							layer.msg("编辑生日数据成功");
							tableIns.reload({where: {sort:'lunar_days', order: 'asc'}});
						}
					},
					error: function() {
						console.log(obj);
						layer.msg("调用编辑生日数据服务出错");
					}
				});
	  		}
	  	   	// 点击弹出日期选择
	  		else if(layEvent === 'check'){// td click
	  			var index = $(this.parentElement).attr("data-index");
	  			laydate.render({
	  				elem : this.children[0], //指定元素
	  				max : 0,
	  				calendar: true,
	  				type: 'datetime',
	  				format : 'yyyy-MM-dd HH:mm:ss',
	  				value: data.birthday,
	  				done: function(value, date, endDate) {
	  					//  对应单元格的日期缓存更新，否则编辑依然是旧值
	  					table.cache["birthdayTable"][index].birthday = value;
	  				}
	  			});
	  	    }
	  	  });
	  	
	  	//执行一个laydate实例
		laydate.render({
			elem : '#birthday', //指定元素
			max : 0,
			calendar: true,
			type: 'datetime',
			format : 'yyyy-MM-dd HH:mm:ss',
// 			done: function(value, date, endDate){
// 			      if(date.year == 2017 && date.month == 11 && date.date == 30){
// 			        dateIns.hint('一不小心就月底了呢');
// 			      }
// 			    }
		});
	  	
		
		//新增
		form.on('submit(insertBirthday)', function(data) {
			var name = data.field.name,
			birthday = data.field.birthday;
			$.ajax({
				url: '../birthday/insertBirthday.do',
				method: 'post',
				data: {
						name: name, 
						birthday: birthday
					},
				success: function(result) {
					console.log(result);
					if (result != null) {
						layer.msg("添加生日数据"+result.name+"成功");
						tableIns.reload({where: {sort:'id', order: 'desc'}});
					}
				},
				error: function() {
					layer.msg("调用添加生日数据服务出错");
				}
			});
		});
	});
</script>
</body>
</html>