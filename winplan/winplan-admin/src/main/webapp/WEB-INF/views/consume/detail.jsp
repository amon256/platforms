<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="editpanel">
		<form class="form-inline">
		  <div class="form-group">
		    <label>消费账号:</label>
		    <u class="form-control-static">${record.account }</u>
		  </div>
		  <div class="form-group">
		    <label>消费金额:</label>
		    <u class="form-control-static"><fmt:formatNumber value="${record.amount }" pattern="0.00" /></u>
		  </div>
		  <div class="form-group">
		    <label>入账日期:</label>
		    <u class="form-control-static"><fmt:formatDate value="${record.createTime }" pattern="yyyy.MM.dd"/></u>
		  </div>
		  <div class="form-group">
		    <label>备注:</label>
		    <u class="form-control-static">${record.description }</u>
		  </div>
		 </form>
		 <c:choose>
		 	<c:when test="${record.periodNumber > 0 }">
		 		<p>返现进度</p>
		 		<div class="progress">
				  <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="${percent }" aria-valuemin="0" aria-valuemax="100" style="width: ${percent}%;min-width: 2em;">
				    ${percent }%
				  </div>
				</div>
		 		<table class="table">
					<thead>
						<tr>
							<th>序号</th>
							<th>返现日期</th>
							<th>金额</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${returnRecords }" var="rec">
							<tr>
								<td>${rec.periodIndex }</td>
								<td><fmt:formatDate value="${rec.returnDate }" pattern="yyyy.MM.dd"/> </td>
								<td><fmt:formatNumber value="${rec.amount }" pattern="0.00"/> </td>
								<td>
									<c:choose>
										<c:when test="${rec.complete == true }"><span class="text text-success">己返</span></c:when>
										<c:otherwise>待返</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		 	</c:when>
		 	<c:otherwise>
		 		<p>该消费无返现</p>
		 	</c:otherwise>
		 </c:choose>
		</div>
	</div>
