<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/bonuscash/apply') }" method="post">
				<c:if test="${not empty msg }">
					<div class="alert alert-warning alert-dismissible" role="alert">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  	${msg }
					</div>
				</c:if>
				<div class="form-group">
					<label for="account" class="col-sm-2 control-label">可提现余额：</label>
					<div class="col-sm-6">
						<span class="form-control-static"><fmt:formatNumber value="${user.bonus }" pattern="0.00"></fmt:formatNumber></span>
					</div>
				</div>
				<div class="form-group">
					<label for="applyAmount" class="col-sm-2 control-label">提现金额：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="applyAmount" name="applyAmount" value="" />
					</div>
				</div>
				<button type="submit" class="btn btn-primary">申请</button>
			</form>
		<div class="editpanel">
	</div>
