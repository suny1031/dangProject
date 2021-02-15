package com.dang.member.school.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.dang.board.model.vo.Board;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileVo;
import com.dang.map.model.vo.Service;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;



public class SchoolDao {
	
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	
	
	public SchoolMember memberAuthenticate(Connection conn, String kgId, String kgPw) {
		SchoolMember schoolMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from kindergarden where kg_id =? and kg_pw = ? ";
		
		try{

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgId);
			pstm.setString(2, kgPw);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				
				schoolMember = new SchoolMember();
				schoolMember.setKgName(rset.getString("kg_name"));
				schoolMember.setKgId(rset.getString("kg_id"));
				schoolMember.setKgIdx(rset.getString("kg_idx"));
				schoolMember.setKgPw(rset.getString("kg_pw"));
				schoolMember.setKgAddress(rset.getString("kg_address"));
				schoolMember.setKgTell(rset.getString("kg_tell"));
				schoolMember.setKgOperateTime(rset.getString("kg_operate_time"));
				schoolMember.setKgNotice(rset.getString("kg_notice"));
				schoolMember.setKgGrade(rset.getString("kg_grade"));
				schoolMember.setKgEmail(rset.getString("kg_email"));
			
	
			} //select문은 commit이나 rollback 필요없음
			
			
		}catch(SQLException e) {
			throw new DataAccessException(ErrorCode.LM01, e);
		}finally {
			jdt.close(rset, pstm);
		}

		return schoolMember; 
		
	}
	
	
	public Service schoolProService(Connection conn, String kgName) {
		Service schoolProService = null;
		PreparedStatement  pstm = null;
		ResultSet rset = null;
		
		String query = "select * from services where kg_name = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				schoolProService = new Service();
				schoolProService.setKgName(rset.getString("kg_name"));
				schoolProService.setIsKg(rset.getInt("is_kg"));
				schoolProService.setIsCafe(rset.getInt("is_cafe"));
				schoolProService.setIsHotel(rset.getInt("is_hotel"));
				schoolProService.setIsPickup(rset.getInt("is_pickup"));
				schoolProService.setIsMedic(rset.getInt("is_medic"));
				schoolProService.setIsAcademy(rset.getInt("is_academy"));
				schoolProService.setIsSpa(rset.getInt("is_spa"));
				
			}		
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SE01, e);
		}finally {
			jdt.close(rset, pstm);
		}

		return schoolProService;
	}
	
	
	
	public SchoolMember findSchoolId(Connection conn, String schoolName, String schoolPhone) {
		SchoolMember schoolMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from kindergarden where kg_name = ? and kg_tell = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, schoolName);
			pstm.setString(2, schoolPhone);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				schoolMember = new SchoolMember();
				schoolMember.setKgName(rset.getString("kg_name"));
				schoolMember.setKgId(rset.getString("kg_id"));
				schoolMember.setKgPw(rset.getString("kg_pw"));
				schoolMember.setKgAddress(rset.getString("kg_address"));
				schoolMember.setKgTell(rset.getString("kg_tell"));
				schoolMember.setKgOperateTime(rset.getString("kg_operate_time"));
				schoolMember.setKgNotice(rset.getString("kg_notice"));
				schoolMember.setKgGrade(rset.getString("kg_grade"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		}finally {
			jdt.close(rset, pstm);;
		}
		
		return schoolMember;
		
	}
	
	
	

	
	
	
	public int modifySchoolInfo(Connection conn, String kgId, String kgName, String kgAddress, String kgTell, String kgOperateTime, String kgNotice, String kgEmail ) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "update kindergarden set kg_name =?, kg_address=?, kg_tell=?, kg_operate_time=?, kg_notice=?, kg_email=? where kg_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			pstm.setString(2, kgAddress);
			pstm.setString(3, kgTell);
			pstm.setString(4, kgOperateTime);
			pstm.setString(5, kgNotice);
			pstm.setString(6, kgEmail);
			pstm.setString(7, kgId);
			
			res = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01, e);
			
		}finally {
			jdt.close(pstm);
		}
		
		return res;
		
	}
	
	
