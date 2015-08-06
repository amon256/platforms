<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:choose>
	<c:when test="${not empty graphNode }">
		<table width="200" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td align="right" valign="bottom">&nbsp;</td>
					<td align="center">
						<table border="0">
							<tbody>
								<tr>
									<td><div style="width: 5px"></div></td>
									<td>
										<table border="1" cellpadding="0" cellspacing="0" style="background-color: #F90; width: 200px">
											<tbody>
												<tr>
		
													<td colspan="3" align="center"><div style="height: 30px; margin-top: 10px">
															<a href="${ctx }/user/usergragh?account=${graphNode.value.account}" style="color: #fff; font-size: 16px"><b>${graphNode.value.account }</b></a>
														</div>
														<table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
															<tbody>
																<tr>
																	<td height="1" style="background-color: #777"></td>
																</tr>
															</tbody>
														</table>
														<table>
															<tbody>
																<tr>
																	<td align="left"><a href="${ctx }/user/userinfo?id=${graphNode.value.id}" style="color: #fff">姓名: ${graphNode.value.name }<br>昵称: ${graphNode.value.nickName }
																	</a></td>
																</tr>
															</tbody>
														</table></td>
												</tr>
												<tr>
													<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>A</nobr></td>
													<td bgcolor="#EBFBFC"></td>
													<td width="49%" align="center" bgcolor="#EBFBFC"><nobr>B</nobr></td>
												</tr>
												<tr>
													<td align="center" bgcolor="#FFFFFF"><nobr>${graphNode.value.leftCount }</nobr></td>
													<td align="center" bgcolor="#EBFBFC">总</td>
													<td align="center" bgcolor="#FFFFFF"><nobr>${graphNode.value.rightCount }</nobr></td>
												</tr>
												<tr>
													<td align="center" bgcolor="#FFFFFF"><nobr></nobr></td>
													<td align="center" bgcolor="#EBFBFC">层</td>
													<td align="center" bgcolor="#FFFFFF"><nobr></nobr></td>
												</tr>
												<tr>
													<td align="center" colspan="3"><font color="#FFFFFF"><nobr><fmt:formatDate value="${graphNode.value.createTime }" pattern="yyyy-MM-dd"/> </nobr></font></td>
												</tr>
											</tbody>
										</table>
									</td>
									<td><divstyle"width:5px"> </divstyle"width:5px"></td>
								</tr>
							</tbody>
						</table>
					</td>
					<td align="left" valign="bottom">&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<table border="1" cellpadding="0" cellspacing="0" style="background-color: #CCC; width: 200px; color: #fff">
			<tbody>
				<tr>
					<td height="50" align="center"><a href="${ctx }/user/toregister?parentAccount=${parentAccount}&dir=${dir}">注册下属</a></td>
				</tr>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
