package com.dang.member.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.common.random.RandomString;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.service.UserService;
import com.dang.member.user.model.vo.UserMember;
import com.dang.reservation.model.service.ReservationService;
import com.dang.reservation.model.vo.Reservation;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();
	private ReservationService reservationService = new ReservationService();

    Gson gson = new Gson();
   
    public UserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		switch(uriArr[uriArr.length-1]){
		case "login.do" : login(request, response); //로그인 페이지 이동
			break;
		case "loginimpl.do" : loginImpl(request, response); //로그인 실행
			break;
		case "logout.do" : logout(request, response); //로그아웃 실행
			break;
		case "join.do" : join(request, response); //회원가입 페이지 이동
			break;
		case "mailauth.do": authenticateEmail(request, response); //회원가입 인증 실행
			break;
		case "joinimpl.do" : joinimpl(request, response); //회원가입 실행
			break;
		case "idcheck.do" : idCheck(request, response); //아이디 체크 실행
			break;
		case "userpage.do" : viewUserPage(request, response); //마이 페이지 이동
			break;
		case "userprofile.do" : viewProfile(request, response); //프로필 페이지 이동
			break; 
		case "finduserinfo.do" : findUserInfo(request, response); //아이디,비번 찾기 페이지 이동
			break;
		case "finduserid.do" : findUserIdImpl(request, response); //아이디 찾기 실행
			break;
		case "finduserpw.do" : findUserPwImpl(request, response); //비밀번호 찾기 실행
			break;
		case "withdraw.do" : withdrawUser(request, response); //회원탈퇴 실행
			break;
		case "modifyinfo.do" : modifyUserInfo(request, response); 
		
		
		
		
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
		
		//자바라이브러리 GSON 객체 생성 -> 전역으로 올림
		
		// Json object인 data를 gson으로 map 클래스 객체로 변환!
		Map parsedLoginData = gson.fromJson(data, Map.class);
		
		
		String userId = (String) parsedLoginData.get("id"); //map객체의 id라는 key 값의 value 불러오기
		String userPw = (String) parsedLoginData.get("pw");	//map객체의 pw라는 key 값의 value 불러오기
	
		//schoolDao -> schoolService을 거치며 schoolMember의 객체가 반환된다.
		UserMember userMember = userService.memberAuthenticate(userId, userPw);

		
		if(userMember != null) {
			if(userMember.getIsleave() != 1) {
				request.getSession().setAttribute("userMember" , userMember); //회원정보가 있을 경우 해당 내용을 session에 저장.
				request.setAttribute("alertMsg", "'댕댕아 놀면 뭐하니?'에 오신걸 환영합니다.");
				response.getWriter().print("success"); 
					
			}else if(userMember.getIsleave() == 1){
				response.getWriter().print("withdraw");
				
			}
		}else {
			response.getWriter().print("fail");
		}
	
		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("userMember"); //session에 저장된 정보 삭제 후 메인으로 이동
		request.getRequestDispatcher("/main.do").forward(request, response);
		
		
	}
	
	
	protected void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/join.jsp").forward(request, response);
		
		
	}
	
	
	protected void authenticateEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//form 통해서 넘겨주는 값
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
		
		//String birth를 java.sql.Date로 형변환
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
		
		
		//인증 이메일 발송 => 응답
		//사용자가 이메일의 링크를 통해서 user/join 다시 요청 했을 때
		//회원정보 DB에 저장(사용자가 다시 요청할 때 까지 회원정보를 가지고 있기위해 회원정보 session에 담아두기)
		request.getSession().setAttribute("persistUser", userMember);
		
		//메일발송
		userService.authenticateEmail(userMember);
		
		//사용자에게 보여줄 view 지정
		request.setAttribute("alertMsg", "회원가입을 위한 이메일이 발송되었습니다.");
		request.setAttribute("url", "/user/login.do");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		
		
	}

	
	
	
	protected void joinimpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//persistUser로 저장해두었던 session값 가져와서 userMember에 넣어주기
		UserMember userMember = (UserMember) request.getSession().getAttribute("persistUser");
		
		//persistUser가 없거나, 브라우저를 종료했거나, 재요청없이 30분이 지났을 경우 혹은 회원가입이 완료된 경우에는 회원가입 진행 X
		if(userMember == null) {
			throw new ToAlertException(ErrorCode.AUTH02);
		}
		
		int res = userService.insertUserMembers(userMember);
		
		if(res > 0) {
			//회원가입 인증을 하고 나면 해당 회원가입정보를 session에서 삭제
			request.getSession().removeAttribute("persistUser");
			//view단에서 form으로 보내, 작업한 js내역이 없기 때문에 result.jsp로 넘겨줘서
			//원하는 alert메시지와 원하는 경로로 이동하도록 설정해준다.
			request.setAttribute("alertMsg", "회원가입을 축하합니다."); //alertMsg 설정
			request.setAttribute("url", "/user/login.do"); // url 설정
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		} else {
			request.setAttribute("alertMsg", "회원가입에 실패하였습니다.");
			request.setAttribute("url", "/main/main.do");
			
		}
	}
	
	
	
	
	protected void idCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 application/x-www-form-urlencoded 컨텐츠 타입으로 보낼 것이기 떄문에 (form을 통해 넘길때, 넘어오는 타입이다.)
		//이전과 동일하게 서블릿 코드 작성
	
		String userId = request.getParameter("userId"); //userId값으로 넘어올거라고 우리가 정해준 것
		UserMember userMember = userService.selectUserById(userId);
		
		//userMember가 null이면 없는거고 null이 아니라면 있는 것 !
		//id가 있으면 안되는 상황이기 때문에!
		if(userMember == null) {
			response.getWriter().print("available");//사용가능
		}else {
			response.getWriter().print("unavailable");//사용불가
		}
		
		
		
	}
	

	
	
	protected void viewUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserMember userMember = (UserMember) session.getAttribute("userMember");
		String userId = userMember.getUserId();
		
		ArrayList<Reservation> reservationPreview = reservationService.selectUserPreview(userId);
		
		System.out.println(reservationPreview);
		
		request.setAttribute("reservationPreview", reservationPreview);
		

		request.getRequestDispatcher("/WEB-INF/view/mypage/mypage.jsp").forward(request, response);
		
		
	}
	
	
	
	protected void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/userprofile.jsp").forward(request, response);
		
		
	}
	
	
	protected void findUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/member/user/finduserinfo.jsp").forward(request, response);
		
		
	}
	
	
	protected void findUserIdImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userInfo = request.getParameter("userinfo");
		
		
		Map findIdMap = gson.fromJson(userInfo, Map.class);
		
		String userName = (String) findIdMap.get("username");
		String phoneNumber = (String) findIdMap.get("phone");
		
		
		//검색결과를 객체로 만들어 service단에 넘겨주기
		UserMember userMember = userService.findUserId(userName, phoneNumber);

		if(userMember != null) {
			response.getWriter().print(userMember.getUserId());
		}else {
			response.getWriter().print("fail");
		}
		
		
	}
	
	protected void findUserPwImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//해당정보에 맞는 회원이 있다면(findUserPw 메소드 사용)
		String id = request.getParameter("id");
		String userEmail = request.getParameter("email");
		int res = 0;
		//객체가 리턴된다.
		UserMember userMember = userService.findUserPw(id, userEmail);
		
		if(userMember != null) {
			
			//그 값을 다시 가져와서 비밀번호만 다시 세팅한 후 다시 업데이트 문으로 modify 해주기(modifyInfo 메소드 사용);
			
			//새로운 비밀번호 발급해주기
			String newPw = RandomString.randomStr(3);
			
			//새로운 비밀번호로 password 다시 세팅
			userMember.setPassword(newPw);
			
			//세팅된 비밀번호 포함 userMember정보 다시 가져오기
			String userId = userMember.getUserId();
			String userPw = userMember.getPassword();
			String userName = userMember.getUserName();
			String userNick = userMember.getNickname();
			Date userBirth = userMember.getBirth();
			String userPhone = userMember.getPhoneNumber();
			
			//해당값으로 modify
			res = userService.modifyUserInfo(userId, userPw, userName, userNick, userEmail, userBirth, userPhone);
			
			//비밀번호 세팅이 잘 끝난 경우
			if(res > 0) {
				
				request.getSession().setAttribute("validUserMember", userMember);
				//해당 비밀번호를 포함한 이메일보내기
				userService.finUserPwEmail(userMember);
			
				//세션이 더이상 필요하지 않으니 세션 삭제해주기
				request.removeAttribute("validUserMember");
				request.setAttribute("alertMsg", "임시 비밀번호가 발급되었습니다.");
				request.setAttribute("url", "/user/login.do");
				request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			
			}else {
				//이메일 발송이 실패한 경우
				new ToAlertException(ErrorCode.SM01);
			}
			//비밀번호 세팅중 오류가 생긴 경우
		}else {
			request.setAttribute("alertMsg", "해당정보가 존재하지 않습니다.");
			request.setAttribute("url", "/user/finduserinfo.do");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
	}
	
	
	protected void withdrawUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//굳이 비동기로 안해도 될 듯
		int res = 0;
		String deleteUser = request.getParameter("deleteUser");
		
		
		Map deleteUserMap = gson.fromJson(deleteUser, Map.class);
		String userId = (String) deleteUserMap.get("userId");
		
		
		res = userService.withdrawUser(userId);
		if(res > 0) {
			response.getWriter().print("success");
			request.getSession().removeAttribute("userMember"); //session에 저장된 정보 삭제
		} else {
			response.getWriter().print("fail");
			
		}
		
	}
	
	
	protected void modifyUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int res = 0;
		String modifyInfo = request.getParameter("modifyinfo");
		Map modifyInfoMap = gson.fromJson(modifyInfo, Map.class);
		String userId = (String) modifyInfoMap.get("userId");
		String userPw = (String) modifyInfoMap.get("userPw");
		String userName = (String) modifyInfoMap.get("userName");
		String userNick = (String) modifyInfoMap.get("userNick");
		String userEmail =(String) modifyInfoMap.get("userEmail");
		String birthday = (String) modifyInfoMap.get("userBirth");
		java.sql.Date userBirth = java.sql.Date.valueOf(birthday);
		String userPhone = (String) modifyInfoMap.get("userPhone");
		String kgName = (String) modifyInfoMap.get("userKg");
		
		res = userService.modifyUserInfo(userId, userPw, userName, userNick, userEmail, userBirth, userPhone);

		
		
		if(res > 0) {
			UserMember userMember = new UserMember();
			userMember.setUserId(userId);
			userMember.setPassword(userPw);
			userMember.setUserName(userName);
			userMember.setNickname(userNick);
			userMember.setEmail(userEmail);
			userMember.setBirth(userBirth);
			userMember.setPhoneNumber(userPhone);
			userMember.setKgName(kgName);
			request.getSession().setAttribute("userMember", userMember);
			response.getWriter().print("success");
			
			
		} else {
			response.getWriter().print("fail");
			
		}
	}
	

	
	
	
	
	
	
	

}
