<%@page import="com.dang.common.code.ConfigCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
</head>
<body>
    <div style="background-color: rgb(240, 240, 240); width:100%; height: 50vh; display: flex;" >
        <div  style="background-color: white; width: 50%; height: 70%; border: 1px solid #d1d1d1; display: flex; flex-direction: column; justify-content: center;  margin: auto;"> 
            <div style="text-align: center; margin: 2% 0 2% 0; font-size: 1.5vw" >${param.userId}님 안녕하세요</div>
            <div style="text-align: center; font-size: 1.5vw; margin: 0 0 2% 0">${param.KgName}에서 알려드립니다.</div>
            <div style="text-align: center; font-size: 1.5vw"><span style="color: red;">${param.date}</span> 예약이 완료되었습니다</div>
        </div>
    </div>
</body>
</html>