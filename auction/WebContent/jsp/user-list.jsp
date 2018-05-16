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
	<title><fmt:message bundle="${current_locale}" key="locale.users"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 users_list_page_name">
				<p><fmt:message bundle="${current_locale}" key="locale.users"/></p>
			</div>
		</div>
		<div id="search-input">
            <div class="input-group col-md-10 col-md-offset-0">
	            <form action="FrontController" method="GET">
					<input type="hidden" name="command" value="GET_USERS_BY_SEARCHING"/>
					<input type="hidden" name="usersPageNumber" value="1"/>
					<input type="text" class="input-lg col-md-11" name="searchLine" maxlength="200" placeholder=<fmt:message bundle="${current_locale}" key="locale.list.search"/> />
					<button class="btn btn-info btn-lg col-md-1" type="submit">
	                   	<i class="glyphicon glyphicon-search"></i>
	                </button>
				</form>
        	</div>
        </div>
        <div class="col-md-12 users_list_panel">
	        <div class="row">
		        <c:if test="${requestScope.usersInfo != null}">
					<c:set var="users" value="${requestScope.usersInfo}"/>
					<c:if test="${empty users.users }">
						<div class="users_list_not_found col-md-12 text-center">
							<p><fmt:message bundle="${current_locale}" key="locale.not.found"/></p>
						</div>
					</c:if>
					<c:if test="${not empty users.users }">
						<c:forEach items="${users.users}" var="current">
							<div class="row col-md-12">
								<div class="users_list_user">
									<form action="FrontController" method="GET">
										<input type="hidden" name="command" value="GET_USER_INFO"/>
										<input type="hidden" name="userLogin" value="${current.login}"/>
										<button type="submit" class="btn col-md-10 col-md-offset-1">
											<div class="users_list_picture col-md-4">
												<img alt="" src="${current.picture}"/>
											</div>
											<div class="users_list_info col-md-7 col-md-offset-1">
												<div class="text-left user_login">
													<p>${current.login}</p>
												</div>
												<div class="text-left user_surname">
													<span class="users_list_info_text"><fmt:message bundle="${current_locale}" key="locale.user.surname"/>:</span>
													<span>${current.surname}</span>	
												</div>
												<div class="text-left user_name">
													<span class="users_list_info_text"><fmt:message bundle="${current_locale}" key="locale.user.name"/>:</span>
													<span>${current.name}</span>	
												</div>
												<div class="text-left user_email">
													<span class="users_list_info_text"><fmt:message bundle="${current_locale}" key="locale.user.email"/>:</span>
													<span>${current.email}</span>	
												</div>
												<div class="text-left user_phone">
													<span class="users_list_info_text"><fmt:message bundle="${current_locale}" key="locale.user.phone"/>:</span>
													<span>${current.phone}</span>	
												</div>
												<div class="text-left user_country">
													<span class="users_list_info_text"><fmt:message bundle="${current_locale}" key="locale.user.country"/>:</span>
													<c:if test="${current.country == 'Belarus'}">
														<span><fmt:message bundle="${current_locale}" key="locale.belarus"/></span>
													</c:if>
													<c:if test="${current.country == 'Russia'}">
														<span><fmt:message bundle="${current_locale}" key="locale.russia"/></span>
													</c:if>
													<c:if test="${current.country == 'USA'}">
														<span><fmt:message bundle="${current_locale}" key="locale.usa"/></span>
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
								<div class="users_list_navigation text-center">
									<c:if test="${users.currentPage > 1 }">
										<div class="previous">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_USERS_LIST"/>
												<input type="hidden" name="usersPageNumber" value="${users.currentPage - 1 }"/>
												<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
											</form>
										</div>
									</c:if>
									<div class="current">
										<p>${users.currentPage }</p>
									</div>
									<c:if test="${users.currentPage < users.pages}">
										<div class="next">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_USERS_LIST"/>
												<input type="hidden" name="usersPageNumber" value="${users.currentPage + 1 }"/>
												<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.next.button"/></button>
											</form>
										</div>
									</c:if>
								</div>
							</c:if>
							<c:if test="${requestScope.listType == 'searchingBySearchLine' }">
								<div class="users_list_navigation text-center">
									<c:if test="${users.currentPage > 1 }">
										<div class="previous">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_USERS_BY_SEARCHING"/>
												<input type="hidden" name="usersPageNumber" value="${users.currentPage - 1 }"/>
												<input type="hidden" name="searchLine" value="${requestScope.searchLine }"/>
												<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></button>
											</form>
										</div>
									</c:if>
									<div class="current">
										<p>${users.currentPage }</p>
									</div>
									<c:if test="${users.currentPage < users.pages}">
										<div class="next">
											<form action="FrontController" method="GET">
												<input type="hidden" name="command" value="GET_USERS_BY_SEARCHING"/>
												<input type="hidden" name="usersPageNumber" value="${users.currentPage + 1 }"/>
												<input type="hidden" name="searchLine" value="${requestScope.searchLine }"/>
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