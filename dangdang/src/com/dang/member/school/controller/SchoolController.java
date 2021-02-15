package com.dang.member.school.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

import java.util.ArrayList;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.board.model.vo.Board;
import com.dang.common.code.ConfigCode;
import com.dang.common.util.file.FileVo;
import com.dang.diary.model.service.DiaryService;
import com.dang.diary.model.vo.Diary;
import com.dang.map.model.vo.Service;
import com.dang.member.school.model.service.SchoolService;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;
import com.dang.reservation.model.service.ReservationService;
import com.dang.reservation.model.vo.Reservation;

import com.google.gson.Gson;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

/**
 * Servlet implementation class SchoolController
 */
@WebServlet("/school/*")
public class SchoolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchoolService schoolService = new SchoolService();
	private ReservationService reservationService = new ReservationService();
	private DiaryService diaryService = new DiaryService();

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
		case "modifyinfo.do" : modifySchoolInfo(request, response); //기본정보 수정 실행
			break;
		case "modifyservice.do" : modifySchoolService(request, response); //서비스정보 수정 실행
			break;
		case "uploadphoto.do" : uploadSchoolPhoto(request, response); //사진정보 업로드 및 수정 실행
			break;
		case "kinderclass.do" : kinderClassReg(request, response); //사진정보 업로드 및 수정 실행
			break;
		case "kinderclassimol.do" : kinderClassRegImpl(request, response); //사진정보 업로드 및 수정 실행
			break;
		
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/member/school/schoollogin.jsp").forward(request, response);

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
			
				// 회원정보 있을 경우 해당 내용을 session에 저장.
				request.getSession().setAttribute("schoolMember", schoolMember);
				response.getWriter().print("success");// 클라이언트에 응답하기 위한 출력 스트림을 반환햔다.


		} else {
			response.getWriter().print("fail");
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().removeAttribute("schoolMember"); // session에 저장된 정보 삭제 후 메인으로 이동
		request.getRequestDispatcher("/main.do").forward(request, response);

	}

	protected void viewSchoolPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		String kgName = schoolMember.getKgName();
		
		//reservation 데이터 request에 저장
		ArrayList<Reservation> reservationPreview = reservationService.selectReservationPreview(kgName);
		System.out.println(reservationPreview);
		request.setAttribute("reservationPreview", reservationPreview);
		
		//notice 데이터 request에 저장
		ArrayList<Board> NoticePreview = schoolService.selectNoticePreview(kgName);
		request.setAttribute("NoticePreview", NoticePreview);
		
		//class 데이터 request에 저장
		ArrayList<UserMember> classMemberList = schoolService.selectClassMember(kgName);
		request.setAttribute("classMemberList", classMemberList);
		
		//diary 데이터 request에 저장
		ArrayList<Diary> diaryList = diaryService.selectDiaryPreview(kgName);
		request.setAttribute("diaryList", diaryList);

		request.getRequestDispatcher("/WEB-INF/view/mypage/mypage.jsp").forward(request, response);
		
		
	
	}


	protected void viewSchoolProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		Service schoolProService = schoolService.schoolProService(schoolMember.getKgName());
		request.setAttribute("schoolService", schoolProService);
		
		ArrayList<FileVo> photoList = schoolService.selectSchoolPhoto(schoolMember.getKgIdx());
		if(photoList != null) {
			request.setAttribute("photoList", photoList);
		}
		
		
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
		
		int res = 0;
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		String kgName = schoolMember.getKgName();

		
		//getParameter로 받아오는 value 값은 String을 return 하기 때문에 int로 parse진행
		int isKg = Integer.parseInt(request.getParameter("isKg"));
		int isCafe = Integer.parseInt(request.getParameter("isCafe"));
		int isHotel = Integer.parseInt(request.getParameter("isHotel"));
		int isPickup = Integer.parseInt(request.getParameter("isPickup"));
		int isMedic = Integer.parseInt(request.getParameter("isMedic"));
		int isAcademy = Integer.parseInt(request.getParameter("isAcademy"));
		int isSpa = Integer.parseInt(request.getParameter("isSpa"));

		res = schoolService.modifySchoolService(kgName, isKg, isCafe, isHotel, isPickup, isMedic, isAcademy, isSpa);

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
			
			//수정된 값을 다시 받아서 다시 세션에 저장해주기
			request.getSession().setAttribute("schoolService", schoolService);
			request.setAttribute("alertMsg", "서비스를 성공적으로 수정하였습니다.");
			request.setAttribute("url", "/school/schoolprofile.do");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			
		} else {
			request.setAttribute("alertMsg", "서비스 수정이 실패하였습니다.");
			request.setAttribute("url", "/school/schoolprofile.do");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}
		
	
		
	}
	
	
	
	
	protected void uploadSchoolPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				//파일테이블 : 원본파일명, rename 파일명, 게시글번호
				//F_IDX, TYPE_IDX, ORIGIN_FILE_NAME, RENAME_FILE_NAME, SAVE_PATH
				//F_IDX -> nextval로 설정
		
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember)session.getAttribute("schoolMember");

		schoolService.uploadSchoolPhoto(schoolMember, request);
		
		request.setAttribute("alertMsg", "사진 등록이 완료되었습니다.");
		request.setAttribute("url", "/school/schoolprofile.do");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
		
		
		
				
		}
	
	
	
	protected void kinderClassReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		String kgName = schoolMember.getKgName();
		ArrayList<UserMember> classMemberList = schoolService.selectClassMember(kgName);
		
		if(classMemberList != null) {
			request.setAttribute("classMemberList", classMemberList);
		}
		
		request.getRequestDispatcher("/WEB-INF/view/member/school/classmember.jsp").forward(request, response);
		
		
	}
	
	
	protected void kinderClassRegImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}


	
	
	
		
	
	
	


}
