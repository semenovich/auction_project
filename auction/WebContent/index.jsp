<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<form action="FrontController" method="GET">
		<input type="hidden" name="command" value="GET_USER_INFO"/>
		<input type="hidden" name="userLogin" value="${sessionScope.userLogin}"/>
		<div>
			<input type="submit" value='MyPage'/>
		</div>
	</form>
	<form action="FrontController" method="GET">
		<input type="hidden" name="command" value="GET_USERS_LIST"/>
		<input type="hidden" name="usersPageNumber" value="1"/>
		<div>
			<input type="submit" value='Users'/>
		</div>
	</form>
	<form action="FrontController" method="GET">
		<input type="hidden" name="command" value="GET_LOTS_LIST"/>
		<input type="hidden" name="lotsPageNumber" value="1"/>
		<div>
			<input type="submit" value='Lots'/>
		</div>
	</form>
	<form action="FrontController" method="GET">
		<input type="hidden" name="command" value="GET_AUCTIONS_LIST"/>
		<input type="hidden" name="auctionsPageNumber" value="1"/>
		<div>
			<input type="submit" value='Auctions'/>
		</div>
	</form>
	<form action="FrontController" method="GET">
		<input type="hidden" name="command" value="GET_USER_LOTS"/>
		<input type="hidden" name="userLogin" value="${sessionScope.userLogin}"/>
		<input type="hidden" name="choosenLotsPageNumber" value="1"/>
		<div>
			<input type="submit" value='MyLots'/>
		</div>
	</form>
	<form action="FrontController" method="GET">
		<input type="hidden" name="command" value="GET_USER_AUCTION_PARTICIPATIONS"/>
		<input type="hidden" name="userLogin" value="${sessionScope.userLogin}"/>
		<input type="hidden" name="choosenAuctionsPageNumber" value="1"/>
		<div>
			<input type="submit" value='MyAuctionParticipations'/>
		</div>
	</form>
	<a href="auction-create.jsp">Create auction</a>
	<a href="lot-offer.jsp">Offer Lot</a>
	<form action="FrontController" method="POST">
		<input type="hidden" name="command" value="LOGOUT"/>
		<div>
			<input type="submit" value='LogOUT'/>
		</div>
	</form>
</body>
</html>