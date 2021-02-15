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
			<div id="class_board">
				<div id= "class_title">
					<h2>학급관리</h2>
				</div>
				<div id="class_form">
					<table>
						<tr>
							<td>번호</td>
							<td>아이디</td>
							<td>이름</td>
							<td>이메일</td>
							<td>핸드폰번호</td>
							<td>닉네임</td>
							<td><a>회원삭제</a></td>
						</tr>
						<c:forEach var="userMember" items="${classMemberList}" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${userMember.userId}</td>
								<td>${userMember.userName}</td>
								<td>${userMember.email}</td>
								<td>${userMember.phoneNumber}</td>
								<td>${userMember.nickname}</td>
								<td><a>삭제</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div>
					<input type=text id ="userId" name="userId" size="20">
					<button onclick= "userIdCheck()">추가할 회원 검색</button>
				</div>
				<div>
					<button onclick= "#">수정 완료</button>
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
	<script type="text/javascript">
		let userIdCheck = () => {
				
				let headerObj = new Headers();
				headerObj.append('content-type', "application/x-www-form-urlencoded");
				
				
				// 사용자가 입력한 아이디값을 받아서
				let userId = userId.value; //id가 id인 데이터의 value값
				let idCheck = document.querySelector('#idCheck');
				let url = "/school/classmembercheck.do"
				
				if(userId){ // true일때
					fetch(url,{
						method: "post",
						headers: headerObj,
						body: "userId=" + userId
						
					}).then(response => response.text()) // then해주면 응답(response)이 넘어옴, 바로 return
					  .then((message)=>{ // message가 넘어올 것
						if(message == 'available'){
							idCheckFlg = true;
							idCheck.innerHTML = '사용 가능한 아이디 입니다.';
						} else {
							idCheckFlg = false;
							idCheck.innerHTML = '사용 불가능한 아이디 입니다.';
						}				
					  }).catch(error => {
						 
						  error.alertMessage();
					  })
				} else {
					alert('아이디를 입력하지 않았습니다.');	
				}
			}
		
			
			
	
	
	</script>
	

</body>
</html>