<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form id="editUser" role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/user/changepwd')}" method="post">
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
	</div>