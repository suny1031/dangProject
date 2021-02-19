<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../../../../resources/css/main.css" />
<link rel="stylesheet" href="../../../../resources/css/member.css" />
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








		<!-- Main -->
		<section class="my_user_profile_board">
			<div id="user_profile">
				<div class ="profile_info">
				
				
					<div id="separate_form">
							<div id="modify_form">
								<div class="info_form">
										<ul id="modify_info">
											<h3>기본정보</h3>
											<li>아이디
												<br><input type="text" value="${sessionScope.userMember.userId}" id="id" name="id" readonly>
											</li>
											<li>비밀번호<span class="valid_info" id = "pw_confirm" style="display:none"></span>
												<br><input type ="password"  id="pw" name="pw" placeholder="비밀번호를 입력하세요" required>
											</li>
											<li>비밀번호 확인
												<br><input type ="password" id="checkpw">
											</li>
											<li>이름
												<br><input type ="text" value="${sessionScope.userMember.userName}" id="username" name="username">
											</li>
											<li>닉네임
												<br><input type ="text" value="${sessionScope.userMember.nickname}" id="nickname" name="nickname">
											</li>
											<li>이메일
												<br><input type ="text" value="${sessionScope.userMember.email}" id="email" name="email">
											</li>
											<li>생년월일
												<br><input type ="date" value="${sessionScope.userMember.birth}" id="birth" name="birth">
											</li>
											<li>휴대폰번호
												<br><input type ="text" value="${sessionScope.userMember.phoneNumber}" id="phonenumber" name="phoneNumber">
											</li>
											<li id="modify_btn"><button type="submit" onclick="modifyInfo()" id="modify_user_info">프로필수정</button></li>	
										</ul>
								</div>
								<div id="user_kg_form">
									<div class="user_kg_separate">
										<div class="school_info">
											<div>
												<h3>유치원정보</h3><br>
												<input type ="text" value="${sessionScope.userMember.kgName}" id="kg_info" name="kg_info" readonly>
											</div>
										</div>
									</div>
								</div>
							</div>
					</div>
						
					
					<div id="widthdraw_part">
						<button id="withdraw_btn" onclick="withdraw()">회원탈퇴하기</button>
					</div>
				
				
				</div>
			</div>
		</section>













		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">

			</ul>
			<ul class="copyright">
				<li>&copy;댕댕아놀면뭐하니?</li>
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
	<script src="../../../../resources/js/member.js"></script>

	

</body>
</html>