<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>


<!--페이징-->
<%@page import="java.util.List"%>
<%@page import="com.dang.map.model.service.MapService"%>
<%@page import="com.dang.map.model.vo.Kindergarten"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/resources/css/main.css" />
<link rel="stylesheet" href="/resources/css/reservation.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
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
								<li><a href="#">캘린더</a></li>
								<c:choose>
									<c:when test ="${sessionScope.schoolMember != null}"><li><a href="/school/logout.do">로그아웃</a></li></c:when>
									<c:when test ="${sessionScope.userMember != null}"><li><a href="/user/logout.do">로그아웃</a></li></c:when>
								</c:choose>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>

<!-- Main -->
<%	
	String keyword = request.getParameter("keyword"); // 검색어 value

	int pageSize = 3; // 한 페이지에 출력할 레코드 수

	// 페이지 링크를 클릭한 번호 / 현재 페이지
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null){ // 클릭한게 없으면 1번 페이지
		pageNum = "1";
	}
	// 연산을 하기 위한 pageNum 형변환 / 현재 페이지
	int currentPage = Integer.parseInt(pageNum);

	// 해당 페이지에서 시작할 레코드 / 마지막 레코드
	int startRow = (currentPage - 1) * pageSize + 1;
	int endRow = currentPage * pageSize;

	int count = 0;
	int fCount = 0;
	
	MapService mapService = new MapService();
	count = mapService.selectCountPage(); // 데이터베이스에 저장된 총 갯수
	
	fCount = mapService.selectSearchCount(keyword); // DB에 저장된 검색어와 일치하는 총 갯수
	
	
	List<Kindergarten> list = null;
	List<Kindergarten> flist = null;
	
	if (count > 0 && keyword == null) {
		// getList()메서드 호출 / 해당 레코드 반환
		list = mapService.selectKindergartenPage(startRow, endRow);
	}
	
	if(fCount > 0){
		flist = mapService.selectSearchKindergarten(keyword,startRow, endRow);
	}
%>
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
						<form action="" id = "form">
						<label>보호자명 : <input></label>
						<label>연락처 : <input></label>
						<label>희망서비스 : <input></label>
						<label>반려 견종 : <input></label>
						<label>반려견 나이 : <input></label>
						<label>픽업서비스 : <input type="radio" value="0" name="pickUp"><input type="radio" value="1" name="pickUp"></label>
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