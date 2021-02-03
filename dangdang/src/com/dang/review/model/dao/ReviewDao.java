package com.dang.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
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

			String query = "select * from review where kg_name = ?";

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
	
	
	public int insertReview(Connection conn, Review review) {
		int res = 0;
		String sql = "insert into review (RV_IDX,KG_NAME,USER_NAME,TITLE,content) Values('b' || sc_rv_idx.nextval, ?, ?, ?)";

		PreparedStatement pstm = null;

		try {

			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IB01, e);

		} finally {
			jdt.close(pstm);
		}

		return res;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
