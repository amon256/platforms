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
		<div class="editpanel">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>账号</th>
						<th>加入日期</th>
						<th>最后登录时间</th>
						<th>状态</th>
						<th>操作/<a href="${ctx }/${cus:url(activeMenu.id,'/adminuser/toAdd') }">新增</a></th>
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
									<td><fmt:formatDate value="${d.lastLoginTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>
										<c:choose>
											<c:when test="${d.status == 'INIT' }"><span class="text-muted">未启用</span></c:when>
											<c:when test="${d.status == 'EFFECT' }"><span class="text-success">己启用</span></c:when>
											<c:when test="${d.status == 'DISABLED' }"><span class="text-danger">禁用</span></c:when>
										</c:choose>
									</td>
									<td>
										<a href="${ctx }/${cus:url(activeMenu.id,'/adminuser/toUpdate') }&id=${d.id}">修改</a>
										<c:if test="${d.status != 'EFFECT' }">
										|&nbsp;<a href="${ctx }/${cus:url(activeMenu.id, '/adminuser/toEffect') }&id=${d.id}">激活</a>
										</c:if>
										<c:if test="${d.status == 'EFFECT' }">
										|&nbsp;<a href="javascript:void(0)" onclick="confirmOperation('${ctx }/${cus:url(activeMenu.id,'/adminuser/disabled') }&id=${d.id}','确定禁用${d.account }')">禁用</a>
										</c:if>
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
			</table>
			<jsp:include page="../common/pagination.jsp" flush="true">
				<jsp:param value="${cus:url(activeMenu.id,'/adminuser/list') }" name="url"/>
			</jsp:include>
		</div>
	</div>
	<script type="text/javascript">
		function confirmOperation(url,msg){
			if(confirm(msg)){
				window.location.href = url;
			}
		}
	</script>
