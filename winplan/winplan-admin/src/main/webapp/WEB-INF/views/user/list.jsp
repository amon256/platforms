<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>

	<div class="container-fluid">
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
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>账号</th>
					<th>加入日期</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty datas }">
						<c:forEach items="${datas }" var="d" varStatus="s">
							<tr>
								<td>${pagination.startIndex + s.index + 1}</td>
								<td>${d.name}</td>
								<td>${d.account}</td>
								<td><fmt:formatDate value="${d.createTime }" pattern="yyyy.MM.dd"/></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">没有更多数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">
						<jsp:include page="../common/pagination.jsp" flush="true">
							<jsp:param value="${cus:url(activeMenu.id,'/user/list') }" name="url"/>
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>