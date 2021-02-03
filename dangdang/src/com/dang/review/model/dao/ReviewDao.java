package com.dang.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileVo;
import com.dang.review.model.vo.Review;

public class ReviewDao {

	public ReviewDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance(); // 템플릿 생성

	public ArrayList<Review> selectReview(Connection conn, String kgName) {

		ArrayList<Review> reviewList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from review where kg_name = ? ORDER by rv_idx desc";

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Review review = new Review();
				review.setRvIdx(rset.getString("RV_IDX"));
				review.setKgName(rset.getString("KG_NAME"));
				review.setContent(rset.getString("CONTENT"));
				review.setStarRate(rset.getInt("STAR_RATE"));
				review.setRegDate(rset.getDate("REG_DATE"));
				review.setTitle(rset.getString("TITLE"));
				review.setUserName(rset.getString("USER_NAME"));
				reviewList.add(review);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return reviewList;

	}
	
	public ArrayList<FileVo> selectFile(Connection conn, String kgName) {

		ArrayList<FileVo> fileList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from d_file f join review r on(type_idx = rv_idx) where kg_name = ? ORDER by r.rv_idx desc";

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				FileVo file = new FileVo();
				file.setFidx(rset.getInt(1));
				file.setTypeIdx(rset.getString(2));
				file.setOriginFileName(rset.getString(3));
				file.setRenameFileName(rset.getString(4));
				file.setSavePath(rset.getString(5));
				file.setRegDate(rset.getDate(6));
				file.setIsDel(rset.getInt(7));
				fileList.add(file);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return fileList;

	}
	
	
	

	public int insertReview(Connection conn, Review review) {
		int res = 0;
		String sql = "insert into REVIEW (RV_IDX,KG_NAME,USER_NAME,TITLE,CONTENT) Values('r' || sc_rv_idx.nextval, ?, ?,?,?)";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, review.getKgName());
			pstm.setString(2, review.getUserName());
			pstm.setString(3, review.getTitle());
			pstm.setString(4, review.getContent());
			res = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IB01, e);
		} finally {
			jdt.close(pstm);
		}

		return res;

	}
	
	
	public int insertFile(Connection conn, FileVo fileData) {
		int res = 0;
		String rvIdx = "";
		if (fileData.getTypeIdx() == null) {
			// 1. 새로 등록되는 게시글의 파일 정보 저장
			// typeIdx값이 시퀀스의 currval
			rvIdx = " 'r' || sc_rv_idx.currval";

		} else {

			// 2. 수정할 때 사용자가 파일을 추가 등록해서 파일 정보 저장
			// 수정할 게시글의 bdIdx값
			rvIdx = "'" + fileData.getTypeIdx() + "'";

		}
		System.out.println(fileData);

		String sql = "insert into D_FILE (F_IDX,TYPE_IDX,ORIGIN_FILE_NAME,RENAME_FILE_NAME, SAVE_PATH) values(sc_f_idx.nextVal,"
				+ rvIdx + ",?,?,?)";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileData.getOriginFileName());
			pstm.setString(2, fileData.getRenameFileName());
			pstm.setString(3, fileData.getSavePath());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IF01, e);
		} finally {
			jdt.close(pstm);
		}

		return res;
	}
	
}