public int modifySchoolService(Connection conn, String kgName, int isKg, int isCafe, int isHotel, int isPickup, int isMedic, int isAcademy, int isSpa ) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "update services set is_kg =?, is_cafe=?, is_hotel=?, is_pickup=?, is_medic =?, is_academy =?, is_spa = ? where kg_name = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, isKg);
			pstm.setInt(2, isCafe);
			pstm.setInt(3, isHotel);
			pstm.setInt(4, isPickup);
			pstm.setInt(5, isMedic);
			pstm.setInt(6, isAcademy);
			pstm.setInt(7, isSpa);
			pstm.setString(8, kgName);
			
			res = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01, e);
			
		}finally {
			jdt.close(pstm);
		}
		
		return res;
		
	}


	public int uploadSchoolPhoto(Connection conn, FileVo schoolPhotoData, String kgIdx) {
		int res = 0;
		String fIdx = "";
		
		
		/* 1. 새로 등록되는 게시글의 파일 정보 저장
		typeIdx값이 시퀀스 currval
		if(fileData.getTypeIdx() == null) {
			fIdx = "'k'||kgIdx";
		//2. 수정할 때 사용자가 파일을 추가 등록해서 파일 정보 저장
		//	수정할 게시글의 bdIdx값
		}else {
			
		}*/
		
		fIdx = "'k'||" + kgIdx;
		
		
		
		String query = "insert into d_file (f_idx,type_idx,origin_file_name,rename_file_name,save_path) "
				+ "values(sc_f_idx.nextval," + fIdx + ",?,?,?)";
		
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, schoolPhotoData.getOriginFileName());
			pstm.setString(2, schoolPhotoData.getRenameFileName());
			pstm.setString(3, schoolPhotoData.getSavePath());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IF01, e);
		}finally {
			jdt.close(pstm);
		}
		
		return res;
	}
		
	
	
	
	public ArrayList<FileVo> selectSchoolPhoto(Connection conn, String kgIdx){
		ArrayList<FileVo> photoList = new ArrayList<>();
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		String findkgIdx = "k" + kgIdx;
		
		String query = "SELECT * FROM (SELECT * FROM D_FILE WHERE TYPE_IDX = ? ORDER BY REG_DATE DESC) WHERE ROWNUM < 5";

		
		try {
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1,findkgIdx);
			rset = pstm.executeQuery();
			
			while(rset.next()){
				FileVo photoFile = new FileVo();
				photoFile.setFidx(rset.getInt(1));
				photoFile.setTypeIdx(rset.getString(2));
				photoFile.setOriginFileName(rset.getString(3));
				photoFile.setRenameFileName(rset.getString(4));
				photoFile.setSavePath(rset.getString(5));
				photoFile.setRegDate(rset.getDate(6));
				photoFile.setIsDel(rset.getInt(7));
				photoList.add(photoFile);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.FILE02, e);
			
		}finally {
			jdt.close(rset, pstm);
		}
		
		return photoList;
		
		
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
	
	
	public ArrayList<UserMember> selectClassMember(Connection conn, String kgName){
		
		ArrayList<UserMember> classMemberList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from Member where kg_name = ?";
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				UserMember userMember = new UserMember();
				userMember.setUserId(rset.getString("user_id"));
				userMember.setUserName(rset.getString("user_name"));
				userMember.setEmail(rset.getString("email"));
				userMember.setPhoneNumber(rset.getString("phone_number"));
				userMember.setNickname(rset.getString("nickname"));
				
				classMemberList.add(userMember);
			}
			
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
			
		}finally {
			jdt.close(rset, pstm);
		}
		
		return classMemberList;
	}
	
	
	
	
	
	
	

}
