<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../common/common.jsp"></jsp:include>
<link href="${ctx }/bootstrap/bootstrap-treenav/css/bootstrap-treenav.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/bootstrap/bootstrap-treenav/js/bootstrap-treenav.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="../common/head.jsp" flush="true"></jsp:include>
		<ul class="breadcrumb">
			<li><a href="${ctx }/index">主页面</a></li>
		</ul>
		<ul class="breadcrumb">
			<li><a href="${ctx }/main">总汇页面</a></li>
			<li class="active">团队系统</li>
			<li class="active">介绍系统</li>
		</ul>
		<c:if test="${not empty msg }">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  	${msg }
			</div>
		</c:if>
		<c:if test="${not empty root }">
			<div class="row">
				<ul class="nav nav-pills nav-stacked nav-tree" id="myTree" data-toggle="nav-tree">
					<li>
						<span>
							<a href="${ctx }/user/usertree?account=${root.value.account}">${root.value.account }(${root.value.name })</a>
							<a href="${ctx }/user/userinfo?id=${root.value.id}">【查看】</a>
						</span>
						<ul class="nav nav-pills nav-stacked nav-tree" >
							<c:choose>
								<c:when test="${root.left != null }">
									<c:set var="treeLeaf" value="${root.left }" scope="request"></c:set>
									<c:import url="leaf.jsp" />
								</c:when>
								<c:otherwise>
									<li>
										<span><a href="${ctx }/user/toregister?parentAccount=${root.value.account}&dir=left">注册</a></span>
									</li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${root.right != null }">
									<c:set var="treeLeaf" value="${root.right }" scope="request"></c:set>
									<c:import url="leaf.jsp" />
								</c:when>
								<c:otherwise>
									<li>
										<span><a href="${ctx }/user/toregister?parentAccount=${root.value.account}&dir=right">注册</a></span>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</li>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>