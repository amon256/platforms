<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../common/common.jsp"></jsp:include>
<link href="${ctx }/bootstrap/bootstrap-treenav/css/bootstrap-treenav.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/bootstrap/bootstrap-treenav/js/bootstrap-treenav.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="../common/head.jsp" flush="true"></jsp:include>
		<ul class="breadcrumb">
			<li><a href="${ctx }/index">主页面</a></li>
		</ul>
		<ul class="breadcrumb">
			<li><a href="${ctx }/main">总汇页面</a></li>
			<li class="active">团队系统</li>
			<li class="active">安置系统</li>
		</ul>
		<c:if test="${not empty msg }">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  	${msg }
			</div>
		</c:if>
		<c:if test="${not empty root }">
			<div class="row">
				<table border="0" align="center" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td colspan="4" align="center">
							<%-- 第0层 --%>
								<c:set var="graphNode" value="${root }"  scope="request"/>
								<c:import url="graph.jsp" />
							</td>
						</tr>
						<%-- 0-1连接线 --%>
						<tr>
							<td colspan="4" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tbody>
										<tr>
											<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0" width="50%">
													<tbody>
														<tr>
															<td height="1" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
										<tr>
											<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
											<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
									</tbody>
								</table></td>
						</tr>
						<tr>
							<td width="50%" colspan="2" align="center" valign="top">
								<%-- 第1层左 --%>
								<c:set var="graphNode" value="${root.left }"  scope="request"/>
								<c:set var="parentAccount" value="${root.value.account }" scope="request"/>
								<c:set var="dir" value="left" scope="request"/>
								<c:import url="graph.jsp" />
							</td>
							<td width="50%" colspan="2" align="center" valign="top">
								<%-- 第1层右 --%>
								<c:set var="graphNode" value="${root.right }"  scope="request"/>
								<c:set var="parentAccount" value="${root.value.account }" scope="request"/>
								<c:set var="dir" value="right" scope="request"/>
								<c:import url="graph.jsp" />
							</td>
						</tr>
<c:if test="${(not empty root.left) or (not empty root.right) }">
						<%-- 1-2分隔线 --%>
						<tr>
							<td width="50%" colspan="2" align="center">
<c:if test="${not empty root.left}">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tbody>
										<tr>
											<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0" width="50%">
													<tbody>
														<tr>
															<td height="1" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
										<tr>
											<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
											<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
									</tbody>
								</table>
</c:if>
							</td>
							<td width="50%" colspan="2" align="center">
<c:if test="${not empty root.right }">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tbody>
										<tr>
											<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0" width="50%">
													<tbody>
														<tr>
															<td height="1" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
										<tr>
											<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
											<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td width="1" height="10" bgcolor="#b5d6e6"></td>
														</tr>
													</tbody>
												</table></td>
										</tr>
									</tbody>
								</table>
</c:if>
							</td>
						</tr>
						<tr>
							<td width="25%" align="center" valign="top">
								<%-- 第2层1 --%>
<c:if test="${not empty root.left}">
								<c:set var="graphNode" value="${root.left.left }"  scope="request"/>
								<c:set var="parentAccount" value="${root.left.value.account }" scope="request"/>
								<c:set var="dir" value="left" scope="request"/>
								<c:import url="graph.jsp" />
</c:if>
							</td>
							<td width="25%" align="center" valign="top">
								<%-- 第2层2 --%>
<c:if test="${not empty root.left}">
								<c:set var="graphNode" value="${root.left.right }"  scope="request"/>
								<c:set var="parentAccount" value="${root.left.value.account }" scope="request"/>
								<c:set var="dir" value="right" scope="request"/>
								<c:import url="graph.jsp" />
</c:if>
							</td>
							<td width="25%" align="center" valign="top">
								<%-- 第2层3 --%>
<c:if test="${not empty root.right}">
								<c:set var="graphNode" value="${root.right.left }" scope="request"/>
								<c:set var="parentAccount" value="${root.right.value.account }" scope="request"/>
								<c:set var="dir" value="left" scope="request"/>
								<c:import url="graph.jsp" />
</c:if>
							</td>
							<td width="25%" align="center" valign="top">
							<%-- 第2层4 --%>
								<c:if test="${not empty root.right}">
								<c:set var="graphNode" value="${root.right.right }"  scope="request"/>
								<c:set var="parentAccount" value="${root.right.value.account }" scope="request"/>
								<c:set var="dir" value="right" scope="request"/>
								<c:import url="graph.jsp" />
</c:if>
							</td>
						</tr>
</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		<div class="row">
			<div style="height: 10px;"></div>
		</div>
	</div>
</body>
</html>