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
						<form action ="${context}/school/modifyservice.do?kgName=${sessionScope.schoolMember.kgName}" method="post" id="school_service_form"> <!-- action ="${context}/school/modifyservice.do" method="post" -->
							<fieldset id="sort_school_service">
								<h3> [제공서비스]</h3><br>
								<div class ="service_list">
								
								</div>
								<table class="kg_form">
									<tr class = "kg_pro">
									<td class="kg_service_list">유치원</td>
										<c:choose>
											<c:when test ="${schoolService.isKg == 0}">
												<td>제공<input type = "radio" name ="isKg" value="0"  checked="checked"></td>
												<td>비제공<input type = "radio" name ="isKg" value="1"></td>
											</c:when>
											<c:otherwise>
												<td>제공<input type = "radio"  name ="isKg" value="0"></td>
												<td>비제공<input type = "radio" name ="isKg" value="1" checked="checked" ></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</table>
								
							
								<table class="kg_form">
									<tr class = "kg_pro">
									<td class="kg_service_list">카페</td>
										<c:choose>
											<c:when test ="${schoolService.isCafe == 0}">
												<td>제공<input type = "radio" name ="isCafe" value="0"  checked="checked"></td>
												<td>비제공<input type = "radio" name ="isCafe" value="1"></td>
											</c:when>
											<c:otherwise>
												<td>제공<input type = "radio"  name ="isCafe" value="0"></td>
												<td>비제공<input type = "radio" name ="isCafe" value="1" checked="checked" ></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</table>
								
								
								<table class="kg_form">
									<tr class = "kg_pro">
									<td class="kg_service_list">호텔</td>
										<c:choose>
											<c:when test ="${schoolService.isHotel == 0}">
												<td>제공<input type = "radio" name ="isHotel" value="0"  checked="checked"></td>
												<td>비제공<input type = "radio" name ="isHotel" value="1"></td>
											</c:when>
											<c:otherwise>
												<td>제공<input type = "radio"  name ="isHotel" value="0"></td>
												<td>비제공<input type = "radio" name ="isHotel" value="1" checked="checked" ></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</table>
							
								
								<table class="kg_form">
									<tr class = "kg_pro">
									<td class ="kg_service_list">픽업서비스</td>
										<c:choose>
											<c:when test ="${schoolService.isPickup == 0}">
												<td>제공<input type = "radio" name ="isPickup" value="0"  checked="checked"></td>
												<td>비제공<input type = "radio" name ="isPickup" value="1"></td>
											</c:when>
											<c:otherwise>
												<td>제공<input type = "radio"  name ="isPickup" value="0"></td>
												<td>비제공<input type = "radio" name ="isPickup" value="1" checked="checked" ></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</table>
								
								
								<table class="kg_form">
									<tr class = "kg_pro">
									<td class ="kg_service_list">메디컬 센터</td>
										<c:choose>
											<c:when test ="${schoolService.isMedic == 0}">
												<td>제공<input type = "radio" name ="isMedic" value="0"  checked="checked"></td>
												<td>비제공<input type = "radio" name ="isMedic" value="1"></td>
											</c:when>
											<c:otherwise>
												<td>제공<input type = "radio"  name ="isMedic" value="0"></td>
												<td>비제공<input type = "radio" name ="isMedic" value="1" checked="checked" ></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</table>
								
								
								<table class="kg_form">
									<tr class = "kg_pro">
									<td class="kg_service_list">아카데미</td>
										<c:choose>
											<c:when test ="${schoolService.isAcademy == 0}">
												<td>제공<input type = "radio" name ="isAcademy" value="0"  checked="checked"></td>
												<td>비제공<input type = "radio" name ="isAcademy" value="1"></td>
											</c:when>
											<c:otherwise>
												<td>제공<input type = "radio"  name ="isAcademy" value="0"></td>
												<td>비제공<input type = "radio" name ="isAcademy" value="1" checked="checked" ></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</table>
								
								
								<table class="kg_form">
									<tr class = "kg_pro">
									<td class="kg_service_list">스파</td>
									<c:choose>
										<c:when test ="${schoolService.isSpa == 0}">
											<td>제공<input type = "radio"  name ="isSpa" value="0"  checked="checked"></td>
											<td>비제공<input type = "radio" name ="isSpa" value="1"></td>
										</c:when>
										<c:otherwise>
											<td>제공<input type = "radio"  name ="isSpa" value="0"></td>
											<td>비제공<input type = "radio" name ="isSpa" value="1" checked="checked" ></td>
										</c:otherwise>
									</c:choose>
									</tr>
								</table>
								
								
								
						 		<button id="school_modify_btn" type="submit">제공 서비스 수정</button><!--  onclick="schoolModifyService()" -->
							</fieldset>
						</form>
					</div>
					
					
					
					
					<form action="${context}/school/uploadphoto.do" method="post" id="school_photo_form" enctype="multipart/form-data">
						<div>
							<h3>[유치원사진]</h3><br>
							<input type ="file" name ="files" id="kg_photo" multiple/><!-- 여러개 파일 선택 속성 -->
						</div>
						<div id="photo_border">
							<c:if test="${!empty photoList}">
								<c:forEach var ="i" begin="0" end ="${photoList.size()-1}" step="1">
									<div>
										<img class = "schoolPhoto${i}" src = "/file${photoList[i].savePath}${photoList[i].renameFileName}" onclick="deletephoto()">
									</div>
								</c:forEach>
							</c:if>
						</div>
						<button id="file_upload_btn">파일 업로드</button>
					</form>
					
					
					
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