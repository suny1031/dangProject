package com.dang.member.user.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dang.member.school.model.service.SchoolService;
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
		case "userpage.do" : viewUserPage(request, response);
		break;
		case "userprofile.do" : viewProfile(request, response);
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
	

	protected void viewUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/mypage.jsp").forward(request, response);
		
		
	}
	
	
	
	
	protected void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/userprofile.jsp").forward(request, response);
		
		
	}
	
	
	
	
	
	
	

}
