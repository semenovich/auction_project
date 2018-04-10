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
	<c:set var="auction" value="${requestScope.auction}"/>	
	<c:if test="${auction != null}">
		<p>${auction.toString()}</p>
		
		<form action="FrontController" method="POST">
			<input type="hidden" name="command" value="PLACE_BET"/>
			<input type="hidden" name="auctionId" value="${auction.id }"/>
			<input type="hidden" name="auctionMinimumPrice" value="${auction.minBet.value }"/>
			
	
	  		<div class='form-row'>
	    		<label for='form_userPassword'>Bet: </label>
	    		<input type='text' id='form_userPassword' name='userBet'>
			</div>
	
			<div>
				<input type="submit" value='Bet'>
			</div>
		</form>
	</c:if>
</body>
</html>