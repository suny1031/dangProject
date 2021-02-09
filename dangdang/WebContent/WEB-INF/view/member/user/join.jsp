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
			<form action="${context}/user/joinimpl.do" method="post" id="form_join">
				<fieldset id="join_field">
					<table id ="join_table">
						<tr><td>아이디<span class="valid_info" id ="idCheck"></span></td></tr>
						<tr>
							<td><input type="text" name="id" id="id" size=22% placeholder ="아이디를 입력하세요" required>
								<button type="button" onclick="idCheck()">check</button>
							</td>
						</tr>
						
						<tr><td>비밀번호<span class="valid_info" id = "pw_confirm"></span></td></tr>
						<tr>
							<td><input type="text" name="pw" id="pw" size=28% placeholder ="비밀번호를 입력하세요" required>
							
							</td>
						<tr>
						<tr><td>비밀번호 확인<span class="valid_info" id = "doubleCheckPw" ></span></td></tr>
						<tr>
							<td><input type="password" name="checkpw" id="checkpw" size=28% placeholder ="비밀번호를 확인하세요" required></td>
						<tr>
						<tr><td>이름</td></tr>
						<tr>
							<td><input type="text" name="username" size=28% required></td>
						<tr>
						<tr><td>유치원명</td></tr>
						<tr>
							<td><input type="text" name="kinder" size=28% placeholder ="등록 유치원 있는 경우만 입력"></td>
							<!--  <button type="button" onclick="kgCheck()">search</button></td>-->
						<tr>
						<tr><td>이메일</td></tr>
						<tr>
							<td><input type="email" name="email" size=28% placeholder ="사용중인 이메일을 입력하세요" required></td>
						<tr>
						<tr><td>전화번호</td></tr>
						<tr>
							<td><input type="tel" name="tell" size=28% required></td>
						<tr> 
						<tr><td>닉네임</td></tr>
						<tr>
							<td><input type="text" name="nickname" size=28% required></td>
						<tr>
						<tr> 
						<tr><td>생년월일</td></tr>
						<tr>
							<td><input type="date" name="birth" ></td>
						<tr>
						
						<tr>
							<td id="join_btn_part"><button type="submit">회원가입</button> <button type="reset">취	소</button></td>
						</tr>
						
					</table>
				</fieldset>
			</form>
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
	<script src="../../../../resources/js/member.js"></script>
	<script type="text/javascript">
	// JS 전역변수로 뺴주어야 한다.
	let idCheckFlg = false;
	
	// 있는 아이디인지 확인을 위해 필요한 js
	let idCheck = () => {
		
		let headerObj = new Headers();
		headerObj.append('content-type', "application/x-www-form-urlencoded");
		
		
		// 사용자가 입력한 아이디값을 받아서
		let userId = id.value; //id가 id인 데이터의 value값
		let idCheck = document.querySelector('#idCheck');
		let url = "/user/idcheck.do"
		
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
	
	
	
	// 아이디체크와 비밀번호 조합이 잘되었는지 확인하고 비밀번호가 서로 일치하는지 확인하는 js
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
		      } else {
		    	  
		    	  let firstPw =  pw.value;
				  let secondPw = checkpw.value;
				  //비밀번호 double check 메소드
				  if(firstPw != secondPw){
					
						document.querySelector("#pw_confirm").innerHTML = '비밀번호가 맞지 않습니다.';
						checkpw.value= ""; //pw의 value값 비워주기
						e.preventDefault(); //데이터전송 막기
					} else {
						document.querySelector("#pw_confirm").innerHTML = '비밀번호가 확인되었습니다.';
					}
			   
		      }
		      
		
		   }); 

	   


	
	</script>
	
</body>
</html>