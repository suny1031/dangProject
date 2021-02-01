<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/resources/css/main.css" />
<link rel="stylesheet" href="/resources/css/infrm.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<noscript>
	<link rel="stylesheet" href="resources/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<h1>
				<div id="dangmark"></div>
				<a href="/main.do" id="headermain" class="mainfont">댕댕아 놀면 뭐하니?</a>
			</h1>
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="/main.do">Home</a></li>
								<li><a href="/mypage.do">마이페이지</a></li>
								<li><a href="/map.do">유치원 찾기</a></li>
								<li><a href="#">캘린더</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>







		<!-- Main -->

		<div class="board">
			<div id="storeWrap">
				<div id="photo"></div>
				<div id="wrap"></div>
				<div id="storeInfrm">
					<div id="InfrmList">
						<div id="name">${kindergarten.getKgName()}</div>
						<div id="review">방문자리뷰</div>
						<div id="kakaoIcon">
							<i class="fas fa-map-signs"></i> <i class="fas fa-road"></i>
						</div>
						<div id="kakao" class="list">
							<a>길찾기</a><a>로드뷰</a>
						</div>
						<div id="reservation">
							<button id="rsv_btn">예약</button>
						</div>
						<div id="notice" class="list">${kindergarten.getKgNotice()}</div>
						<div id="tell" class="list">${kindergarten.getKgTell()}</div>
						<div id="address" class="list">${kindergarten.getKgAddress()}</div>
						<div id="operateTime" class="list">${kindergarten.getKgOperateTime()}</div>
						<div id="services" class="list">#ㄻㄴㅇㄹ#ㅇㄹㄴㅇ#ㄹㄴㅇ</div>

					</div>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<footer id="footer"> </footer>
	</div>
	<!-- Scripts -->
	<script src="/resources/js/jquery.min.js"></script>
	<script src="/resources/js/jquery.scrollex.min.js"></script>
	<script src="/resources/js/jquery.scrolly.min.js"></script>
	<script src="/resources/js/browser.min.js"></script>
	<script src="/resources/js/breakpoints.min.js"></script>
	<script src="/resources/js/util.js"></script>
	<script src="/resources/js/main.js"></script>

</body>
</html>