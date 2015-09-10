<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/bonuscash/process') }" method="post">
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
				<input type="hidden" name="id" value="${apply.id }"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-6">
						<span class="form-control-static">${apply.applyer.name }</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">银行卡号：</label>
					<div class="col-sm-6">
						<span class="form-control-static">${apply.applyer.cardNumber }</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开户行：</label>
					<div class="col-sm-6">
						<span class="form-control-static">${apply.applyer.bankName }</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开户行地址：</label>
					<div class="col-sm-6">
						<span class="form-control-static">${apply.applyer.bankAddress }</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">电话：</label>
					<div class="col-sm-6">
						<span class="form-control-static">${apply.applyer.mobile }</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">可提现金额：</label>
					<div class="col-sm-6">
						<span class="form-control-static"><fmt:formatNumber value="${apply.applyer.bonus }" pattern="0.00"></fmt:formatNumber></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">提现金额：</label>
					<div class="col-sm-6">
						<span class="form-control-static"><fmt:formatNumber value="${apply.applyAmount }" pattern="0.00"></fmt:formatNumber></span>
					</div>
				</div>
				<div class="form-group">
					<label for="result" class="col-sm-2 control-label">意见：</label>
					<div class="col-sm-6">
						<label class="radio-inline">
						  <input type="radio" name="result" id="inlineRadio1" value="AGREE" checked="checked">同意
						</label>
						<label class="radio-inline">
						  <input type="radio" name="result" id="inlineRadio1" value="DISAGREE">拒绝
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="applyMsg" class="col-sm-2 control-label">意见：</label>
					<div class="col-sm-6">
						<textarea class="form-control" rows="3" name="applyMsg"></textarea>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">审批</button>
			</form>
		</div>
	</div>