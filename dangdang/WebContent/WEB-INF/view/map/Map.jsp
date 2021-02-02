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
<link rel="stylesheet" href="/resources/css/map.css" />
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
								<li><a href="/mypage.do">마이페이지</a></li>
								<li><a href="/map/map.do">유치원 찾기</a></li>
								<li><a href="#">캘린더</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>

<!-- Main -->
<%
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
	MapService mapService = new MapService();
	count = mapService.selectCount(); // 데이터베이스에 저장된 총 갯수

	List<Kindergarten> kindergartenList = null;
	if (count > 0) {
		// getList()메서드 호출 / 해당 레코드 반환
		kindergartenList = mapService.selectKindergartenPage(startRow, endRow);
	}
%>
		<div class="board">
	
			<div id="mapWrap">
				<!--검색어를 검색 할 곳-->
				<div id="search">
					<div id="searchBox">
						<input type="search" id="searchInput"> <i
							class="fas fa-search"></i>
					</div>
					<div id="line"></div>

					<div id="infrmWrap">

							
									<%
										if (count > 0) { // 데이터베이스에 데이터가 있으면
											int number = count - (currentPage - 1) * pageSize; // 글 번호 순번 
											for (int i = 0; i < kindergartenList.size(); i++) {
												Kindergarten kindergarten = kindergartenList.get(i); // 반환된 list에 담긴 참조값 할당
									%>
									
									<div class="infrmBox">
									<div class="infrm">
										<p><%=kindergarten.getKgName()%></p>
										<p><%=kindergarten.getKgAddress()%></p>
										<p><%=kindergarten.getKgOperateTime()%></p>
									</div>
								</div> 
									<%
											}
										} else { // 데이터가 없으면
									%>
									<div>
										<p>게시글이 없습니다.</p>
									</div>
										<%
											}
										%>
									
									<div class = "pageCount">
											<%	// 페이징  처리
												if(count > 0){
													// 총 페이지의 수
													int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
													// 한 페이지에 보여줄 페이지 블럭(링크) 수
													int pageBlock = 10;
													// 한 페이지에 보여줄 시작 및 끝 번호(예 : 1, 2, 3 ~ 10 / 11, 12, 13 ~ 20)
													int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
													int endPage = startPage + pageBlock - 1;
													
													// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
													if(endPage > pageCount){
														endPage = pageCount;
													}
													
													if(startPage > pageBlock){ // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
											%>
														<a href="/map/map.do?pageNum=<%=startPage - 10%>">[이전]</a>	
											<%			
													}
													
													for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
														if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
											%>
															[<%=i %>]
											<%									
														}else{ // 현재 페이지가 아닌 경우 링크 설정
											%>
															<a href="/map/map.do?pageNum=<%=i%>">[<%=i %>]</a>
											<%	
														}
													} // for end
													
													if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
											%>
														<a href="/map/map.do?pageNum=<%=startPage + 10 %>">[다음]</a>
											<%			
													}
												}
											%>
										</div>
					</div>
				</div>

				<!-- 지도를 표시할 div 입니다 -->
				<div id="map"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=df05d9d53d8d4a2d40f65a23b163b044"></script>   
			</div>
		</div>
		
		
		
		
	
		
		<script>
        let coords = {};

        navigator.geolocation.getCurrentPosition((position) => {
            let lat = position.coords.latitude;
            let lng = position.coords.longitude;

            var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(lat, lng), //지도의 중심좌표.
                level: 3 //지도의 레벨(확대, 축소 정도)
            };
  
            var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
            
            var positions = [
            <c:forEach var="map" items="${mapList}" varStatus="status">
            <c:choose>
            <c:when test = "${status.count == 5}">
                {   content: '<div>${map.kgName}</div><a href = /map/infrm.do?${map.kgName}>상세정보</a>',
                    latlng: new kakao.maps.LatLng(${map.kgLat}, ${map.kgLag}),
                }
            </c:when>
                <c:otherwise>
                {   content: '<div id = "kgName" onclick="kgName()" >${map.kgName}</div>',
                    latlng: new kakao.maps.LatLng(${map.kgLat}, ${map.kgLag}),
                },
            	</c:otherwise>
            	</c:choose>
        	</c:forEach> 
                ];
         
	 		for (var i = 0; i < positions.length; i ++) {
	 		    
	 		    // 마커를 생성합니다
	 		    var marker = new kakao.maps.Marker({
	 		        map: map, // 마커를 표시할 지도
	 		        position: positions[i].latlng // 마커를 표시할 위치
	 		    });
	 		    
	 		    // 마커에 표시할 인포윈도우를 생성합니다 
	 		    var infowindow = new kakao.maps.InfoWindow({
	 		        content: positions[i].content, // 인포윈도우에 표시할 내용
	 		        removable : iwRemoveable =true
	 		    });

	 		    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	 		    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	 		    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	 		    kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
	 	
	 		}
	 			// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	 		   function makeOverListener(map, marker, infowindow) {
	 		       return function() {
	 		           infowindow.open(map, marker);
	 		       };
	 		   }

	 		   // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	 		   function makeOutListener(infowindow) {
	 		       return function() {
	 		           infowindow.close();
	 		       };
	 		   };
	 			 		 
         // 마커가 지도 위에 표시되도록 설정합니다
         marker.setMap(map);

	 		   });
    </script>
	<script>
	   let kgName = ()=>{
		var kgName = document.querySelector('#kgName').innerText;
		let url = "/map/infrm.do";
		let headerObj = new Headers();
		headerObj.append('content-type','application/x-www-form-urlencoded');
 		fetch(url,{
						method : "post",
						headers:headerObj,
						body : "kgName="+kgName})
	   .then(response=>response.text())
	   .then(text=>{
		  document.querySelector('html').innerHTML = text;
	   })
		   
	   }   
		 
/* 	   let url = "/map/infrm.do?kgName="+kgName;
	   fetch(url,{
	   				method : "get",
	   				headers:headerObj}); */
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