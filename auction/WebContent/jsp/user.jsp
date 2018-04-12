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
	<title><fmt:message bundle="${current_locale}" key="locale.user.profile"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 user_page_name">
				<p><fmt:message bundle="${current_locale}" key="locale.user.profile"/></p>
			</div>
		</div>
		<c:if test="${requestScope.userLogin == sessionScope.userLogin}">
			<ul class="nav nav-pills">
				<li class="active">
	          		<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_USER_INFO"/>
						<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.header.profile"/></button>
					</form>
	           	</li>
		           <li>
		           	<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_USER_LOTS"/>
						<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
						<input type="hidden" name="choosenLotsPageNumber" value="1"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.user.lots.button"/></button>
					</form>
		           </li>
		           <li>
		           	<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_USER_WIN_LOTS"/>
						<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
						<input type="hidden" name="choosenLotsPageNumber" value="1"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.user.win.lots"/></button>
					</form>
		           </li>
		           <li>
					<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_USER_AUCTION_PARTICIPATIONS"/>
						<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
						<input type="hidden" name="choosenAuctionsPageNumber" value="1"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.user.participations.button"/></button>
					</form>
				</li>
			</ul>
		</c:if>
		<div class="user_info_panel">
			<c:if test="${requestScope.user != null}">
				<c:if test="${requestScope.isUserDataInvalid == true}">
					<div id="user_edit_info_invalid_message">
						<p><fmt:message bundle="${current_locale}" key="locale.user.edit.invalid.data"/></p>
					</div>
				</c:if>
				<c:if test="${requestScope.isUserDataInvalid != true}">
					<div id="user_edit_info_invalid_message" style="visibility: hidden; display:inline;">
						<p><fmt:message bundle="${current_locale}" key="locale.user.edit.invalid.data"/></p>
					</div>
				</c:if>
				<c:set var="user" value="${requestScope.user}"/>
				<div class="tab-content">
					<div id="user_info" class="tab-pane fade in active">
						<div class="user_picture col-md-4">
							<img alt="" src="${user.picture}"/>
							<c:if test="${requestScope.userLogin == sessionScope.userLogin}">		
								<form class="user_upload_image" action="ImageUploader" method="post" enctype="multipart/form-data" >
						            <input type="hidden" name="command" value="user"/>
						            <div class="form-group">
						                <input type="file" accept=".jpg,.jpeg" class="form-control-file" id="choose" name="image"/>
						                <div class="col-md-4">
											<button class="btn-success btn-md" type="submit"><fmt:message bundle="${current_locale}" key="locale.change"/></button>            
							    		</div>
						            </div>
						            <input type="hidden" name="userLogin" value="${requestScope.userLogin }">
						        </form>
					        </c:if>
						</div>
						<div class="col-md-7 col-md-offset-1 text-left user_login">
							<h1>${user.login}</h1>
							<c:if test="${sessionScope.userRole == 'ADMIN' && user.role != 'ADMIN'}">
								<c:if test="${!user.blocked}">
									<form action="FrontController" method="POST">
										<input type="hidden" name="command" value="BLOCK_USER"/>
								    	<input type="hidden" name="userLogin" value="${user.login}"/>
								    	<div class="col-md-3">
										<button class="btn btn-md btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.lot.delete"/></button>            
								    	</div>
								    </form>
								</c:if>
								<c:if test="${user.blocked}">
									<form action="FrontController" method="POST">
										<input type="hidden" name="command" value="UNBLOCK_USER"/>
								    	<input type="hidden" name="userLogin" value="${user.login}"/>
								    	<div class="col-md-3">
											<button class="btn btn-md btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.lot.delete"/></button>            
								    	</div>	
									</form>
								</c:if>
							</c:if>
						</div>
						<div class="col-md-7 col-md-offset-1 text-left user_surname">
							<span class="user_info_text"><fmt:message bundle="${current_locale}" key="locale.user.surname"/>:</span>
							<span>${user.surname}</span>	
						</div>
						<div class="col-md-7 col-md-offset-1 text-left user_name">
							<span class="user_info_text"><fmt:message bundle="${current_locale}" key="locale.user.name"/>:</span>
							<span>${user.name}</span>	
						</div>
						<div class="col-md-7 col-md-offset-1 text-left user_email">
							<span class="user_info_text"><fmt:message bundle="${current_locale}" key="locale.user.email"/>:</span>
							<span>${user.email}</span>	
						</div>
						<div class="col-md-7 col-md-offset-1 text-left user_phone">
							<span class="user_info_text"><fmt:message bundle="${current_locale}" key="locale.user.phone"/>:</span>
							<span>${user.phone}</span>	
						</div>
						<div class="col-md-7 col-md-offset-1 text-left user_country">
							<span class="user_info_text"><fmt:message bundle="${current_locale}" key="locale.user.country"/>:</span>
							<c:if test="${user.country == 'Belarus'}">
								<span><fmt:message bundle="${current_locale}" key="locale.belarus"/></span>
							</c:if>
							<c:if test="${user.country == 'Russia'}">
								<span><fmt:message bundle="${current_locale}" key="locale.russia"/></span>
							</c:if>
							<c:if test="${user.country == 'USA'}">
								<span><fmt:message bundle="${current_locale}" key="locale.usa"/></span>
							</c:if>	
						</div>
						
					</div>
					<c:if test="${requestScope.userLogin == sessionScope.userLogin}">		
						<div id="user_edit" class="tab-pane fade">
							<div class="col-md-6 col-md-offset-2">
								<form class="edit_form" action="FrontController" method="POST" onsubmit="validate(); return false">
									<input type="hidden" name="command" value="EDIT_USER_INFO"/>
								    <input type="hidden" name="userLogin" value="${user.login}"/>
								    <h2 class="edit_form_heading"><fmt:message bundle="${current_locale}" key="locale.user.edit"/></h2>
									<div class="col-md-4 text-right">
										<label for='userPassword'><fmt:message bundle="${current_locale}" key="locale.user.password"/>:</label>
						    		</div>
						    		<div class="col-md-8">
										<input type='password' class="form-control" id='userPassword' name='userPassword' value="${user.password}" placeholder=<fmt:message bundle="${current_locale}" key="locale.user.password"/> />	
									</div>
									<div class="col-md-4 text-right">
										<label for='userSurname'><fmt:message bundle="${current_locale}" key="locale.user.surname"/>: </label>
						    		</div>
						    		<div class="col-md-8">
										<input type='text' id='userSurname' class="form-control" name='userSurname' value="${user.surname}" placeholder=<fmt:message bundle="${current_locale}" key="locale.user.surname"/> required />
									</div>
									<div class="col-md-4 text-right">
										<label for='userName'><fmt:message bundle="${current_locale}" key="locale.user.name"/>: </label>
						    		</div>
						    		<div class="col-md-8">
										<input type='text' id='userName' class="form-control" name='userName' value="${user.name}" placeholder=<fmt:message bundle="${current_locale}" key="locale.user.name"/> required />
									</div>
									<div class="col-md-4 text-right">
										<label for='userEmail'><fmt:message bundle="${current_locale}" key="locale.user.email"/>:</label>
						    		</div>
						    		<div class="col-md-8">
										<input type='text' id='userEmail' class="form-control" name='userEmail' value="${user.email}" placeholder=<fmt:message bundle="${current_locale}" key="locale.user.email"/> required />			
									</div>
									<div class="col-md-4 text-right">
										<label for='userPhone'><fmt:message bundle="${current_locale}" key="locale.user.phone"/>: </label>
						    		</div>
						    		<div class="col-md-8">
										<input type='text' id='userPhone' class="form-control" name='userPhone' value="${user.phone}" placeholder=<fmt:message bundle="${current_locale}" key="locale.user.phone"/> required />
									</div>
									<div class="col-md-4 text-right">
										<label for='userPassportId'><fmt:message bundle="${current_locale}" key="locale.user.passport.id"/>: </label>
							    	</div>
							    	<div class="col-md-8">
										<input type='text' id='userPassportId' class="form-control" name='userPassportId' value="${user.passportId}" placeholder=<fmt:message bundle="${current_locale}" key="locale.user.passport.id"/> required />
									</div>
									<div class="col-md-4 text-right">
										<label for='userIssuedBy'><fmt:message bundle="${current_locale}" key="locale.user.passport.issued.by"/>: </label>
						    		</div>
						    		<div class="col-md-8">
										<input type='text' id='userIssuedBy' class="form-control" name='userPassportIssuedBy' value="${user.passportIssuedBy}" placeholder=<fmt:message bundle="${current_locale}" key="locale.user.passport.issued.by"/> required />
									</div>
									<div class="row">
										<div class="col-md-4 col-md-offset-5">
											<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${current_locale}" key="locale.user.edit"/></button>            
						    			</div>
						    		</div>
					    		</form>
					    	</div>
						</div>
					</c:if>
				</div>
				<div class="col-md-12">
					<div class="user_edit_tab">
						<ul class="nav nav-pills">
						  	<li class="active"><a data-toggle="tab" href="#user_info"><fmt:message bundle="${current_locale}" key="locale.user.profile"/></a></li>
						  	<c:if test="${requestScope.userLogin == sessionScope.userLogin}">
						  		<li><a data-toggle="tab" href="#user_edit"><fmt:message bundle="${current_locale}" key="locale.user.edit"/></a></li>
						  	</c:if>
						</ul>
					</div>
				</div>
			</c:if>
			<c:if test="${requestScope.userLotsInfo != null}">
				<c:set var="lots" value="${requestScope.userLotsInfo}"/>
				<p class="user_lots_text"><fmt:message bundle="${current_locale}" key="locale.user.lots.button"/></p>	
				<c:if test="${not empty lots.lots}">
					<c:forEach items="${lots.lots}" var="current">
						<div class="row col-md-12">
							<form action="FrontController" method="GET">
								<input type="hidden" name="command" value="GET_LOT_INFO"/>
								<input type="hidden" name="lotId" value="${current.id}"/>
								<button type="submit" class="btn col-md-8 col-md-offset-2">
									<div class="user_lot_img col-md-4">
										<img src="${current.picture }"/>
									</div>
									<div id="user_lot_info" class="col-md-7 col-md-offset-1 text-left">
										<div class="user_lot_name">
											<span><h1>${current.name }</h1></span>
										</div>
										<div class="user_lot_type">
											<span class="user_lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.type"/>:</span>
											<c:if test="${current.type == 'CAR'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.car"/></span>
											</c:if>
											<c:if test="${current.type == 'JET'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.jet"/></span>
											</c:if>
											<c:if test="${current.type == 'ART'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.art"/></span>
											</c:if>
											<c:if test="${current.type == 'REALTY'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.realty"/></span>
											</c:if>
											<c:if test="${current.type == 'SPORT'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.sport"/></span>
											</c:if>
										</div>
										<div class="user_lot_quantity">
											<span class="user_lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.quantity"/>:</span>
											<span>${current.quantity }</span>
										</div>
										<div class="user_lot_status">
											<span class="user_lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.status"/>:</span>
											<c:if test="${current.status == 'BLOCKED'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.status.blocked"/></span>	
											</c:if>
											<c:if test="${current.status == 'ACTIVE'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.status.active"/></span>	
											</c:if>
											<c:if test="${current.status == 'SOLED'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.status.soled"/></span>	
											</c:if>
											<c:if test="${current.status == 'CONFIRMING'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.status.confirming"/></span>	
											</c:if>
											<c:if test="${current.status == 'READY'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.status.ready"/></span>	
											</c:if>
										</div>
									</div>
								</button>
							</form>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty lots.lots}">
					<div class="user_not_found">
						<p><fmt:message bundle="${current_locale}" key="locale.not.found"/></p>
					</div>
				</c:if>
				<div class="col-md-12">
					<div class="user_lots_navigation">
						<c:if test="${lots.currentPage > 1 }">
							<div class="previous">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_USER_LOTS"/>
									<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
									<input type="hidden" name="choosenLotsPageNumber" value="${lots.currentPage - 1 }"/>
									<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
								</form>
							</div>
						</c:if>
						<div class="current">
							<p>${lots.currentPage }</p>
						</div>
						<c:if test="${lots.currentPage < lots.pages}">
							<div class="next">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_USER_LOTS"/>
									<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
									<input type="hidden" name="choosenLotsPageNumber" value="${lots.currentPage + 1 }"/>
									<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
								</form>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>
			<c:if test="${requestScope.userWinLotsInfo != null}">
				<c:set var="lots" value="${requestScope.userWinLotsInfo}"/>
				<p class="user_lots_text"><fmt:message bundle="${current_locale}" key="locale.user.win.lots"/></p>
				<c:if test="${not empty lots.lots}">
					<c:forEach items="${lots.lots}" var="current">
						<div class="row col-md-12">
							<form action="FrontController" method="GET">
								<input type="hidden" name="command" value="GET_LOT_INFO"/>
								<input type="hidden" name="lotId" value="${current.id}"/>
								<button type="submit" class="btn col-md-8 col-md-offset-2">
									<div class="user_lot_img col-md-4">
										<img src="${current.picture }"/>
									</div>
									<div id="user_lot_info" class="col-md-7 col-md-offset-1 text-left">
										<div class="user_lot_name">
											<span><h1>${current.name }</h1></span>
										</div>
										<div class="user_lot_type">
											<span class="user_lot_info_text"></span>
											<c:if test="${current.type == 'CAR'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.car"/></span>
											</c:if>
											<c:if test="${current.type == 'JET'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.jet"/></span>
											</c:if>
											<c:if test="${current.type == 'ART'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.art"/></span>
											</c:if>
											<c:if test="${current.type == 'REALTY'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.realty"/></span>
											</c:if>
											<c:if test="${current.type == 'SPORT'}">
												<span><fmt:message bundle="${current_locale}" key="locale.lot.type.sport"/></span>
											</c:if>
										</div>
										<div class="user_lot_quantity">
											<span class="user_lot_info_text"></span>
											<span>${current.quantity }</span>
										</div>
									</div>
								</button>
							</form>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty lots.lots}">
					<div class="user_not_found">
						<p><fmt:message bundle="${current_locale}" key="locale.not.found"/></p>
					</div>
				</c:if>
				<div class="col-md-12">
					<div class="user_lots_navigation">
						<c:if test="${lots.currentPage > 1 }">
							<div class="previous">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_USER_LOTS"/>
									<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
									<input type="hidden" name="choosenLotsPageNumber" value="${lots.currentPage - 1 }"/>
									<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
								</form>
							</div>
						</c:if>
						<div class="current">
							<p>${lots.currentPage }</p>
						</div>
						<c:if test="${lots.currentPage < lots.pages}">
							<div class="next">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_USER_LOTS"/>
									<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
									<input type="hidden" name="choosenLotsPageNumber" value="${lots.currentPage + 1 }"/>
									<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
								</form>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>
			<c:if test="${requestScope.userAuctionParticipationsInfo != null}">
				<c:set var="auctions" value="${requestScope.userAuctionParticipationsInfo}"/>
					<c:if test="${not empty auctions.auctions}">
						<c:forEach items="${auctions.auctions}" var="current">
							<form action="FrontController" method="GET">
								<input type="hidden" name="command" value="GET_AUCTION_INFO"/>
								<input type="hidden" name="auctionId" value="${current.id}"/>
								<button type="submit" class="btn btn col-md-8 col-md-offset-2">
									<div class="user_auction_img col-md-4">
										<img src="${current.lot.picture }"/>
									</div>
									<div id="user_auction_info" class="col-md-7 col-md-offset-1 text-left">
										<div class="user_auction_name">
											<span><h1>${current.lot.name }</h1></span>
										</div>
										<div class="user_auction_start_time">
											<span class="user_auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.start.time"/>:</span>
											<span>${current.startTime}</span>
										</div>
										<c:if test="${current.status == 'PENDING_PAYMENT' || current.status == 'COMPLETED'}">
											<div class="user_auction_end_time">
												<span class="user_auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.end.time"/>:</span>
												<c:if test="${current.type == 'ONLINE' || current.status == 'COMPLETED' || current.status == 'PENDING_PAYMENT'}">
													<span>${current.endTime}</span>
												</c:if>
											</div>
										</c:if>
										<div class="user_auction_type">
											<span class="user_auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.type"/>:</span>
											<c:if test="${current.type == 'ENGLISH'}">
												<span><fmt:message bundle="${current_locale}" key="locale.auction.type.english"/></span>
											</c:if>
											<c:if test="${current.type == 'ONLINE'}">
												<span><fmt:message bundle="${current_locale}" key="locale.auction.type.online"/></span>
											</c:if>
										</div>
										<div class="user_auction_status">
											<span class="user_auction_info_text"></span>
											<c:if test="${current.status == 'COMPLETED'}">
												<span><fmt:message bundle="${current_locale}" key="locale.auction.status.completed"/></span>
												<div class="user_auction_winner">
													<span class="user_auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.winner"/>:</span>
													<span>${current.lastBetUser }</span>
												</div>	
											</c:if>
											<c:if test="${current.status == 'ACTIVE'}">
												<span><fmt:message bundle="${current_locale}" key="locale.auction.status.active"/></span>	
											</c:if>
											<c:if test="${current.status == 'PENDING_PAYMENT'}">
												<span><fmt:message bundle="${current_locale}" key="locale.auction.status.pending.payment"/></span>	
												<div class="user_auction_winner">
													<span class="user_auction_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.winner"/>:</span>
													<span>${current.lastBetUser }</span>
												</div>
											</c:if>
										</div>
									</div>
								</button>
							</form>
						</c:forEach>
					</c:if>
					<c:if test="${empty auctions.auctions}">
						<div class="user_not_found">
							<p><fmt:message bundle="${current_locale}" key="locale.not.found"/></p>
						</div>
					</c:if>
					<div class="col-md-12">
						<div class="user_participations_navigation">
							<c:if test="${auctions.currentPage > 1 }">
								<div class="previous">
									<form action="FrontController" method="GET">
										<input type="hidden" name="command" value="GET_USER_LOTS"/>
										<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
										<input type="hidden" name="choosenLotsPageNumber" value="${lots.currentPage - 1 }"/>
										<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
									</form>
								</div>
							</c:if>
							<div class="current">
								<p>${auctions.currentPage }</p>
							</div>
							<c:if test="${auctions.currentPage < lots.pages}">
								<div class="next">
									<form action="FrontController" method="GET">
										<input type="hidden" name="command" value="GET_USER_LOTS"/>
										<input type="hidden" name="userLogin" value="${requestScope.userLogin}"/>
										<input type="hidden" name="choosenLotsPageNumber" value="${lots.currentPage + 1 }"/>
										<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
									</form>
								</div>
							</c:if>
						</div>
					</div>
			</c:if>
		</div>
	</div>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
</body>
</html>