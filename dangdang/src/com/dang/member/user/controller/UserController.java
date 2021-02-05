package com.dang.member.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dang.member.user.model.service.UserService;
import com.dang.member.user.model.vo.UserMember;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();
   
    public UserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		switch(uriArr[uriArr.length-1]){
		case "login.do" : login(request, response);
			break;
		case "loginimpl.do" : loginImpl(request, response);
			break;
		case "logout.do" : logout(request, response);
			break;
		case "join.do" : join(request, response);
			break;
		case "joinimpl.do" : joinimpl(request, response);
			break;
		case "idcheck.do" : idCheck(request, response);
			break;
		case "userpage.do" : viewUserPage(request, response);
			break;
		case "userprofile.do" : viewProfile(request, response);
			break;
		case "finduserinfo.do" : findUserInfo(request, response);
			break;
		case "finduserid.do" : findUserIdImpl(request, response);
			break;
		case "finduserpw.do" : findUserPwImpl(request, response);
			break;
		
		
		
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/userlogin.jsp").forward(request, response);
		
		
	}
	
	protected void loginImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//사용자가 보낸 파라미터 값(아이디/비번 받아서 service 단에 넘겨주기)
		//login.jsp 상에서 fetch를 통해 body에 data로 저장해놓았기 때문에 그 값을 불러오면 된다.
		String data = request.getParameter("data");
		
		//자바라이브러리 GSON 객체 생성
		Gson gson = new Gson();
		
		// Json object인 data를 gson으로 map 클래스 객체로 변환!
		Map parsedLoginData = gson.fromJson(data, Map.class);
		
		
		String userId = (String) parsedLoginData.get("id"); //map객체의 id라는 key 값의 value 불러오기
		String userPw = (String) parsedLoginData.get("pw");	//map객체의 pw라는 key 값의 value 불러오기
	
		//schoolDao -> schoolService을 거치며 schoolMember의 객체가 반환된다.
		UserMember userMember = userService.memberAuthenticate(userId, userPw);
	
		if(userMember != null) {
			request.getSession().setAttribute("userMember" , userMember);
			response.getWriter().print("success"); //회원정보가 있을 경우 해당 내용을 session에 저장.
		}else {
			response.getWriter().print("fail");
		}
		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("userMember"); //session에 저장된 정보 삭제 후 메인으로 이동
		request.getRequestDispatcher("/WEB-INF/view/main/main.jsp").forward(request, response);
		
		
	}
	
	protected void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/join.jsp").forward(request, response);
		
		
	}
	
	protected void joinimpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserMember userMember = new UserMember();
		String userId = request.getParameter("id");
		String password = request.getParameter("pw");
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("tell");
		String nick = request.getParameter("nickname");
		String birth = request.getParameter("birth");
		String kinder = request.getParameter("kinder");
		String className = request.getParameter("classname");
		
		//String birth를 java.sql.Date로 형변환해주는 단계
		java.sql.Date birthday = java.sql.Date.valueOf(birth);
		
		
		userMember.setUserId(userId);
		userMember.setPassword(password);
		userMember.setUserName(userName);
		userMember.setEmail(email);
		userMember.setPhoneNumber(phone);
		userMember.setNickname(nick);
		userMember.setBirth(birthday);
		userMember.setKgName(kinder);
		userMember.setClassName(className);
	
		
		int res = userService.insertuserMember(userMember);
		
		if(res > 0) {
			response.getWriter().println("success");
		} else {
			response.getWriter().println("fail");
			
		}
	}
	
protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 application/x-www-form-urlencoded 컨텐츠 타입으로 보낼 것이기 떄문에 (form을 통해 넘길때, 넘어오는 타입이다.)
		//이전과 동일하게 서블릿 코드 작성
	
		String userId = request.getParameter("userId"); //userId값으로 넘어올거라고 우리가 정해준 것
		UserMember userMember = userService.selectUserById(userId);
		
		//userMember가 null이면 없는거고 null이 아니라면 있는 것 !
		//id가 있으면 안되는 상황이기 때문에..
		if(userMember == null) {
			response.getWriter().print("available");//사용가능
		}else {
			response.getWriter().print("unavailable");//사용불가
		}
		
		
		
	}
	

	protected void viewUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/mypage.jsp").forward(request, response);
		
		
	}
	
	
	
	
	protected void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/userprofile.jsp").forward(request, response);
		
		
	}
	
	protected void findUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/finduserinfo.jsp").forward(request, response);
		
		
	}
	
	protected void findUserIdImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	protected void findUserPwImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		
	}
	

	
	
	
	
	
	
	

}
