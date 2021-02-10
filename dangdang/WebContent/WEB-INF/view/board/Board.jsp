<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dang.board.model.dao.BoardDao" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="board" class="com.dang.board.model.vo.Board" scope="page" />
<jsp:setProperty name="board" property="title" />
<jsp:setProperty name="board" property="content" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>댕스쿨</title>
</head>
<body>
	<%
		String userId = null;
		if(session.getAttribute("userId") != null){
			userId = (String)session.getAttribute("userId");
		}
		if(userId == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'userlogin.jsp'");
			script.println("</script>");
		}else{
			if(board.getTitle() == null || board.getContent() == null){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");				
			}else{
				BoardDao boardDao = new BoardDao();
				int result = boardDao.addBoard(board.getTitle(), board.getKgName(), board.getContent());
				if(result == -1){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				}else{
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = BoardLsit1.jsp");
					script.println("</script>");
				}
			}
			
		}
		
	%>
	

</body>
</html>