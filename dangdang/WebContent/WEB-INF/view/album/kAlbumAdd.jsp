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
<link rel="stylesheet" href="/resources/css/kAlbumAdd.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>

</head>	

	<body class="is-preload">
		<div id = "addWrap">
			<div id = "addBox">
				<form id = "addForm" action="/album/addphotoimpl.do"><input id = "addInput" type="file" accept='image/jpg,image/jpeg,image/gif,image/png' onChange="chk(this)"><button id = "addBtn">등록</button></form>
			</div>
		</div>
	
	
	<script>
		let check = false;
		let file = document.querySelector('#addInput');
		
		function chk(obj) {
		    if (/(\.gif|\.jpg|\.jpeg|\.png)$/i.test(obj.value) == false) {
		        alert("이미지 형식의 파일을 선택하십시오");
		        check = false;
		        return;
		    }else{
		        check = true;
		    }
		}
			
		    document.querySelector('#addForm').addEventListener('submit',(e) => {
			    if (!check) {
		    	console.dir(check)
			         e.preventDefault();
			          alert("이미지 형식의 파일을 선택하십시오");
				      return;
				}
		    });
		
	
	
		
		</script>
		
	</body>
</html>