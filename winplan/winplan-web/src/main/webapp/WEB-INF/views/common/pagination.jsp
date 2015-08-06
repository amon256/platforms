<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="com.winplan.utils.Pagination"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	Pagination pagination = (Pagination)request.getAttribute("pagination");
	int start = 1;
	if(pagination.getCurrentPage() > 3){
		start = pagination.getCurrentPage() - 2;
	}
	int max = pagination.getPageCount();
	List<Integer> pages = new LinkedList<Integer>();
	do{
		pages.add(start);
		start ++;
	}while(start <= max && pages.size() < 5);
	pageContext.setAttribute("pages",pages);
	String url = request.getParameter("url");
	if(url.indexOf("?") > 0){
		url += "&";
	}else{
		url += "?";
	}
	url += "currentPage=";
	pageContext.setAttribute("url",url);
%>
<nav>
  <ul class="pagination">
    <li <c:if test="${pagination.currentPage == 1 }"> class="disabled"</c:if>>
      <a href="${requestScope.ctx }/<%=url + pagination.getPreviousPage() %>" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach items="${pageScope.pages }" var="p">
    	<li <c:if test="${pagination.currentPage == p }"> class="active"</c:if>><a href="${requestScope.ctx }/<%=url%>${p}" >${p }</a></li>
    </c:forEach>
    <li <c:if test="${pagination.currentPage == pagination.pageCount }"> class="disabled"</c:if>>
      <a href="${requestScope.ctx }/<%=url + pagination.getNextPage() %>" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>