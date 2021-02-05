<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림 게시판 (업주)</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${context}/resources/css/main.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${context}/resources/css/board.css" />
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
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
								<li><a href="generic.html">마이페이지</a></li>
								<li><a href="/map.do">유치원 찾기</a></li>
								<li><a href="#">캘린더</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>


		<!-- Main -->
		<section class="board">
			<div class="boardList-wrap">
				<div class="boardList">
					<div class="boardList-head">
						<div class="bd_no_idx">번호</div>
						<div class="title">제목</div>
						<div class="kg_name">글쓴이</div>
						<div class="reg_date">작성일</div>
					</div>
					<div class="boardList-body">
						<div class="item">
							<div class="bd_no_idx">5</div>
							<div class="title"><a href ="#">댕댕스쿨 5</a></div>
							<div class="kg_name">OO유치원</div>
							<div class="reg_date">2021-02-04</div>
						</div>
						<div class="item">
							<div class="bd_no_idx">4</div>
							<div class="title"><a href ="#">댕댕스쿨 4</a></div>
							<div class="kg_name">OO유치원</div>
							<div class="reg_date">2021-02-04</div>
						</div>
						<div class="item">
							<div class="bd_no_idx">3</div>
							<div class="title"><a href ="#">댕댕스쿨 3</a></div>
							<div class="kg_name">OO유치원</div>
							<div class="reg_date">2021-02-04</div>
						</div>
						<div class="item">
							<div class="bd_no_idx">2</div>
							<div class="title"><a href ="#">댕댕스쿨 2</a></div>
							<div class="kg_name">OO유치원</div>
							<div class="reg_date">2021-02-04</div>
						</div>
						<div class="item">
							<div class="bd_no_idx">1</div>
							<div class="title"><a href ="#">댕댕스쿨 1</a></div>
							<div class="kg_name">OO유치원</div>
							<div class="reg_date">2021-02-04</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="paging">
				<a href="#" class="bt first">처음페이지</a>
				<a href="#" class="bt prev">이전 페이지</a>
				<a href="#" class="num on">1</a>
				<a href="#" class="bt next">다음 페이지</a>
				<a href="#" class="bt last">마지막 페이지</a>
				<a href ="#" class="writing_box">글쓰기</a>
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
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.scrollex.min.js"></script>
	<script src="resources/js/jquery.scrolly.min.js"></script>
	<script src="resources/js/browser.min.js"></script>
	<script src="resources/js/breakpoints.min.js"></script>
	<script src="resources/js/util.js"></script>
	<script src="resources/js/main.js"></script>

</body>
</html>