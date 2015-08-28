<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<form role="form" class="form-horizontal" action="" method="post">
			<input type="hidden" name="id" value="${data.id }">
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
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">名称：</label>
				<div class="col-sm-6">
					<p class="form-control-static">${data.name }</p>
				</div>
			</div>
			<div class="form-group">
				<label for="expect" class="col-sm-2 control-label">发放金额：</label>
				<div class="col-sm-6">
					<p class="form-control-static"><fmt:formatNumber value="${data.expect }" pattern="0.00"/></p>
				</div>
			</div>
			<div class="form-group">
				<label for="reality" class="col-sm-2 control-label">实际金额：</label>
				<div class="col-sm-6">
					<p class="form-control-static"><fmt:formatNumber value="${data.reality }" pattern="0.00"/></p>
				</div>
			</div>
			<div class="form-group">
				<label for="percapita" class="col-sm-2 control-label">人均金额：</label>
				<div class="col-sm-6">
					<p class="form-control-static"><fmt:formatNumber value="${data.percapita }" pattern="0.00"/></p>
				</div>
			</div>
			<div class="form-group">
				<label for="date" class="col-sm-2 control-label">计划日期：</label>
				<div class="col-sm-6">
					<p class="form-control-static"><fmt:formatDate value="${data.date }" pattern="yyyy-MM-dd"/></p>
				</div>
			</div>
			<div class="form-group">
				<label for="giveTime" class="col-sm-2 control-label">实际日期：</label>
				<div class="col-sm-6">
					<p class="form-control-static"><fmt:formatDate value="${data.giveTime }" pattern="yyyy-MM-dd"/></p>
				</div>
			</div>
			<div class="form-group">
				<label for="result" class="col-sm-2 control-label">结果：</label>
				<div class="col-sm-6">
					<p class="form-control-static">${data.result }</p>
					<c:if test="${data.complete == true }">
						<a href="${ctx }/${cus:url(activeMenu.id,'/dividendrecord/list') }&strategy=${data.id}">查看发放明细</a>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">发放范围策略：</label>
				<div class="col-sm-6">
					<table id="stratergyTable">
						<tbody>
							<c:choose>
								<c:when test="${not empty data.strategys }">
									<c:forEach items="${data.strategys }" var="d">
										<tr>
											<td>
												<select tag="fieldName" class="form-control" required="required" disabled="disabled">
													<option value="account" <c:if test="${d.fieldName == 'account' }">selected="selected"</c:if>>账号</option>
													<option value="createTime" <c:if test="${d.fieldName == 'createTime' }">selected="selected"</c:if>>注册日期</option>
												</select>
											</td>
											<td>
												<select class="form-control" required="required" disabled="disabled">
													<option value="$eq" <c:if test="${d.operation == '$eq' }">selected="selected"</c:if>>等于</option>
													<option value="$gt" <c:if test="${d.operation == '$gt' }">selected="selected"</c:if>>大于</option>
													<option value="$gte" <c:if test="${d.operation == '$gte' }">selected="selected"</c:if>>大于等于</option>
													<option value="$lt" <c:if test="${d.operation == '$lt' }">selected="selected"</c:if>>小于</option>
													<option value="$lte" <c:if test="${d.operation == '$lte' }">selected="selected"</c:if>>小于等于</option>
													<option value="$in" <c:if test="${d.operation == '$in' }">selected="selected"</c:if>>包含</option>
													<option value="$nin" <c:if test="${d.operation == '$nin' }">selected="selected"</c:if>>不包含</option>
												</select>
											</td>
											<td>
												<input type="text" class="form-control" value="${d.value }" required="required" disabled="disabled">
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label">备注：</label>
				<div class="col-sm-6">
					<textarea class="form-control" rows="3" name="description" disabled="disabled">${data.description }</textarea>
				</div>
			</div>
		</form>
		<a href="javascript:window.history.go(-1);" role="button" class="btn btn-info">返回</a>
	</div>