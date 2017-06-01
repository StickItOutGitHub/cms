<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<span>
当前栏目:${current.name }&nbsp;
<a href="<%=request.getContextPath() %>/admin/channel/add/${id}" class="admin_link">添加子栏目</a>
<a href="<%=request.getContextPath() %>/admin/channel/channels/${id}/-1" class="admin_link">子栏目列表</a>
</span>