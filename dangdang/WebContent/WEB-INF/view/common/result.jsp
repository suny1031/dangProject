<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<body>

	<script type="text/javascript">
	let context = "${context}";
	
	<c:if test = "${!empty alertMsg}">
		alert("${alertMsg}");
		
	</c:if>
	
	<%-- 뒤로가기 --%>
	<c:if test = "${!empty back}">
		history.back();
	</c:if>
	
	
	<c:if test = "${!empty url}">
		location.href = context + '${url}'
	</c:if>
	
	
	
	
	</script>







</body>
</html>