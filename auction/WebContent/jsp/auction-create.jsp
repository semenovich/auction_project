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
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<c:if test="${sessionScope.locale == null}">
		<fmt:setLocale value="en"/>
	</c:if>
	<fmt:setBundle basename="resources/locales/locale" var="current_locale" scope="session"/>
	<title><fmt:message bundle="${current_locale}" key="locale.auction.create.auction"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<div class="container">
		<c:if test="${requestScope.isBetInvalid == true}">
			<div id="auction_bet_invalid_message">
				<p><fmt:message bundle="${current_locale}" key="locale.auction.min.bet.invalid"/></p>
			</div>
		</c:if>
		<c:if test="${requestScope.isBetInvalid != true}">
			<div id="auction_bet_invalid_message" style="visibility: hidden; display:inline;">
				<p><fmt:message bundle="${current_locale}" key="locale.auction.min.bet.invalid"/></p>
			</div>
		</c:if>
		<c:if test="${requestScope.isLotDataInvalid == true}">
			<div id="lot_create_info_invalid_message">
				<p><fmt:message bundle="${current_locale}" key="locale.lot.info.invalid"/></p>
			</div>
		</c:if>
		<c:if test="${requestScope.isLotDataInvalid != true}">
			<div id="lot_create_info_invalid_message" style="visibility: hidden; display:inline;">
				<p><fmt:message bundle="${current_locale}" key="locale.lot.info.invalid"/></p>
			</div>
		</c:if>
		<div class="col-md-6 col-md-offset-2 auction_create_panel">
			<form id="create_auction" action="FrontController" method="POST" onsubmit="validate(); return false">
				<input type="hidden" name="command" value="CREATE_AUCTION_WITH_LOT"/>
				<h2 class="auction_create_form_heading col-md-offset-2 text-center"><fmt:message bundle="${current_locale}" key="locale.auction.create.auction"/></h2>
				<div class="col-md-3 text-right">
					<label for='lotName'><fmt:message bundle="${current_locale}" key="locale.lot.name"/>:</label>
				</div>
				<div class="col-md-9">
					<input type='text' id='lotName' class="form-control" name='lotName' placeholder=<fmt:message bundle="${current_locale}" key="locale.lot.name"/> required />			
				</div>
				<div class="col-md-12">
					<label class="col-md-3 text-right" for="lot_type_select"><fmt:message bundle="${current_locale}" key="locale.lot.type"/>:</label>
					<div class="col-md-6">
						<select id="lot_type_select" class="form-control" name="lotType">
					    	<option value="CAR"><fmt:message bundle="${current_locale}" key="locale.lot.type.car"/></option>
					    	<option value="JET"><fmt:message bundle="${current_locale}" key="locale.lot.type.jet"/></option>
					    	<option value="ART"><fmt:message bundle="${current_locale}" key="locale.lot.type.art"/></option>
					    	<option value="REALTY"><fmt:message bundle="${current_locale}" key="locale.lot.type.realty"/></option>
					    	<option value="SPORT"><fmt:message bundle="${current_locale}" key="locale.lot.type.sport"/></option>
					  	</select>
					</div>
				</div>
				<div class="col-md-12">
					<label class="col-md-3 text-right" for="lot_quantity_select"><fmt:message bundle="${current_locale}" key="locale.lot.quantity"/>:</label>
					<div class="col-md-2">
						<select id="lot_quantity_select" class="form-control" name="lotQuantity">
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
					</div>
				</div>
				<div class="col-md-3 text-right">
					<label for='lotDescription'><fmt:message bundle="${current_locale}" key="locale.lot.description"/>:</label>
				</div>
				<div class="col-md-9">
					<textarea id='lotDescription' class="form-control" name='lotDescription' rows="5" placeholder=<fmt:message bundle="${current_locale}" key="locale.lot.description"/> required></textarea>				</div>
				<div class="auction_type col-md-12">
					<div class="form-group">
						<div class="col-md-12">
							<label class="col-md-3 text-right" for="auction_type_select"><fmt:message bundle="${current_locale}" key="locale.auction.type"/>:</label>
							<div class="col-md-4">
								<select id="auction_type_select" class="form-control" name="auctionType"  onchange="checkType()">
							    	<option value="ENGLISH"><fmt:message bundle="${current_locale}" key="locale.auction.type.english"/></option>
							    	<option value="ONLINE"><fmt:message bundle="${current_locale}" key="locale.auction.type.online"/></option>
							  	</select>
							 </div>
						</div>
					</div>
					<div class="form-group" id="online_type_end_time_select" style="visibility: hidden;">
						<div class="col-md-12">
							<label class="col-md-3 text-right" for="auction_online_type_end_time"><fmt:message bundle="${current_locale}" key="locale.auction.end.time"/>:</label>
							<div class="col-md-4">
								<select class="form-control" id="online_type_end_time" name="auctionEndTime">
									<option value="TEN_MINUTES"><fmt:message bundle="${current_locale}" key="locale.ten.minutes"/></option>
								  	<option value="HOUR"><fmt:message bundle="${current_locale}" key="locale.hour"/></option>
								  	<option value="DAY"><fmt:message bundle="${current_locale}" key="locale.day"/></option>
								  	<option value="WEEK"><fmt:message bundle="${current_locale}" key="locale.week"/></option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group" id="auction_min_bet">
					<div class="col-md-3 text-right ">
						<label for='auction_minimum_price'><fmt:message bundle="${current_locale}" key="locale.auction.min.bet"/>:</label>
				   	</div>
				   	<div class="col-md-5">
						<input type='text' id='auction_minimum_price' class="form-control" name='auctionMinimumPrice' placeholder=<fmt:message bundle="${current_locale}" key="locale.auction.min.bet"/> />			
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.auction.create.auction"/></button>            
				 	</div>
				</div>
			</form>
		</div>
	</div>
	<script src="js/checkAuctionType.js"></script>
	<script src="js/validateBet.js"></script>
</body>
</html>