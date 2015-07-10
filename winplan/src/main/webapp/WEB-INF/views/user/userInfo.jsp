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
			<li class="active">用户信息</li>
		</ul>
		<form id="editUser" role="form" class="form-horizontal" action="#" method="post">
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
				<label for="name" class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="name" readonly="readonly" value="${user.name }" />
				</div>
			</div>
			<div class="form-group">
				<label for="nickName" class="col-sm-2 control-label">昵称：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="nickName" name="nickName" readonly="readonly" value="${user.nickName }" />
				</div>
			</div>
			<div class="form-group">
				<label for="createTime" class="col-sm-2 control-label">加入时间：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="createTime" readonly="readonly" readonly="readonly" value="<fmt:formatDate value="${user.createTime }" pattern="yyyy年M月d日"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="mobile" class="col-sm-2 control-label">手机号码：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="mobile" name="mobile" readonly="readonly" value="${user.mobile }" />
				</div>
			</div>
			<button type="button" class="btn btn-primary" onclick="window.history.go(-1);">返回</button>
		</form>
	</div>
</body>
</html>