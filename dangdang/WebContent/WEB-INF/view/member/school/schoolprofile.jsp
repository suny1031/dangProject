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
		<section class="myprofile_board">
			<div class="school_info_form" id="school_profile">
				<div id="school_modify_form">
					<fieldset >
						<div id="school_info_part">
							<div id ="school_info_detail">
								<ul id="school_modify_info">
									<li><h3>아이디</h3>
										<input type="text" value="${sessionScope.schoolMember.kgId}" id="kgId" name="kgId" readonly>
									</li>
									<li><h3>업체명</h3>
										<input type="text" value="${sessionScope.schoolMember.kgName}" id="kgName" name="kgName" readonly>
									</li>
									<li><br><h3>주소</h3>
										<textarea rows = "5" cols = "20" style = "resize: none" id="kgAddress" name="kgAddress">${sessionScope.schoolMember.kgAddress}</textarea>
									</li>
									<li><br><h3>전화번호</h3>
										<input type ="text" value="${sessionScope.schoolMember.kgTell}" id="kgTell" name="kgTell">
									</li>
									<li><br><h3>이메일</h3>
										<input type ="text" value="${sessionScope.schoolMember.kgEmail}" id="kgEmail" name="kgEmail">
									</li>
									<li><br><h3>운영시간</h3>
										<input type ="text" value="${sessionScope.schoolMember.kgOperateTime}" id="kgOperateTime" name="kgOperateTime">
									</li>
									<li><br><h3>안내사항</h3>
										<textarea rows = "5" cols = "20" style = "resize: none" id="kgNotice" name="kgNotice">${sessionScope.schoolMember.kgNotice}</textarea>
									</li>
									<li>
										<div id="modify_part">
											<button type="submit" id="school_modify_btn" onclick ="schoolModifyInfo()">기본 프로필수정</button>
										</div>
									</li>
								</ul>
							</div>							
						</div>
						
					</fieldset>
				</div>
				
				
				<div id="separate_part">
					<div id="school_service">
						<div id="school_service_form">
							<fieldset id="sort_school_service">
								<input id ="kgNameForService" type ="hidden" value="${sessionScope.schoolMember.kgName}"></input><h3> [제공서비스]</h3><br>
								<label for ="isKg">
								<c:choose>
									<c:when test ="${sessionScope.schoolService.isKg == 0}">
										<input type = "checkbox" id = "isKg" name ="isKg"  value = "0" checked="checked"> 유치원
									</c:when>
									<c:when test ="${sessionScope.schoolService.isKg != 0}">
										<input type = "checkbox" id = "isKg" name ="isKg"  value = "1"> 유치원
									</c:when>
								</c:choose>
								</label>
								
								<label for ="isCafe">
								<c:choose>
									<c:when test ="${sessionScope.schoolService.isCafe == 0}">
										<input type = "checkbox" id = "isCafe" name ="isCafe"  value = "0" checked="checked"> 카페</c:when>
									<c:when test ="${sessionScope.schoolService.isCafe != 0}">
										<input type = "checkbox" id = "isCafe" name ="isCafe"  value = "1"> 카페</c:when>
								</c:choose>
								</label>
								
								<label for ="isHotel">
								<c:choose>
									<c:when test ="${sessionScope.schoolService.isHotel == 0}">
										<input type = "checkbox" id = "isHotel" name ="isHotel"  value = "0" checked="checked"> 호텔</c:when>
									<c:when test ="${sessionScope.schoolService.isHotel != 0}">
										<input type = "checkbox" id = "isHotel" name ="isHotel"  value = "1"> 호텔</c:when>
								</c:choose>
								</label>
								
								<label for ="isPickup">
								<c:choose>
									<c:when test ="${sessionScope.schoolService.isPickup == 0}">
										<input type = "checkbox" id = "isPickup" name ="isPickup"  value = "0" checked="checked"> 픽업서비스</c:when>
									<c:when test ="${sessionScope.schoolService.isPickup != 0}">
										<input type = "checkbox" id = "isPickup" name ="isPickup"  value = "1"> 픽업서비스</c:when>
								</c:choose>
								</label>
								
								<label for ="isMedic">
								<c:choose>
									<c:when test ="${sessionScope.schoolService.isMedic == 0}">
										<input type = "checkbox" id = "isMedic" name ="isMedic"  value = "0" checked="checked"> 메디컬센터</c:when>
									<c:when test ="${sessionScope.schoolService.isMedic != 0}">
										<input type = "checkbox" id = "isMedic" name ="isMedic"  value = "1"> 메디컬센터</c:when>
								</c:choose>
								</label>
								
								<label for ="isAcademy">
								<c:choose>
									<c:when test ="${sessionScope.schoolService.isAcademy == 0}">
										<input type = "checkbox" id = "isAcademy" name ="isAcademy"  value = "0" checked="checked"> 아카데미</c:when>
									<c:when test ="${sessionScope.schoolService.isAcademy != 0}">
										<input type = "checkbox" id = "isAcademy" name ="isAcademy"  value = "1"> 아카데미</c:when>
								</c:choose>
								</label>
								
								<label for ="isSpa">
								<c:choose>
									<c:when test ="${sessionScope.schoolService.isSpa == 0}">
										<input type = "checkbox" id = "isSpa" name ="isSpa"  value = "0" checked="checked"> 스파</c:when>
									<c:when test ="${sessionScope.schoolService.isSpa != 0}">
										<input type = "checkbox" id = "isSpa" name ="isSpa"  value = "1"> 스파</c:when>
								</c:choose>
								</label>
						 		<button id="school_modify_btn" onclick="schoolModifyService()">제공 서비스 수정</button>
							</fieldset>
						</div>
					</div>
					<div action="${context}/school/profilephoto.do" method="post" id="school_photo_form">
						<div>
							<h3>유치원사진</h3><br>
							<label><input type ="file" id="kg_photo"></label>
						</div>
						<div id="photo_border">
							사진 첨부
						</div>
						<button id="file_upload_btn" onclick="schoolModifyPhoto()">파일 업로드</button>
					</div>
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
	<script src="../../../../resources/js/member.js"></script>
	
</body>
</html>