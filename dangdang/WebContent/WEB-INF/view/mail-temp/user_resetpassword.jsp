<%@page import="com.dang.common.code.ConfigCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<body>

	<h1>회원님의 임시 비밀번호는</h1>
	<h1>${requestScope.userPw}</h1>
	<h1>입니다.</h1>
	<h1>아래의 링크를 클릭하시면 로그인 창으로 이동합니다.</h1>
	<h1>로그인 후 임시비밀번호를 바꿔주세요.</h1>

	<a href = "<%= ConfigCode.DOMAIN %>/user/login.do">홈페이지 이동하기</a>
		
</body>
</html>