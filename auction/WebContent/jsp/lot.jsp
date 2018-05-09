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
	<title><fmt:message bundle="${current_locale}" key="locale.lot"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<c:set var="lot" value="${requestScope.lot}"/>	
	<div class="container">
		<div class="row">
			<div class="col-md-12 lot_page_name">
				<p><fmt:message bundle="${current_locale}" key="locale.lot"/></p>
			</div>
		</div>
		<div class="lot_info_panel row">
			<c:if test="${lot != null}">
				<c:if test="${requestScope.isLotDataInvalid == true}">
					<div id="lot_edit_info_invalid_message">
						<p><fmt:message bundle="${current_locale}" key="locale.lot.info.invalid"/></p>
					</div>
				</c:if>
				<c:if test="${requestScope.isLotDataInvalid != true}">
					<div id="lot_edit_info_invalid_message" style="display:none;">
						<p><fmt:message bundle="${current_locale}" key="locale.lot.info.invalid"/></p>
					</div>
				</c:if>
				<c:if test="${requestScope.isBetInvalid == true}">
					<div id="auction_bet_invalid_message">
						<p><fmt:message bundle="${current_locale}" key="locale.auction.min.bet.invalid"/></p>
					</div>
				</c:if>
				<c:if test="${requestScope.isBetInvalid != true}">
					<div id="auction_bet_invalid_message" style="display:none;">
						<p><fmt:message bundle="${current_locale}" key="locale.auction.min.bet.invalid"/></p>
					</div>
				</c:if>
				<div class="col-md-12">
					<div class="lot_operation_tab">
						<ul class="nav nav-pills">
						  	<li class="active"><a data-toggle="tab" href="#lot_info"><fmt:message bundle="${current_locale}" key="locale.lot"/></a></li>
						  	<c:if test="${lot.owner == sessionScope.userLogin && (lot.status == 'CONFIRMING' || lot.status == 'READY')}">
						  		<li><a data-toggle="tab" href="#lot_edit"><fmt:message bundle="${current_locale}" key="locale.lot.edit"/></a></li>
						  	</c:if>
							<c:if test="${sessionScope.userRole == 'ADMIN' && (lot.status == 'CONFIRMING' || lot.status == 'READY')}">
						  		<li><a data-toggle="tab" href="#lot_create_auction"><fmt:message bundle="${current_locale}" key="locale.auction.create.auction"/></a></li>
						  	</c:if>
						</ul>
					</div>
				</div>
				<div class="tab-content col-md-10 col-md-offset-1">
					<div id="lot_info" class="tab-pane fade in active">
						<div class="col-md-4 lot_picture">
							<img src="${lot.picture }"/>
							<c:if test="${lot.owner == sessionScope.userLogin && (lot.status == 'CONFIRMING' || lot.status == 'READY')}">
								<form class="lot_upload_image" action="ImageUploader" method="post" enctype="multipart/form-data" >
							    	<input type="hidden" name="command" value="lot"/>
							        <div class="form-group">
							        	<input type="file" accept=".jpg,.jpeg" class="form-control-file" id="choose" name="image"/>
							            <div class="col-md-4">
											<button class="btn-md" type="submit"><fmt:message bundle="${current_locale}" key="locale.change"/></button>            
								    	</div>
							        </div>
							        <input type="hidden" name="lotId" value="${lot.id }">
							    </form>
						    </c:if>
						</div>
						<div class="col-md-7 col-md-offset-1 text-left lot_name">
							<p class="col-md-12">${lot.name }</p>
						</div>
						<div class="col-md-7 col-md-offset-1 text-left lot_type">
							<span class="lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.type"/>:</span>
							<c:if test="${lot.type == 'CAR'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.type.car"/></span>
							</c:if>
							<c:if test="${lot.type == 'JET'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.type.jet"/></span>
							</c:if>
							<c:if test="${lot.type == 'ART'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.type.art"/></span>
							</c:if>
							<c:if test="${lot.type == 'REALTY'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.type.realty"/></span>
							</c:if>
							<c:if test="${lot.type == 'SPORT'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.type.sport"/></span>
							</c:if>
						</div>
						<div class="col-md-7 col-md-offset-1 text-left lot_status">
							<span class="lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.status"/>:</span>
							<c:if test="${lot.status == 'BLOCKED'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.status.blocked"/></span>	
							</c:if>
							<c:if test="${lot.status == 'ACTIVE'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.status.active"/></span>	
							</c:if>
							<c:if test="${lot.status == 'SOLED'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.status.soled"/></span>	
							</c:if>
							<c:if test="${lot.status == 'CONFIRMING'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.status.confirming"/></span>	
							</c:if>
							<c:if test="${lot.status == 'READY'}">
								<span><fmt:message bundle="${current_locale}" key="locale.lot.status.ready"/></span>	
							</c:if>
						</div>
						<div class="col-md-7 col-md-offset-1 text-left lot_quantity">
							<span class="lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.quantity"/>:</span>
							<span>${lot.quantity }</span>
						</div>
						<div class="col-md-7 col-md-offset-1 text-left lot_description">
							<span class="lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.description"/>:</span>
							<span>${lot.description }</span>
						</div>
						<c:if test="${lot.status != 'SOLED'}">
							<div class="col-md-7 col-md-offset-1 text-left lot_owner">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_USER_INFO"/>
									<input type="hidden" name="userLogin" value="${lot.owner}"/>
									<span class="lot_info_text span2"><fmt:message bundle="${current_locale}" key="locale.lot.owner"/>:</span>
									<button type="submit" class="btn"><a>${lot.owner }</a></button>
								</form>
							</div>
						</c:if>
						<div class="col-md-7 col-md-offset-1 text-left lot_operations">
							<c:if test="${sessionScope.userRole == 'ADMIN' && lot.status == 'CONFIRMING'}">
								<form action="FrontController" method="POST">
									<input type="hidden" name="command" value="BLOCK_LOT"/>
									<input type="hidden" name="lotId" value="${lot.id}"/>
									<div class="col-md-4">
										<button class="btn btn-md btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.admin.block"/></button>            
							    	</div>
								</form>
							</c:if>
							<c:if test="${sessionScope.userRole == 'ADMIN' && lot.status == 'BLOCKED'}">
								<form action="FrontController" method="POST">
									<input type="hidden" name="command" value="UNBLOCK_LOT"/>
									<input type="hidden" name="lotId" value="${lot.id}"/>
									<div class="col-md-4">
										<button class="btn btn-md btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.admin.unblock"/></button>            
							    	</div>
								</form> 
							</c:if>
							<c:if test="${lot.owner == sessionScope.userLogin && (lot.status == 'CONFIRMING' || lot.status == 'READY')}">
								<form action="FrontController" method="POST">
									<input type="hidden" name="command" value="DELETE_WAITING_LOT"/>
									<input type="hidden" name="lotId" value="${lot.id}"/>
									<input type="hidden" name="lotOwner" value="${lot.owner }">
									<div class="col-md-4">
										<button class="btn btn-md btn-danger btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.lot.delete"/></button>            
							    	</div>
								</form>
							</c:if>
						</div>
					</div>
					<c:if test="${lot.owner == sessionScope.userLogin && (lot.status == 'CONFIRMING' || lot.status == 'READY')}">
						<div id="lot_edit" class="tab-pane fade">
							<div class="col-md-10">
								<form action="FrontController" method="POST">
									<input type="hidden" name="command" value="EDIT_WAITING_LOT"/>
									<input type="hidden" name="lotId" value="${lot.id}"/>
									<input type="hidden" name="lotOwner" value="${lot.owner }">
									<input type="hidden" name="lotQuantity" value="${lot.quantity }"/>
									<div class="col-md-3 text-right">
										<label for='lotDescription'><fmt:message bundle="${current_locale}" key="locale.lot.description"/>:</label>
							    	</div>
							    	<div class="col-md-9">
										<textarea id='lotDescription' class="form-control" name='lotDescription' rows="5" placeholder=<fmt:message bundle="${current_locale}" key="locale.lot.description"/> required>${lot.description }</textarea>	
									</div>
									<div class="row">
										<div class="col-md-4 col-md-offset-5">
											<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.lot.edit"/></button>            
							    		</div>
							    	</div>
								</form>
							</div>
						</div>
					</c:if>
					<c:if test="${sessionScope.userRole == 'ADMIN' && (lot.status == 'CONFIRMING' || lot.status == 'READY')}">	
						<div id="lot_create_auction" class="tab-pane fade">
							<div class="col-md-10 col-md-offset-1">
								<form id="lot_create_auction" action="FrontController" method="POST" onsubmit="validate(); return false">
									<input type="hidden" name="command" value="CREATE_AUCTION_FROM_EXISTING_LOT"/>
									<input type="hidden" name="lotId" value="${lot.id}"/>
									<div class="auction_type col-md-12">
										<div class="form-group">
											<div class="col-md-12">
												<label class="col-md-5 select_label text-right" for="auction_type_select"><fmt:message bundle="${current_locale}" key="locale.auction.type"/>:</label>
												<div class="col-md-4">
													<select id="auction_type_select" class="form-control" name="auctionType"  onchange="checkType()">
												    	<option value="ENGLISH"><fmt:message bundle="${current_locale}" key="locale.auction.type.english"/></option>
												    	<option value="ONLINE"><fmt:message bundle="${current_locale}" key="locale.auction.type.online"/></option>
												  	</select>
												 </div>
											</div>
										</div>
										<div class="form-group" id="online_type_end_time_select" style="display: none;">
											<div class="col-md-12 online_type_end_time_select">
												<label class="col-md-5 select_label text-right" for="auction_online_type_end_time"><fmt:message bundle="${current_locale}" key="locale.auction.end.time"/>:</label>
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
										<div class="col-md-5 text-right ">
											<label for='lot_auction_minimum_price'><fmt:message bundle="${current_locale}" key="locale.auction.min.bet"/>:</label>
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
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script src="js/checkAuctionType.js"></script>
	<script src="js/validateBet.js"></script>
</body>
</html>