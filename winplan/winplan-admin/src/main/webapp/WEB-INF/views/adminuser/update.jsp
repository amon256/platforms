<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>

	<div class="container-fluid">
		<div class="editpanel">
			<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/adminuser/update') }" method="post">
				<input type="hidden" name="id" value="${user.id }"/>
				<input type="hidden" name="account" value="${user.account }"/>
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
					<label for="name" class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="name" name="name"  value="${user.name }" />
					</div>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-sm-2 control-label">手机号码：</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="mobile" name="mobile" value="${user.mobile }" />
					</div>
				</div>
				<div class="form-group">
					<label for="roles" class="col-sm-2 control-label">访问权限：</label>
					<div class="col-sm-6">
						<c:forEach items="${menus }" var="menu">
							<c:choose>
								<c:when test="${not empty menu.subMenus }">
									<c:forEach items="${menu.subMenus }" var="subMenu">
										<label class="checkbox-inline">
										  <input type="checkbox" name="roles"  <c:if test="${fn:contains(user.roles,subMenu.id)}">checked="checked"</c:if> value="${subMenu.id }">${subMenu.name }
										</label>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<label class="checkbox-inline">
									  <input type="checkbox" name="roles"  <c:if test="${fn:contains(user.roles,menu.id)}">checked="checked"</c:if> value="${menu.id }">${menu.name }
									</label>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">确定修改</button>&nbsp;<a href="${ctx }/${cus:url(activeMenu.id,'/adminuser/list') }" role="button" class="btn btn-info" >返回</a>
			</form>
		</div>
	</div>