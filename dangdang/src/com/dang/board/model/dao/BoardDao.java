package com.dang.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.board.model.vo.Board;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;

public class BoardDao {

    public BoardDao(){

    }
    
    JDBCTemplate jdt = JDBCTemplate.getInstance();
    
    public ArrayList<Board> addBoard(Connection conn){
    	ArrayList<Board> addBoard = new ArrayList<Board>();
    	PreparedStatement pstm = null;
    	ResultSet rSet = null;
    	
    	try {
    		
			String query = "select * from BD_NOTICE";
			pstm = conn.prepareStatement(query);
			rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				Board board = new Board();
				board.setBdIdx(rSet.getInt("BD_NO_IDX"));
				board.setKgName(rSet.getString("KG_NAME"));
				board.setTitle(rSet.getString("TITLE"));
				board.setRegDate(rSet.getDate("REG_DATE"));
				board.setContent(rSet.getString("CONTENT"));
				addBoard.add(board);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BA01, e);
		}finally {
			jdt.close(rSet, pstm);
		}
    	
    	return addBoard;
    }
    
    public List<Board> listBoard(Connection conn){
    List listBoard = new ArrayList<>(); 
    	return null;
    }
    
    public List<Board> modifyBoard(Connection conn){
    	return null;
    }
    
    public List<Board> viewBoard(Connection conn){
    	return null;
    }
    
}
