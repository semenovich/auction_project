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
	<c:set var="lot" value="${requestScope.lot}"/>	
	<c:if test="${lot != null}">
		<p>${lot.toString()}</p>
		<c:if test="${lot.owner.equals(sessionScope.userLogin) && lot.status.toString().equals(\"CONFIRMING\")}">
			<form action="FrontController" method="POST">
				<input type="hidden" name="command" value="DELETE_CONFIRMING_LOT"/>
				<input type="hidden" name="lotId" value="${lot.id}"/>
				<input type="hidden" name="lotOwner" value="${lot.owner }">
				<div>
					<input type="submit" value='Delete'>
				</div>
			</form>
			<form action="FrontController" method="POST">
				<input type="hidden" name="command" value="EDIT_CONFIRMING_LOT"/>
				<input type="hidden" name="lotId" value="${lot.id}"/>
				<input type="hidden" name="lotOwner" value="${lot.owner }">
				<div class='form-row'>
					<label for='form_lotPicture'>Picture: </label>
					<input type='text' name='lotPicture' value='${lot.picture }'>
		  		</div>
				<div class='form-row'>
					<label for='form_lotName'>Name: </label>
		    		<input type='text' id='form_lotName' name='lotName' value='${lot.name }'>
		  		</div>
				<div class='form-row'>
					<label for='form_lotDescription'>Description: </label>
		    		<input type='text' id='form_lotDescription' name='lotDescription' value='${lot.description }'>
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
				
				<div>
					<input type="submit" value='Edit'>
				</div>
			</form>
		</c:if>
	</c:if>
</body>
</html>