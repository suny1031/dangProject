<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../../../../resources/css/main.css" />
<link rel="stylesheet" href="../../../../resources/css/member.css" />
<link rel="stylesheet" href="/resources/css/myPage.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
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
		<section class="user_board">
			<div class="member_mypage">
				<div id="mypage_profile">
					<div id= "personal_photo">
						<c:choose>
							<c:when test ="${sessionScope.schoolMember != null}">
							
							<c:if test="${!empty photoList}">
							<a href="/school/schoolprofile.do"><img id ="profile_photo" src="/file${photoList[0].savePath}${photoList[0].renameFileName}"></a><br>
							</c:if>				
							<c:if test="${empty photoList}">
							<a id ="profile_photo2" href="/school/schoolprofile.do" ></a><br>
							</c:if>
							
							</c:when>
							
							<c:when test ="${sessionScope.userMember != null}"><a id ="profile_photo2" href="/user/userprofile.do"></a><br></c:when>
						</c:choose>
						<div class="profile_name_board">
							<c:choose>
								<c:when test ="${sessionScope.schoolMember != null}">
									<a class="profile_name">${sessionScope.schoolMember.kgName}</a><i class="fas fa-cog profilebtn" onclick="location.href='/school/schoolprofile.do'"></i>
								</c:when>
								<c:when test ="${sessionScope.userMember != null}" >
									<a class="profile_name">${sessionScope.userMember.nickname}</a><i class="fas fa-cog profilebtn" onclick="location.href='/user/userprofile.do'"></i>
								</c:when>
							</c:choose>
							<br>
						</div>
						
						<c:if test="${!empty schoolMember}">
							<button id="class_btn" onclick="location.href='/school/kinderclass.do'">학급관리</button>
						</c:if>
					</div>
				</div>
				<div id="mypage_board">
				  <div class="mypage_detail">
				    <div class="detail_board">
				      <c:choose>
				        <c:when test="${sessionScope.schoolMember != null}">
				          <a href="/album/kAlbumview.do" class="school_photo">앨범</a>
				        </c:when>
				        <c:otherwise><!-- userMember일 때 -->
				          <a  href=/album/uAlbumview.do class="user_photo">앨범</a>
				        </c:otherwise>
				      </c:choose>
				    </div>
				    
				    
				    
				    
				    <div class="detail_board">
				        <c:if test="${sessionScope.schoolMember != null}">
				          <a href="/reservation/mngngRsrvt.do" class="school_photo">예약</a>
				        </c:if>
				        
				        <c:if test="${sessionScope.userMember != null}">
				        	<a href="/reservation/mngngRsrvt2.do" class="school_photo">예약</a>
				        </c:if>
				        
				      <div class="box">
				        <div id = "reservationWrap">
							<c:if test="${sessionScope.schoolMember != null}">
							  <c:forEach var="reservation" items="${reservationPreview}" varStatus="status">
							    <div class="preview">
							      <div>${status.count}</div>
							      <div>${reservation.rsIdx}</div>
							      <div>${reservation.userId}</div>
							      <div>${reservation.regDate}</div>
							    </div>
							  </c:forEach>
							</c:if>
							
							
							<c:if test="${sessionScope.userMember != null}">
							  <c:forEach var="reservation" items="${reservationPreview}" varStatus="status">
							    <div class="preview">
							      <div>${status.count}</div>
							      <div>${reservation.rsIdx}</div>
							      <div>${reservation.userId}</div>
							      <div>${reservation.regDate}</div>
							    </div>
							  </c:forEach>
							</c:if>
				        </div>
				      </div>
				    </div>
				  </div>
				  
				  
				  
				  <div class="mypage_detail">			   			    
			    		<div class="detail_board">
				    	<c:if test="${sessionScope.schoolMember != null}">
				     		 <a href="/board/kindergardenview.do">공지사항</a>
				      	</c:if>
				      	<c:if test="${sessionScope.userMember != null}">
				      		<a href="/board/userview.do">공지사항</a>
				      	</c:if>
				   <div class="box">
				        <div id = "reservationWrap">
							<c:if test="${sessionScope.schoolMember != null}">
							  <c:forEach var="board" items="${NoticePreview}" varStatus="status">
							    <div class="preview">
							      <div style="width: 60%; text-align: center;">${board.content}</div>
							      <div style="width: 20%; margin-left: 20%;">${board.regDate}</div>
							    </div>							 
							  </c:forEach>
							</c:if>
							
							
							<c:if test="${sessionScope.userMember != null}">
							  <c:forEach var="board" items="${NoticePreview}" varStatus="status">
							    <div class="preview">
							   	  <div style="width: 60%; text-align: center;">${board.content}</div>
							      <div style="width: 20%; margin-left: 20%;">${board.regDate}</div>
							    </div>							    
							  </c:forEach>
							</c:if>
				        </div>
				      </div>
				      	
				    </div>


				    <div class="detail_board">
				    	<c:if test="${sessionScope.schoolMember != null}">
				     		 <a href="/diary/kindergardenview.do">알림장</a>
				      	</c:if>
				      	<c:if test="${sessionScope.userMember != null}">
				      		<a href="/diary/userview.do">알림장</a>
				      	</c:if>
				   <div class="box">
				        <div id = "reservationWrap">
							<c:if test="${sessionScope.schoolMember != null}">
							  <c:forEach var="diary" items="${diaryList}" varStatus="status">
							    <div class="preview">
							      <div style="width: 60%; text-align: center;">${diary.content}</div>
							      <div style="width: 20%; margin-left: 20%;">${diary.regDate}</div>
							    </div>							 
							  </c:forEach>
							</c:if>
							
							
							<c:if test="${sessionScope.userMember != null}">
							  <c:forEach var="diary" items="${diaryList}" varStatus="status">
							    <div class="preview">
							   	  <div style="width: 60%; text-align: center;">${diary.content}</div>
							      <div style="width: 20%; margin-left: 20%;">${diary.regDate}</div>
							    </div>							    
							  </c:forEach>
							</c:if>
				        </div>
				      </div>
				      	
				    </div>
				    
				  </div>
				</div>
			</div>
		</section>
		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">

			</ul>
			<ul class="copyright">
				<li>&copy;댕댕아놀면뭐하니?</li>
			</ul>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="../../../../resources/js/jquery.min.js"></script>
	<script src="../../../../resources/js/jquery.scrollex.min.js"></script>
	<script src="../../../../resources/js/jquery.scrolly.min.js"></script>
	<script src="../../../../resources/js/browser.min.js"></script>
	<script src="../../../../resources/js/breakpoints.min.js"></script>
	<script src="../../../../resources/js/util.js"></script>
	<script src="../../../../resources/js/main.js"></script>
	<script src="../../../../resources/js/member.js"></script>
	
	

</body>
</html>