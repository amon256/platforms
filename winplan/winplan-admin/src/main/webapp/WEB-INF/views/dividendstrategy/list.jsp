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
			<a href="${ctx }/${cus:url(activeMenu.id,'/dividendstrategy/toAdd') }" role="button" class="btn btn-info">新增</a>
		</div>
		<div class="contentpanel">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>预计金额</th>
						<th>预计日期</th>
						<th>状态</th>
						<th>发放日期</th>
						<th>实际金额</th>
						<th>人均金额</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty datas }">
							<c:forEach items="${datas }" var="d" varStatus="s">
								<tr>
									<td>${pagination.startIndex + s.index + 1}</td>
									<td>${d.name }</td>
									<td><fmt:formatNumber value="${d.expect }" pattern="0.00"/></td>
									<td><fmt:formatDate value="${d.date }" pattern="yyyy/MM/dd"/></td>
									<td>
										<c:choose>
											<c:when test="${d.complete == true }"><span class="text text-success">己发放</span></c:when>
											<c:otherwise>待发放</c:otherwise>
										</c:choose>
									</td>
									<td><fmt:formatDate value="${d.giveTime }" pattern="yyyy/MM/dd"/></td>
									<td><fmt:formatNumber value="${d.reality }" pattern="0.00"/></td>
									<td><fmt:formatNumber value="${d.percapita }" pattern="0.00"/></td>
									<td>
										<c:if test="${d.complete != true }">
											<a href="${ctx }/${cus:url(activeMenu.id,'/dividendstrategy/toUpdate') }&id=${d.id}">修改</a>
										</c:if>
										<a href="${ctx }/${cus:url(activeMenu.id,'/dividendstrategy/detail') }&id=${d.id}">查看</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="9">没有更多数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<jsp:include page="../common/pagination.jsp" flush="true">
								<jsp:param value="${cus:url(activeMenu.id,'/dividendstrategy/list') }" name="url"/>
							</jsp:include>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>