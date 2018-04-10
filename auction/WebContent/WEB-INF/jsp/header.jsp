<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<c:if test="${sessionScope.locale == null}">
		<fmt:setLocale value="en"/>
	</c:if>
	<fmt:setBundle basename="resources/locales/locale" var="current_locale" scope="session"/>
</head>
<body>
   <div class="container">
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div id="navbar" class="">
            <ul class="nav navbar-nav">
            	<li class="header_main"><a href="index.jsp"><fmt:message bundle="${current_locale}" key="locale.main"/></a></li>
            	<li>
           			<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_USER_INFO"/>
						<input type="hidden" name="userLogin" value="${sessionScope.userLogin}"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.header.profile"/></button>
					</form>
            	</li>
	            <li>
	            	<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_USER_LOTS"/>
						<input type="hidden" name="userLogin" value="${sessionScope.userLogin}"/>
						<input type="hidden" name="choosenLotsPageNumber" value="1"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.header.my.lots"/></button>
					</form>
	            </li>
	            <li>
	            	<form action="FrontController" method="GET">
						<input type="hidden" name="command" value="GET_AUCTIONS_LIST"/>
						<input type="hidden" name="auctionsPageNumber" value="1"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.header.auctions"/></button>
					</form>
	            </li>
	            <c:if test='${sessionScope.userRole == "ADMIN"}'>
		            <li>
		            	<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_LOTS_LIST"/>
							<input type="hidden" name="lotsPageNumber" value="1"/>
							<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.header.lots"/></button>
						</form>
		            </li>
		            <li>
		            	<form action="FrontController" method="GET">
							<input type="hidden" name="command" value="GET_USERS_LIST"/>
							<input type="hidden" name="usersPageNumber" value="1"/>
							<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.header.users"/></button>
						</form>
		            </li>	
	            </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
	            <li>
		            <form action="FrontController">
		            	<input type="hidden" name="command" value="CHANGE_LOCALE"/>
						<select id="language" class="form-control" name="locale" onchange="submit()">
			                <option value="en"  ${locale == 'en' ? 'selected' : ''}><fmt:message bundle="${current_locale}" key="locale.change.locale.name.en"/></option>
			                <option value="ru" ${locale == 'ru' ? 'selected' : ''}><fmt:message bundle="${current_locale}" key="locale.change.locale.name.ru"/></option>
			            </select>
			        </form>
	            </li>
	            <c:if test='${sessionScope.userRole == "ADMIN"}'>
	            	<li><a href="lot-create.jsp"><fmt:message bundle="${current_locale}" key="locale.header.create.lot"/></a></li>
	            	<li><a href="auction-create.jsp"><fmt:message bundle="${current_locale}" key="locale.header.create.auction"/></a></li>
	            </c:if>
	            <c:if test='${sessionScope.userRole != "ADMIN"}'>
	            	<li><a href="lot-offer.jsp"><fmt:message bundle="${current_locale}" key="locale.header.offer.lot"/></a></li>
	            </c:if>
	            <li>
	            	<form action="FrontController" method="POST">
						<input type="hidden" name="command" value="LOGOUT"/>
						<button type="submit" class="btn"><fmt:message bundle="${current_locale}" key="locale.header.log.out"/></button>
					</form>
	            </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
   </div>
</body>
</html>