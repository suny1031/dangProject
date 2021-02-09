<%@page import="com.dang.common.code.ConfigCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<body>

	<h1>반갑습니다. ${param.userId}님</h1>
	<h1>"댕댕아 놀면 뭐하니?" 사이트에 가입하신 것을 환영합니다</h1>
	<h1>회원가입을 마무리 하기 위해 아래의 링크를 클릭해주세요</h1>

	<a href = "<%= ConfigCode.DOMAIN %>/member/joinimpl.do">회원가입 완료</a>
		
</body>
</html>