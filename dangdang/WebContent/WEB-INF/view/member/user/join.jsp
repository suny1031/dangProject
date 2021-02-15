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
		<section class="user_board">
			<form action="${context}/user/mailauth.do" method="post" id="form_join">
				<fieldset id="join_field">
					<table id ="join_table">
						<tr><td>아이디<span class="valid_info" id ="idCheck"></span></td></tr>
						<tr>
							<td><input type="text" name="id" id="id" size=22% placeholder ="아이디를 입력하세요" required>
								<button type="button" onclick="idCheck()">check</button>
							</td>
						</tr>
						
						<tr><td>비밀번호<span class="valid_info" id = "pw_confirm"></span></td></tr>
						<tr>
							<td><input type="password" name="pw" id="pw" size=28% placeholder ="비밀번호를 입력하세요" required>
							
							</td>
						<tr>
						<tr><td>비밀번호 확인<span class="valid_info" id = "doubleCheckPw" ></span></td></tr>
						<tr>
							<td><input type="password" name="checkpw" id="checkpw" size=28% placeholder ="비밀번호를 확인하세요" required></td>
						<tr>
						<tr><td>이름</td></tr>
						<tr>
							<td><input type="text" name="username" size=28% required></td>
						<tr>
						<tr><td>유치원명</td></tr>
						<tr>
							<td><input type="text" name="kinder" size=28% placeholder ="등록 유치원 있는 경우만 입력"></td>
							<!--  <button type="button" onclick="kgCheck()">search</button></td>-->
						<tr>
						<tr><td>이메일</td></tr>
						<tr>
							<td><input type="email" name="email" size=28% placeholder ="사용중인 이메일을 입력하세요" required></td>
						<tr>
						<tr><td>전화번호</td></tr>
						<tr>
							<td><input type="tel" name="tell" size=28% required></td>
						<tr> 
						<tr><td>닉네임</td></tr>
						<tr>
							<td><input type="text" name="nickname" size=28% required></td>
						<tr>
						<tr> 
						<tr><td>생년월일</td></tr>
						<tr>
							<td><input type="date" name="birth" required></td>
						<tr>
						
						<tr>
							<td id="join_btn_part"><button type="submit">회원가입</button> <button type="reset">취	소</button></td>
						</tr>
						
					</table>
				</fieldset>
			</form>
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