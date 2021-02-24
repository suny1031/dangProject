package com.dang.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.board.model.dao.BoardDao;
import com.dang.board.model.vo.Board;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.diary.model.vo.Diary;

public class BoardService {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();

	public BoardService() {

	}

	// 공지사항 작성 메서드
	public void insertBoard(Board board) {
		Connection conn = jdt.getConnection();

		try {

			boardDao.insertBoard(conn, board);

			jdt.commit(conn);

		} catch (DataAccessException e) {

			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);

		} finally {
			jdt.close(conn);
		}

	}

	// 공지사항 페이지 페이징
	public List<Board> selectBoardPage(int startRow, int endRow, String kgName) {
		Connection conn = jdt.getConnection();
		List<Board> board;
		try {
			board = boardDao.selectBoardPage(conn, startRow, endRow, kgName);

		} finally {
			jdt.close(conn);
		}
		return board;

	}

	// 알림장 총 개수 메서드
	public int selectCountPage(String kgName) {
		Connection conn = jdt.getConnection();

		int count;
		try {
			count = boardDao.selectCountPage(conn, kgName);
		} finally {
			jdt.close(conn);
		}
		return count;

	}

	// 공지사항 미리보기
	public ArrayList<Board> selectDiaryPreview(String kgName) {
		Connection conn = jdt.getConnection();
		ArrayList<Board> boardList;
		try {
			boardList = boardDao.selectBoardPreview(conn, kgName);

		} finally {
			jdt.close(conn);
		}
		return boardList;

	}

	public Board selectDetail(String bdIdx) {
		Connection conn = jdt.getConnection();
		Board board;
		try {
			board = boardDao.selectDetail(conn, bdIdx);

		} finally {
			jdt.close(conn);
		}
		return board;

	}

	// 알림 수정 해주는 메서드
	public int updateBoard(String title, String content, int bdIdx) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.updateBoard(conn, title, content, bdIdx);
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
	public int deleteBoard(int bdIdx) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.deleteBoard(conn, bdIdx);
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
