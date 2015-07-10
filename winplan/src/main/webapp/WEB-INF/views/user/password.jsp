<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<li class="active">我的资料</li>
			<li class="active">修改登录密码</li>
		</ul>
		<form id="editUser" role="form" class="form-horizontal" action="${ctx }/user/changepwd" method="post">
			<c:if test="${not empty msg }">
				<div class="alert alert-warning alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  	${msg }
				</div>
			</c:if>
			<c:if test="${not empty succ }">
				<div class="alert alert-success alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  	${succ }
				</div>
			</c:if>
			<div class="form-group">
				<label for="account" class="col-sm-2 control-label">用户账号：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="account" readonly="readonly" value="${user.account }" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">原密码：</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="password" name="pwd" value="" />
				</div>
			</div>
			<div class="form-group">
				<label for="npwd" class="col-sm-2 control-label">新密码：</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="npwd" name="npwd" value="" />
				</div>
			</div>
			<div class="form-group">
				<label for="npwd1" class="col-sm-2 control-label">新密码：</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="npwd1" name="npwd1" value="" />
				</div>
			</div>
			<button type="submit" class="btn btn-primary">修改登录密码</button>
		</form>
	</div>
</body>
</html>