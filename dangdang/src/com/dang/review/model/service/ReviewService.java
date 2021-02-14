package com.dang.review.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileUtil;
import com.dang.common.util.file.FileVo;
import com.dang.review.model.dao.ReviewDao;
import com.dang.review.model.vo.Review;

public class ReviewService {

	ReviewDao reviewDao = new ReviewDao();
	JDBCTemplate jbt = JDBCTemplate.getInstance();

	public ReviewService() {

	}

	// 유치원의 리뷰 보여주는 메서드
	public ArrayList<Review> selectReview(String kgName) {
		Connection conn = jbt.getConnection();
		ArrayList<Review> reviewList;
		try {
			reviewList = reviewDao.selectReview(conn, kgName);
		} finally {
			jbt.close(conn);
		}
		return reviewList;

	}

	// 유치원의 리뷰 사진 보여주는 메서드
	public ArrayList<FileVo> selectFile(String kgName) {
		Connection conn = jbt.getConnection();
		ArrayList<FileVo> fileList;
		try {
			fileList = reviewDao.selectFile(conn, kgName);
		} finally {
			jbt.close(conn);
		}
		return fileList;

	}

	// 리뷰 추가 메서드
	public void insertReview(String nickName, String kgName, HttpServletRequest request) {

		Connection conn = jbt.getConnection();

		// 게시글 저장
		Map<String, List> reviewData = new FileUtil().fileUpload(request);

		Review review = new Review();
		review.setUserName(nickName);
		review.setKgName(kgName);
		review.setTitle(reviewData.get("title").get(0).toString());
		review.setContent(reviewData.get("content").get(0).toString());

		try {
			reviewDao.insertReview(conn, review);

			for (FileVo fileData : (List<FileVo>) reviewData.get("fileData")) {
				reviewDao.insertFile(conn, fileData);
			}

			jbt.commit(conn);
		} catch (DataAccessException e) {
			jbt.rollback(conn);
			throw new ToAlertException(e.error, e);
		} finally {
			jbt.close(conn);
		}

	}

}
