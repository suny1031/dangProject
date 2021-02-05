package com.dang.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dang.board.model.dao.BoardDao;
import com.dang.board.model.vo.Board;
import com.dang.common.jdbc.JDBCTemplate;

public class BoardService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();
	
	public void BoardService() {
		
	}
	
	public ArrayList<Board> addBoard() {
		Connection conn = jdt.getConnection();
		ArrayList<Board> boardList;
		try {
			boardList = boardDao.addBoard(conn);
		} finally {
			jdt.close(conn);
		}
		return boardList;
	}
	
	public List<Board> listBoard(){
		Connection conn = jdt.getConnection();
		List<Board> boardList = new ArrayList<>();
		try {
			boardList = boardDao.listBoard(conn);
		} finally {
			jdt.close(conn);
		}
		return boardList;
	}
	
	public List<Board> modifyBoard(){
		Connection conn = jdt.getConnection();
		List<Board> boardList;
		try {
			boardList = boardDao.modifyBoard(conn);
		} finally {
			jdt.close(conn);
		}
		return boardList;
	}
	
	public List<Board> viewBoard(){
		Connection conn = jdt.getConnection();
		List<Board> boardList;
		try {
			boardList = boardDao.viewBoard(conn);
		} finally {
			jdt.close(conn);
		}
		return boardList;
	}

}
