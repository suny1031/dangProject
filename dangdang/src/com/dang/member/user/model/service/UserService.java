package com.dang.member.user.model.service;

import java.sql.Connection;

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
	
	
	
	
	
	
	
	
	
	
	

}
