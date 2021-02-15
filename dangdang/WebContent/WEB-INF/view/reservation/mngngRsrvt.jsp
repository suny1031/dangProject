<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>

<!--페이징-->
<%@page import="java.util.List"%>
<%@page import="com.dang.reservation.model.service.ReservationService"%>
<%@page import="com.dang.reservation.model.vo.Reservation"%>
<%@page import="com.dang.member.school.model.vo.SchoolMember"%>
<%@page import="javax.servlet.http.HttpSession"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/resources/css/main.css" />
<link rel="stylesheet" href="/resources/css/mngngRsrvt.css" />
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
		
		
		
<!--  -->

 <%

	HttpSession KgNameSession = request.getSession();
	SchoolMember kgName = (SchoolMember) KgNameSession.getAttribute("schoolMember");
	
	int pageSize = 5; // 한 페이지에 출력할 레코드 수

	// 페이지 링크를 클릭한 번호 / 현재 페이지
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null){ // 클릭한게 없으면 1번 페이지
		pageNum = "1";
	}
	// 연산을 하기 위한 pageNum 형변환 / 현재 페이지
	int currentPage = Integer.parseInt(pageNum);

	// 해당 페이지에서 시작할 레코드 / 마지막 레코드
	int startRow = (currentPage - 1) * pageSize + 1;
	int endRow = currentPage * pageSize;

 	int count = 0;
	
	ReservationService reservationService = new ReservationService();
	
	count = reservationService.selectCountPage(kgName.getKgName()); // 데이터베이스에 저장된 총 갯수


 	List<Reservation> list = null;
	if (count > 0) {
		// getList()메서드 호출 / 해당 레코드 반환
		list = reservationService.selectReservationPage(startRow, endRow, kgName.getKgName());
		System.out.println(list);

	}  
