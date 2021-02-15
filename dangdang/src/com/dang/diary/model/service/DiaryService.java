package com.dang.diary.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.diary.model.dao.DiaryDao;
import com.dang.diary.model.vo.Diary;
import com.dang.reservation.model.vo.Reservation;

public class DiaryService {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	DiaryDao diaryDao = new DiaryDao();

	// 알림장 작성 메서드
	public void insertDiary(Diary diary) {
		Connection conn = jdt.getConnection();

		try {

			diaryDao.insertDiary(conn, diary);

			jdt.commit(conn);

		} catch (DataAccessException e) {

			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);

		} finally {
			jdt.close(conn);
		}

	}

	// 알림장 페이지 페이징
	public List<Diary> selectDiaryPage(int startRow, int endRow, String kgName) {
		Connection conn = jdt.getConnection();
		List<Diary> diary;
		try {
			diary = diaryDao.selectDiaryPage(conn, startRow, endRow, kgName);

		} finally {
			jdt.close(conn);
		}
		return diary;

	}

	// 알림장 총 개수 메서드
	public int selectCountPage(String kgName) {
		Connection conn = jdt.getConnection();

		int count;
		try {
			count = diaryDao.selectCountPage(conn, kgName);
		} finally {
			jdt.close(conn);
		}
		return count;

	}

	// 알람 미리보기
	public ArrayList<Diary> selectDiaryPreview(String kgName) {
		Connection conn = jdt.getConnection();
		ArrayList<Diary> DiaryList;
		try {
			DiaryList = diaryDao.selectDiaryPreview(conn, kgName);

		} finally {
			jdt.close(conn);
		}
		return DiaryList;

	}

	// 알람 미리보기
	public Diary selectDetail(String bdIdx) {
		Connection conn = jdt.getConnection();
		Diary diary;
		try {
			diary = diaryDao.selectDetail(conn, bdIdx);

		} finally {
			jdt.close(conn);
		}
		return diary;

	}
	
		// 알림 수정 해주는 메서드
		public int updateDiary(String title, String content,int bdIdx) {
			Connection conn = jdt.getConnection();
			int res = 0;
			try {
				res = diaryDao.updateDiary(conn, title, content, bdIdx);
				jdt.commit(conn);
			} catch (DataAccessException e) {
				jdt.rollback(conn);
				throw new ToAlertException(e.error);
			} finally {
				jdt.close(conn);
			}
			return res;

		}
		
		// 알림 삭제 메서드
		public int deleteDiary(int bdIdx) {
			Connection conn = jdt.getConnection();
			int res = 0;
			try {
				res = diaryDao.deleteDiary(conn, bdIdx);
				jdt.commit(conn);
			} catch (DataAccessException e) {
				jdt.rollback(conn);
				throw new ToAlertException(e.error);
			} finally {
				jdt.close(conn);
			}
			return res;

		}
}
