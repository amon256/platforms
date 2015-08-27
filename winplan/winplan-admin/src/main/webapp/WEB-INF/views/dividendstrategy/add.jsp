<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<script type="text/javascript">
		$(function(){
			$('#stratergyTable').find('select[tag=fieldName]').bind('change',changeFieldname);
			$('#stratergyTable').find('a[tag=remove]').bind('click',removeRow);
		});
	
		function addRow(){
			var tr = $('#template').find("tr").clone();
			tr.find('select[tag=fieldName]').bind('change',changeFieldname);
			tr.find('a[tag=remove]').bind('click',removeRow);
			$('#stratergyTable').find("tbody").append(tr);
		}
		
		function removeRow(){
			$(this).closest('tr').remove();
		}
		
		function changeFieldname(){
			var that = $(this);
			var v = that.val();
			var tr = that.closest('tr');
			var input = null;
			if(v == 'createTime'){
				input = $('#template').find('[tag=date]');
			}else{
				input = $('#template').find('[tag=text]')
			}
			tr.find('td:eq(2)').empty().append(input.clone());
		}
		
		function beforeSubmit(){
			$('#stratergyTable').find('tbody').find('tr').each(function(index,dom){
				var that = $(this);
				that.find('td:eq(0)').find('select').attr('name','strategys['+index+'].fieldName');
				that.find('td:eq(1)').find('select').attr('name','strategys['+index+'].operation');
				that.find('td:eq(2)').find('input').attr('name','strategys['+index+'].value');
			});
			return true;
		}
	</script>
	<div class="container-fluid">
		<form role="form" class="form-horizontal" action="${ctx }/${cus:url(activeMenu.id,'/dividendstrategy/add') }" method="post" onsubmit="return beforeSubmit()">
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
					<input type="text" class="form-control" name="name" required="required" value="${data.name }" placeholder="名称"/>
				</div>
			</div>
			<div class="form-group">
				<label for="expect" class="col-sm-2 control-label">发放金额：</label>
				<div class="col-sm-6">
					<input type="number" class="form-control" name="expect" required="required" min="0" value="<fmt:formatNumber value="${data.expect }" pattern="0.00"/>" placeholder="发放金额"/>
				</div>
			</div>
			<div class="form-group">
				<label for="date" class="col-sm-2 control-label">发放日期</label>
				<div class="col-sm-6">
					<input type="date" class="form-control" id="date" name="date" required="required" value="<fmt:formatDate value="${data.date }" pattern="yyyy-MM-dd"/>" placeholder="发放日期"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">发放范围策略</label>
				<div class="col-sm-6">
					<a tag="add" href="javascript:void(0);" onclick="addRow()"><i class="fa fa-fw fa-plus-square"></i></a>
					<table id="stratergyTable">
						<tbody>
							<c:choose>
								<c:when test="${not empty data.strategys }">
									<c:forEach items="${data.strategys }" var="d">
										<tr>
											<td>
												<select tag="fieldName" class="form-control" required="required">
													<option value="account" <c:if test="${d.fieldName == 'account' }">selected="selected"</c:if>>账号</option>
													<option value="createTime" <c:if test="${d.fieldName == 'createTime' }">selected="selected"</c:if>>注册日期</option>
												</select>
											</td>
											<td>
												<select class="form-control" required="required">
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
												<input type="text" class="form-control" value="${d.value }" required="required">
											</td>
											<td>
												<a tag="remove" href="javascript:void(0);" onclick="removeRow(this)"><p class="fa fa-fw fa-minus-circle"></p></a>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td>
											<select tag="fieldName" class="form-control" required="required">
												<option value="account">账号</option>
												<option value="createTime">注册日期</option>
											</select>
										</td>
										<td>
											<select class="form-control" required="required">
												<option value="eq">等于</option>
												<option value="$gt">大于</option>
												<option value="$gte">大于等于</option>
												<option value="$lt">小于</option>
												<option value="$lte">小于等于</option>
												<option value="$in">包含</option>
												<option value="$nin">不包含</option>
											</select>
										</td>
										<td>
											<input type="text" class="form-control" name="" required="required">
										</td>
										<td>
											<a tag="remove" href="javascript:void(0);" onclick="removeRow(this)"><p class="fa fa-fw fa-minus-circle"></p></a>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label">备注：</label>
				<div class="col-sm-6">
					<textarea class="form-control" rows="3" name="description">${data.description }</textarea>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">保存</button>
		</form>
		<div id="template" class="hidden">
			<table>
				<tr tag="tr">
					<td>
						<select tag="fieldName" class="form-control" required="required">
							<option value="account">账号</option>
							<option value="createTime">注册日期</option>
						</select>
					</td>
					<td>
						<select class="form-control" required="required">
							<option value="eq">等于</option>
							<option value="$gt">大于</option>
							<option value="$gte">大于等于</option>
							<option value="$lt">小于</option>
							<option value="$lte">小于等于</option>
							<option value="$in">包含</option>
							<option value="$nin">不包含</option>
						</select>
					</td>
					<td>
						<input type="text" class="form-control" name="" required="required">
					</td>
					<td>
						<a tag="remove" href="javascript:void(0);" onclick="removeRow()"><p class="fa fa-fw fa-minus-circle"></p></a>
					</td>
				</tr>
			</table>
			<input tag="text" type="text" class="form-control" name="" required="required">
			<input tag="date" type="date" class="form-control" name="" placeholder="yyyy-MM-dd" required="required">
		</div>
	</div>