<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link rel="stylesheet" href="css/style.css"/>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<c:if test="${sessionScope.locale == null}">
		<fmt:setLocale value="en"/>
	</c:if>
	<fmt:setBundle basename="resources/locales/locale" var="current_locale" scope="session"/>
	<title><fmt:message bundle="${current_locale}" key="locale.main"/></title>
</head>
<body>
	<c:import url="WEB-INF/jsp/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="main_body">
				<div id="main_carousel" class="carousel slide" data-ride="carousel">
		  			<ol class="carousel-indicators">
				    	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				    	<li data-target="#myCarousel" data-slide-to="1"></li>
				    	<li data-target="#myCarousel" data-slide-to="2"></li>
				    	<li data-target="#myCarousel" data-slide-to="4"></li>
				    	<li data-target="#myCarousel" data-slide-to="5"></li>
					</ol>
				  	<div class="carousel-inner text-center">
				    	<div class="item active">
				    		<img src="img/cars_slider.jpg" />
				    		<div class="carousel-caption">
				    			<p><fmt:message bundle="${current_locale}" key="locale.main.cars"/></p>
				    		</div>
				    	</div>
				
					    <div class="item">
					      	<img src="img/jets_slider.jpg"/>
					    	<div class="carousel-caption">
								<p><fmt:message bundle="${current_locale}" key="locale.main.jets"/></p> 
				    		</div>
					    </div>
					
					    <div class="item">
					      	<img src="img/sport_slider.jpg"/>
					    	<div class="carousel-caption">
								<p><fmt:message bundle="${current_locale}" key="locale.main.sport"/></p>
				    		</div>
					    </div>
					    
					    <div class="item">
					      	<img src="img/art_slider.jpg"/>
					    	<div class="carousel-caption">
								<p><fmt:message bundle="${current_locale}" key="locale.main.art"/></p> 
				    		</div>
					    </div>
					    
					    <div class="item">
					      	<img src="img/realty_slider.jpg"/>
					    	<div class="carousel-caption">
								<p><fmt:message bundle="${current_locale}" key="locale.main.realty"/></p> 
				    		</div>
					    </div>
					 </div>	
					
				  	<a class="left carousel-control" href="#main_carousel" data-slide="prev">
				    	<span class="glyphicon glyphicon-chevron-left"></span>
				    	<span class="sr-only"><fmt:message bundle="${current_locale}" key="locale.previous.button"/></span>
				  	</a>
				  	<a class="right carousel-control" href="#main_carousel" data-slide="next">
				    	<span class="glyphicon glyphicon-chevron-right"></span>
				    	<span class="sr-only"><fmt:message bundle="${current_locale}" key="locale.next.button"/></span>
					</a>
				</div>
				<div class="main_auctions col-md-12 text-center">
					<p><fmt:message bundle="${current_locale}" key="locale.auctions"/></p>
				</div>
				<div class="row">
					<div class="main_chooser col-md-10 col-md-offset-1">
						<div class="main_chooser_header">
							<div class="main_choose col-md-4 text-center">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
									<input type="hidden" name="auctionsPageNumber" value="1"/>
									<input type="hidden" name="lotType" value="CAR"/>
									<button type="submit" class="btn">
										<img src="img/car.png"/>
										<p><fmt:message bundle="${current_locale}" key="locale.main.cars"/></p>
									</button>
								</form>
							</div>
							<div class="main_choose col-md-4 text-center">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
									<input type="hidden" name="auctionsPageNumber" value="1"/>
									<input type="hidden" name="lotType" value="JET"/>
									<button type="submit" class="btn">
										<img src="img/jet.png"/>
										<p><fmt:message bundle="${current_locale}" key="locale.main.jets"/></p>
									</button>
								</form>
							</div>
							<div class="main_choose col-md-4 text-center">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
									<input type="hidden" name="auctionsPageNumber" value="1"/>
									<input type="hidden" name="lotType" value="SPORT"/>
									<button type="submit" class="btn">
										<img src="img/sport.png"/>
										<p><fmt:message bundle="${current_locale}" key="locale.main.sport"/></p>
									</button>
								</form>
							</div>
						</div>
						<div class="row main_chooser_footer">
							<div class="main_choose col-md-4 text-center">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
									<input type="hidden" name="auctionsPageNumber" value="1"/>
									<input type="hidden" name="lotType" value="ART"/>
									<button type="submit" class="btn">
										<img src="img/art.png"/>
										<p><fmt:message bundle="${current_locale}" key="locale.main.art"/></p>
									</button>
								</form>
							</div>
							<div class="main_choose col-md-4 text-center">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_AUCTIONS_BY_LOT_TYPE"/>
									<input type="hidden" name="auctionsPageNumber" value="1"/>
									<input type="hidden" name="lotType" value="REALTY"/>
									<button type="submit" class="btn">
										<img src="img/realty.png"/>
										<p><fmt:message bundle="${current_locale}" key="locale.main.realty"/></p>
									</button>
								</form>
							</div>
							<div class="main_choose col-md-4 text-center">
								<form action="FrontController" method="GET">
									<input type="hidden" name="command" value="GET_AUCTIONS_LIST"/>
									<input type="hidden" name="auctionsPageNumber" value="1"/>
									<button type="submit" class="btn">
										<img src="img/lots.png"/>
										<p><fmt:message bundle="${current_locale}" key="locale.main.all.auctions"/></p>
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>