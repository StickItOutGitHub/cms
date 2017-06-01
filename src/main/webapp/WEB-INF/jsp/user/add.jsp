<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript">
$(function(){
	//$("#addForm").cmsvalidate();
	
	$("#addForm").validate({
		rules:{
			username:{
				required:true,
				remote:"${pageContext.request.contextPath}/admin/user/valid_username"
			},
			nickname:"required",
			password:{
				required:true,
				minlength:[6]
			},
			confirmPwd:{
				required:true,
				equalTo:"#password"
			},
			phone:{
				required:true,
				number:true,
				maxlength:[11]
			},
			email:{
				required:true,
				email:true
			}
		},
		messages:{
			username:{
				required:"用户名不能为空",
				remote:"该用户名已经存在"
			},
			nickname:"显示名称不能为空",
			password:{
				required:"密码不能为空",
				minlength:"密码长度至少为{0}位"
			},
			confirmPwd:{
				required:"确认密码不能为空",
				equalTo:"两次输入密码不一致"
			},
			phone:{
				required:"联系电话不能为空",
				number:"联系电话只能是数字",
				maxlength:"联系电话长度不能超过{0}位"
			},
			email:{
				required:"电子邮件不能为空",
				email:"电子邮件格式不正确"
			}
		},
		errorElement:"span",
		errorClass:"errorContainer"
	});
	
	$.validator.addMethod("phoneNumber", function(value, element, param){
	 //方法中又有三个参数:value:被验证的值， element:当前验证的dom对象，param:参数(多个即是数组)
	 //alert(value + "," + $(element).val() + "," + param[0] + "," + param[1]);
	 return new RegExp(/^1[3458]\d{9}$/).test(value);
	 
	}, "手机号码不正确");
});
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<sf:form method="post" modelAttribute="userDto" id="addForm">
	<table width="800" cellspacing="0" cellPadding="0">
		<thead><tr><td colspan="2">添加用户功能</td></tr></thead>
		<tr>
			<td class="rightTd" width="200px">用户名(必须是英文):</td>
			<td class="leftTd"><sf:input path="username" size="30"/><sf:errors cssClass="errorContainer" path="username"/></td>
		</tr>
		<tr>
			<td class="rightTd">显示名称:</td><td class="leftTd"><sf:input path="nickname" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">用户密码:</td><td><sf:password path="password" size="30"/><sf:errors cssClass="errorContainer" path="password"/></td>
		</tr>
		<tr>
			<td class="rightTd">确认密码:</td><td><input type="password" id="confirmPwd" name="confirmPwd" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">联系电话:</td><td><sf:input path="phone" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">电子邮件:</td><td><sf:input path="email" size="30"/><sf:errors path="email"/></td>
		</tr>
		<tr>
			<td class="rightTd">状态:</td>
			<td>
				<sf:select path="status">
					<sf:option value="0">停用</sf:option>
					<sf:option value="1">启用</sf:option>
				</sf:select>
			</td>
		</tr>
		<tr>
			<td class="rightTd">角色:</td>
			<td>
				<%-- <c:forEach var="role" items="${roles }">
					${role.descr }<input type="checkbox" name="roleIds" value="${role.id }"/>
				</c:forEach> --%>
				<sf:checkboxes  items="${roles}" itemLabel="name" itemValue="id" path="roleIds"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">用户组:</td>
			<td>
				<%-- <c:forEach var="role" items="${roles }">
					${role.descr }<input type="checkbox" name="roleIds" value="${role.id }"/>
				</c:forEach> --%>
				<sf:checkboxes items="${groups }" itemLabel="name" itemValue="id" path="groupIds"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd"><input type="submit" value="添加用户"/><input type="reset"/></td>
		</tr>
	</table>
	</sf:form>
</div>
</body>
</html>