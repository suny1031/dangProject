<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
								<li><a href="/main.do">마이페이지</a></li>
								<li><a href="/map.do">유치원 찾기</a></li>
								<li><a href="#">캘린더</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>








		<!-- Main -->
		<section class="user_board">
			<form action="${context}/member/mailauth.do" method="post" id="form_join">
				<fieldset id="join_field">
					<table id ="join_table">
						<tr><td>아이디</td></tr>
						<tr>
							<td><input type="text" name="id" id="id" size=20% placeholder ="아이디를 입력하세요" required><button type="button" id="idCheck">check</button></td>
						</tr>
						<tr><td>비밀번호</td></tr>
						<tr>
							<td><input type="text" name="pw" id="pw" size=28% placeholder ="비밀번호를 입력하세요" required></td>
						<tr>
						<tr><td>이름</td></tr>
						<tr>
							<td><input type="text" name="username" size=28% required></td>
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
		return location.href="/mypage/user/userpage.do";
	}
	
	</script>

</body>
</html>