%>

	<div class="board">
		<div id = "wrap">
		<center>
		<div id = "title">상담 예약 현황</div>
		<div id = "count">TOTAL : <%=count%></div>
		<table id = "table" align="center">
			<tr  align="center" style="background-color: #f3f3f3;">
				<td class = "infrm" width="7%">예약번호</td>
				<td class = "infrm" width="7%">ID</td>
				<td class = "infrm" width="7%">이름</td>
				<td class = "infrm" width="10%">휴대폰번호</td>
				<td class = "infrm" width="10%">강아지 종</td>
				<td class = "infrm" width="7%">강아지 나이</td>
				<c:choose>
					<c:when test="${service.getIsPickup() == 0}">
						<td class = "infrm" width="7%">픽업 여부</td>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<td class = "infrm" width="10%">신청일</td>				
				<td class = "infrm" width="20%">요구사항</td>
				<td class = "infrm" width="10%">승인여부</td>
				<td class = "infrm" width="5%">삭제</td>
				
			</tr>
			<%
				if (count > 0 ) { // 데이터베이스에 데이터가 있으면
					for (int i = 0; i < list.size(); i++) {
						Reservation reservation = list.get(i);
						// 반환된 list에 담긴 참조값 할당
			%>
			<tr  align="center">
				<td class = "rsIdx"><%=reservation.getRsIdx()%></td>
				<td class = "userId"><%=reservation.getUserId()%></td>
				<td><%=reservation.getProtectorName()%></td>
				<td><%=reservation.getPhoneNumber()%></td>
				<td><%=reservation.getDogBreed()%></td>
				<td><%=reservation.getDogAge()%></td>
				<c:choose>
				<c:when test="${service.getIsPickup() == 0}">
		 				<%if (reservation.getPickup().equals("0")){ %>
						<td>희망</td>
						<%}else {%>
						<td>비희망</td>
						<%} %> 
						</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
					<td class = "date"><%=reservation.getRegDate()%></td>
					<td><%=reservation.getRequirements()%></td>
					<%if (reservation.getIsApproved().equals("1")){ %>
					<td><button class= "approvedBtn">승인</button></td>
					<%}else {%>
					<td><div class= "approvedBtn">승인완료</div></td>
				<%} %>
					<td><i class="fas fa-times del"></i></td>
			</tr>
			<%
					}
				} else { // 데이터가 없으면
			%>
				<script>
					alert("등록된 예약이 없습니다")
				</script>
				<%
					}
				%>
				
			<tr style="background-color: #f3f3f3;">
				<td align="center" colspan="11" style="font-size: 0.7vw">
					<%	// 페이징  처리
						if(count > 0){
							// 총 페이지의 수
							int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
							// 한 페이지에 보여줄 페이지 블럭(링크) 수
							int pageBlock = 10;
							// 한 페이지에 보여줄 시작 및 끝 번호(예 : 1, 2, 3 ~ 10 / 11, 12, 13 ~ 20)
							int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
							int endPage = startPage + pageBlock - 1;
							
							// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
							if(endPage > pageCount){
								endPage = pageCount;
							}
							
							if(startPage > pageBlock){ // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
					%>
								<a href="/reservation/mngngRsrvt.do?pageNum=<%=startPage - 10%>">[이전]</a>	
					<%			
							}
							
							for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
								if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
					%>
									[<%=i %>]
					<%									
								}else{ // 현재 페이지가 아닌 경우 링크 설정
					%>
									<a href="/reservation/mngngRsrvt.do?pageNum=<%=i%>">[<%=i %>]</a>
					<%	
								}
							} // for end
							
							if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
					%>
								<a href="/reservation/mngngRsrvt.do?pageNum=<%=startPage + 10 %>">[다음]</a>
					<%			
							}
						}
					%>
				</td>
			</tr>
		</table>
	</center>

		</div>
		



		<!-- Footer -->
		<footer id="footer">
			<div id = "footerMark">&copy;댕댕아놀면뭐하니?</div>
		</footer>

	</div>
	<script type="text/javascript">
    
	let btn = document.querySelectorAll(".approvedBtn"); 
	let userIdArr = document.querySelectorAll(".userId"); 
	let dateArr = document.querySelectorAll(".date"); 
	let rsIdxArr = document.querySelectorAll(".rsIdx"); 

	for(let i = 0; i < btn.length; i++ ){
 			btn[i].addEventListener('click',(e)=> {
			let userId = userIdArr.item(i).innerText
			let date = dateArr.item(i).innerText
			let rsIdx = rsIdxArr.item(i).innerText
			console.dir(rsIdx)

			
		      let headerObj = new Headers();
		      headerObj.append('content-type',"application/x-www-form-urlencoded");

	          fetch("/reservation/approved.do",{	
	              method : "post",
	              headers : headerObj,
	              body : "userId="+userId+"&rsIdx="+rsIdx+"&date="+date+"&kgName=${kgName}"
	              
	           }).then(response => {
	               if(response.ok){
	                  return response.text();
	               }
	               throw new AsyncPageError(response.text());
	            })
	            .then((msg) => {
	               if(msg == 'success'){
						alert('예약 승인 메일을 보냈습니다.')
	               }
	            }).catch(error=>{
					alert('메일 보내기에 실패하였습니다.')
	            })

		})
	} 

	</script>
	
	<script type="text/javascript">
	let del = document.querySelectorAll(".del"); 
	let dleArr = document.querySelectorAll(".rsIdx"); 

	for(let i = 0; i < del.length; i++ ){
		del[i].addEventListener('click',(e)=> {
		let	result = confirm('정말 삭제하시겠습니까?')
		
		if (result) {
		//삭제를 눌렀을때
		let dleRsIdx = dleArr.item(i).innerText
		console.dir(dleRsIdx)
		
				      let headerObj = new Headers();
		      headerObj.append('content-type',"application/x-www-form-urlencoded");

	          fetch("/reservation/delete.do",{	
	              method : "post",
	              headers : headerObj,
	              body : "dleRsIdx="+dleRsIdx
	              
	           }).then(response => {
	               if(response.ok){
	                  return response.text();
	               }
	               throw new AsyncPageError(response.text());
	            })
	            .then((msg) => {
	               if(msg == 'success'){
						alert('삭제 성공하였습니다.')
	               }
	            }).catch(error=>{
					alert('삭제 실패하였습니다.')
	            })
	          
		}else{
			//삭제 취소를 눌렀을때
		}
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