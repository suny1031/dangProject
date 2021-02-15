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
			<div class="find_info">
			<span id="find_user_title">견주 아이디/비밀번호 찾기</span>
				<div id="find_id">
					<div id="form_find_id">
						<div id="find_id_field">
							<table id ="find_id_table">
								<tr><td>이름</td></tr>
								<tr>
									<td><input type="text" id="username" name="username" size=28% required></td>
								</tr>
								<tr><td>휴대폰번호</td></tr>
								<tr>
									<td><input type="tel"id ="phone" name="phone" size=28% placeholder="123-456-7891" required></td>
								</tr> 
								<tr>
									<td id="find_id_btn"><button id="find_userid_btn" onclick ="findUserId()" type="submit">아이디 찾기</button></td>
								</tr>
							</table>
							<span id="find_id_result" style="display:none"></span>
						</div>
					</div>
				</div>
				<div id="find_pw">
					<div id="form_find_pw">
						<form action="${context}/user/finduserpw.do" method="post" id="find_pw_field">
							<table id ="find_pw_table">
								<tr><td>아이디</td></tr>
								<tr>
									<td><input type="text" name=id size=28% required></td>
								<tr>
								<tr><td>이메일</td></tr>
								<tr>
									<td><input type="email" name="email" size=28% required></td>
								<tr> 
								<tr>
									<td id="find_id_btn"><button type="submit">비밀번호 찾기</button></td>
								</tr>
							</table>
						</form>
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
	<script type="text/javascript"> const urlToFindId = '/user/finduserid.do';</script>
	<script type="text/javascript"> const urlToFinPw = '/user/finduserpw.do';</script>
	
	
	

</body>
</html>