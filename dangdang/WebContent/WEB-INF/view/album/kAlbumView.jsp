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
<link rel="stylesheet" href="/resources/css/kAlbumView.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
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
		
		
	

	<div class="board">
		<div id = "boardWrap">
			<div id = "addPhoto" onclick="addPopup()">사진 추가</div>
			<div id ="photoBox">
			<c:forEach items="${albumList}" var="album" varStatus="status">
				<img class = "fileImg" src="/file${album.savePath}${album.renameFileName}" onclick = "deletePopup(${album.fidx})"/>
			</c:forEach>
			</div>
			
			</div>
		</div>
		<!-- Footer -->
		<footer id="footer">
			<div id = "footerMark">&copy;댕댕아놀면뭐하니?</div>
		</footer>

	</div>

	<script language="javascript">
		  function addPopup() { window.open("addphoto.do", "a", "width=500, height=300, left=350, top=150"); }
	</script>
	
	<script type="text/javascript">
	  function deletePopup(obj) {
		
		  		console.log(obj)
			  let fidx = obj;
		  		console.log(fidx)
			  let headerObj = new Headers();
		      headerObj.append('content-type',"application/x-www-form-urlencoded");

	          fetch("/album/deletephoto.do",{	
	              method : "post",
	              headers : headerObj,
	               body : "fidx="+fidx
	           }).then(response => {
	               if(response.ok){
	                  return response.text();
	               }
	               throw new AsyncPageError(response.text());
	            })
	            .then((msg) => {
	               if(msg == 'success'){
						alert('삭제 성공하였습니다.')
						location.href = "/album/kAlbumview.do"
	               }
	            }).catch(error=>{
					alert('삭제 실패하였습니다.')
	            })
	          
	  
	  }
	  
	  
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