<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../common/common.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="../common/head.jsp" flush="true"></jsp:include>
		<ul class="breadcrumb">
			<li><a href="${ctx }/index">主页面</a></li>
		</ul>
		<ul class="breadcrumb">
			<li><a href="${ctx }/main">总汇页面</a></li>
		</ul>
		<ul>
			<li><a href="#">会员注册</a></li>
			<li>我的资料</li>
			<li>
				<ul>
					<li><a href="${ctx }/user/useredit">个人资料</a></li>
					<li><a href="${ctx }/user/password">修改登录密码</a></li>
				</ul>
			</li>
			<li>财务总汇</li>
			<li>
				<ul>
					<li><a href="#">奖金记录</a></li>
					<li><a href="#">累计奖金记录</a></li>
					<li><a href="${ctx }/bonus/totransfer">奖金转让</a></li>
					<li><a href="${ctx }/bonus/transferhistory">奖金转让记录</a></li>
				</ul>
			</li>
			<li>团队系统</li>
			<li>
				<ul>
					<li><a href="${ctx }/user/usertree">介绍系统</a></li>
					<li><a href="#">安置系统</a></li>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>