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
		<form action="${ctx }/index" method="get" class="form-inline">
			<input type="hidden" name="_m" value="${activeMenu.id }"/>
			<input type="hidden" name="_p" value="${cus:pageId('/consume/list') }"/>
			<div class="form-group">
			    <label for="account">账号</label>
			    <input type="text" class="form-control" id="account" name="account" placeholder="用户账号" value="${record.account }">
		  	</div>
			<button type="submit" class="btn btn-default">查询</button>
			<a href="${ctx }/${cus:url(activeMenu.id,'/consume/toAdd') }" role="button" class="btn btn-info">新增</a>
		</form>
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>序号</th>
					<th>金额</th>
					<th>账号</th>
					<th>消费日期</th>
					<th>全返期数</th>
					<th>己返期数</th>
					<th>己返金额</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty datas }">
						<c:forEach items="${datas }" var="d" varStatus="s">
							<tr>
								<td>${pagination.startIndex + s.index + 1}</td>
								<td><fmt:formatNumber value="${d.amount }" pattern="0.00"/></td>
								<td>${d.account }</td>
								<td><fmt:formatDate value="${d.createTime }" pattern="yyyy.MM.dd"/></td>
								<td>${d.periodNumber }</td>
								<td>${d.returned }</td>
								<td>${d.returnedAmount }</td>
								<td><a href="${ctx }/${cus:url(activeMenu.id,'/consume/detail') }&id=${d.id}">明细</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8">没有更多数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
						<jsp:include page="../common/pagination.jsp" flush="true">
							<jsp:param value="${cus:url(activeMenu.id,'/consume/list') }&account=${queryUser.account }" name="url"/>
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>