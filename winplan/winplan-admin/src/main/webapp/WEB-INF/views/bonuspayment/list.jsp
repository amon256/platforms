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
		<div class="querypanel">
			<a href="${ctx }/${cus:url(activeMenu.id,'/bonuspayment/toAdd') }" role="button" class="btn btn-info">新增</a>
		</div>
		<div class="contentpanel">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>金额</th>
						<th>姓名</th>
						<th>账号</th>
						<th>日期</th>
						<th>发放人</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty datas }">
							<c:forEach items="${datas }" var="d" varStatus="s">
								<tr>
									<td>${pagination.startIndex + s.index + 1}</td>
									<td><fmt:formatNumber value="${d.amount }" pattern="0"/></td>
									<td>${d.user.name }</td>
									<td>${d.user.account }</td>
									<td><fmt:formatDate value="${d.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>${d.creater.name }(${d.creater.account })</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">没有更多数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<jsp:include page="../common/pagination.jsp" flush="true">
				<jsp:param value="${cus:url(activeMenu.id,'/bonuspayment/list') }" name="url"/>
			</jsp:include>
		</div>
	</div>