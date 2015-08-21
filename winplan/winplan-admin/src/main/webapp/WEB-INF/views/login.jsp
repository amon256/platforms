<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<jsp:include page="common/common.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-left text-primary">
					欢迎光临!
				</h1>
				<form id="loginUser" role="form" class="form-horizontal" action="${ctx }/login" method="post">
					<c:if test="${not empty msg }">
						<div class="alert alert-warning alert-dismissible" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						  	${msg }
						</div>
					</c:if>
					<div class="form-group">
						<label for="account" class="col-sm-2 control-label">用户账号：</label>
						<div class="col-sm-6">
							<c:choose>
								<c:when test="${empty accounts }">
									<input type="text" class="form-control" id="account" name="account" value="${user.account }" placeholder="QZ+六位数字">
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" id="account" name="account" value="${user.account }" list="accountList" placeholder="QZ+六位数字">
									<datalist id="accountList">
										<c:forEach items="${accounts }" var="acc">
											<option>${acc }</option>
										</c:forEach>
									</datalist>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<label for="pwd" class="col-sm-2 control-label">用户密码：</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="pwd" name="password" value="${user.password }" placeholder="您的密码">
						</div>
					</div>
					<button type="submit" class="btn btn-primary">登录</button>
				</form>
				<h2 class="text-muted text-center">
					千里之行，始于足下
				</h2>
			</div>
		</div>
	</div>
</body>
</html>