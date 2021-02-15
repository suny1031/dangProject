<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<%@ page import="com.dang.board.model.service.BoardService"%>
<%@ page import="com.dang.member.school.model.vo.SchoolMember"%>
<%@ page import="com.dang.board.model.vo.Board"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림 게시판 글쓰기 페이지 (업주)</title>
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
								<c:choose>
									<c:when test="${sessionScope.schoolMember != null}">
										<li><a href="/school/schoolpage.do">마이페이지</a></li>
									</c:when>
									<c:when test="${sessionScope.userMember != null}">
										<li><a href="/user/userpage.do">마이페이지</a></li>
									</c:when>
								</c:choose>
								<li><a href="/map/map.do">유치원 찾기</a></li>
								<li><a href="/reservation/calendar.do">캘린더</a></li>
								<c:choose>
									<c:when test="${sessionScope.schoolMember != null}">
										<li><a href="/school/logout.do">로그아웃</a></li>
									</c:when>
									<c:when test="${sessionScope.userMember != null}">
										<li><a href="/user/logout.do">로그아웃</a></li>
									</c:when>
								</c:choose>

							</ul>
						</div></li>
				</ul>
			</nav>
		</header>

		<!-- Main -->
		<section class="board">
			<div class="content">
				<h2 id="tit" class="mainfont">알림장</h2>
				<table class="boardView-table">
					<thead class="boardView-thead">
							<th colspan="2" class="boardView-th">알림장 상세 페이지</th>
					</thead>
					<tbody class="boardView-tbody">
						<tr>
							<td class="boardView-title">제목</td>
							<td class="boardView-title-content" name="boardViewTitle">${board.title }</td>
						</tr>
						<tr>
							<td class="boardView-writer">유치원</td>
							<td class="boardView-writer-content">${board.kgName }</td>
						</tr>
						<tr>
							<td class="boardView-date">작성 일자</td>
							<td class="boardView-date-content">${board.regDate }</td>
						</tr>
						<tr>
							<td class="boardView-content">내용</td>
							<td class="boardView-content-content" name="boardViewContent">${board.content }</td>
						</tr>

					</tbody>
				</table>
				<a href="/board/listboard1.do" class="bt-list">목록</a> 
				<a href="modifyboard.do?bdIdx=${board.bdIdx }" class="bt-update">수정</a>
				<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="Delete.do?bdIdx=${board.bdIdx }" class="bt-delete">삭제</a>
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