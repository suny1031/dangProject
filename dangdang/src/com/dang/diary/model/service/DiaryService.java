package com.dang.diary.model.service;

import java.sql.Connection;
import java.util.List;

import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.diary.model.dao.DiaryDao;
import com.dang.diary.model.vo.Diary;
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
}
