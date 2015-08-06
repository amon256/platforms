<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="leaf" value="${requestScope.treeLeaf }" scope="page"></c:set>
<li>
	<span>
		<a href="${ctx }/user/usertree?account=${leaf.value.account}">${leaf.value.account }(${leaf.value.name })</a>
		<a href="${ctx }/user/userinfo?id=${leaf.value.id}">【查看】</a>
	</span>
	<ul class="nav nav-pills nav-stacked nav-tree" >
		<c:choose>
			<c:when test="${leaf.left != null }">
				<c:set var="treeLeaf" value="${leaf.left }" scope="request"></c:set>
				<c:import url="leaf.jsp" />
			</c:when>
			<c:otherwise>
				<li>
					<span><a href="${ctx }/user/toregister?parentAccount=${leaf.value.account}&dir=left">注册</a></span>
				</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${leaf.right != null }">
				<c:set var="treeLeaf" value="${leaf.right }" scope="request"></c:set>
				<c:import url="leaf.jsp" />
			</c:when>
			<c:otherwise>
				<li>
					<span><a href="${ctx }/user/toregister?parentAccount=${leaf.value.account}&dir=right">注册</a></span>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</li>