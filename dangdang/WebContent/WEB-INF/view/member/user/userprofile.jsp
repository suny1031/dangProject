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
		<section class="myprofile_board">
			<div id="user_profile">
				<div class ="profile_info">
					<div id="separate_form">
						<div id="personal_info">
							<form action="${context}/user/info.do" method="post" id="modify_form">
								<fieldset class="info_form">
									<legend>기본정보</legend>
										<ul id="modify_info">
											<li>아이디
												<br><input type="text" value="${sessionScope.userMember.userId}" id="id" name="id" readonly>
											</li>
											<li>비밀번호
												<br><input type ="text" id="pw "name="pw">
											</li>
											<li>비밀번호 확인
												<br><input type ="password">
											</li>
											<li>이름
												<br><input type ="text" value="${sessionScope.userMember.userName}" id="username" name="username">
											</li>
											<li>닉네임
												<br><input type ="text" value="${sessionScope.userMember.nickname}" id="nickname" name="nickname">
											</li>
											<li>생년월일
												<br><input type ="date" value="${sessionScope.userMember.birth}" id="birth" name="birth">
											</li>
											<li>휴대폰번호
												<br><input type ="text" value="${sessionScope.userMember.phoneNumber}" id="phoneNumber" name="phoneNumber">
											</li>
											<li id="modify_btn"><button type="submit">프로필수정</button></li>
											
										</ul>
								</fieldset>
							</form>
						</div>
						<div id="kg_form">
						<fieldset class="info_form">
							<legend>유치원정보</legend>
							<h2 id="kg_info">${sessionScope.userMember.kgName}<br>[${sessionScope.userMember.className}] </h2>
						</fieldset>
						</div>
					</div>
						
					<div id="widthdraw_part">
						<button onclick="withdraw()">회원탈퇴하기</button>
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
	<script type="text/javascript">
	
	let login = () => {
		return location.href="/mypage/userpage.do";
	}
	
	</script>

</body>
</html>