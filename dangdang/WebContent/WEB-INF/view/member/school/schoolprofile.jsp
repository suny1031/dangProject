<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../../../../resources/css/main.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
</head>
<body class="is-preload">

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header">
			<h1>
				<div id="dangmark"> </div>
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
								<li><a href="map/map.do">유치원 찾기</a></li>
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
		<section class="user_board">
			<div id="school_profile">
				<form action="${context}/school/info.do" method="post" id="school_modify_form">
					<fieldset class="school_info_form">
						<div id="school_info">
							<ul id="school_modify_info">
								<li>업체명
									<br><input type="text" value="${sessionScope.schoolMember.kgName}" id="id" name="id" readonly>
								</li>
								<li>주소
									<br><textarea rows = "3" cols = "20" style = "resize: none">${sessionScope.schoolMember.kgAddress}</textarea>
								<li>학급명
									<br><input type ="text" value="${sessionScope.schoolMember.kgClassName}" id="nickname" name="nickname">
								</li>
								<li>전화번호
									<br><input type ="text" value="${sessionScope.schoolMember.kgTell}" id="phoneNumber" name="phoneNumber">
								</li>
								<li>운영시간
									<br><input type ="text" value="${sessionScope.schoolMember.kgOperateTime}" id="phoneNumber" name="phoneNumber">
								</li>
								<li>안내사항
									<br><textarea rows = "3" cols = "20" style = "resize: none">${sessionScope.schoolMember.kgNotice}</textarea>
								</li>
							</ul>
						</div>
						<div id="separate_part">
							<div id="school_service">
								<p>제공서비스</p>
								<input type = "checkbox" id = "isKg" name ="service"  value = "isKg"><label for ="isKg">유치원</label><br>
								<input type = "checkbox" id = "isCafe"  name ="service" value = "isCafe"><label for = "isCafe"> 카페</label>	<br>
								<input type = "checkbox" id = "isHotel"  name ="service" value = "isHotel"><label for = "isHotel"> 호텔</label>	<br>
								<input type = "checkbox" id = "isPickup"  name ="service" value = "isPickup"><label for ="isPickup"> 픽업서비스</label><br>
								<input type = "checkbox" id = "isMedic"  name ="service" value = "isMedic"><label for = "isMedic"> 메니컬센터</label>	<br>
								<input type = "checkbox" id = "isAcademy"  name ="service" value = "isAcademy"><label for = "isAcademy"> 아카데미</label><br>
								<input type = "checkbox" id = "isSpa"  name ="service" value = "isSpa"><label for = "isSpa"> 스파</label>
							</div>
							<div id="school_photo">
								<p>유치원사진</p>
								<input type ="file" id="kg_photo">
							</div>
						</div>
					</fieldset>
				</form>
				<div id="modify_part">
					<button type="submit" id="school_modify_btn">프로필수정</button>
				</div>
			</div>
		</section>













		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">

			</ul>
			<ul class="copyright">
				<li>&copy; Untitled</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
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
	<script src="../../../../resources/js/login.js"></script>
	
</body>
</html>