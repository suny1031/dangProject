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
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
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

		<div class="board">
		
		 <div id = "kinderWrap"><p id= "kinder">${kindergarten.kgName}</p></div><a id ="reviewWrite" href = "/review/write.do?kgName=${kindergarten.kgName}">후기 등록</a>
		<c:choose>
			<c:when test="${empty reviewList}">
				<div id = "noReviewBox"></div>
			</c:when>
		<c:otherwise>
		<c:forEach var="review" items = "${reviewList}" varStatus="status">
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
							<div class="photo"><img id ="img" src="/file${fileList[status.index].savePath}${fileList[status.index].renameFileName}">
							</div>
							</c:if>
						</div>			
					</div>	
				</c:forEach> 
		</c:otherwise>
		</c:choose>


	
		</div>

		<!-- Footer -->
		<footer id="footer"> 
			<div id = "footerMark">&copy;댕댕아놀면뭐하니?</div>
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