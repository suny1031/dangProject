<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/main.css" />
	<link rel="stylesheet" href="/resources/css/map.css" />
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
	<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
	<noscript>
		<link rel="stylesheet" href="assets/css/noscript.css" />
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
			<div id="mapWrap">
				<!--검색어를 검색 할 곳-->
				<div id="search">
					<div id="searchBox">
						<input type="search" id="searchInput">
						<i class="fas fa-search"></i>
					</div>
					<div id="line"></div>
					
					<div id = "infrmWrap">
					
					<div class = "infrmBox">
						<div class = "infrm">
							<p>fsadfsd</p>
							<p>asdfsd</p>
							<p>asfdasdfasd</p>
						</div>
						<div class = "photo"></div>
					</div>
					
					<div class = "infrmBox">
						<div class = "infrm">
							<p>fsadfsd</p>
							<p>asdfsd</p>
							<p>asfdasdfasd</p>
						</div>
						<div class = "photo"></div>
					</div>
					
					<div class = "infrmBox">
						<div class = "infrm">
							<p>fsadfsd</p>
							<p>asdfsd</p>
							<p>asfdasdfasd</p>
						</div>
						<div class = "photo"></div>
					</div>
					
					</div>
				</div>

				<!-- 지도를 표시할 div 입니다 -->
				<div id="map"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=df05d9d53d8d4a2d40f65a23b163b044"></script>
				<script>
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						mapOption = {
							center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
							level: 3 // 지도의 확대 레벨
						};

					// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
					var map = new kakao.maps.Map(mapContainer, mapOption); 
				</script>
			</div>
		</div>
		
		
		
		<!-- Footer -->
		<footer id="footer">
		
		</footer>

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