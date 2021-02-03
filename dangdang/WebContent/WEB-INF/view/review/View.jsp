<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
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
		
		 <div id = "kinderWrap"><p id= "kinder">${kindergarten.kgName}</p></div><a href = "/review/write.do?kgName=${kindergarten.kgName}">후기 등록</a>
		<c:choose>
			<c:when test="${empty reviewList}">
				<div id = "noReviewBox">등록된 후기가 없습니다 <a href = "/review/write.do?kgName=${kindergarten.kgName}">후기 등록</a></div>
			</c:when>
		<c:otherwise>
		<c:forEach var="review" items = "${reviewList}">
					<div class="reviewWrap">
						<div class="dataWrap fristWrap">
							<div class="wrap">
								<div class="user data">${review.userName}</div>
								<div class="title data">${review.title}</div>
								<div class="date data">${review.regDate}</div>
								<div class="contentWrap">
									<textarea
										class="content" readonly="readonly">${review.content}</textarea>
								</div>
							</div>
							<c:if test="${!empty fileList}">
							<c:forEach var="file" items = "${fileList}">
							<div class="photo"><img id ="img" src="/${file.savePath}${file.renameFileName}">
							</div>
							</c:forEach> 
							</c:if>
						</div>			
					</div>	
				</c:forEach> 
		</c:otherwise>
		</c:choose>


	
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