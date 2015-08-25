<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/custom/lib" prefix="cus" %>
	<div class="container-fluid">
		<div class="list-group">
			<div class="list-group-item">奖金：￥${user.bonus}元</div>
			<div class="list-group-item"><a href="${ctx }/${cus:url('M0101','/bonus/bonushistory') }">奖金记录&gt;</a></div>
		</div>
		<div class="list-group">
			<div class="list-group-item">累计奖金：￥${user.totalBonus }元</div>
			<div class="list-group-item"><a href="${ctx }/${cus:url('M0102','/bonus/totalhistory') }">奖金汇总记录&gt;</a></div>
		</div>
	</div>
