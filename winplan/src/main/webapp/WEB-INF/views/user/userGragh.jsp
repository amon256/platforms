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
				<table>
					<tr>
						<td colspan="4" align="center">
							<table style="width:200px;">
								<tr>
									<td align="right" valign="bottom">&nbsp;</td>
									<td align="center">
										<table>
											<tr>
												<td><div style="width: 5px"></div></td>
												<td>
													<table border="1"  style="background-color: #F90; width: 200px">
														<tr>
			
															<td colspan="3" align="center"><div style="height: 30px; margin-top: 10px">
																	<a href="#" style="color: #fff; font-size: 16px"><b>${root.value.account }</b></a>
																</div>
																<table  style="width: 100%">
																	<tr>
																		<td height="1" style="background-color: #777"></td>
																	</tr>
																</table>
																<table>
																	<tr>
																		<td align="left"><a href="#" style="color: #fff">姓名: ${root.value.name }<br />昵称: ${root.value.nickName }
																		</a></td>
																	</tr>
																</table></td>
														</tr>
														<tr>
															<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>A</nobr></td>
															<td bgcolor="#EBFBFC"></td>
															<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>B</nobr></td>
														</tr>
														<tr>
															<td align="center" bgcolor="#FFFFFF"><nobr>${root.value.leftCount }</nobr></td>
															<td align="center" bgcolor="#EBFBFC">总</td>
															<td align="center" bgcolor="#FFFFFF"><nobr>${root.value.rightCount }</nobr></td>
														</tr>
														<tr>
															<td align="center" colspan="3"><font color="#FFFFFF"><nobr><fmt:formatDate value="${root.value.createTime }" pattern="yyyy-MM-dd"/></nobr></font></td>
														</tr>
													</table>
												</td>
												<td>
													<div style="width:5px"></div>
												</td>
											</tr>
										</table>
									</td>
									<td align="left" valign="bottom">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${not empty root.left or not empty root.right }">
						<tr>
							<td colspan="4" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="1" height="10" bgcolor="#b5d6e6"></td>
												</tr>
											</table></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><table border="0" cellpadding="0" cellspacing="0" width="50%">
												<tr>
													<td height="1" bgcolor="#b5d6e6"></td>
												</tr>
											</table></td>
									</tr>
									<tr>
										<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="1" height="10" bgcolor="#b5d6e6"></td>
												</tr>
											</table></td>
										<td width="50%" align="center"><table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="1" height="10" bgcolor="#b5d6e6"></td>
												</tr>
											</table></td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td width="50%" colspan="2" align="center" valign="top">
								<table border="0">
									<tr>
										<td><div style="width: 5px"></div></td>
										<td>
											<table border="1" cellpadding="0" cellspacing="0" style="background-color: #F90; width: 200px">
												<tr>
				
													<td colspan="3" align="center"><div style="height: 30px; margin-top: 10px">
															<a href="/Member/?Seitenzahl=9600002&Anmelden=1436938381&sc=GC57750555" style="color: #fff; font-size: 16px"><b>GC57750555</b></a>
														</div>
														<table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
															<tr>
																<td height="1" style="background-color: #777"></td>
															</tr>
														</table>
														<table>
															<tr>
																<td align="left"><a href="/Member/?Seitenzahl=9600002&Anmelden=1436938381&sc=GC57750555" style="color: #fff">姓名: 徐玉兰<br />昵称: OK2170
																</a></td>
															</tr>
														</table></td>
												</tr>
												<tr>
													<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>A</nobr></td>
													<td bgcolor="#EBFBFC"></td>
													<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>B</nobr></td>
												</tr>
												<tr>
													<td align="center" bgcolor="#FFFFFF"><nobr>2</nobr></td>
													<td align="center" bgcolor="#EBFBFC">总</td>
													<td align="center" bgcolor="#FFFFFF"><nobr>0</nobr></td>
												</tr>
												<tr>
													<td align="center" colspan="3"><font color="#FFFFFF"><nobr>2015-04-22</nobr></font></td>
												</tr>
											</table>
										</td>
										<td><divstyle"width:5px">
											</div></td>
									</tr>
								</table>
							</td>
							<td width="50%" colspan="2" align="center" valign="top">
								<table border="0">
									<tr>
										<td><div style="width: 5px"></div></td>
										<td>
											<table border="1" cellpadding="0" cellspacing="0" style="background-color: #F90; width: 200px">
												<tr>
				
													<td colspan="3" align="center"><div style="height: 30px; margin-top: 10px">
															<a href="/Member/?Seitenzahl=9600002&Anmelden=1436938381&sc=GC78878555" style="color: #fff; font-size: 16px"><b>GC78878555</b></a>
														</div>
														<table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
															<tr>
																<td height="1" style="background-color: #777"></td>
															</tr>
														</table>
														<table>
															<tr>
																<td align="left"><a href="/Member/?Seitenzahl=9600002&Anmelden=1436938381&sc=GC78878555" style="color: #fff">姓名: 郭顺环<br />昵称: OK6313
																</a></td>
															</tr>
														</table></td>
												</tr>
												<tr>
													<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>A</nobr></td>
													<td bgcolor="#EBFBFC"></td>
													<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>B</nobr></td>
												</tr>
												<tr>
													<td align="center" bgcolor="#FFFFFF"><nobr>2</nobr></td>
													<td align="center" bgcolor="#EBFBFC">总</td>
													<td align="center" bgcolor="#FFFFFF"><nobr>1</nobr></td>
												</tr>
												<tr>
													<td align="center" colspan="3"><font color="#FFFFFF"><nobr>2015-04-26</nobr></font></td>
												</tr>
											</table>
										</td>
										<td><divstyle"width:5px">
											</div></td>
									</tr>
								</table>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>