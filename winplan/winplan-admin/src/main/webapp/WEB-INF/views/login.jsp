<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<jsp:include page="common/common.jsp"></jsp:include>
	<style type="text/css">
html,body {
	height: 100%;
}
.box {
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6699FF', endColorstr='#6699FF'); /*  IE */
	background-image:linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-o-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-moz-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-webkit-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-ms-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	
	margin: 0 auto;
	position: relative;
	width: 100%;
	height: 100%;
}
.login-box {
	width: 100%;
	max-width:500px;
	height: 400px;
	position: absolute;
	top: 50%;

	margin-top: -200px;
	/*设置负值，为要定位子盒子的一半高度*/
	
}
@media screen and (min-width:500px){
	.login-box {
		left: 50%;
		/*设置负值，为要定位子盒子的一半宽度*/
		margin-left: -250px;
	}
}	

.form {
	width: 100%;
	max-width:500px;
	height: 275px;
	margin: 25px auto 0px auto;
	padding-top: 25px;
}	
.login-content {
	height: 300px;
	width: 100%;
	max-width:500px;
	background-color: rgba(255, 250, 2550, .6);
	float: left;
}		
	
	
.input-group {
	margin: 0px 0px 30px 0px !important;
}
.form-control,
.input-group {
	height: 40px;
}

.form-group {
	margin-bottom: 0px !important;
}
.login-title {
	padding: 20px 10px;
	background-color: rgba(0, 0, 0, .6);
}
.login-title h1 {
	margin-top: 10px !important;
}
.login-title small {
	color: #fff;
}

.link p {
	line-height: 20px;
	margin-top: 30px;
}
.btn-sm {
	padding: 8px 24px !important;
	font-size: 16px !important;
}
</style>
</head>
<body>
	<div class="box">
		<div class="login-box">
			<c:if test="${not empty msg }">
				<div class="alert alert-warning alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  	${msg }
				</div>
			</c:if>
			<div class="login-title text-center">
				<h1><small>JK Group Manager</small></h1>
			</div>
			<div class="login-content ">
				<div class="form">
					<form action="${ctx }/login" method="post">
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-user fa fa-fw fa-user"></span></span>
									<c:choose>
										<c:when test="${empty accounts }">
											<input type="text" class="form-control" id="account" name="account" value="${user.account }" placeholder="管理员账号">
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control" id="account" name="account" value="${user.account }" list="accountList" placeholder="管理员账号">
											<datalist id="accountList">
												<c:forEach items="${accounts }" var="acc">
													<option>${acc }</option>
												</c:forEach>
											</datalist>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-lock fa fa-fw fa-unlock"></span></span>
									<input type="password" class="form-control" id="pwd" name="password" value="${user.password }" placeholder="您的密码">
								</div>
							</div>
						</div>
						<div class="form-group form-actions">
							<div class="col-xs-4 col-xs-offset-4 ">
								<button type="submit" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-off"></span>登录</button>
							</div>
						</div>
						<!-- <div class="form-group">
							<div class="col-xs-6 link">
								<p class="text-center remove-margin"><small>忘记密码？</small> <a href="javascript:void(0)" ><small>找回</small></a>
								</p>
							</div>
							<div class="col-xs-6 link">
								<p class="text-center remove-margin"><small>还没注册?</small> <a href="javascript:void(0)" ><small>注册</small></a>
								</p>
							</div>
						</div> -->
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>