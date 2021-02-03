package com.dang.member.school.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dang.member.school.model.service.SchoolService;
import com.dang.member.school.model.vo.SchoolMember;
import com.google.gson.Gson;

/**
 * Servlet implementation class SchoolController
 */
@WebServlet("/school/*")
public class SchoolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private SchoolService schoolService = new SchoolService();
   
    public SchoolController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		case "schoolpage.do" : viewSchoolPage(request, response);
		break;
		case "schoolprofile.do" : viewSchoolProfile(request, response);
		break;
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

		request.getRequestDispatcher("/WEB-INF/view/member/school/schoollogin.jsp").forward(request, response);;
		
		}
	
	
	protected void loginImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//사용자가 보낸 파라미터 값(아이디/비번 받아서 service 단에 넘겨주기)
		//login.jsp 상에서 fetch를 통해 body에 data로 저장해놓았기 때문에 그 값을 불러오면 된다.
		String data = request.getParameter("data");
		
		
		Gson gson = new Gson(); //자바라이브러리 GSON 객체 생성
		Map parsedLoginData = gson.fromJson(data, Map.class); // Json object인 data를 gson으로 map 클래스 객체로 변환!
		
		String schoolId = (String) parsedLoginData.get("id");//map객체의 id라는 key 값의 value 불러오기
		String schoolPw = (String) parsedLoginData.get("pw");//map객체의 pw라는 key 값의 value 불러오기
		
		SchoolMember schoolMember = schoolService.memberAuthenticate(schoolId, schoolPw);
	
		
		//schoolDao -> schoolService을 거치며 schoolMember의 객체가 반환된다.
		if(schoolMember != null) {
			
			//회원정보가 있을 경우 해당 내용을 session에 저장.
			request.getSession().setAttribute("schoolMember", schoolMember);
			response.getWriter().print("success");// 클라이언트에 응답하기 위한 출력 스트림을 반환햔다.
		} else {
			response.getWriter().print("fail");
		}
		

		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.getRequestDispatcher("/WEB-INF/view/member/school/schoollogin.jsp").forward(request, response);;
		
		}
	
	
	protected void viewSchoolPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/schoolpage.jsp").forward(request, response);
	
	}
	
	
	
	
	
	protected void viewSchoolProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/school/schoolprofile.jsp").forward(request, response);;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
