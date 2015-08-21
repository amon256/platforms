<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String port = request.getServerPort()==80?"":":"+request.getServerPort();
String ctx = request.getScheme()+"://"+request.getServerName()+port+request.getContextPath();
request.setAttribute("ctx", ctx);
%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctx }/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/bootstrap/3.3.4/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${ctx }/adminLTE/css/AdminLTE.min.css" rel="stylesheet">
<link href="${ctx }/adminLTE/css/skins/skin-blue.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<script type="text/javascript" src="${ctx }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/adminLTE/js/app.min.js"></script>
<script type="text/javascript">
	var ctx = '${ctx }';
</script>
<title>JK Group Manager Controller</title>