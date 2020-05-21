<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Login</h3>
<c:url value="./login-controller" var="loginUrl" />
<form:form modelAttribute="userLogin" method="post" action="${loginUrl}">
	<p>Name:</p><form:input path="name"/>
	<form:errors path="name" />
	
	<p>Password:</p><form:input path="password"/>
	<form:errors path="password" />
	<br><br>
	<form:button type="submit">Login</form:button>
</form:form>
<br>
<p>No account? </p><a href="./dang-ky">Register</a>