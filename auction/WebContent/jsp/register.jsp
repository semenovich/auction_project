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
	<title><fmt:message bundle="${current_locale}" key="locale.register.registration"/></title>
</head>
<body>
	<c:if test="${requestScope.isUserExists}">
		<div id="register_user_existance_text">
			<p><fmt:message bundle="${current_locale}" key="locale.register.user.exists"/></p>
		</div>
	</c:if>
	<c:if test="${requestScope.isUserDataInvalid}">
		<div id="register_data_invalid_text">
			<p><fmt:message bundle="${current_locale}" key="locale.register.data.invalid"/></p>
		</div>
	</c:if>
	<c:if test="${!requestScope.isUserDataInvalid}">
		<div id="register_data_invalid_text" style="display: none;">
			<p><fmt:message bundle="${current_locale}" key="locale.register.data.invalid"/></p>
		</div>
	</c:if>
	<div class="container">
		<div class="register_language col-md-2 col-md-offset-5">
			<form action="FrontController">
		        <input type="hidden" name="command" value="CHANGE_LOCALE"/>
				<select id="language" class="form-control" name="locale" onchange="submit()">
			        <option value="en"  ${locale == 'en' ? 'selected' : ''}><fmt:message bundle="${current_locale}" key="locale.change.locale.name.en"/></option>
			    	<option value="ru" ${locale == 'ru' ? 'selected' : ''}><fmt:message bundle="${current_locale}" key="locale.change.locale.name.ru"/></option>
				</select>
			</form>
		</div>
		<div class="col-md-6 col-md-offset-3 register_panel">
	      	<form class="register_form" action="FrontController" method="POST" onsubmit="validate(); return false">
				<input type="hidden" name="command" value="REGISTER"/>
			    <h2 class="register_form_heading text-center"><fmt:message bundle="${current_locale}" key="locale.register.registration"/></h2>
				<div class="col-md-4 text-right">
					<label for='userLogin'><fmt:message bundle="${current_locale}" key="locale.register.login"/>:</label>
	    		</div>
	    		<div class="col-md-8">
					<input type='text' class="form-control" id='userLogin' name='userLogin' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.login"/> required autofocus />
	  			</div>
	  			<div class="col-md-4 text-right">
					<label for='userPassword'><fmt:message bundle="${current_locale}" key="locale.register.password"/>:</label>
	    		</div>
	    		<div class="col-md-8">
					<input type='password' class="form-control" id='userPassword' name='userPassword' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.password"/> required />	
				</div>
				<div class="col-md-4 text-right">
					<label for='userSurname'><fmt:message bundle="${current_locale}" key="locale.register.surname"/>: </label>
	    		</div>
	    		<div class="col-md-8">
					<input type='text' id='userSurname' class="form-control" name='userSurname' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.surname"/> required />
				</div>
				<div class="col-md-4 text-right">
					<label for='userName'><fmt:message bundle="${current_locale}" key="locale.register.name"/>: </label>
	    		</div>
	    		<div class="col-md-8">
					<input type='text' id='userName' class="form-control" name='userName' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.name"/> required />
				</div>
				<div class="col-md-4 text-right">
					<label for='userEmail'><fmt:message bundle="${current_locale}" key="locale.register.email"/>:</label>
	    		</div>
	    		<div class="col-md-8">
					<input type='text' id='userEmail' class="form-control" name='userEmail' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.email"/> required />			
				</div>
				<div class="col-md-4 text-right">
					<label for='userPhone'><fmt:message bundle="${current_locale}" key="locale.register.phone"/>: </label>
	    		</div>
	    		<div class="col-md-8">
					<input type='text' id='userPhone' class="form-control" name='userPhone' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.phone"/> required />
				</div>
				<div class="col-md-4 text-right">
					<label for='userPassportId'><fmt:message bundle="${current_locale}" key="locale.register.passport.id"/>: </label>
		    	</div>
		    	<div class="col-md-8">
					<input type='text' id='userPassportId' class="form-control" name='userPassportId' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.passport.id"/> required />
				</div>
				<div class="col-md-4 text-right">
					<label for='userIssuedBy'><fmt:message bundle="${current_locale}" key="locale.register.passport.issued.by"/>: </label>
	    		</div>
	    		<div class="col-md-8">
					<input type='text' id='userIssuedBy' class="form-control" name='userPassportIssuedBy' placeholder=<fmt:message bundle="${current_locale}" key="locale.register.passport.issued.by"/> required />
				</div>
				<div class="col-md-4 text-right">
					<label for='userCountry'><fmt:message bundle="${current_locale}" key="locale.register.country"/>: </label>
	    		</div>
	    		<div class="col-md-8 text-right">
					<select id='userCountry' class="form-control" name="userCountry">
					  	<option value="Belarus"><fmt:message bundle="${current_locale}" key="locale.belarus"/></option>
					  	<option value="Russia"><fmt:message bundle="${current_locale}" key="locale.russia"/></option>
					  	<option value="USA"><fmt:message bundle="${current_locale}" key="locale.usa"/></option>
					</select>
				</div>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.register.register.button"/></button>            
	    			</div>
	    		</div>
    		</form>
    		<div class="register_form_login text-center">
    			<a href="login.jsp"><u><fmt:message bundle="${current_locale}" key="locale.register.login.button"/></u></a>
    		</div>
    	</div>
	</div>
	<script src="js/validateRegistration.js"></script>
</body>
</html>