<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>

	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/adminuser/password') }" method="post">
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
					<label for="oldPassword" class="col-sm-2 control-label">旧密码：</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="oldPassword" name="oldPassword" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">新密码：</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="password" name="password" value="" />
					</div>
				</div>
				<div class="form-group">
					<label for="repassword" class="col-sm-2 control-label">重复密码：</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="repassword" name="repassword" value="" />
					</div>
				</div>
				<button type="submit" class="btn btn-primary">确&nbsp;定</button>
			</form>
		</div>
	</div>