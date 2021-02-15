package com.dang.member.user.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dang.board.model.vo.Board;
import com.dang.common.code.ConfigCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.mail.MailSender;
import com.dang.common.util.http.HttpUtils;
import com.dang.member.user.model.dao.UserDao;
import com.dang.member.user.model.vo.UserMember;

public class UserService {
	
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	UserDao userDao = new UserDao();
	
	
	
	public UserMember memberAuthenticate(String userId, String userPw) {
		//Connection 연결
		Connection conn = jdt.getConnection();
		//반환할 user 객체 생성
		UserMember res = null;
		
		try {
			res = userDao.memberAuthenticate(conn, userId, userPw);
			
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	
	
		
		public UserMember selectUserById(String userId) {
		Connection conn = jdt.getConnection();
		//반환할 유저 객체 생성
		UserMember res = null;
		
		try {
			
			res = userDao.selectUserById(conn, userId);
			
		} finally {
			jdt.close(conn);
		}
		
		return res;
	}
		
		
		
		
		
		public UserMember findUserId(String userName, String phoneNumber) {
			Connection conn = jdt.getConnection();
			//반환할 유저 객체 생성
			UserMember userMember = null;
			
			try {
				userMember = userDao.findUserId(conn, userName, phoneNumber);
			} finally {
				jdt.close(conn);
			}
			return userMember;
		}
		
		
		
		
		
		
		
		public UserMember findUserPw(String userId, String userEmail) {
			Connection conn = jdt.getConnection();
			UserMember userMember = null;
			
			try{
				userMember = userDao.findUserPw(conn, userId, userEmail);
			 
			}finally {
				jdt.close(conn);
			}
			return userMember;
			
		}
		
		
	

		
		//회원가입시 이메일 인증보내기
		public void authenticateEmail(UserMember userMember) {
			
			String subject = "회원가입을 마무리해주세요.";
			String htmlText = "";
			
			
			HttpUtils http = new HttpUtils();
			//url 작성
			String url = ConfigCode.DOMAIN + "/mail.do";
			
			//header를 작성
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/x-www-form-urlencoded");
			
			//parameter 저장
			Map<String, String> params = new HashMap<String, String>();
			params.put("template", "user_joincomplete");
			params.put("userId", userMember.getUserId());
			
			//body로 전송해서 받기
			htmlText = http.post(url, http.urlEncodedForm(params), headers);
					
			String to = userMember.getEmail();
			new MailSender().sendEmail(subject, htmlText, to);
			
		}
		
		//비밀번호 찾기시 이메일보내기
		public void finUserPwEmail(UserMember userMember) {
			
			
			String subject = "임시 비밀번호가 발급되었습니다.";
			String htmlText = "";
			
			
			HttpUtils http = new HttpUtils();
			//url 작성
			String url = ConfigCode.DOMAIN + "/mail.do";
			
			//header를 작성
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/x-www-form-urlencoded");
			
			//parameter 저장
			Map<String, String> params = new HashMap<String, String>();
			params.put("template", "user_resetpassword");
			params.put("userPw", userMember.getPassword());
			
			//body로 전송해서 받기
			htmlText = http.post(url, http.urlEncodedForm(params), headers);
					
			String to = userMember.getEmail();
			new MailSender().sendEmail(subject, htmlText, to);
			
		}
		
		
		
	
		
		
		
		//insert문에는 commit 과 rollback !
		public int insertUserMembers(UserMember userMember) {
			Connection conn = jdt.getConnection();
			int res = 0;
			
			try{
				res = userDao.insertuserMember(conn, userMember);

				jdt.commit(conn);
			}catch(DataAccessException e){
				jdt.rollback(conn);
			}finally {
				jdt.close(conn);
			}
			return res;
		}
		
		
		
		
		
		
		
		public int withdrawUser(String userId) {
			Connection conn = jdt.getConnection();
			int res = 0;
			
			try {
				res = userDao.withdrawUser(conn, userId);
				jdt.commit(conn);
			}catch(DataAccessException e) {
				jdt.rollback(conn);
			}finally {
				jdt.close(conn);
			}
			return res;
		}
	
	
		
		
		
		public int modifyUserInfo(String userId, String userPw, String userName, String userNick, String userEmail, Date userBirth, String userPhone) {
			
			Connection conn = jdt.getConnection();
			int res = 0;
			
			try {
			
			res = userDao.modifyUserInfo(conn, userId, userPw, userName, userNick, userEmail, userBirth, userPhone);
			jdt.commit(conn);
			
			}catch(DataAccessException e){
				jdt.rollback(conn);
			}finally {
				jdt.close(conn);
			}
			System.out.println(res);
			return res;
			
		}
		
		
		
		
		
		public ArrayList<Board> selectNoticePreview(String kgName){
			Connection conn = jdt.getConnection();
			ArrayList<Board> noticeList;
			
				try {
					noticeList = userDao.selectNoticePreview(conn, kgName);
				}finally {
					jdt.close(conn);
				}
				return noticeList;
			}
	
	
	
	
	
	

}
