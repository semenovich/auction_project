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
	<title><fmt:message bundle="${current_locale}" key="locale.lots"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 lots_list_page_name">
				<p><fmt:message bundle="${current_locale}" key="locale.lots"/></p>
			</div>
		</div>
		<div id="search-input">
            <div class="input-group col-md-10">
	            <form action="FrontController" method="GET">
					<input type="hidden" name="command" value="GET_LOTS_BY_SEARCHING"/>
					<input type="hidden" name="lotsPageNumber" value="1"/>
					<input type="text" class="input-lg col-md-11" name="searchLine" placeholder=<fmt:message bundle="${current_locale}" key="locale.list.search"/> />
					<button class="btn btn-info btn-lg col-md-1" type="submit">
	                   	<i class="glyphicon glyphicon-search"></i>
	                </button>
				</form>
        	</div>
        </div>
        <div class="col-md-10 col-md-offset-0 lots_list_type_chooser">
        	<ul class="nav navbar-nav">
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_LOTS_BY_TYPE"/>
							<input type="hidden" name="lotsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="CAR"/>
							<button type="submit" class="btn">
								<fmt:message bundle="${current_locale}" key="locale.main.cars"/>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_LOTS_BY_TYPE"/>
							<input type="hidden" name="lotsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="JET"/>
							<button type="submit" class="btn">
								<fmt:message bundle="${current_locale}" key="locale.main.jets"/>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_LOTS_BY_TYPE"/>
							<input type="hidden" name="lotsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="SPORT"/>
							<button type="submit" class="btn">
								<fmt:message bundle="${current_locale}" key="locale.main.sport"/>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_LOTS_BY_TYPE"/>
							<input type="hidden" name="lotsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="ART"/>
							<button type="submit" class="btn">
								<fmt:message bundle="${current_locale}" key="locale.main.art"/>
							</button>
						</form>
					</div>
				</li>
				<li>
					<div class="text-left">
						<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_LOTS_BY_TYPE"/>
							<input type="hidden" name="lotsPageNumber" value="1"/>
							<input type="hidden" name="lotType" value="REALTY"/>
							<button type="submit" class="btn">
								<fmt:message bundle="${current_locale}" key="locale.main.realty"/>
							</button>
						</form>
					</div>
				</li>
			</ul>
		</div>
		<div class="col-md-10 col-md-offset-0 lots_list_status_chooser">
			<ul class="nav navbar-nav">
				<li>
		           	<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_LOTS_LIST"/>
						<input type="hidden" name="lotsPageNumber" value="1"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.all.lots"/></button>
					</form>
		        </li>
		        <li>
		           	<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_WAITING_LOTS"/>
						<input type="hidden" name="lotsPageNumber" value="1"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.waiting.lots"/></button>
					</form>
		        </li>
			</ul>
		</div>
        <div class="col-md-12 lots_list_panel">
        	<div class="row">
				<c:if test="${requestScope.lotsInfo != null}">
					<c:set var="lots" value="${requestScope.lotsInfo}"/>
					<c:if test="${empty lots.lots}">
						<div class="lots_list_not_found col-md-12 text-center">
							<p><fmt:message bundle="${current_locale}" key="locale.not.found"/></p>
						</div>
					</c:if>
					<c:if test="${not empty lots.lots}">
						<c:forEach items="${lots.lots}" var="current">
							<div class="row col-md-12">
								<div class="lots_list_lot">
									<form action="FrontController" method="GET">
										<input type="hidden" name="command" value="GET_LOT_INFO"/>
										<input type="hidden" name="lotId" value="${current.id}"/>
										<button type="submit" class="btn col-md-10 col-md-offset-1">
											<div class="lots_list_img col-md-4">
												<img src="${current.picture }"/>
											</div>
											<div id="lots_list_info" class="col-md-7 col-md-offset-1 text-left">
												<div class="lots_list_name">
													<span>${current.name }</span>
												</div>
												<div class="lots_list_type">
													<span class="lots_list_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.type"/>:</span>
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
												<div class="lots_list_owner">
													<span class="lots_list_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.owner"/>:</span>
													<span>${current.owner }</span>
												</div>
												<div class="lots_list_quantity">
													<span class="lots_list_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.quantity"/>:</span>
													<span>${current.quantity }</span>
												</div>
												<div class="lots_list_status">
													<span class="lots_list_info_text"><fmt:message bundle="${current_locale}" key="locale.lot.status"/>:</span>
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
							</div>
						</c:forEach>
						<div class="col-md-12">
							<c:if test="${requestScope.listType == 'list' }">
								<div class="lots_list_navigation text-center">
									<c:if test="${lots.currentPage > 1 }">
										<div class="previous">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_LOTS_LIST"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage - 1 }"/>
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
												<input type="hidden" name="command" value="GET_LOTS_LIST"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage + 1 }"/>
												<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
											</form>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${requestScope.listType == 'searchingBySearchLine' }">
								<div class="lots_list_navigation text-center">
									<c:if test="${lots.currentPage > 1 }">
										<div class="previous">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_LOTS_BY_SEARCHING"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage - 1 }"/>
												<input type="hidden" name="searchLine" value="${requestScope.searchLine }"/>
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
												<input type="hidden" name="command" value="GET_LOTS_BY_SEARCHING"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage + 1 }"/>
												<input type="hidden" name="searchLine" value="${requestScope.searchLine }"/>
												<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
											</form>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${requestScope.listType == 'searchingByType' }">
								<div class="lots_list_navigation text-center">
									<c:if test="${lots.currentPage > 1 }">
										<div class="previous">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_LOTS_BY_TYPE"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage - 1 }"/>
												<input type="hidden" name="lotType" value="${requestScope.lotType }"/>
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
												<input type="hidden" name="command" value="GET_LOTS_BY_TYPE"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage + 1 }"/>
												<input type="hidden" name="lotType" value="${requestScope.lotType }"/>
												<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
											</form>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${requestScope.listType == 'waitingList' }">
								<div class="lots_list_navigation text-center">
									<c:if test="${lots.currentPage > 1 }">
										<div class="previous">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_WAITING_LOTS"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage - 1 }"/>
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
												<input type="hidden" name="command" value="GET_WAITING_LOTS"/>
												<input type="hidden" name="lotsPageNumber" value="${lots.currentPage + 1 }"/>
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
	</div>
</body>
</html>