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
		<input type="hidden" name="command" value="GET_LOTS_BY_SEARCHING"/>
		<input type="hidden" name="lotsPageNumber" value="1"/>
		<input type="text" name="searchLine"/>
		<div>
			<input type="submit" value='Search'/>
		</div>
	</form>
	<c:if test="${requestScope.lotsInfo != null}">
			<c:set var="lots" value="${requestScope.lotsInfo}"/>
			<p>${lots.toString()}</p>
	</c:if>
</body>
</html>