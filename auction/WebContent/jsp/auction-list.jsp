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
	<title><fmt:message bundle="${current_locale}" key="locale.auctions"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 auctions_list_page_name">
				<p><fmt:message bundle="${current_locale}" key="locale.auctions"/></p>
			</div>
		</div>
		<div id="search-input">
            <div class="input-group col-md-10 col-md-offset-1">
	            <form action="FrontController" method="GET">
					<input type="hidden" name="command" value="GET_AUCTIONS_BY_SEARCHING"/>
					<input type="hidden" name="auctionsPageNumber" value="1"/>
					<span>
						<input type="text" class="form-control input-lg" name="searchLine" placeholder=<fmt:message bundle="${current_locale}" key="locale.list.search"/> />
						<button class="btn btn-info btn-lg" type="submit">
	                    	<i class="glyphicon glyphicon-search"></i>
	                	</button>
					</span>
				</form>
        	</div>
        </div>
        <div class="col-md-10 col-md-offset-1 auctions_list_type_chooser">
        	<ul class="nav navbar-nav">
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_AUCTIONS_LIST"/>
							<input type="hidden" name="auctionsPageNumber" value="1"/>
							<button type="submit" class="btn">
								<p><fmt:message bundle="${current_locale}" key="locale.main.all.auctions"/></p>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
							<input type="hidden" name="auctionsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="CAR"/>
							<button type="submit" class="btn">
								<p><fmt:message bundle="${current_locale}" key="locale.main.cars"/></p>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
							<input type="hidden" name="auctionsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="JET"/>
							<button type="submit" class="btn">
								<p><fmt:message bundle="${current_locale}" key="locale.main.jets"/></p>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
							<input type="hidden" name="auctionsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="SPORT"/>
							<button type="submit" class="btn">
								<p><fmt:message bundle="${current_locale}" key="locale.main.sport"/></p>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
							<input type="hidden" name="auctionsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="ART"/>
							<button type="submit" class="btn">
								<p><fmt:message bundle="${current_locale}" key="locale.main.art"/></p>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
							<input type="hidden" name="auctionsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="REALTY"/>
							<button type="submit" class="btn">
								<p><fmt:message bundle="${current_locale}" key="locale.main.realty"/></p>
							</button>
						</form>
					</div>
				</li>
			</ul>
		</div>
		<div class="col-md-10 col-md-offset-1 auctions_list_panel">
			<c:if test="${requestScope.auctionsInfo != null}">
				<c:set var="auctions" value="${requestScope.auctionsInfo}"/>
				<c:if test="${empty auctions.auctions }">
					<div class="auctions_list_not_found col-md-12 text-center">
						<p><fmt:message bundle="${current_locale}" key="locale.not.found"/></p>
					</div>
				</c:if>
				<c:if test="${not empty auctions.auctions }">
					<c:forEach items="${auctions.auctions}" var="current">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_AUCTION_INFO"/>
							<input type="hidden" name="auctionId" value="${current.id}"/>
							<button type="submit" class="btn btn col-md-10 col-md-offset-1">
								<div class="auctions_list_img col-md-4">
									<img src="${current.lot.picture }"/>
								</div>
								<div id="auctions_list_info" class="col-md-7 col-md-offset-1 text-left">
									<div class="auctions_list_name">
										<span><p>${current.lot.name }</p></span>
									</div>
									<div class="auctions_list_lot_type">
										<span class="auctions_list_lot_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.type"/>:</span>
										<c:if test="${current.lot.type == 'CAR'}">
											<span><fmt:message bundle="${current_locale}" key="locale.lot.type.car"/></span>
										</c:if>
										<c:if test="${current.lot.type == 'JET'}">
											<span><fmt:message bundle="${current_locale}" key="locale.lot.type.jet"/></span>
										</c:if>
										<c:if test="${current.lot.type == 'ART'}">
											<span><fmt:message bundle="${current_locale}" key="locale.lot.type.art"/></span>
										</c:if>
										<c:if test="${current.lot.type == 'REALTY'}">
											<span><fmt:message bundle="${current_locale}" key="locale.lot.type.realty"/></span>
										</c:if>
										<c:if test="${current.lot.type == 'SPORT'}">
											<span><fmt:message bundle="${current_locale}" key="locale.lot.type.sport"/></span>
										</c:if>
									</div>
									<div class="auctions_list_start_time">
										<span class="auctions_list_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.start.time"/>:</span>
										<span>${current.startTime}</span>
									</div>
									<c:if test="${current.status == 'PENDING_PAYMENT' || current.status == 'COMPLETED'}">
										<div class="auctions_list_end_time">
											<span class="auctions_list_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.end.time"/>:</span>
											<c:if test="${current.type == 'ONLINE' || current.status == 'COMPLETED' || current.status == 'PENDING_PAYMENT'}">
												<span>${current.endTime}</span>
											</c:if>
										</div>
									</c:if>
									<div class="auctions_list_type">
										<span class="auctions_list_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.type"/>:</span>
										<c:if test="${current.type == 'ENGLISH'}">
											<span><fmt:message bundle="${current_locale}" key="locale.auction.type.english"/></span>
										</c:if>
										<c:if test="${current.type == 'ONLINE'}">
											<span><fmt:message bundle="${current_locale}" key="locale.auction.type.online"/></span>
										</c:if>
									</div>
									<div class="auctions_list_status">
										<span class="auctions_list_info_text"></span>
										<c:if test="${current.status == 'COMPLETED'}">
											<span><fmt:message bundle="${current_locale}" key="locale.auction.status.completed"/></span>
											<div class="auctions_list_winner">
												<span class="auctions_list_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.winner"/>:</span>
												<span>${current.lastBetUser }</span>
											</div>	
										</c:if>
										<c:if test="${current.status == 'ACTIVE'}">
											<span><fmt:message bundle="${current_locale}" key="locale.auction.status.active"/></span>	
										</c:if>
										<c:if test="${current.status == 'PENDING_PAYMENT'}">
											<span><fmt:message bundle="${current_locale}" key="locale.auction.status.pending.payment"/></span>	
											<div class="auctions_list_winner">
												<span class="auctions_list_info_text"><fmt:message bundle="${current_locale}" key="locale.auction.winner"/>:</span>
												<span>${current.lastBetUser }</span>
											</div>
										</c:if>
									</div>
								</div>
							</button>
						</form>
					</c:forEach>
					<div class="col-md-12">
						<c:if test="${requestScope.listType == 'list' }">
							<div class="auctions_list_navigation text-center">
								<c:if test="${auctions.currentPage > 1 }">
									<div class="previous">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_AUCTIONS_LIST"/>
											<input type="hidden" name="auctionsPageNumber" value="${auctions.currentPage - 1 }"/>
											<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
										</form>
									</div>
								</c:if>
								<div class="current">
									<p>${auctions.currentPage }</p>
								</div>
								<c:if test="${auctions.currentPage < auctions.pages}">
									<div class="next">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_AUCTIONS_LIST"/>
											<input type="hidden" name="auctionsPageNumber" value="${auctions.currentPage + 1 }"/>
											<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
										</form>
									</div>
								</c:if>
							</div>
						</c:if>
						<c:if test="${requestScope.listType == 'searchingBySearchLine' }">
							<div class="auctions_list_navigation text-center">
								<c:if test="${auctions.currentPage > 1 }">
									<div class="previous">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_AUCTIONS_BY_SEARCHING"/>
											<input type="hidden" name="auctionsPageNumber" value="${auctions.currentPage - 1 }"/>
											<input type="hidden" name="searchLine" value="${requestScope.searchLine }" />
											<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
										</form>
									</div>
								</c:if>
								<div class="current">
									<p>${auctions.currentPage }</p>
								</div>
								<c:if test="${auctions.currentPage < auctions.pages}">
									<div class="next">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_AUCTIONS_BY_SEARCHING"/>
											<input type="hidden" name="auctionsPageNumber" value="${auctions.currentPage + 1 }"/>
											<input type="hidden" name="searchLine" value="${requestScope.searchLine }" />
											<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
										</form>
									</div>
								</c:if>
							</div>
						</c:if>
						<c:if test="${requestScope.listType == 'searchingByLotType' }">
							<div class="auctions_list_navigation text-center">
								<c:if test="${auctions.currentPage > 1 }">
									<div class="previous">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
											<input type="hidden" name="auctionsPageNumber" value="${auctions.currentPage - 1 }"/>
											<input type="hidden" name="lotType" value="${requestScope.lotType }"/>
											<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
										</form>
									</div>
								</c:if>
								<div class="current">
									<p>${auctions.currentPage }</p>
								</div>
								<c:if test="${auctions.currentPage < auctions.pages}">
									<div class="next">
										<form action="FrontController" method="GET">
											<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
											<input type="hidden" name="auctionsPageNumber" value="${auctions.currentPage + 1 }"/>
											<input type="hidden" name="lotType" value="${requestScope.lotType }"/>
											<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
										</form>
									</div>
								</c:if>
							</div>
						</c:if>
					</div>
				</c:if>
			</c:if>
		</div>
	</div>
</body>
</html>