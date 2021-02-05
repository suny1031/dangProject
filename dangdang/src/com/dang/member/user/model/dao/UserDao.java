package com.dang.member.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.member.user.model.vo.UserMember;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;

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
	
	
	//해당 ID가 존재하는지 확인시 사용할 기능
	//userMember의 아이디를 기준으로 userMember의 데이터가 존재하는지 확인하는 메소드
	public UserMember selectUserById(Connection conn, String userId) {
		
			UserMember userMember = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
			
			String query = "select * from member where user_id = ?";
			
			try {
				pstm = conn.prepareStatement(query);
				pstm.setString(1, userId);
				
				//쿼리문 실행하고 결과값 받기
				rset = pstm.executeQuery();
				
				//결과 값이 있다면,
				if(rset.next()) {
					userMember = new UserMember();
					userMember.setUserId(rset.getString("user_id"));
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
	
	
	
	//아이디찾기시 쓸 기능
	//userMember의 아이디와 핸드폰 번호를 기준으로 userMember의 데이터가 존재하는지 확인하는 메소드
	public UserMember selectUserByName(Connection conn, String userName, String phoneNumber) {
		
		UserMember userMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_id = ? and phone_number = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userName);
			pstm.setString(2, phoneNumber);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				userMember = new UserMember();
				userMember.setUserId(rset.getString("user_id"));
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
			e.printStackTrace();
		} finally {
			jdt.close(rset, pstm);
		}
		return userMember;	
	
	}
	
	
	//join시 필요한 기능
	public int insertuserMember(Connection conn, UserMember userMember) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "insert into member(user_id, password, user_name, email, birth, phone_number, nickname, class_name, kg_name) values (?,?,?,?,?,?,?,?,?)";
		
		try {
			pstm = conn.prepareStatement(query);

			pstm.setString(1, userMember.getUserId());
			pstm.setString(2, userMember.getPassword());
			pstm.setString(3, userMember.getUserName());
			pstm.setString(4, userMember.getEmail());
			pstm.setDate(5, (java.sql.Date) userMember.getBirth());
			pstm.setString(6, userMember.getPhoneNumber());
			pstm.setString(7, userMember.getNickname());
			pstm.setString(8, userMember.getClassName());
			pstm.setString(9, userMember.getKgName());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IM01, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
		
	}
	
	
	
	
	
	
	
	
	

}
