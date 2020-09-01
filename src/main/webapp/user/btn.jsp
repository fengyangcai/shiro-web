<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>user/btn.jsp</title>
</head>
<body>
user/btn.jsp(需要授权update才能看到)
<shiro:hasPermission name="user_add_btn">
    <button id="addBtn">添加</button>
</shiro:hasPermission>
<hr>
<%--这里没有授权user_add_btn1所以不会在页面显示--%>
<shiro:hasPermission name="user_add_btn1">
    <button id="addBtn">添222加</button>
</shiro:hasPermission>
<hr>
<shiro:hasPermission name="user_add_btn">
    <button id="addBtn">添加2</button>
</shiro:hasPermission>

</body>
</html>