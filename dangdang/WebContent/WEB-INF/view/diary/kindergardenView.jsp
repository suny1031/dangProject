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
	<link rel="stylesheet" href="/resources/css/diaryView.css" />
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
	<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">	
	
	<!--글씨 체-->
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<!--     -->
	
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
			<div id = "wrap">
				<div id = "title">알림장</div>
					<center>
						<table class = "table" align="center">
							<thead>
								<tr  align="center">
									<td class = "infrm" width="7%">번호</td>
									<td class = "infrm" width="7%">작성자</td>
									<td class = "infrm" width="7%">제목</td>
									<td class = "infrm" width="10%">작성일</td>							
								</tr>
							</thead>
							<tbody>
							<c:forEach var="diary" items="${list}">
							<tr  align="center">
								<td class = "bdIdx"><a href="/diary/detail.do?bdIdx=${diary.bdDiaryIdx}">${diary.bdDiaryIdx}</a></td>
								<td>${diary.kgName}</td>
								<td>${diary.title}</td>
								<td>${diary.regDate}</td>							
							</tr>
							</c:forEach>
								
							<tr>
								<td align="center" colspan="4" style="font-size: 0.7vw">
								<c:if test="${count > 0}">
								   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
								   <c:set var="startPage" value="${pageGroupSize*(numPageGroup-1)+1}"/>
								   <c:set var="endPage" value="${startPage + pageGroupSize-1}"/>
								  
								   <c:if test="${endPage > pageCount}" >
								     <c:set var="endPage" value="${pageCount}" />
								   </c:if>
								         
								   <c:if test="${numPageGroup > 1}">
								        <a href="/diary/kindergardenview.do?pageNum=${(numPageGroup-2)*pageGroupSize+1 }">[이전]</a>
								   </c:if>
								   
								   <c:forEach var="i" begin="${startPage}" end="${endPage}">
								       <a href="/diary/kindergardenview.do?pageNum=${i}">
								        <font color=" #B22222" />
								          <c:if test="${currentPage == i}">
								          <font color="#bbbbbb" />
								        </c:if>
								        [${i}]
								       </font>
								       </a>
								   </c:forEach>
								   <c:if test="${numPageGroup < pageGroupCount}">
								        <a href="/diary/kindergardenview.do?pageNum=${numPageGroup*pageGroupSize+1}">[다음]</a>
								   </c:if>
								</c:if>
								</td>
							</tr>
						</tbody>
						</table>
					</center>
			
			
				<div id = "write">
					<button id ="writeBtn"><a href="/diary/write.do">글쓰기</a></button>
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