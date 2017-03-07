<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<!-- 表单提交方式必须是post -->
	<form action="/spring-test/login" method="post">
		<!-- 此隐藏域是为了防止跨站请求伪造，可以禁止 -->
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		账户：<input type="text" name="username">
		密码：<input type="password" name="password">
		记住我：<input type="checkbox" name="remember-me"/>
		<input type="submit" value="登录" />
	</form>
</body>
</html>