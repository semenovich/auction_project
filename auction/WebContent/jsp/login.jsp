<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<c:if test="${requestScope.isUserNotExist}">
		<p>User doesn't exist</p>
	</c:if>
	<c:if test="${requestScope.isUserDataInvalid}">
		<p>Incorrect input</p>
	</c:if>
	<form action="FrontController" method="POST">
		<input type="hidden" name="command" value="LOGIN"/>
		<div class='form-row'>
			<label for='form_userLogin'>Login: </label>
    		<input type='text' id='form_userLogin' name='userLogin'>
  		</div>

  		<div class='form-row'>
    		<label for='form_userPassword'>Password: </label>
    		<input type='password' id='form_userPassword' name='userPassword'>
		</div>

		<div>
			<input type="submit" value='Go'>
		</div>
	</form>
	<a href="register.jsp"><i>Registration</i></a>
</body>
</html>