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
<body>


						<form id="school_service_form">
							<fieldset id="sort_school_service">
								
								<label for ="isKg"><input type = "checkbox" id = "isKg" name ="isKg"  value = "0" > 유치원</label>
								<label for ="isCafe"><input type = "checkbox" id = "isCafe" name ="isCafe"  value = "0"> 카페</label>
								<label for ="isHotel"><input type = "checkbox" id = "isHotel" name ="isHotel"  value = "0" > 호텔</label>
								<label for ="isPickup"><input type = "checkbox" id = "isPickup" name ="isPickup"  value = "0" > 픽업서비스</label>
								<label for ="isMedic"><input type = "checkbox" id = "isMedic" name ="isMedic"  value = "0" > 메디컬센터</label>
								<label for ="isAcademy"><input type = "checkbox" id = "isAcademy" name ="isAcademy" > 아카데미</label>
								<label for ="isSpa"><input type = "checkbox" id = "isSpa" name ="isSpa"  value = "0" > 스파</label>
						 		<button id="school_modify_btn" onclick ="schoolModifyService()" >제공 서비스 수정</button> <!-- onclick="schoolModifyService()" -->
							</fieldset>
						</form>

</body>



<script type="text/javascript">





let schoolModifyService =() =>{
	
	let checked = document.querySelector('input checkbox')
	let value = 1;
	if(checked){
		value = 0;
	}
	
	let schoolServiceObj = new Object();
	
	
	schoolServiceObj.isKg = isKg.value;
	
	schoolServiceObj.isCafe = isCafe.value;
	schoolServiceObj.isHotel = isHotel.value;
	schoolServiceObj.isPickup = isPickup.value;
	schoolServiceObj.isMedic = isMedic.value;
	schoolServiceObj.isAcademy = isAcademy.value;
	schoolServiceObj.isSpa = isSpa.value;
	console.dir(schoolServiceObj)
	let url ="/school/modifyservice.do"
	
	let headerObj = new Headers();
	headerObj.append("content-type", "application/x-www-form-urlencoded");
	
	fetch(url, {
		method:"post",
		headers:headerObj,
		body:"schoolModifyService="+JSON.stringify(schoolServiceObj)
	}).then(response =>{
		if(response.ok){
			return response.text();
		}
	}).then((text) =>{
		if(text == 'fail'){
			alert('유치원 서비스 업데이트가 실패하였습니다.');
		}else if(text =='seccess'){
			alert('유치원 서비스가 성공적으로 업데이트 되었습니다.');
			location.href = "/school/schoolprofile.do"
			
		}else{
			alert('유치원정보 업데이트 중 오류발생');
		}
	})

	
}



</script>
</html>

