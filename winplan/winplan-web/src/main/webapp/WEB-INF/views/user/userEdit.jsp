<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form id="editUser" role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/user/userSave')}" method="post">
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
						<input type="text" class="form-control" id="nickName" name="nickName" value="${user.nickName }" />
					</div>
				</div>
				<div class="form-group">
					<label for="createTime" class="col-sm-2 control-label">加入时间：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="createTime" readonly="readonly" value="<fmt:formatDate value="${user.createTime }" pattern="yyyy年M月d日"/>" />
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-sm-2 control-label">手机号码：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="mobile" name="mobile" value="${user.mobile }" />
					</div>
				</div>
				<div class="form-group">
					<label for="cardNumber" class="col-sm-2 control-label">银行卡号：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="cardNumber" name="cardNumber" value="${user.cardNumber }" />
					</div>
				</div>
				<div class="form-group">
					<label for="bankName" class="col-sm-2 control-label">开户行：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="bankName" name="bankName" value="${user.bankName }" />
					</div>
				</div>
				<div class="form-group">
					<label for="bankAddress" class="col-sm-2 control-label">开户行地址：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="bankAddress" name="bankAddress" value="${user.bankAddress }" />
					</div>
				</div>
				<div class="form-group">
					<label for="roles" class="col-sm-2 control-label">头像：</label>
					<div class="col-sm-6">
						<label class="radio-inline">
						  <input type="radio" name="headPhoto"  <c:if test="${user.headPhoto == '/adminLTE/img/avatar.png'}">checked="checked"</c:if> value="/adminLTE/img/avatar.png">
						  <img src="${ctx}/adminLTE/img/avatar.png" class="img-circle" style="width: 40px;height:40px;" alt="User Image" />
						</label>
						<label class="radio-inline">
						  <input type="radio" name="headPhoto"  <c:if test="${user.headPhoto == '/adminLTE/img/avatar2.png'}">checked="checked"</c:if> value="/adminLTE/img/avatar2.png">
						  <img src="${ctx}/adminLTE/img/avatar2.png" class="img-circle" style="width: 40px;height:40px;" alt="User Image" />
						</label>
						<label class="radio-inline">
						  <input type="radio" name="headPhoto"  <c:if test="${user.headPhoto == '/adminLTE/img/avatar3.png'}">checked="checked"</c:if> value="/adminLTE/img/avatar3.png">
						  <img src="${ctx}/adminLTE/img/avatar3.png" class="img-circle" style="width: 40px;height:40px;" alt="User Image" />
						</label>
						<label class="radio-inline">
						  <input type="radio" name="headPhoto"  <c:if test="${user.headPhoto == '/adminLTE/img/avatar04.png'}">checked="checked"</c:if> value="/adminLTE/img/avatar04.png">
						  <img src="${ctx}/adminLTE/img/avatar04.png" class="img-circle" style="width: 40px;height:40px;" alt="User Image" />
						</label>
						<label class="radio-inline">
						  <input type="radio" name="headPhoto"  <c:if test="${user.headPhoto == '/adminLTE/img/avatar5.png'}">checked="checked"</c:if> value="/adminLTE/img/avatar5.png">
						  <img src="${ctx}/adminLTE/img/avatar5.png" class="img-circle" style="width: 40px;height:40px;" alt="User Image" />
						</label>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">修改个人资料</button>&nbsp;&nbsp;<a href="${ctx }/${cus:url(null,'/user/password') }">修改密码</a>
			</form>
		<div class="editpanel">
	</div>
