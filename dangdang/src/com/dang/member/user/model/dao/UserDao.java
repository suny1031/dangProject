package com.dang.member.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.member.user.model.vo.UserMember;

public class UserDao {
	
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public UserMember memberAuthenticate(Connection conn, String userId, String userPw) {
		UserMember userMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		
		try {
			String query = "select * from member where user_id =? and password = ?";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, userPw);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				userMember = new UserMember();
				userMember.setUserId(rset.getString("user_id"));
				userMember.setPassword(rset.getString("password"));
				userMember.setUserName(rset.getString("user_name"));
				userMember.setKgName(rset.getString("kg_name"));
				userMember.setClassName(rset.getString("class_name"));
				userMember.setEmail(rset.getString("email"));
				userMember.setBirth(rset.getDate("birth"));
				userMember.setPhoneNumber(rset.getString("phone_number"));
				userMember.setNickname(rset.getString("nickname"));
				userMember.setClassName(rset.getString("class_name"));
				userMember.setGrade(rset.getString("grade"));
			
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		
		return userMember;
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
