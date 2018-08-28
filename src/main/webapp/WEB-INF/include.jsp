<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
<!-- angularjs -->
<script type="text/javascript" src="<%=path%>/js/angular/angular.min.js"></script>
<!-- bootstrap -->
<script src="<%=path%>/js/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
<link href="<%=path%>/js/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css" />