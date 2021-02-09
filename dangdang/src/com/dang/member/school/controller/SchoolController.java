package com.dang.member.school.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.map.model.vo.Service;
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
  	  Gson gson = new Gson();
   
    public SchoolController() {
        super();
       
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
		case "findschoolinfo.do" : findSchoolInfo(request, response);
			break;
		case "findschoolid.do" : findSchoolIdImpl(request, response);
			break;
		case "findschoolpw.do" : findSchoolPwImpl(request, response);
			break;
		case "modifyinfo.do" : modifySchoolInfo(request, response);
			break;
		case "modifyservice.do" : modifySchoolService(request, response);
			break;
		case "modifyphoto.do" : modifySchoolPhoto(request, response);
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
		
		
		//Gson gson = new Gson(); //자바라이브러리 GSON 객체 생성 -> 전역변수로 올림
		Map parsedLoginData = gson.fromJson(data, Map.class); // Json object인 data를 gson으로 map 클래스 객체로 변환!
		
		String schoolId = (String) parsedLoginData.get("id");//map객체의 id라는 key 값의 value 불러오기
		String schoolPw = (String) parsedLoginData.get("pw");//map객체의 pw라는 key 값의 value 불러오기
		
		SchoolMember schoolMember = schoolService.memberAuthenticate(schoolId, schoolPw);

		
		//schoolDao -> schoolService을 거치며 schoolMember의 객체가 반환된다.
		if(schoolMember != null) {
			Service schoolProService = schoolService.SchoolproService(schoolMember.getKgName());
			
			if(schoolProService != null) {
				//회원정보와 서비스내역 있을 경우 해당 내용을 session에 저장.
				request.getSession().setAttribute("schoolMember", schoolMember);
				request.getSession().setAttribute("schoolService", schoolProService);
				response.getWriter().print("success");// 클라이언트에 응답하기 위한 출력 스트림을 반환햔다.
				request.setAttribute("alertMsg", "'댕댕아 놀면 뭐하니?'에 오신걸 환영합니다.");
			}else if(schoolProService == null){
				request.setAttribute("alertMsg", "유치원 서비스 조회 중 문제가 발생하였습니다.");
			}
			
		} else {
			response.getWriter().print("fail");
			request.setAttribute("alertMsg", "유치원 로그인에 실패하였습니다.");
		}
		
	}
	
	
	
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("schoolMember"); //session에 저장된 정보 삭제 후 메인으로 이동
		request.getRequestDispatcher("/WEB-INF/view/main/main.jsp").forward(request, response);
		
		
		}
	
	
	protected void viewSchoolPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/mypage.jsp").forward(request, response);
	
	}
	
	
	
	
	
	protected void viewSchoolProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/school/schoolprofile.jsp").forward(request, response);;
	
	}
	
	
	
	protected void findSchoolInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/school/findschoolinfo.jsp").forward(request, response);;
	
	}
	
	
	protected void findSchoolIdImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String schoolInfo = request.getParameter("userinfo");
		
		Map findIdMap = gson.fromJson(schoolInfo, Map.class);
		
		String schoolName = (String)findIdMap.get("username");
		String schoolTell = (String)findIdMap.get("phone");
		
		SchoolMember schoolMember = schoolService.selectSchoolByName(schoolName, schoolTell);
		
		if(schoolMember != null) {
			response.getWriter().print(schoolMember.getKgId());
		}else {
			response.getWriter().print("fail");
		}
	}
	
	protected void findSchoolPwImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
	}
	
	protected void modifySchoolInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int res = 0;
		String modifyInfo = request.getParameter("schoolModifyInfo");
		Map modifyInfoMap = gson.fromJson(modifyInfo, Map.class);
		String kgId = (String) modifyInfoMap.get("kgId");
		String kgName = (String) modifyInfoMap.get("kgName");
		String kgAddress = (String) modifyInfoMap.get("kgAddress");
		String kgTell = (String) modifyInfoMap.get("kgTell");
		String kgOperateTime = (String) modifyInfoMap.get("kgOperateTime");
		String kgNotice = (String) modifyInfoMap.get("kgNotice");
		String kgEmail = (String) modifyInfoMap.get("kgEmail");

		
		res = schoolService.modifySchoolInfo(kgId, kgName, kgAddress, kgTell, kgOperateTime, kgNotice, kgEmail);
	
		if(res > 0) {
			
			SchoolMember schoolMember = new SchoolMember();
			schoolMember.setKgId(kgId);
			schoolMember.setKgName(kgName);
			schoolMember.setKgAddress(kgAddress);
			schoolMember.setKgTell(kgTell);
			schoolMember.setKgOperateTime(kgOperateTime);
			schoolMember.setKgNotice(kgNotice);
			schoolMember.setKgEmail(kgEmail);
			request.getSession().setAttribute("schoolMember", schoolMember);
			response.getWriter().print("success");
			
		}else {
			response.getWriter().print("fail");
		}
		
		
	}
	
	
	protected void modifySchoolService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int res = 0;
		String modifyService = request.getParameter("schoolModifyService");
		Map modifyServiceMap = gson.fromJson(modifyService, Map.class);
		String kgName = (String) modifyServiceMap.get("kgName");
		System.out.println(kgName);
		int isKg = Integer.parseInt((String)modifyServiceMap.get("isKg"));
		int isCafe = Integer.parseInt((String)modifyServiceMap.get("isCafe"));
		int isHotel = Integer.parseInt((String)modifyServiceMap.get("isHotel"));
		int isPickup = Integer.parseInt((String)modifyServiceMap.get("isPickup"));
		int isMedic = Integer.parseInt((String)modifyServiceMap.get("isMedic"));
		int isAcademy = Integer.parseInt((String)modifyServiceMap.get("isAcademy"));
		int isSpa = Integer.parseInt((String) modifyServiceMap.get("isSpa"));
		System.out.print(isKg +"," + isCafe +"," + isHotel +"," +isPickup +"," +isMedic +"," + isAcademy +"," + isSpa);
		
		res = schoolService.modifySchoolService(kgName, isKg, isCafe, isHotel, isPickup, isMedic, isAcademy, isSpa);
		System.out.println("Controlle " + res);
		if(res > 0) {
			
			Service schoolService = new Service();
			schoolService.setKgName(kgName);
			schoolService.setIsKg(isKg);
			schoolService.setIsCafe(isCafe);
			schoolService.setIsHotel(isHotel);
			schoolService.setIsPickup(isPickup);
			schoolService.setIsMedic(isMedic);
			schoolService.setIsAcademy(isAcademy);
			schoolService.setIsSpa(isSpa);			
			System.out.println("schoolservice = " + schoolService);
			request.getSession().setAttribute("schoolService", schoolService);
			response.getWriter().print("success");
			
			
		}
		
		
	}
	
	protected void modifySchoolPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
		}
		
	
	
	
	
	
	
	
	
	
	

}
