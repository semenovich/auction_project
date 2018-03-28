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
	<c:if test="${requestScope.user != null}">
		<c:set var="user" value="${requestScope.user}"/>
		<p>${user.toString()}</p>
		<c:if test="${requestScope.owner}">
				<c:if test="${requestScope.isUserDataInvalid == true}">
					<p>Invalid user data</p>
				</c:if>
				<form action="FrontController" method="POST">
					<input type="hidden" name="command" value="EDIT_USER_INFO"/>
					<div class='form-row'>
			    		<input type='hidden' name='userLogin' value="${user.login}">
			  		</div>
			  		<div class='form-row'>
						<label for='form_userPicture'>Picture: </label>
						<input type='text' name='userPicture' value="${user.picture}">
  					</div>
			  		<div class='form-row'>
			    		<label for='form_userPassword'>Password: </label>
			    		<input type='password' id='form_userPassword' name='userPassword' value="${user.password}">
					</div>
			
					<div class='form-row'>
						<label for='form_userEmail'>Email: </label>
			    		<input type='text' id='form_userEmail' name='userEmail' value="${user.email}">
			  		</div>
					
					<div class='form-row'>
						<label for='form_userSurname'>Surname: </label>
			    		<input type='text' id='form_userSurname' name='userSurname' value="${user.surname }">
			  		</div>
					<div class='form-row'>
						<label for='form_userName'>Name: </label>
			    		<input type='text' id='form_userName' name='userName' value="${user.name}">
			  		</div>
					<div class='form-row'>
						<label for='form_userPhone'>Phone: </label>
			    		<input type='text' id='form_userPhone' name='userPhone' value="${user.phone}">
			  		</div>
					<div class='form-row'>
						<label for='form_userPassportId'>Passport id: </label>
			    		<input type='text' id='form_userPassportId' name='userPassportId' value="${user.passportId}">
			  		</div>
					<div class='form-row'>
						<label for='form_userIssuedBy'>Issued by: </label>
			    		<input type='text' id='form_userIssuedBy' name='userPassportIssuedBy' value="${user.passportIssuedBy}">
			  		</div>
			  		<div class='form-row'>
			    		<input type='hidden' name='userCountry' value="${user.country}">
			  		</div>
					<div>
						<input type="submit" value='Edit'>
					</div>
				</form>
		</c:if>
	</c:if>
	<c:if test="${requestScope.userLotsInfo != null}">
		<c:set var="lots" value="${requestScope.userLotsInfo}"/>
		<p>${lots.toString()}</p>
		<form action="FrontController" method="GET">
			<input type="hidden" name="command" value="GET_LOT_INFO"/>
			<input type="hidden" name="lotId" value="${lots.lots.get(0).id}"/>
			<div>
				<input type="submit" value='View lot'>
			</div>
		</form>
		<form action="FrontController" method="POST">
			<input type="hidden" name="command" value="CREATE_AUCTION_FROM_EXISTING_LOT"/>
			<input type="hidden" name="lotId" value="${lots.lots.get(0).id}"/>
			<div class='form-row'>
				<label for='form_auctionMinimumPrice'>Min price: </label>
				<input type='text' name='auctionMinimumPrice'/>
		  	</div>
			<select name="auctionEndTime">
			  	<option value=""></option>
			  	<option value="HOUR">Hour</option>
			  	<option value="DAY">Day</option>
			  	<option value="WEEK">Week</option>
			</select>
			<select name="auctionType">
			  	<option value="ONLINE">Online</option>
			  	<option value="ENGLISH">English</option>
			</select>
			<div>
				<input type="submit" value='Create auction'>
			</div>
		</form>
	</c:if>
	<c:if test="${requestScope.userAuctionParticipationsInfo != null}">
		<c:set var="auctions" value="${requestScope.userAuctionParticipationsInfo}"/>
		<p>${auctions.toString()}</p>
	</c:if>
</body>
</html>