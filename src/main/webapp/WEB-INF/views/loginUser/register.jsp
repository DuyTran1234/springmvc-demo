<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<h3>Register</h3>
<c:url value="./register-controller" var="urlRegister" />
<form:form modelAttribute="userRegister" method="post" action="${urlRegister}">
	<p>User name:</p><form:input path="name" /><br>
	<form:errors path="name" />
	<br>
	<p>Password:</p><form:input path="password" /><br>
	<form:errors path="password" />
	<br>
	<form:button type="submit">Register</form:button>
	
</form:form>
