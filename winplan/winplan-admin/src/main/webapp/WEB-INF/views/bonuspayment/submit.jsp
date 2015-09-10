<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/bonuspayment/submit') }" method="post" onsubmit="return beforeSubmit()">
				<input type="hidden" name="user.account" value="${data.user.account }">
				<input type="hidden" name="amount" value="${data.amount }">
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
					<label class="col-sm-2 control-label">账号：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.user.account }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.user.name }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">电话：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.user.mobile }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">金额：</label>
					<div class="col-sm-6">
						<p class="form-control-static"><fmt:formatNumber value="${data.amount }" pattern="0.00"/></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注：</label>
					<div class="col-sm-6">
						<textarea class="form-control" rows="3" name="description">${data.description }</textarea>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" onclick="window.history.go(-1);">取消</button>
			</form>
		</div>
	</div>