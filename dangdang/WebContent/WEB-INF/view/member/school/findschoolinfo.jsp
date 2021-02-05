<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../../../../resources/css/main.css" />
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
				<div id="dangmark"> </div>
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
								<li><a href="#">캘린더</a></li>
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
			<div class="find_info">
			<span id="find_user_title">유치원 아이디/비밀번호 찾기</span>
				<div id="find_id">
					<form action="${context}/school/findschoolid.do" method="post" id="form_find_id">
						<fieldset id="find_id_field">
							<table id ="find_id_table">
								<tr><td>유치원명</td></tr>
								<tr>
									<td><input type="text" name="kgname" size=28% required></td>
								<tr>
								<tr><td>유치원 전화번호</td></tr>
								<tr>
									<td><input type="tel" name="kgtell" size=28% required></td>
								<tr> 
								<tr>
									<td id="find_id_btn"><button type="submit">아이디 찾기</button></td>
								</tr>
							</table>
						</fieldset>
					</form>
				</div>
				<div id="find_pw">
					<form action="${context}/school/findschoolpw.do" method="post" id="form_find_pw">
						<fieldset id="find_pw_field">
							<table id ="find_pw_table">
								<tr><td>아이디</td></tr>
								<tr>
									<td><input type="text" name="username" size=28% required></td>
								<tr>
								<tr><td>유치원 이메일</td></tr>
								<tr>
									<td><input type="email" name="email" size=28% required></td>
								<tr> 
								<tr>
									<td id="find_id_btn"><button type="submit">비밀번호 찾기</button></td>
								</tr>
							</table>
						</fieldset>
					</form>
				</div>
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
	<script src="../../../../resources/js/jquery.min.js"></script>
	<script src="../../../../resources/js/jquery.scrollex.min.js"></script>
	<script src="../../../../resources/js/jquery.scrolly.min.js"></script>
	<script src="../../../../resources/js/browser.min.js"></script>
	<script src="../../../../resources/js/breakpoints.min.js"></script>
	<script src="../../../../resources/js/util.js"></script>
	<script src="../../../../resources/js/main.js"></script>
	<script type="text/javascript">
	
	<!--아이디 확인을 위해 필요한 js -->
	let idCheck = () => {
		let idCheckFlg = false;
		let headerObj = new Headers();
		headerObj.append('content-type', "application/x-www-form-urlencoded");
		
		
		<!-- 사용자가 입력한 아이디값을 받아서 -->
		let userId = id.value; <!--id가 id인 데이터의 value값 -->
		let idCheck = document.querySelector('#idCheck');
		let url = "/user/idcheck.do"
		
		if(userId){ <!--true일떄 -->
			fetch(url,{
				method: "post",
				headers: headerObj,
				body: "userId=" + userId
				
			}).then(response => response.text()) <!--then해주면 응답(response)이 넘어옴, 바로 return -->
			  .then((message)=>{ <!--message가 넘어올 것-->
				if(message == 'available'){
					idCheckFlg = true;
					idCheck.innerHTML = '사용 가능한 아이디 입니다.';
				} else {
					
					console.dir(message);
					idCheck.innerHTML = '사용 불가능한 아이디 입니다.';
				}				
			  }).catch(error => {
				 
				  error.alertMessage();
			  })
		} else {
			alert('아이디를 입력하지 않았습니다.');
			
		}
	}
	
	/*
	
	//아이디체크와 비밀번호 조합이 잘되었는지 확인하는 js
	   document.querySelector('#form_join').addEventListener('submit',(e) => {
		     //  요소의 아이디로 엘리먼트 객체 호출 가능(웹표준이 아님)    
		      if(!idCheckFlg){
		         alert("아이디 중복검사를 통과하지 못했습니다.");
		         id.value = "";
		         e.preventDefault();
		      }
		      
		      let password = pw.value;
		      let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;
		     
		      if(!(regExp.test(password))){
		         //form의 데이터 전송을 막음
		         e.preventDefault();
		         pw_confirm.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상이어야 합니다.';
		         pw.value = '';
		      }
		   }); 
	*/
	
	</script>

</body>
</html>