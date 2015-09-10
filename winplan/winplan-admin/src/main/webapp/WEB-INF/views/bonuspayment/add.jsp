<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/bonuspayment/add') }" method="post" onsubmit="return beforeSubmit()">
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
					<label for="user.account" class="col-sm-2 control-label">账号：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="user.account" required="required" value="${data.user.account }" placeholder="账号"/>
					</div>
				</div>
				<div class="form-group">
					<label for="amount" class="col-sm-2 control-label">金额：</label>
					<div class="col-sm-6">
						<input type="number" class="form-control" name="amount" required="required" min="0" value="<fmt:formatNumber value="${data.amount }" pattern="0.00"/>" placeholder="发放金额"/>
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 control-label">备注：</label>
					<div class="col-sm-6">
						<textarea class="form-control" rows="3" name="description">${data.description }</textarea>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">保存</button>
			</form>
		</div>
	</div>