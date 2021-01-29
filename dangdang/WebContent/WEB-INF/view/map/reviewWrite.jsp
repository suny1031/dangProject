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
	<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
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
                <div class="desc_board">
                    <form action="${context}/board/upload.do" method="post"
                        enctype="multipart/form-data">
                        <div>
                            <div class="tit_board">
                                제목 : <input id = "titleInput" type="text" name="title" required="required"/>
                                <!--multiple : 여러개 파일 선택을 허용하는 속성-->
                                파일 : <input type="file" name="files" id="contract_file" multiple />
                            </div>
                            <div class="text">
                                <textarea id="board-content" class="board-content" name="content"> </textarea>
                            </div>
                            <div class="btn_section">
                                <button>전송</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
		</div>
		
		
		
		<!-- Footer -->
		<footer id="footer">
		
		</footer>

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