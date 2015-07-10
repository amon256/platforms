<%@page import="com.winplan.entity.User"%>
<%@page import="com.winplan.context.WebContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
User user = WebContext.getLoginUser();
%>
<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-10 ">
				<h3 class="text-left text-primary">欢迎您！尊敬的VIP<%=user.getName() %>。</h3>
			</div>
			<div class="col-md-2">
				<a href="${ctx }/logout" class="btn btn-danger btn-default" role="button">安全退出</a>
			</div>
		</div>
	</div>
</div>