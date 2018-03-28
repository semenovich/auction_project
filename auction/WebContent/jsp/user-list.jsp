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
		<input type="hidden" name="command" value="GET_USERS_BY_SEARCHING"/>
		<input type="hidden" name="usersPageNumber" value="1"/>
		<input type="text" name="searchLine"/>
		<div>
			<input type="submit" value='Users'/>
		</div>
	</form>
	<c:if test="${requestScope.usersInfo != null}">
		<c:set var="users" value="${requestScope.usersInfo}"/>
		<p>${users.toString()}</p>
		<form action="FrontController" method="POST">
			<input type="hidden" name="command" value="BLOCK_USER"/>
			<input type="hidden" name="userLogin" value="${users.users.get(0).login}"/>
			<div>
				<input type="submit" value='Block'/>
			</div>
		</form>
		<form action="FrontController" method="POST">
			<input type="hidden" name="command" value="UNBLOCK_USER"/>
			<input type="hidden" name="userLogin" value="${users.users.get(0).login}"/>
			<div>
				<input type="submit" value='Unblock'/>
			</div>
		</form>				
	</c:if>
</body>
</html>