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
			<form action="${ctx }/index" method="get" class="form-inline">
				<input type="hidden" name="_m" value="${activeMenu.id }"/>
				<input type="hidden" name="_p" value="${cus:pageId('/bonuscash/applyList') }"/>
				<div class="form-group">
				    <label for="account">账号</label>
				    <input type="text" class="form-control" id="account" name="account" placeholder="用户账号" value="${queryUser.account }">
			  	</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
		</div>
		<div class="contentpanel">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>金额</th>
						<th>申请人</th>
						<th>账号</th>
						<th>申请日期</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty datas }">
							<c:forEach items="${datas }" var="d" varStatus="s">
								<tr>
									<td>${pagination.startIndex + s.index + 1}</td>
									<td><fmt:formatNumber value="${d.applyAmount }" pattern="0"/></td>
									<td>${d.applyer.name }</td>
									<td>${d.applyer.account }</td>
									<td><fmt:formatDate value="${d.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>
										<c:choose>
											<c:when test="${d.status == 'END' }">
												<c:choose>
													<c:when test="${d.result == 'AGREE' }"><span style="color: green;">己提现</span></c:when>
													<c:otherwise><span style="color: red;">己拒绝</span></c:otherwise>
												</c:choose>											
											</c:when>
											<c:otherwise><a href="${ctx }/${cus:url(activeMenu.id,'/bonuscash/toProcess') }&id=${d.id}">待处理</a></c:otherwise>
										</c:choose>
									</td>
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
				<tfoot>
					<tr>
						<td colspan="6">
							<jsp:include page="../common/pagination.jsp" flush="true">
								<jsp:param value="${cus:url(activeMenu.id,'/bonuscash/applyList') }&account=${queryUser.account }" name="url"/>
							</jsp:include>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>