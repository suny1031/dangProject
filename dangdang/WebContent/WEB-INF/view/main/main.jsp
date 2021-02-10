<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE HTML>

<html>
<head>
	<title>댕댕최고</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<!-- <link rel="preconnect" href="https://fonts.gstatic.com"> -->
		<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
		<noscript><link rel="stylesheet" href="../../../../resources/css/noscript.css" /></noscript>
</head>

<body class="landing is-preload">

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<h1>
				<div id="dangmark"></div>
				<a href="/main.do" id="headermain" class="mainfont">댕댕아 놀면 뭐하니?</a>
			</h1>
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>MENU</span></a>
						<div id="menu">
							<ul>
								<li><a href="/main.do">Home</a></li>
								<c:choose>
									<c:when test ="${sessionScope.schoolMember != null}"><li><a href="/school/schoolpage.do">마이페이지</a></li></c:when>
									<c:when test ="${sessionScope.userMember != null}"><li><a href="/user/userpage.do">마이페이지</a></li></c:when>
								</c:choose>
								<li><a href="/map/map.do">유치원찾기</a></li>
								<li><a href="/reservation/calendar.do">캘린더</a></li>
								<c:choose>
									<c:when test ="${sessionScope.schoolMember != null}"><li><a href="/school/logout.do">로그아웃</a></li></c:when>
									<c:when test ="${sessionScope.userMember != null}"><li><a href="/user/logout.do">로그아웃</a></li></c:when>
								</c:choose>
								
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>

		<!-- Banner -->
		
			
			
			
			
			<c:choose>
				<c:when test ="${sessionScope.schoolMember.kgName != null}"><!-- session에 school값 있을 때 -->
					<section id="banner">
						<div class="inner">
							<h2 class="mainfont">댕댕아 놀면 뭐하니?</h2>
							<p > ${sessionScope.schoolMember.kgName}님 안녕하세요!</p>
						</div>
					</section>	
				</c:when>
				<c:when test ="${sessionScope.userMember.userId != null}"><!-- session에 user값 있을 때 -->
					<section id="banner">
						<div class="inner">
							<h2 class="mainfont">댕댕아 놀면 뭐하니?</h2>
							<p> 안녕하세요. ${sessionScope.userMember.nickname}님! <br><br> ${sessionScope.userMember.nickname}님의 반려동물이 <br>오늘 어떤 하루를 보냈을지 확인해보세요! </p>
						</div>	
					</section>
				</c:when>	
				
				<c:otherwise><!-- session에 아무것도 없을 때 -->
					<section id="banner">
					<div class="inner">
						<h2 class="mainfont">댕댕아 놀면 뭐하니?</h2>
							<p>
								내 반려견이 유치원에서<br/> 재밌게 놀고 있는지 궁금하시다면 ?<br/> Please join with us!
							</p>
					</div>
					<a href="#one" class="more scrolly">Login</a>
					</section>
					
					<!-- One -->
					<section id="one" class="main">
						<div class="inner">
							<header class="major">
								<div class="main_login_box">
									<div class="main_login">
										<a href="/user/login.do">견주</a>
									</div>
									<div class="main_login">
										<a href="/school/login.do">업주</a>
									</div>
								</div>	
							</header>
						</div>
					</section>

					
					
					
				</c:otherwise>
			</c:choose>
	
	
	
		
		
		
		
		


		<!-- Footer -->
		<footer id="footer">
			
			
			
			
			
		</footer>

	</div>

	<!-- Scripts -->
	<script src="../../../../resources/js/jquery.min.js"></script>
	<script src="../../../../resources/js/jquery.scrollex.min.js"></script>
	<script src="../../../../resources/js/jquery.scrolly.min.js"></script>
	<script src="../../../../resources/js/browser.min.js"></script>
	<script src="../../../../resources/js/breakpoints.min.js"></script>
	<script src="../../../../resources/js/util.js"></script>
	<script src="../../../../resources/js/main.js"></script>
	<script src="../../../../resources/js/member.js"></script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>