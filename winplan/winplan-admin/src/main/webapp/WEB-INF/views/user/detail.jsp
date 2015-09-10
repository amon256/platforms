<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" >
				<div class="form-group">
					<label class="col-sm-2 control-label">账号：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.account }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.name }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">昵称：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.nickName }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.mobile }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">可用奖金：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.bonus }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">银行卡号：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.cardNumber }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开户行：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.bankName }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开户行地址：</label>
					<div class="col-sm-6">
						<p class="form-control-static">${data.bankAddress }</p>
					</div>
				</div>
				<button type="submit" class="btn btn-primary" onclick="window.history.go(-1);">返回</button>
			</form>
		</div>
	</div>