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
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
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
			<div class="member_mypage">
				<div id="mypage_profile">
					<div>
						<c:choose>
							<c:when test ="${sessionScope.schoolMember != null}"><a href="/school/schoolprofile.do">프로필설정</a></c:when>
							<c:when test ="${sessionScope.userMember != null}"><a href="/user/userprofile.do">프로필설정</a></c:when>
						</c:choose>
					</div>
					<div>
						<c:choose>
							<c:when test ="${sessionScope.schoolMember != null}"><a id ="profile_photo" href="/school/schoolprofile.do"></a></c:when>
							<c:when test ="${sessionScope.userMember != null}"><a id ="profile_photo" href="/user/userprofile.do"></a></c:when>
						</c:choose>
						<div class="profile_name_board">
							<c:choose>
								<c:when test ="${sessionScope.schoolMember != null}">
									<a href="/school/schoolprofile.do" class="profile_name">${sessionScope.schoolMember.kgName}</a>
								</c:when>
								<c:when test ="${sessionScope.userMember != null}" >
									<a href="/user/userprofile.do"  class="profile_name">${sessionScope.userMember.nickname}</a>
								</c:when>
							</c:choose>
							
						</div>
					</div>
				</div>
				
				<div id ="mypage_board">
					<div class="mypage_detail">
						<div class="detail_board">
							<a>앨범</a>
						</div>
						<div class="detail_board">
							<a>식단</a>
						</div>
					</div>
					<div class="mypage_detail">
						<div class="detail_board">
							<a>공지사항</a>
						</div>
						<div class="detail_board">
							<a>알림장</a>
						</div>
					</div>
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