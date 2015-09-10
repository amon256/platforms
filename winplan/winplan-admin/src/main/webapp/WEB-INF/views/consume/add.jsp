<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/consume/add') }" method="post">
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
				<input type="hidden" name="__TOKEN__" value="${__TOKEN__ }"/>
				<div class="form-group">
					<label for="account" class="col-sm-2 control-label">消费账号：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="account" value="${account }" placeholder="消费用户账号"/>
					</div>
				</div>
				<div class="form-group">
					<label for="amount" class="col-sm-2 control-label">消费金额：</label>
					<div class="col-sm-6">
						<input type="number" class="form-control" name="amount" value="${amount }" placeholder="消费金额"/>
					</div>
				</div>
				<div class="form-group">
					<label for="periodNumber" class="col-sm-2 control-label">全返期数(按月,为0则不返)：</label>
					<div class="col-sm-6">
						<input type="number" class="form-control" id="periodNumber" name="periodNumber" value="${returnPeriod }" placeholder="全返期数(按月,为0则不返)"/>
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 control-label">备注：</label>
					<div class="col-sm-6">
						<textarea class="form-control" rows="3" name="description"></textarea>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">保存</button>
			</form>
		</div>
	</div>