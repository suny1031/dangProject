package com.dang.review.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.review.model.dao.ReviewDao;
import com.dang.review.model.vo.Review;



public class ReviewService {
	
	ReviewDao reviewDao = new ReviewDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public ReviewService() {

	}
	
	
	public ArrayList<Review> selectReview(String kgName) {
		Connection conn = jdt.getConnection();
		ArrayList<Review> reviewList;
		try {
			reviewList = reviewDao.selectReview(conn, kgName);
		} finally {
			jdt.close(conn);
		}
		return reviewList;

	}

	
	
	
	
}
