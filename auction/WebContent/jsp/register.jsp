<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${requestScope.isUserExists}">
		<p>User with similar login exists</p>
	</c:if>
	<c:if test="${requestScope.isUserDataInvalid}">
		<p>Incorrect input</p>
	</c:if>
	<form action="FrontController" method="POST">
		<input type="hidden" name="command" value="REGISTER"/>
		
		<div class='form-row'>
			<label for='form_userPicture'>Picture: </label>
			<input type='text' name='userPicture'>
  		</div>
		<div class='form-row'>
			<label for='form_userLogin'>Login: </label>
    		<input type='text' id='form_userLogin' name='userLogin'>
  		</div>

  		<div class='form-row'>
    		<label for='form_userPassword'>Password: </label>
    		<input type='password' id='form_userPassword' name='userPassword'>
		</div>

		<div class='form-row'>
			<label for='form_userEmail'>Email: </label>
    		<input type='text' id='form_userEmail' name='userEmail'>
  		</div>
		
		<div class='form-row'>
			<label for='form_userSurname'>Surname: </label>
    		<input type='text' id='form_userSurname' name='userSurname'>
  		</div>
		<div class='form-row'>
			<label for='form_userName'>Name: </label>
    		<input type='text' id='form_userName' name='userName'>
  		</div>
		<div class='form-row'>
			<label for='form_userPhone'>Phone: </label>
    		<input type='text' id='form_userPhone' name='userPhone'>
  		</div>
		<div class='form-row'>
			<label for='form_userPassportId'>Passport id: </label>
    		<input type='text' id='form_userPassportId' name='userPassportId'>
  		</div>
		<div class='form-row'>
			<label for='form_userIssuedBy'>Issued by: </label>
    		<input type='text' id='form_userIssuedBy' name='userPassportIssuedBy'>
  		</div>
		<select name="userCountry">
		  	<option value="Belarus">Belarus</option>
		  	<option value="Russia">Russia</option>
		  	<option value="USA">USA</option>
		</select>
		<div>
			<input type="submit" value='Go'>
		</div>
	</form>
	<a href="login.jsp"><i>Login</i></a>
</body>
</html>