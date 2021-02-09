package com.dang.member.user.model.service;

import java.sql.Connection;
import java.sql.Date;

import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
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
		
		
		
		
		
		public UserMember selectUserByName(String userName, String phoneNumber) {
			Connection conn = jdt.getConnection();
			//반환할 유저 객체 생성
			UserMember res = null;
			
			try {
				res = userDao.selectUserByName(conn, userName, phoneNumber);
			} finally {
				jdt.close(conn);
			}
			return res;
		}
	
		
		//insert문에는 commit 과 rollback !
		public int insertuserMember(UserMember userMember) {
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
