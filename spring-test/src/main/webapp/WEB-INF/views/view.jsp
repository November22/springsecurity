<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" 
		uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视图页面</title>
</head>
<body>
	<h1>视图页面</h1>
	<p>
		principal包含的值：<security:authentication property="principal" /> 
		details包含的值：<security:authentication property="details" /> 
		Credentials的值：<security:authentication property="Credentials" /> 
		authorities的值：<security:authentication property="authorities" /> 
	</p>
	
	<%-- 用户的ID <security:authentication property="principal" /> ! --%>
	
	<input type="hidden" value="<security:authentication property='principal.username' />"/>
	<!-- Filter会自动拦截针对logout的请求，默认跳转到登录页面，当期然可以自己设置退出后显示的页面 
	######必须注意退出登录的链接必须以 POST 的方式提交######
	-->
	<security:authorize access="hasRole('ROLE_iths')">
		具有角色ROLE_iths	
	</security:authorize>
	
	<security:accesscontrollist hasPermission="wahaha" domainObject="principal.authorities">
		具有哇哈哈权限	
	</security:accesscontrollist>
	
	<security:authorize url="/admin">
		这些内容之会被有权限发送请求到"/admin" URL的用户才可以看到。
	</security:authorize>
	
	<form id="_form" method="post" action="signout">
	  <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	  <a onclick="document.getElementById('_form').submit();" href="#">点击退出</a>
	</form>

</body>
</html>