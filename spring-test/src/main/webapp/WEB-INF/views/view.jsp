<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视图页面</title>
</head>
<body>
	<h1>视图页面</h1>
	<!-- Filter会自动拦截针对logout的请求，默认跳转到登录页面，当期然可以自己设置退出后显示的页面 
	######必须注意退出登录的链接必须以 POST 的方式提交######
	-->
	<form id="_form" method="post" action="signout">
	  <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	  <a onclick="document.getElementById('_form').submit();" href="#">点击退出</a>
	</form>

</body>
</html>