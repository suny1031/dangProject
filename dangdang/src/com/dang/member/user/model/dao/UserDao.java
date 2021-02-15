package com.dang.member.user.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dang.board.model.vo.Board;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.member.user.model.vo.UserMember;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;

import oracle.jdbc.proxy.annotation.Pre;

public class UserDao {
	
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	
	
	public UserMember memberAuthenticate(Connection conn, String userId, String userPw) {
		UserMember userMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where user_id =? and password = ?";
		
		try {
			
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
				userMember.setIsleave(rset.getInt("isleave"));
			
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.LM01, e);
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
	
	
	
	//아이디 찾기시 쓸 기능
	//userMember의 아이디와 핸드폰 번호를 기준으로 userMember의 데이터가 존재하는지 확인하는 메소드
	public UserMember findUserId(Connection conn, String userName, String phoneNumber) {
		
		UserMember userMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_name = ? and phone_number = ?";
		
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
			throw new DataAccessException(ErrorCode.SM01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return userMember;	
	
	}
	
	//해당 정보에 맞는 회원검색
	public UserMember findUserPw(Connection conn, String userId, String userEmail) {
		
		UserMember userMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_id = ? and email = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, userEmail);
			
			rset = pstm.executeQuery();
			
			//결과값이 있다면
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
			jdt.commit(conn);
			
		}
		return res;
		
	}
	
	
	
	
	public int withdrawUser(Connection conn, String userId) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "update member set isleave = '1' where user_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DM01, e);
		
		}finally {
			jdt.close(pstm);
		}
		
	return res;
	
	}
	
	
	public int modifyUserInfo(Connection conn, String userId, String userPw, String userName, String userNick, String userEmail,  Date userBirth, String userPhone) {
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "update member set password =?, user_name=?, nickname=?, email=?, birth=?, phone_number=? where user_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userPw);
			pstm.setString(2, userName);
			pstm.setString(3, userNick);
			pstm.setString(4, userEmail);
			pstm.setDate(5, userBirth);
			pstm.setString(6, userPhone);
			pstm.setString(7, userId);
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01, e);
		}finally {
			jdt.close(pstm);
		}
	
		return res;
		
	}
	
	
public ArrayList<Board> selectNoticePreview(Connection conn, String kgName){
		
		ArrayList<Board> noticeList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from bd_notice where kg_name = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset =  pstm.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setBdIdx(rset.getInt("bd_no_idx"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setKgName(rset.getString("kg_name"));
				
				noticeList.add(board);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BL01, e);
		}finally {
			jdt.close(rset, pstm);
		}
		
		return noticeList;
		
	}
	
	
	
	
	
	
	
	

}
