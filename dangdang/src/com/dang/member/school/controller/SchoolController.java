package com.dang.member.school.controller;

import java.io.IOException;

import java.sql.Date;

import java.util.ArrayList;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.common.random.RandomString;
import com.dang.map.model.vo.Service;
import com.dang.member.school.model.service.SchoolService;
import com.dang.member.school.model.vo.SchoolMember;


import com.dang.reservation.model.service.ReservationService;
import com.dang.reservation.model.vo.Reservation;

import com.google.gson.Gson;

/**
 * Servlet implementation class SchoolController
 */
@WebServlet("/school/*")
public class SchoolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchoolService schoolService = new SchoolService();
	private ReservationService reservationService = new ReservationService();

	Gson gson = new Gson();

	public SchoolController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		
		switch(uriArr[uriArr.length-1]){
		case "login.do" : login(request, response); //로그인 페이지 이동
			break;
		case "loginimpl.do" : loginImpl(request, response); //로그인 실행
			break;
		case "logout.do" : logout(request, response); //로그아웃 실행
			break;
		case "schoolpage.do" : viewSchoolPage(request, response); //마이 페이지로 이동
			break;
		case "schoolprofile.do" : viewSchoolProfile(request, response); //프로필 페이지 이동
			break;
		case "findschoolinfo.do" : findSchoolInfo(request, response); //아이디,비번찾기 페이지 이동
			break;
		case "findschoolid.do" : findSchoolIdImpl(request, response); //아이디 찾기 실행
			break;
		case "findschoolpw.do" : findSchoolPwImpl(request, response); //비번 찾기 실행
			break;
		case "modifyinfo.do" : modifySchoolInfo(request, response); //기본정보 수정 실행
			break;
		case "modifyservice.do" : modifySchoolService(request, response); //서비스정보 수정 실행
			break;
		case "uploadphoto.do" : uploadSchoolPhoto(request, response); //사진정보 업로드 및 수정 실행
			break;
		case "serviceModify.do" : serviceModify(request, response); //2021.02.09 현재 사용X
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/member/school/schoollogin.jsp").forward(request, response);
		;

	}

	protected void loginImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 사용자가 보낸 파라미터 값(아이디/비번 받아서 service 단에 넘겨주기)
		// login.jsp 상에서 fetch를 통해 body에 data로 저장해놓았기 때문에 그 값을 불러오면 된다.
		String data = request.getParameter("data");

		// Gson gson = new Gson(); //자바라이브러리 GSON 객체 생성 -> 전역변수로 올림
		Map parsedLoginData = gson.fromJson(data, Map.class); // Json object인 data를 gson으로 map 클래스 객체로 변환!

		String schoolId = (String) parsedLoginData.get("id");// map객체의 id라는 key 값의 value 불러오기
		String schoolPw = (String) parsedLoginData.get("pw");// map객체의 pw라는 key 값의 value 불러오기

		SchoolMember schoolMember = schoolService.memberAuthenticate(schoolId, schoolPw);

		// schoolDao -> schoolService을 거치며 schoolMember의 객체가 반환된다.
		if (schoolMember != null) {
			Service schoolProService = schoolService.SchoolproService(schoolMember.getKgName());

			if (schoolProService != null) {
				// 회원정보와 서비스내역 있을 경우 해당 내용을 session에 저장.
				request.getSession().setAttribute("schoolMember", schoolMember);
				request.getSession().setAttribute("schoolService", schoolProService);
				response.getWriter().print("success");// 클라이언트에 응답하기 위한 출력 스트림을 반환햔다.
			
			}else if(schoolProService == null){
				response.getWriter().print("servicefail");
				
			} else {
				response.getWriter().print("fail");
			
			}

		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().removeAttribute("schoolMember"); // session에 저장된 정보 삭제 후 메인으로 이동
		request.getRequestDispatcher("/WEB-INF/view/main/main.jsp").forward(request, response);

	}

	protected void viewSchoolPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		String kgName = schoolMember.getKgName();
		
		ArrayList<Reservation> reservationPreview = reservationService.selectReservationPreview(kgName);
		System.out.println(reservationPreview);
		
		request.setAttribute("reservationPreview", reservationPreview);
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/mypage.jsp").forward(request, response);
	
	}


	protected void viewSchoolProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/member/school/schoolprofile.jsp").forward(request, response);
	}
	
	
	

	protected void findSchoolInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/member/school/findschoolinfo.jsp").forward(request, response);
		

	}

	protected void findSchoolIdImpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String schoolInfo = request.getParameter("userinfo");

		Map findIdMap = gson.fromJson(schoolInfo, Map.class);

		
		String schoolName = (String)findIdMap.get("username");
		String schoolTell = (String)findIdMap.get("phone");
		
		SchoolMember schoolMember = schoolService.findSchoolId(schoolName, schoolTell);
		
		if(schoolMember != null) {
			response.getWriter().print(schoolMember.getKgId());
		} else {
			response.getWriter().print("fail");
		}
	}

	
	
	
	
	protected void findSchoolPwImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//해당정보에 맞는 회원이 있다면(findUserPw 메소드 사용)
				String id = request.getParameter("id");
				String userEmail = request.getParameter("email");
				int res = 0;
				//객체가 리턴된다.
				SchoolMember schoolMember = schoolService.findSchoolPw(id, userEmail);
				
				if(schoolMember != null) {
					
					//그 값을 다시 가져와서 비밀번호만 다시 세팅한 후 다시 업데이트 문으로 modify 해주기(modifyInfo 메소드 사용);
					
					//새로운 비밀번호 발급해주기
					String newPw = RandomString.randomStr(3);
					
					//새로운 비밀번호로 password 다시 세팅
					schoolMember.setKgPw(newPw);
					
					//세팅된 비밀번호 포함 userMember정보 다시 가져오기
					String kgName = schoolMember.getKgName();
					String kgIdx = schoolMember.getKgIdx();
					String kgId = schoolMember.getKgId();
					String kgPw = schoolMember.getKgPw();
					String kgAddress = schoolMember.getKgAddress();
					String kgTell = schoolMember.getKgTell();
					String kgOperateTime = schoolMember.getKgOperateTime();
					String kgNotice = schoolMember.getKgNotice();
					String kgGrade = schoolMember.getKgGrade();
					String kgEmail = schoolMember.getKgEmail();
					
					//해당값으로 다시 update문 돌려주기
					res = schoolService.modifySchoolInfo(kgId, kgName, kgAddress, kgTell, kgOperateTime, kgNotice, kgEmail);
					
					//비밀번호 세팅이 잘 끝난 경우
					if(res > 0) {
						
						request.getSession().setAttribute("validUserMember", schoolMember);
						//해당 비밀번호를 포함한 이메일보내기
						schoolService.finSchoolPwEmail(schoolMember);
					
						//세션이 더이상 필요하지 않으니 세션 삭제해주기
						request.removeAttribute("validUserMember");
						request.setAttribute("alertMsg", "임시 비밀번호가 발급되었습니다.");
						request.setAttribute("url", "/school/login.do");
						request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
					
					}else {
						//이메일 발송이 실패한 경우
						new ToAlertException(ErrorCode.SM01);
					}
					//비밀번호 세팅중 오류가 생긴 경우
				}else {
					request.setAttribute("alertMsg", "해당정보가 존재하지 않습니다.");
					request.setAttribute("url", "/school/findschoolinfo.do");
					request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
				}
				
				
				
	
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

		} else {
			response.getWriter().print("fail");
		}

	}

	
	
	protected void modifySchoolService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*

		int res = 0;
		String modifyService = request.getParameter("schoolModifyService");
		Map modifyServiceMap = gson.fromJson(modifyService, Map.class);
		//String kgName = (String) modifyServiceMap.get("kgName");
		
	
		String kgName = request.getParameter("kgName");
		System.out.println(kgName);
		int isKg = Integer.parseInt((String) modifyServiceMap.get("isKg"));
		int isCafe = Integer.parseInt((String) modifyServiceMap.get("isCafe"));
		int isHotel = Integer.parseInt((String) modifyServiceMap.get("isHotel"));
		int isPickup = Integer.parseInt((String) modifyServiceMap.get("isPickup"));
		int isMedic = Integer.parseInt((String) modifyServiceMap.get("isMedic"));
		int isAcademy = Integer.parseInt((String) modifyServiceMap.get("isAcademy"));
		int isSpa = Integer.parseInt((String) modifyServiceMap.get("isSpa"));
		System.out.print(
				isKg + "," + isCafe + "," + isHotel + "," + isPickup + "," + isMedic + "," + isAcademy + "," + isSpa);

		res = schoolService.modifySchoolService(kgName, isKg, isCafe, isHotel, isPickup, isMedic, isAcademy, isSpa);

		System.out.println("Controller " + res);
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
			request.setAttribute("schoolService", schoolService);
			response.getWriter().print("success");

			
			
		}  */
		
	
		
	}
	
	protected void uploadSchoolPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int res = 0;
				//업로드한 user, 첨부파일
				//파일테이블 : 원본파일명, 리네임파일명, 게시글번호(?), 저장경로 
				SchoolMember schoolMember = (SchoolMember)request.getSession().getAttribute("schoolMember");
				System.out.println(schoolMember.getKgIdx());
				res = schoolService.uploadSchoolPhoto(request);
				
				if(res > 0) {
					request.setAttribute("alertMsg", "사진 등록이 완료되었습니다.");
					request.setAttribute("url", "/school/schoolprofile.do");
					request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
				}
				
			
			
		}
	
	
	//2021.02.09 현재 사용X
	protected void serviceModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/school/schoolservicepop.jsp").forward(request, response);
	
	}
		
	
	
	


}
