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
<link rel="stylesheet" href="/resources/css/reservation.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
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
								<c:choose>
									<c:when test ="${sessionScope.schoolMember != null}"><li><a href="/school/schoolpage.do">마이페이지</a></li></c:when>
									<c:when test ="${sessionScope.userMember != null}"><li><a href="/user/userpage.do">마이페이지</a></li></c:when>
								</c:choose>
								<li><a href="/map/map.do">유치원 찾기</a></li>
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

		<div class="board">
		<div id = "wrap">
				<div id="mapWrap">
					<div id ="kgName">${kindergarten.getKgName()}</div>
	
					<!-- 지도를 표시할 div 입니다 -->
					<div id="map"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=df05d9d53d8d4a2d40f65a23b163b044"></script>   
				</div>
				<div id = "formWrap">
						<form action="/reservation/reservationimpl.do" id = "form" method="post">
						<label>보호자명 : <input type="text" required="required" name ="protectorName" autocomplete="off"></label>
						<label>연락처 : <input type="tel" required="required" name = "phoneNumber" maxlength="13" placeholder="010-000-0000"></label>
						<label>반려 견종 : <input type="text" required="required" name = "dogBreed"></label>
						<label>반려견 나이 : <input type="text" required="required" name="dogAge"></label>
						<label>예약 날짜 : <input type="date" required="required" name="date" id ="date"></label>
						<input type="hidden" name = "kgName" value="${kindergarten.getKgName()}">
						<label>요청사항 : <textarea id = "requestedTerm" required="required" maxlength="20" name = "requestedTerm"></textarea></label>
						<c:if test="${service.getIsPickup() == 0}">
						<label id = "pickWrap">픽업서비스 : <div id = "pickBox">신청 <input type="radio" value="0" name="pickup"> 신청 안함<input type="radio" value="1" name="pickup"></div></label>
						</c:if>
						<button id = "formBtn">신청하기</button>
						</form>
					</div>
			
		</div>
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng( ${kindergarten.getKgLat()} , ${kindergarten.getKgLag()}), // 지도의 중심좌표
		        level: 5 // 지도의 확대 레벨
		    };

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(${kindergarten.getKgLat()}, ${kindergarten.getKgLag()}); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null);    
		</script>



		<!-- Footer -->
		<footer id="footer"> 
			<div id = "footerMark">&copy;댕댕아놀면뭐하니?</div>
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