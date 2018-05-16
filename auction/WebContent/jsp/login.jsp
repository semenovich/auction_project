<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link rel="stylesheet" href="css/style.css"/>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<c:if test="${sessionScope.locale == null}">
		<fmt:setLocale value="en"/>
	</c:if>
	<fmt:setBundle basename="resources/locales/locale" var="current_locale" scope="session"/>
	<title><fmt:message bundle="${current_locale}" key="locale.login"/></title>
</head>
<body>
	<c:if test="${requestScope.isUserNotExist}">
		<div id="login_user_existance_text">
			<p><fmt:message bundle="${current_locale}" key="locale.login.user.not.exist"/></p>
		</div>
	</c:if>
	<c:if test="${requestScope.isUserDataInvalid}">
		<div id="login_data_invalid_text">
			<p><fmt:message bundle="${current_locale}" key="locale.login.data.invalid"/></p>
		</div>
	</c:if>
	<c:if test="${!requestScope.isUserDataInvalid}">
		<div id="login_data_invalid_text" style="display: none;">
			<p><fmt:message bundle="${current_locale}" key="locale.login.data.invalid"/></p>
		</div>
	</c:if>
	<div class="container">
		<div class="login_language col-md-12">
			<form action="FrontController">
		        <input type="hidden" name="command" value="CHANGE_LOCALE"/>
				<select id="language" class="form-control" name="locale" onchange="submit()">
			        <option value="en"  ${locale == 'en' ? 'selected' : ''}><fmt:message bundle="${current_locale}" key="locale.change.locale.name.en"/></option>
			    	<option value="ru" ${locale == 'ru' ? 'selected' : ''}><fmt:message bundle="${current_locale}" key="locale.change.locale.name.ru"/></option>
				</select>
			</form>
		</div>
		<div class="login_panel col-md-4 col-md-offset-4 text-center">
	      	<form class="login_form" action="FrontController" method="POST" onsubmit="validate(); return false">
				<input type="hidden" name="command" value="LOGIN"/>
		        <p class="login_form_heading"><fmt:message bundle="${current_locale}" key="locale.login.text"/></p>
		        <label for="userLogin" class="sr-only"><fmt:message bundle="${current_locale}" key="locale.login.login"/></label>
		        <input type="text" id="userLogin" class="form-control" name='userLogin' maxlength="200" placeholder=<fmt:message bundle="${current_locale}" key="locale.login.login"/> required autofocus>
		        <label for="userPassword" class="sr-only"><fmt:message bundle="${current_locale}" key="locale.login.password"/></label>
		        <input type="password" id="userPassword" class="form-control" name='userPassword' maxlength="200" placeholder=<fmt:message bundle="${current_locale}" key="locale.login.password"/> required>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
			        	<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.login.login.button"/></button>
		        	</div>
		        </div>
	        	<div class="login_form_register col-md-12 text-center">  
					<a href="register.jsp"><u><fmt:message bundle="${current_locale}" key="locale.register.registration"/></u></a>
    			</div>
    		</form>
    	</div>
	</div>
	<script src="js/validateLogin.js"></script>
</body>
</html>