<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<script type="text/javascript" src="${ctx }/Highcharts-4.1.3/js/highcharts.js"></script>
	<script type="text/javascript">
		$(function(){
			var dateList = [<c:forEach items="${dates }" var="d" varStatus="s">'${d }'<c:if test="${!s.last }">,</c:if></c:forEach>];
			var datas = [{name:"注册人数",data:[<c:forEach items="${dataList }" var="d" varStatus="s">${d }<c:if test="${!s.last }">,</c:if></c:forEach>]}];
			$('#chart').highcharts({
				plotOptions : {
					spline : {
						fillOpacity : 0.9,//透明度
						lineWidth : 3 //线粗
					}
				},
				credits:{
					enabled: false
				},
				title: {
					text: "注册人数报表",
					x: -20 //center
				},
				xAxis: {
					categories: dateList
				},
				yAxis: {
					title: {
						text: '人'
					},
					min : 0,
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				tooltip: {
					valueDecimals: 0,
					valueSuffix: '人'
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'middle',
					borderWidth: 0
				},
				series: datas
			});
		});
		
		function setQueryParam(f,t){
			$('#from').val(f);
			$('#to').val(t);
		}
	</script>
	<div class="container-fluid">
		<div class="querypanel">
			<form role="form" class="form-inline" action="${ctx }/${cus:url(activeMenu.id,'/bi/registerTrend') }" method="post">
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
					<label for="from">从：</label>
					<input type="date" class="form-control" id="from" name="from" value="<fmt:formatDate value="${from }" pattern="yyyy-MM-dd"/>" />
				</div>
				<div class="form-group">
					<label for="to">到：</label>
					<input type="date" class="form-control" id="to" name="to" value="<fmt:formatDate value="${to }" pattern="yyyy-MM-dd"/>" />
				</div>
				<a href="javascript:void(0)" onclick="setQueryParam('<fmt:formatDate value="${sevenDayAgo }" pattern="yyyy-MM-dd"/>','<fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>')">七天内</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="setQueryParam('<fmt:formatDate value="${firstDayOfThisMonth }" pattern="yyyy-MM-dd"/>','<fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>')">本月</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="setQueryParam('<fmt:formatDate value="${firstDayOfLastMonth }" pattern="yyyy-MM-dd"/>','<fmt:formatDate value="${lastDayOfLastMonth }" pattern="yyyy-MM-dd"/>')">上月</a>
				<button type="submit" class="btn btn-primary">查询</button>
			</form>
		</div>
		<div class="contentpanel">
			<div id="chart" style="margin-right: 15px;">
			</div>
		</div>
	</div>