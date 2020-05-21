<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>

<h2>Header content</h2>
<a href="./trang-chu">Home</a>
<br>
<c:if test="${userSession == null}">
	<a href="./dang-nhap">Login</a>
	<br>
	<a href="./dang-ky">Register</a>
</c:if>

<c:if test="${userSession != null}">
	<p>Hello </p><a href="#">${userSession.getName()}</a>
	<br>
	<a href="./dang-xuat">Logout</a>
</c:if>
<hr>