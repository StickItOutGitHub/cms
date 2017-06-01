<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ztree/zTreeStyle.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ztree/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript">
var mt;
$(function(){
	mt = $("#tree").mytree();
});
function refreshTree() {
	mt.reAsyncChildNodes(null,"refresh");
}
	/* $(function() {
	var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pid",
					rootPId : -1
				}
			},
			async : {
				enable : true,
				url : "getTrees"
			},
			view : {

			},
			callback: {
				onClick: zTreeOnClick
			}
		};

		function zTreeOnClick(event,treeId,treeNode) {
			$("#iframe").attr("src","getChildent/"+treeNode.id);
		}
		$.fn.zTree.init($("#tree"), setting);
	}); */
	
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<span>正在使用栏目管理功能</span>
	</h3>
	<TABLE border=0 align=left height="600px">
		<TR>
			<TD width=150px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:150px; overflow:auto;"></ul>
			</TD>
			<TD width=650px align=left valign=top><IFRAME ID="iframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=600px ></IFRAME></TD>
		</TR>
	</TABLE>
</div>
</body>
</html>