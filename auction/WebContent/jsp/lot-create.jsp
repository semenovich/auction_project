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
	<c:if test="${requestScope.isLotDataInvalid == true}">
		<p>Incorrect input</p>
	</c:if>
	<form action="FrontController" method="POST">
		<input type="hidden" name="command" value="CREATE_LOT"/>
		
		<div class='form-row'>
			<label for='form_lotPicture'>Picture: </label>
			<input type='text' name='lotPicture'>
  		</div>
		<div class='form-row'>
			<label for='form_lotName'>Name: </label>
    		<input type='text' id='form_lotName' name='lotName'>
  		</div>

		<div class='form-row'>
			<label for='form_lotDescription'>Description: </label>
    		<input type='text' id='form_lotDescription' name='lotDescription'>
  		</div>
		
		<select name="lotQuantity">
		  	<option value="1">1</option>
		  	<option value="2">2</option>
		  	<option value="3">3</option>
		  	<option value="4">4</option>
		  	<option value="5">5</option>
		  	<option value="6">6</option>
		  	<option value="7">7</option>
		  	<option value="8">8</option>
		  	<option value="9">9</option>
		  	<option value="10">10</option>
		</select>
		
		<select name="lotType">
		  	<option value="CAR">Car</option>
		  	<option value="ART">Art</option>
		  	<option value="JET">Jet</option>
		  	<option value="REALTY">Realty</option>
		  	<option value="SPORT">Sport</option>
		</select>
		<div>
			<input type="submit" value='Go'>
		</div>
	</form>
</body>
</html>