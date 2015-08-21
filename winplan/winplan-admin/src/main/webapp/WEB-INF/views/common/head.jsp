<%@page import="com.winplan.entity.AdminUser"%>
<%@page import="com.winplan.entity.User"%>
<%@page import="com.winplan.context.WebContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
AdminUser user = WebContext.getLoginUser();
%>
<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-8 ">
				<h4 class="text-left text-primary">欢迎您！尊敬的管理员【<%=user != null?user.getName() : "" %>】。<a href="${ctx }/logout" class="btn btn-danger btn-default" role="button">安全退出</a></h4>
			</div>
		</div>
	</div>
</div>