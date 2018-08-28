<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<html ng-app="birthday">
<head>
<%@ include file="../include.jsp" %>
<!-- 选择日期控件 -->
<script src="<%=path%>/js/laydate/laydate.js" type="text/javascript"></script>
<script src="<%=path%>/js/angular/ui-grid.min.js" type="text/javascript"></script>
<link href="<%=path%>/js/angular/ui-grid.min.css" rel="stylesheet" type="text/css" />

<title>简治理</title>
<style type="text/css">
.container {
	width: 80%;
	margin: 0 auto;
	padding: 0 30px;
}
section {
	padding: 60px 0 0 0;
}
section h1 {
	font-weight: 700;
	margin-bottom: 5px;
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
<body  ng-controller="Main">
	<!-- TOP 导航栏 -->
	<nav class="navbar navbar-inverse navbar-fixed-top" >
		<div class="container-fluid">
			<!-- 导航栏 -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">
					<img style="max-width: 20px; height: auto;" alt="简治理" src="<%=path%>/img/jicon.png">
				</a>
				<p class="navbar-text">JustZhiLi</p>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="#">生日数据管理</a></li>
					<li><a href="#">知识库管理</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">社区</a></li>
				</ul>

			</div>
		</div>
	</nav>
	<!-- TOP end -->
	 <section>
            <div class="container">
                <h1> 生日管理</h1>
                <p>
	                <ul>
	                <li class="task-list-item"><input type="checkbox" class="task-list-item-checkbox" checked disabled> 管理数据</li>
	                <li class="task-list-item"><input type="checkbox" class="task-list-item-checkbox"  disabled> 自动通知</li>
	                <li class="task-list-item"><input type="checkbox" class="task-list-item-checkbox"  disabled> 数据加密</li>
	                </ul>
                </p>
            </div>
        </section>	


	<section>
		<div class="container">
			<div class="row justify-content-start" style="padding-bottom: 20px;">
				
			</div>
			<div class="row justify-content-start" style="padding-bottom: 10px;">
				<div class="col-6 col-sm-3">
					<input class="form-control small" ng-model="insertName" placeholder="请输入姓名" />
				</div>
				<div class="col-6 col-sm-3">
					<input class="form-control layui-input" id="birthdaySelect"
						ng-model="insertBirthday" placeholder="请选择公历生日" />
				</div>
				<div class="col-6 col-sm-3">
					<button type="button" id="editData" class="btn btn-primary"
						ng-click="insert()">新增数据</button>
					<button type="button" id="addData" class="btn btn-warning"
						ng-click="update()">保存改动</button>
					<button type="button" id="delData" class="btn btn-danger"
						ng-click="del()">删除数据</button>
				</div>
			</div>
			<!-- ui-grid-edit 编辑  ui-gird-pagination 分页 ui-grid-selection 选择行 
			ui-grid-exporter 导出 ui-grid-resize-columns 拉伸行 ui-grid-auto-resize 自适应   -->
			<div ui-grid="birthdayGrid" class="grid" ui-grid-edit ui-grid-cellNav  ui-grid-pagination 
				ui-grid-selection ui-grid-exporter ui-grid-resize-columns ui-grid-auto-resize ui-grid-grouping></div>
		
		</div>
	</section>
	
	<section>
	 <div style="background-color: #101010;" class="container text-center">
		   <!--  <hr />
		  <div class="row">
		    <div class="col-lg-12">
		      <div class="col-md-3">
		        <ul class="nav nav-pills nav-stacked">
		          <li><a href="#">About us</a></li>
		          <li><a href="#">Blog</a></li>
		        </ul>
		      </div>
		      <div class="col-md-3">
		        <ul class="nav nav-pills nav-stacked">
		          <li><a href="#">Product for Mac</a></li>
		          <li><a href="#">Product for Windows</a></li>
		        </ul>
		      </div>
		      <div class="col-md-3">
		        <ul class="nav nav-pills nav-stacked">
		          <li><a href="#">Web analytics</a></li>
		          <li><a href="#">Presentations</a></li>          
		        </ul>
		      </div>
		      <div class="col-md-3">
		        <ul class="nav nav-pills nav-stacked">
		          <li><a href="#">Product Help</a></li>
		          <li><a href="#">Developer API</a></li>
		        </ul>
		      </div>  
		    </div>
		  </div> -->
		  <hr>
		    <div class="row">
		        <div class="col-lg-12">
		            <ul class="nav nav-pills nav-justified">
		                <li><a href="/">© 2013 Company Name.</a></li>
		                <li><a href="#">Terms of Service</a></li>
		                <li><a href="#">Privacy</a></li>
		            </ul>
		        </div>
		    </div>
		</div>
	</section>
	
	<script>
		// 
	    var app = angular.module('birthday', [ 'ui.grid', 'ui.grid.edit', 'ui.grid.cellNav', 'ui.grid.pagination', 
	    	'ui.grid.selection', 'ui.grid.exporter', 'ui.grid.resizeColumns', 'ui.grid.autoResize', 'ui.grid.grouping']);
		
		// controller 中注入i18nService
	    app.controller('Main', ['$scope', 'i18nService', '$http', function($scope, i18nService, $http) {
	    	
	    	// 国际化：中文
	    	i18nService.setCurrentLang("zh-cn");
	    	
	    	// 分页参数
	    	var paginationOptions = {
	    		pageNumber: 1,
	    		pageSize: 25,
	    		sort: null,
	    		order: null
	    	};
	    	
	    	// ui-grid 配置
	        $scope.birthdayGrid = {
	    		enableFiltering: true, // 筛选
				enableGridMenu: true, // 菜单
				//enableColumnMenus: true,
				
				//----------- 选中 ----------------------
	     		showGridFooter: true, // 页脚
                enableFooterTotalSelected: true, // 是否显示选中的总数，默认为true, 如果显示，showGridFooter 必须为true
               	
                //----------- 分页 ----------------------
                paginationPageSizes: [25, 50, 75],
			    paginationPageSize: 25,
	    	    
			    // ----- 导出--------
	    	    exporterCsvFilename: 'myFile.csv',
	    	    exporterPdfDefaultStyle: {fontSize: 9},
	    	    exporterPdfTableStyle: {margin: [30, 30, 30, 30]},
	    	    exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},
	    	    exporterPdfHeader: { text: "My Header", style: 'headerStyle' },
	    	    exporterPdfFooter: function ( currentPage, pageCount ) {
	    	      return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
	    	    },
	    	    exporterPdfCustomFormatter: function ( docDefinition ) {
	    	      docDefinition.styles.headerStyle = { fontSize: 22, bold: true };
	    	      docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
	    	      return docDefinition;
	    	    },
	    	    exporterPdfOrientation: 'portrait',
	    	    exporterPdfPageSize: 'LETTER',
	    	    exporterPdfMaxGridWidth: 500,
	    	    exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
	        	
	    	    // 字段设置
	    	    columnDefs : [
	               	{ field:'name', displayName:'姓名' },
	               	{ field:'age', displayName:'年龄', enableCellEdit: false},
	               	{ field:'birthday', displayName:'公历生日'},// date number string boolean
	               	{ field:'lunar_birthday', displayName:'农历生日', enableCellEdit: false },
	               	{ field:'next_lunar', displayName:'下一次农历生日', enableCellEdit: false },
	               	{ field:'lunar_days', displayName:'还剩天数', enableCellEdit: false },
	               /* 	{name: 'type', editableCellTemplate: 'ui-grid/dropdownEditor', cellFilter: 'mapType',
	               		enableCellEditOnFocus: true,
	               		editDropdownOptionsArray:  [{id: '1', value: '农历'},{id: '0', value: '公历'}],
	                    editDropdownIdLabel: 'id', editDropdownValueLabel: 'value' }, */
	        	],
	        	
	        	// 注册配置
	        	onRegisterApi: function( gridApi ) {
	        		$scope.birthdayGridApi = gridApi;
	        		
	        		// 排序事件
	        		$scope.birthdayGridApi.core.on.sortChanged($scope, function(grid, sortColumns) {
	        	        if (sortColumns.length == 0) {
	        	          paginationOptions.sort = null;
	        	          paginationOptions.order = null;
	        	        } else {
	        	          paginationOptions.order = sortColumns[0].sort.direction;
	        	          paginationOptions.sort = sortColumns[0].name;
	        	        }
	        	        getPage();
	        	    });
	        		
	        		// 分页事件
	        		$scope.birthdayGridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	        	        paginationOptions.pageNumber = newPage;
	        	        paginationOptions.pageSize = pageSize;
	        	        getPage();
	        	    });
	        		
	        		// 编辑事件
        	        $scope.updateMap = new Map();
	        		$scope.birthdayGridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
	        			$scope.updateMap.set(rowEntity.gco, rowEntity);
	        	    });
	        	}
	        };
	
	        var projectPath = "<%=path%>/";
	        
	        // 分页查询
	    	var getPage = function() {
		        $http.post(projectPath + "birthday/showBirthday.do", {
		        	page: paginationOptions.pageNumber,
		        	rows: paginationOptions.pageSize,
		        	sort: paginationOptions.sort,
		        	order: paginationOptions.order
		        })
		        .then(function(data) {
		            $scope.birthdayGrid.data = data.data;
		        });
	    	}
	    	getPage();
	    	
	    	/* 新增行,日期选择控件初始化 */
			laydate.render({
				elem : '#birthdaySelect', //指定元素
				max : 0,
				//theme:'grid', 风格
				type: 'datetime',
				format : 'yyyy-MM-dd HH:mm:ss',
				done: function(value, date, endDate){
				   	$scope.insertBirthday = value;
				 },
				trigger: 'click'
			});
	    	
			// 新增
			$scope.insert = function(index) {
				var insertName = $scope.insertName;
				var insertBirthday = $scope.insertBirthday;
				if (insertName == null || insertName.length < 1 || insertBirthday == null || insertBirthday.length < 1) {
					console.log("请输入完整生日数据");
					return;
				}
				$http.post(projectPath+"birthday/insertBirthday.do", {
					name : insertName,
					birthday : insertBirthday,
				}).then(function(result) {
					$scope.insertName = "";
					$scope.insertBirthday = "";
					// 返回增加的数据信息
					$scope.birthdayGrid.data.push(result.data);
				}); 
			}
			
			// 修改，暂时保存在updateList中，点击保存，才保存到数据库
			var updateList = [];
			$scope.update = function(index) {
				if (!confirm("是否要保存改动过的数据?"))
					return;
				$scope.updateMap.forEach(function (value, key, map) {
					updateList.push(value);
				});
				var updateListStr = JSON.stringify(updateList);
				$.post(projectPath+"birthday/updateBirthday.do",{
					birthdays: updateListStr
				}).then(function(result) {
					if (result.data == 1) {
						console.log("修改成功!");
						getPage();
					}
				});
			}
			
			// 删除  TODO 日志、备忘录（实现恢复数据功能）
			$scope.del = function(index) {
				if (!confirm("是否要删除选中数据?"))
					return;
				var rows = $scope.birthdayGridApi.selection.getSelectedRows();
				var gcos = "";
				for (var i = 0; i < rows.length; i++) {
					gcos += rows[i].gco + ',';
				}				
				$.post(projectPath+"birthday/delBirthday.do",{
					gcos : gcos.substring(0,gcos.length-1)
					})
					.then(function(result) {
						if (result.data == rows.length) {
							angular.forEach(rows, function (data, index) {
								$scope.birthdayGrid.data.splice($scope.birthdayGrid.data.lastIndexOf(data), 1);
							 });
						} else {
							console.log("删除失败");
						}
				});
			}
			
	    }])
	    // 实现对应
	    .filter('mapType', function() {
			var typeHash = { 0 : '公历', 1 : '农历' };
			return function(input) {
				if (!input) {
					return '';
				} else {
					return typeHash[input];
				}
			};
		});;
	</script>
	
	<!-- MAIN end -->

	<!-- FOOT 广告 -->
	
	<!-- FOOT end -->
</body>
</html>