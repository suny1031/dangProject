<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.dang.board.model.service.BoardService" %>
<%@ page import="com.dang.board.model.vo.Board" %>
<%@ page import="com.dang.member.school.model.vo.SchoolMember" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>알림 게시판 (업주)</title>
		<meta name="viewport"
			content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="preconnect" href="https://fonts.gstatic.com">
		<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
		<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
			rel="stylesheet">
		<link rel="stylesheet" href="/resources/css/board.css" />
		<link
			href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap"
			rel="stylesheet">
		<noscript><link rel="stylesheet" href="../../../../resources/css/noscript.css" /></noscript>
</head>
<body class="is-preload">

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<h1>
				<div id="dangmark"></div>
				<a href="/main.do" id="headermain" class="mainfont">댕댕아 놀면 뭐하니?</a>
			</h1>
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>MENU</span></a>
						<div id="menu">
							<ul>
								<li><a href="/main.do">Home</a></li>
								<c:choose>
									<c:when test ="${sessionScope.schoolMember != null}"><li><a href="/school/schoolpage.do">마이페이지</a></li></c:when>
									<c:when test ="${sessionScope.userMember != null}"><li><a href="/user/userpage.do">마이페이지</a></li></c:when>
								</c:choose>
								<li><a href="/map/map.do">유치원찾기</a></li>
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
		<section class="board">
			<div class="content">
				<table class="boardList">
					<h2 style="text-align: center; color: #8b4513;">알림장</h2>
						<thead class="boardList-head">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody class="boardList-body">
							<!-- boardlist 에 추가할 내용을 for 문으로 꺼내오기 -->
							<c:forEach var="boardList" items="${boardList }" varStatus="status" >
								<tr>
									<!-- 게시물 번호 -->
									<td>${boardList.getBdIdx() }</td>
									<!-- 제목 제목 클릭시 해당 게시물 번호를 보여줄 BoardView 로 연결 -->
									<c:choose>
								    	<c:when test="${sessionScope.schoolMember != null}">
								      		<td class="boardList-title"><a href = "/board/viewboard1.do?bdIdx=${boardList.getBdIdx() }">${boardList.getTitle() }</a></td>
								      	</c:when>
								      	<c:when test="${sessionScope.userMember != null}">
								      		<td class="boardList-title"><a href = "/board/viewboard2.do?bdIdx=${boardList.getBdIdx() }">${boardList.getTitle() }</a></td>
								      	</c:when>
								    </c:choose>
									<!-- 게시물을 작성한 유치원 이름 kgId 로 구분해야 할 듯 -->
									<td>${boardList.getKgName() }</td>
									<!-- 게시물 작성일자 -->
									<td>${boardList.getRegDate() }</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
				<%-- <!-- 게시판의 페이지 번호가 1번이 아닐 경우 이전 페이지 버튼을 만들어줌 -->
				<%
					if(pageNumber != 1){
				%>
					<a href="BoardList1.jsp?pageNumber=<%= pageNumber -1 %>" class="bt prev">이전 페이지</a>
					
				<!-- 다음페이지가 존재할 시 다음 페이지로 가는 버튼을 만들어준다. -->
				<%
					}if(boardService.nextPage(pageNumber + 1)){
				%>
				<!-- 다음 페이지로 넘어 갔을 때 이전페이지를 만들어 주기 위해 페이지 넘버에 1을 더해준다. -->
					<a href="BoardList1.jsp?pageNumber=<%= pageNumber +1 %>" class="bt next">다음 페이지</a>
				<%		
					}
				%> --%>
				<a href="/board/addboard.do" class="writing_box">글쓰기</a>
			</div>

			<!-- <div class="paging">
				<a href="#" class="bt first">처음페이지</a> 
				<a href="#" class="bt prev">이전 페이지</a> 
				<a href="#" class="num on">1</a> 
				<a href="#" class="bt next">다음 페이지</a> 
				<a href="#" class="bt last">마지막 페이지</a> 
				<a href="addboard.do" class="writing_box">글쓰기</a>
			</div>  -->
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