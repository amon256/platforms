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
			<li class="active">会员注册</li>
		</ul>
		<form role="form" class="form-horizontal" action="${ctx }/user/register" method="post">
			<input type="hidden" name="__TOKEN__" value="${__TOKEN__ }"/>
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
				<label for="recommend" class="col-sm-2 control-label">推荐人：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="recommend" name="recommend"  value="${user.recommend }" />
				</div>
			</div>
			<div class="form-group">
				<label for="parent" class="col-sm-2 control-label">接点人：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="parent" name="parentAccount"  value="${parentUser.account }" />
				</div>
			</div>
			<div class="form-group">
				<label for="dir" class="col-sm-2 control-label src-only">接点人：</label>
				<div class="col-sm-6">
					<label class="radio-inline"><input type="radio" name="dir" value="left"  <c:if test="${dir eq 'left' }">checked="checked"</c:if>/>左边</label>
					<label class="radio-inline"><input type="radio"  id="dir" name="dir" value="right"  <c:if test="${dir eq 'right' }">checked="checked"</c:if>/>右边</label>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="name" name="name"  value="${user.name }" />
				</div>
			</div>
			<div class="form-group">
				<label for="nickName" class="col-sm-2 control-label">昵称：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="nickName" name="nickName" value="${user.nickName }" />
				</div>
			</div>
			<div class="form-group">
				<label for="mobile" class="col-sm-2 control-label">手机号码：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="mobile" name="mobile" value="${user.mobile }" />
				</div>
			</div>
			<button type="submit" class="btn btn-primary">确定注册</button>&nbsp;<button type="button" class="btn btn-info" onclick="window.history.go(-1);">返回</button>
		</form>
	</div>
</body>
</html>