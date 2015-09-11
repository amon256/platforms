<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String port = request.getServerPort()==80?"":":"+request.getServerPort();
String ctx = request.getScheme()+"://"+request.getServerName()+port+request.getContextPath();
request.setAttribute("ctx", ctx);
%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${ctx }/favicon.ico">
<link href="${ctx }/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/bootstrap/3.3.4/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${ctx }/adminLTE/css/AdminLTE.min.css" rel="stylesheet">
<link href="${ctx }/adminLTE/css/skins/skin-blue.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="${ctx }/adminLTE/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${ctx }/adminLTE/css/ionicons.min.css">
<script type="text/javascript" src="${ctx }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/adminLTE/js/app.min.js"></script>
<script type="text/javascript">
	var ctx = '${ctx }';
</script>
<style>
	.editpanel{
		margin-right: 0;
		margin-left: 0;
		background-color: #fff;
		border-color: #ddd;
		border-width: 1px;
		border-radius: 4px;
		position: relative;
		padding: 15px 15px 15px;
		margin: 0 -15px 15px;
		border-style: solid;
	}
	.querypanel{
		margin-right: 0;
		margin-left: 0;
		background-color: #fff;
		border-color: #ddd;
		border-width: 1px;
		border-radius: 4px 4px 0 0;
		position: relative;
		padding: 15px 15px 15px;
		margin: 0 -15px 15px;
		border-style: solid;
	}
	.contentpanel{
		margin-top: -16px;
		margin-right: 0;
		margin-left: 0;
		border-width: 1px;
		border-bottom-right-radius: 4px;
		border-bottom-left-radius: 4px;
		margin: -15px -15px 15px;
		border-radius: 0;
		padding: 9px 14px;
		background-color: #fff;
		border: 1px solid #e1e1e8;
	}
</style>
<title>JK Group</title>