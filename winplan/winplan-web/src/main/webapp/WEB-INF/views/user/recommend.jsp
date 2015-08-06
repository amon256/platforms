<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String port = request.getLocalPort()==80?"":":"+request.getLocalPort();
String ctx = request.getScheme()+"://"+request.getServerName()+port+request.getContextPath();
request.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctx }/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/zTree_v3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<title>JK Group</title>
<script type="text/javascript">
	var ctx = '${ctx }';
</script>
</head>
<script type="text/javascript">
	var setting = {
		view: {
			selectedMulti: false
		},
		async: {
			enable: true,
			url:"${ctx}/user/recommendData",
			autoParam:["id"],
			dataFilter: filter
		}
	};
	
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			if(parentNode){
				childNodes[i].icon = "${ctx}/zTree_v3/images/leaf.png";
			}else{
				childNodes[i].icon = "${ctx}/zTree_v3/images/root.png";
			}
		}
		return childNodes;
	}
	
	$(function(){
		$.fn.zTree.init($("#recommendTree"), setting);
	});
</script>
<body>
		<ul id="recommendTree" class="ztree"></ul>
</body>
</html>