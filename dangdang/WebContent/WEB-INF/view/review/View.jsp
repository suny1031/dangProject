<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/resources/css/main.css" />
<link rel="stylesheet" href="/resources/css/view.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<noscript>
	<link rel="stylesheet" href="/resources/css/noscript.css" />
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
								<li><a href="/map.do">유치원 찾기</a></li>
								<li><a href="#">캘린더</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>








		<!-- Main -->

		<div class="board">
			<div class="reviewWrap">

				<div class="dataWrap fristWrap">
					<div class="user data">장군짱</div>
					<div class="tite data">유치원 좋아여</div>
					<div class="date data">2021/01/31</div>
					<div class="contentWrap">
						<textarea class="content">강지지아짊ㄴ아린아리나이라닝라ㅣㅁㄴㅇ린앎ㄴ아림ㄴㅇ린아린아리ㅏㅁㄴ이람ㄴ이라민아린망림나일나ㅣㄹㄴ림나임나ㅣㄴㅇ라ㅣ</textarea>
						<div class="photo"></div>
					</div>

				</div>

			</div>

		</div>

		<!-- Footer -->
		<footer id="footer"> </footer>

	</div>

	<!--ViewScripts-->
	<script type="text/javascript">
		function submitData(url) {
			location.href = url;
		}

		function downloadFile(ofname, rfname, savePath) {
			let params = {
				'ofname' : ofname,
				'rfname' : rfname,
				'savePath' : savePath
			};

			location.href = '${context}' + "/board/download.do?"
					+ urlEncodeForm(params);
		}
	</script>


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