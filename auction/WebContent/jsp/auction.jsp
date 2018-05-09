<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link rel="stylesheet" href="css/style.css"/>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<c:if test="${sessionScope.locale == null}">
		<fmt:setLocale value="en"/>
	</c:if>
	<fmt:setBundle basename="resources/locales/locale" var="current_locale" scope="session"/>
	<title><fmt:message bundle="${current_locale}" key="locale.auction"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 auction_page_name">
				<p><fmt:message bundle="${current_locale}" key="locale.auction"/></p>
			</div>
		</div>
		<div class="auction_info_panel">
			<div class="row">
				<c:if test="${requestScope.isBetInvalid != true}">
					<div id="auction_bet_invalid_message" style="display:none;">
						<p><fmt:message bundle="${current_locale}" key="locale.bet.error"/></p>
					</div>
				</c:if>
				<c:if test="${requestScope.isBetInvalid == true}">
					<div id="auction_bet_invalid_message">
						<p><fmt:message bundle="${current_locale}" key="locale.bet.error"/></p>
					</div>
				</c:if>
				<c:set var="auction" value="${requestScope.auction}"/>	
				<c:if test="${auction != null}">
					<div class="col-md-12">
						<div class="col-md-4 auction_picture">
							<img src="${auction.lot.picture }"/>
						</div>
						<div class="auction_info col-md-7 col-md-offset-1">
							<div class="text-left auction_name">
								<p>${auction.lot.name }</p>		
							</div>
							<div class="text-left auction_info_type">
								<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.type"/>:</span>
								<c:if test="${auction.type == 'ENGLISH' }">
									<span><fmt:message bundle="${current_locale}" key="locale.auction.type.english"/></span>
								</c:if>
								<c:if test="${auction.type == 'ONLINE' }">
									<span><fmt:message bundle="${current_locale}" key="locale.auction.type.online"/></span>
								</c:if>
							</div>
							<div class="text-left auction_lot">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_LOT_INFO"/>
									<input type="hidden" name="lotId" value="${auction.lot.id}"/>
									<span class="auction_info_text span3"><fmt:message bundle="${current_locale}" key="locale.lot"/>:</span>
									<button type="submit" class="btn"><a>${auction.lot.name }</a></button>
								</form>
							</div>
							<div class="text-left auction_lot_type">
								<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.type"/>:</span>
								<c:if test="${auction.lot.type == 'CAR'}">
									<span><fmt:message bundle="${current_locale}" key="locale.lot.type.car"/></span>
								</c:if>
								<c:if test="${auction.lot.type == 'JET'}">
									<span><fmt:message bundle="${current_locale}" key="locale.lot.type.jet"/></span>
								</c:if>
								<c:if test="${auction.lot.type == 'ART'}">
									<span><fmt:message bundle="${current_locale}" key="locale.lot.type.art"/></span>
								</c:if>
								<c:if test="${auction.lot.type == 'REALTY'}">
									<span><fmt:message bundle="${current_locale}" key="locale.lot.type.realty"/></span>
								</c:if>
								<c:if test="${auction.lot.type == 'SPORT'}">
									<span><fmt:message bundle="${current_locale}" key="locale.lot.type.sport"/></span>
								</c:if>
							</div>
							<div class="text-left auction_lot_quantity">
								<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.quantity"/>:</span>
								<span>${auction.lot.quantity }</span>
							</div>
							<div class="text-left auction_status">
								<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.status"/>:</span>
								<c:if test="${auction.status == 'ACTIVE' }">
									<span><fmt:message bundle="${current_locale}" key="locale.auction.status.active"/></span>
								</c:if>
								<c:if test="${auction.status == 'COMPLETED' }">
									<span><fmt:message bundle="${current_locale}" key="locale.auction.status.completed"/></span>
								</c:if>
								<c:if test="${auction.status == 'PENDING_PAYMENT' }">
									<span><fmt:message bundle="${current_locale}" key="locale.auction.status.pending.payment"/></span>
								</c:if>
							</div>
							<div class="text-left auction_start_time">
								<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.start.time"/>:</span>
								<span>${auction.startTime }</span>
							</div>
							<c:if test="${auction.type == 'ONLINE' }">
								<div class="text-left auction_end_time">
									<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.end.time"/>:</span>
									<span>${auction.endTime }</span>
								</div>
							</c:if>
							<div class="text-left auction_min_bet">
								<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.min.bet"/>:</span>
								<span>${auction.minBet.value} ${auction.minBet.currency }</span>
							</div>
							<c:if test="${not empty auction.lastBetUser }">
								<div class="text-left auction_last_bet_user">
									<c:if test="${auction.status == 'ACTIVE' }">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_USER_INFO"/>
											<input type="hidden" name="userLogin" value="${auction.lastBetUser}"/>
											<span class="auction_info_text span3"><fmt:message bundle="${current_locale}" key="locale.auction.last.bet.user"/>:</span>
											<button type="submit" class="btn"><a>${auction.lastBetUser }</a></button>
										</form>
										<div class="text-left auction_current_bet">
											<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.current.bet"/>:</span>
											<span>${auction.lastBet.value} ${auction.lastBet.currency }</span>
										</div>
									</c:if>
									<c:if test="${auction.status == 'COMPLETED' || auction.status == 'PENDING_PAYMENT' }">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_USER_INFO"/>
											<input type="hidden" name="userLogin" value="${auction.lastBetUser}"/>
											<span class="auction_info_text span3"><fmt:message bundle="${current_locale}" key="locale.auction.winner"/>:</span>
											<button type="submit" class="btn"><a>${auction.lastBetUser }</a></button>
										</form>
										<div class="text-left auction_current_bet">
											<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.last.bet"/>:</span>
											<span>${auction.lastBet.value} ${auction.lastBet.currency }</span>
										</div>
									</c:if>
									<div class="text-left auction_current_bet_time">
										<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.last.bet.time"/>:</span>
										<span>${auction.lastBetTime }</span>
									</div>
								</div>
							</c:if>
						</div>
					</div>
					<div class="col-md-12 row bet_pay_panel">
						<c:if test="${auction.status == 'ACTIVE' }">
							<form id="auction_place_bet" action="FrontController" method="POST" onsubmit="validate(); return false">
								<input type="hidden" name="command" value="PLACE_BET"/>
								<input type="hidden" name="auctionId" value="${auction.id }"/>
								<input type="hidden" name="auctionMinimumPrice" value="${auction.minBet.value }"/>
								<div class="col-md-12 text-center">
									<label for='placeBet' class="place_bet_header"><fmt:message bundle="${current_locale}" key="locale.place.bet"/></label>
					    			<p>
						    			<span class="auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.min.bet"/>:</span>
										<c:if test="${empty auction.lastBetUser }">
											<span>${auction.minBet.value} ${auction.minBet.currency }</span>
										</c:if>
										<c:if test="${not empty auction.lastBetUser }">
											<span>${auction.lastBet.value + 5} ${auction.minBet.currency }</span>
										</c:if>
					    			</p>
					    		</div>
					    		<div class="col-md-4 col-md-offset-4">
									<input type='text' class="form-control" id='placeBet' name='userBet' placeholder=<fmt:message bundle="${current_locale}" key="locale.place.bet.value"/> required autofocus />
					  			</div>
					  			<div class="col-md-4 col-md-offset-4">
									<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.place.bet"/></button>            
			    				</div>
							</form>
						</c:if>
						<c:if test="${auction.status == 'PENDING_PAYMENT' && sessionScope.userLogin == auction.lastBetUser }">
							<form action="FrontController" method="POST">
								<input type="hidden" name="command" value="PAY_FOR_LOT"/>
								<input type="hidden" name="auctionId" value="${auction.id }"/>
								<input type="hidden" name="lotId" value="${auction.lot.id }"/>
								<div class="col-md-4 col-md-offset-4">
									<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.user.pay.for.lot.button"/></button>            
			    				</div>
							</form>
						</c:if>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<script src="js/validateBet.js"></script>
</body>
</html>