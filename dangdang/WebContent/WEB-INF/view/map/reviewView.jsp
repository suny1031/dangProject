<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/resources/css/main.css" />
<link rel="stylesheet" href="/resources/css/review.css" />
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
			<div class="content">
				<h2 class="tit">*게시판</h2>
				<div class="desc_board">
					<h4 class="tit_board">제목 :</h4>
					<div class="info">
						<span>게시글 번호 : ${data.board.bdIdx}</span> <span>등록일 :
							${data.board.regDate}</span> <span>작성자 : ${data.board.userId}</span>
					</div>
					<div class="info">
						<c:forEach var="file" items="${data.fileList}">
							<button type="button" class="btn_down-file"
								onclick="downloadFile('${file.originFileName}','${file.renameFileName }','${file.savePath}')">${file.originFileName}</button>
							<br>
						</c:forEach>

					</div>
					<div class="text">${data.board.content}</div>
					<div class="btn_section btn_list">
						<button style="color: white" onclick="submitData('list')">
							<span>목록</span>
						</button>
					</div>

					<div class="btn_section btn_delete">
						<button style="color: white">
							<span>삭제</span>
						</button>
					</div>
					<div class="btn_section btn_modify">
						<button style="color: white">
							<span>수정</span>
						</button>
					</div>
				</div>
			</div>
		</div>



		<!-- Footer -->
		<footer id="footer"> </footer>

	</div>

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