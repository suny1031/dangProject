<%@page import="com.dang.diary.model.service.DiaryService"%>
<%@page import="com.dang.diary.model.vo.Diary"%>
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
	<link rel="stylesheet" href="/resources/css/detail.css" />
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
			<div id = "detailWrap">
				<div id = "detailBox">
					<table class="detail-table">
					<thead class="detail-thead">
							<th colspan="2" class="detail-th">공지사항 상세 페이지</th>
					</thead>
					<tbody class="detail-tbody">
						<tr>
							<td class="detail-title">제목</td>
							<td class="detail-title-content">${board.title}</td>
						</tr>
						<tr>
							<td class="detail-writer">유치원</td>
							<td class="detail-writer-content">${board.kgName}</td>
						</tr>
						<tr>
							<td class="detail-date">작성 일자</td>
							<td class="detail-date-content">${board.regDate}</td>
						</tr>
						<tr>
							<td class="detail-content">내용</td>
							<td class="detail-content-content">${board.content}</td>
						</tr>

					</tbody>
				</table>
				<c:if test="${!empty schoolMember}">
					<div id = "btnBox"><button class = "btn"><a href="/board/kindergardenview.do">목록</a></button><button class = "btn"><a href="/board/mdfd.do?bdIdx=${board.bdIdx}">수정</a></button> <button class = "btn"><a href="/board/delete.do?bdIdx=${board.bdIdx}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a></button></div>
				</c:if>
				<c:if test="${empty schoolMember}">
				</c:if>
				<c:if test="${sessionScope.userMember != null}">
					<div id = "backList"><a href="/board/userview.do">목록</a></div>
				</c:if>
				
				</div>
			</div>
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