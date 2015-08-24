<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/custom/lib" prefix="cus" %>
<link href="${ctx }/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
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
	<div class="container-fluid">
		<ul id="recommendTree" class="ztree"></ul>
	</div>
