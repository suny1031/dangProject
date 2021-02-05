<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림 게시판 수정 페이지 (업주)</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${context}/resources/css/main.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="${context}/resources/css/board.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap"
	rel="stylesheet">
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
			<div class="content">
				<h2 id="tit" class="mainfont">알림장</h2>
				<br>
				<div id="desc_board">
					<form action="${context}/board/modifyboard.do" method="post"
						enctype="multipart/form-data">
						<div>
							<div id="tit_board" class="mainfont">
								제목 : <input type="text" class="title-box" name="title"
									required="required" /><br> 파일 첨부 : <input type="file"
									name="files" id="contract_file" multiple />
								<!-- multiple : 여러개 파일 선택을 허용하는 속성 -->
								<select id="select_class" class="mainfont">
									<option>반 이름</option>
									<option>깜식 반</option>
									<option>아롱 반</option>
									<option>희망 반</option>
								</select>
								<!-- 파일 : <input type="file" name="files" id="contract_file" multiple /> -->
							</div>
							<textarea id="board-content" class="noticefont" name="content"
								style="width: 99%; height: 600px;" required="required">
						</textarea>

							<div class="before-next">
								다음글 : <br> 이전글 :
							</div>

							<div class="WMC_box">
								<button class="mainfont">닫기</button>
							</div>
							<div class="WMC_box">
								<button class="mainfont">삭제</button>
							</div>
							<div class="WMC_box">
								<button class="mainfont">수정</button>
							</div>
						</div>
					</form>
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
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.scrollex.min.js"></script>
	<script src="resources/js/jquery.scrolly.min.js"></script>
	<script src="resources/js/browser.min.js"></script>
	<script src="resources/js/breakpoints.min.js"></script>
	<script src="resources/js/util.js"></script>
	<script src="resources/js/main.js"></script>

</body>
</html>