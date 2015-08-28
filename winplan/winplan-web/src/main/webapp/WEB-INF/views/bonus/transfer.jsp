<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<form id="editUser" role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/bonus/transfer') }" method="post">
			<c:if test="${not empty msg }">
				<div class="alert alert-warning alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  	${msg }
				</div>
			</c:if>
			<c:if test="${not empty succ }">
				<div class="alert alert-success alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  	${succ }<a href="${ctx }/${cus:url(activeMenu.id,'/bonus/transferhistory') }">查看记录</a>
				</div>
			</c:if>
			<div class="form-group">
				<label for="account" class="col-sm-2 control-label">转出账号：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="account"  name="account" value="${account }" />
				</div>
			</div>
			<div class="form-group">
				<label for="bonus" class="col-sm-2 control-label">转出金额：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="bonus" name="bonus" value="${bonus }" />
				</div>
				<span>剩余可用奖金:${user.bonus }</span>
			</div>
			<div class="form-group">
				<label for="desc" class="col-sm-2 control-label">备注：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="desc" name="desc" value="${desc }" />
				</div>
			</div>
			<button type="submit" class="btn btn-primary">确定转出</button>
		</form>
	</div>