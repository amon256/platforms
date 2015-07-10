<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="common/common.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="common/head.jsp" flush="true"></jsp:include>
		<ul class="breadcrumb">
			<li><a href="${ctx }/index">主页面</a></li>
		</ul>
		<ul class="breadcrumb">
			<li><a href="${ctx }/main">总汇页面</a></li>
		</ul>
		<div class="list-group">
			<div class="list-group-item">奖金：￥${user.bonus}元</div>
			<div class="list-group-item"><a href="#">奖金记录&gt;</a></div>
		</div>
		<div class="list-group">
			<div class="list-group-item">累计奖金：￥${user.totalBonus }元</div>
			<div class="list-group-item"><a href="#">奖金汇总记录&gt;</a></div>
		</div>
	</div>
</body>
</html>