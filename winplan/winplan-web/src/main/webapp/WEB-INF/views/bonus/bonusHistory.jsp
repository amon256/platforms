<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">		
		<div class="editpanel">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>日期</th>
						<th>类型</th>
						<th>金额</th>
						<th>剩余</th>
						<th>说明</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty datas }">
							<c:forEach items="${datas }" var="d" varStatus="s">
								<tr>
									<td>${pagination.startIndex + s.index + 1}</td>
									<td><fmt:formatDate value="${d.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>
										<c:choose>
											<c:when test="${d.type == 'TRANSFER_IN'}"><span class="text-success">转入</span></c:when>
											<c:when test="${d.type == 'TRANSFER_OUT'}"><span class="text-danger">转出</span></c:when>
											<c:when test="${d.type == 'BONUS_KOU'}"><span class="text-danger">扣除</span></c:when>
											<c:when test="${d.type == 'BONUS_TJ'}"><span class="text-success">推荐奖</span></c:when>
											<c:when test="${d.type == 'BONUS_CEN'}"><span class="text-success">层奖</span></c:when>
											<c:otherwise><span class="text-danger">其它</span></c:otherwise>
										</c:choose>
									</td>
									<td><fmt:formatNumber value="${d.bonus }" pattern="0.00"/></td>
									<td><fmt:formatNumber value="${d.surplus }" pattern="0.00"/></td>
									<td>${d.desc }</td>
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
				<jsp:param value="${cus:url(activeMenu.id,'/bonus/bonushistory') }" name="url"/>
			</jsp:include>
		</div>
	</div>