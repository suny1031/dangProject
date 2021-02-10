package com.dang.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.board.model.dao.BoardDao;
import com.dang.board.model.vo.Board;
import com.dang.common.jdbc.JDBCTemplate;

public class BoardService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();
	
	public BoardService() {
		
	}
	
	public String getDate() {
		return boardDao.getDate();
	}
	
	public int getNext() {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.getNext();
		} finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public int addBoard(String title, String kgName, String content) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.addBoard(title, kgName, content);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public ArrayList<Board> listBoard(int pageNumber){
		Connection conn = jdt.getConnection();
		ArrayList<Board> boardList = new ArrayList<>();
		try {
			boardList = boardDao.listBoard(pageNumber);
		} finally {
			jdt.close(conn);
		}
		return boardList;
	}
	
	public boolean nextPage(int pageNumber) {
		return boardDao.nextPage(pageNumber);
	}
	
	public int modifyBoard(String bdIdx) throws SQLException{
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = boardDao.modifyBoard(conn, bdIdx);
			jdt.commit(conn);
		} finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public Board viewBoard(int bdIdx){
		Connection conn = jdt.getConnection();
		try {
			Board board = boardDao.viewBoard(bdIdx);
		} finally {
			jdt.close(conn);
		}
		return null;
	}

}
