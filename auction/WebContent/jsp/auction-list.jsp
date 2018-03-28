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
	<form action="FrontController" method="GET">
		<input type="hidden" name="command" value="GET_AUCTIONS_BY_SEARCHING"/>
		<input type="hidden" name="auctionsPageNumber" value="1"/>
		<input type="text" name="searchLine"/>
		<div>
			<input type="submit" value='Search'/>
		</div>
	</form>
	<c:if test="${requestScope.auctionsInfo != null}">
		<c:set var="auctions" value="${requestScope.auctionsInfo}"/>
		<p>${auctions.toString()}</p>
		<form action="FrontController" method="GET">
			<input type="hidden" name="command" value="GET_AUCTION_INFO"/>
			<input type="hidden" name="auctionId" value="${auctions.auctions.get(0).id}"/>
			<div>
				<input type="submit" value='View'>
			</div>
		</form>
		<form action="FrontController" method="GET">
			<input type="hidden" name="command" value="PAY_FOR_LOT"/>
			<input type="hidden" name="auctionId" value="${auctions.auctions.get(0).id}"/>
			<input type="hidden" name="lotId" value="${auctions.auctions.get(0).lot.id}"/>
			<div>
				<input type="submit" value='Pay'>
			</div>
		</form>
		
	</c:if>
</body>
</html>