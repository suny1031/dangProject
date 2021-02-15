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
	<link rel="stylesheet" href="/resources/css/write.css" />
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<!-- <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet"> -->
	<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">	
	    <style>
        #star_grade a{
           text-decoration: none;
           color: gray;
       }
       #star_grade a.on{
           color: red;
       }
   </style>
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
			<div class="reviewWrap">
				<form id = "writeForm" action="${context}/review/upload.do?kgName=${kgName}" method="post" enctype="multipart/form-data">
					<div class="formWrap">
						<input id="titleInput" type="text" name="title" required="required" placeholder="제목을 입력해주세요." maxlength="10" autocomplete="off"/>
						<!--multiple : 여러개 파일 선택을 허용하는 속성-->
						<input id = "file" type='file' name='files' accept='image/jpg,image/jpeg,image/gif,image/png' onChange="chk(this)">
						<textarea id="content" class="board-content" name="content" required="required"></textarea>
						<div id="btnWrap">
							<button class = "btn">등록</button>
						</div>
					</div>
				</form>
			</div>
			
		</div>
		
		
		
		<!-- Footer -->
		<footer id="footer">
			<div id = "footerMark">&copy;댕댕아놀면뭐하니?</div>
		</footer>

	</div>
	<script>
	let check = false;
	let file = document.querySelector('#file');
	
	function chk(obj) {
	    if (/(\.gif|\.jpg|\.jpeg|\.png)$/i.test(obj.value) == false) {
	        alert("이미지 형식의 파일을 선택하십시오");
	        check = false;
	        return;
	    }else{
	        check = true;
	    }
	}
		
	    document.querySelector('#writeForm').addEventListener('submit',(e) => {
		    if (!check) {
	    	console.dir(check)
		         e.preventDefault();
		          alert("이미지 형식의 파일을 선택하십시오");
			      return;
			}
	    });
	


	
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