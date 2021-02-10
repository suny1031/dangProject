package com.dang.member.user.model.service;

import java.sql.Connection;
import java.sql.Date;

import com.dang.common.code.ConfigCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.mail.MailSender;
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
			String htmlText = "<h1>회원가입을 마무리하기 위해 아래의 링크를 클릭해주세요.</h1>";
			htmlText += "<a href='"+ ConfigCode.DOMAIN + "/user/joinimpl.do'>회원가입 링크</a>";
			String to = userMember.getEmail();
			
			new MailSender().sendEmail(subject, htmlText, to);
			
		}
		
		//비밀번호 찾기시 이메일보내기
		public void finUserPwEmail(UserMember userMember) {
			
			String subject = "임시 비밀번호가 발급되었습니다.";
			String htmlText ="<h1>'${param.userPw}'</h1><h1>입니다."
							+ "</h1><h1>아래의 링크를 클릭하시면 로그인 창으로 이동합니다."
							+ "</h1><h1>로그인 후 임시비밀번호를 바꿔주세요.</h1>";
			
			htmlText += "<a href='"+ ConfigCode.DOMAIN + "/user/login.do\'>홈페이지 이동하기</a>";
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
	
	
	
	
	
	

}
