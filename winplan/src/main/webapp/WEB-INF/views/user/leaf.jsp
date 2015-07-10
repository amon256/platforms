<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<li>
	<span>
		<a href="${ctx }/user/usertree?account=${treeLeaf.value.account}">${treeLeaf.value.account }(${treeLeaf.value.name })</a>
		<a href="${ctx }/user/userinfo?id=${treeLeaf.value.id}">【查看】</a>
	</span>
	<ul class="nav nav-pills nav-stacked nav-tree" >
		<c:choose>
			<c:when test="${treeLeaf.left != null }">
				<c:set var="treeLeaf" value="${treeLeaf.left }" scope="request"></c:set>
				<c:import url="leaf.jsp" />
			</c:when>
			<c:otherwise>
				<li>
					<span><a href="${ctx }/user/toregister?parentAccount=${treeLeaf.value.account}&dir=left">注册</a></span>
				</li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${treeLeaf.right != null }">
				<c:set var="treeLeaf" value="${treeLeaf.right }" scope="request"></c:set>
				<c:import url="leaf.jsp" />
			</c:when>
			<c:otherwise>
				<li>
					<span><a href="${ctx }/user/toregister?parentAccount=${treeLeaf.value.account}&dir=right">注册</a></span>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</li>