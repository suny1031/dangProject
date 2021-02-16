package com.dang.board.model.dao;

import java.sql.Connection;
import java.sql.Date;
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

	public BoardDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance();

	// 게시글을 올렸을때 SQL에서 now 명령어로 현재 시간을 받아와 작성 시간에 올려줌.\
//	public String getDate(Connection conn) {
//
//		PreparedStatement pstm = null;
//		ResultSet rSet = null;
//
//		try {
//			String query = "select now()";
//			pstm = conn.prepareStatement(query);
//			rSet = pstm.executeQuery();
//			if (rSet.next()) {
//				return rSet.getString(1);
//			}
//
//		} catch (SQLException e) {
//			throw new DataAccessException(ErrorCode.BD01, e);
//		} finally {
//			jdt.close(rSet, pstm);
//		}
//		// 오류 날 경우 ""반환
//		return "";
//	}

	// 게시글을 올렸을 때 기존 게시글이 있을 경우 + 1 을 해주어 bdIdx 를 매겨주는 역할
//	public int getNext(Connection conn) {
//
//		PreparedStatement pstm = null;
//		ResultSet rSet = null;
//
//		try {
//			String query = "select BD_NO_IDX from BD_NOTICE order by BD_NO_IDX desc";
//			pstm = conn.prepareStatement(query);
//			rSet = pstm.executeQuery();
//			// 게시물이 쌓이기 때문에 게시물이 있을 경우 +1 을 해준다.
//			if (rSet.next()) {
//				return rSet.getInt(1) + 1;
//			}
//			// 첫번째 게시물일 경우 1번이기 때문에 1 반환.
//			return 1;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			jdt.close(rSet, pstm);
//		}
//		// DB에서 오류가 날 경우를 알려줌.
//		return -1;
//	}

	// 게시글을 올릴 때 DB 에 데이터를 추가 시키는 메서드
	public int addBoard(Connection conn, String title, String kgName, String content) {
		// 작성 완료시 게시판 테이블에 들어갈 데이터
		PreparedStatement pstm = null;
		try {
			String query = "insert into BD_NOTICE(BD_NO_IDX , KG_NAME, TITLE, CONTENT, POST) values (SC_BD_NO_IDX.nextval , ?, ?, ?, ?)";
			pstm = conn.prepareStatement(query);

			pstm.setString(1, kgName);
			pstm.setString(2, title);
			pstm.setString(3, content);
			pstm.setInt(4, 1);

			return pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IB01, e);
		} finally {
			jdt.close(pstm);
		}
	}

	// 게시글 목록을 보기 위해 while 문으로 DB에 담겨 있는 게시글을 가져오는 역할
	// 한 페이지에 10개까지의 게시글을 담아옴
	public ArrayList<Board> listBoard(Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rSet = null;

		ArrayList<Board> list = new ArrayList<Board>();
		try {
			// 게시물을 불러오면서 한페이지에 최대 10개까지 보이게끔 불러옴 
			String query = "select * from BD_NOTICE where POST = 1";
			pstm = conn.prepareStatement(query);
			rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				Board board = new Board();
				board.setBdIdx(rSet.getInt(1));
				board.setKgName(rSet.getString(2));
				board.setTitle(rSet.getString(3));
				board.setRegDate(rSet.getDate(4));
				board.setContent(rSet.getString(5));
				board.setPost(rSet.getInt(6));
				
				list.add(board);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BL01, e);
		}finally {
			jdt.close(rSet, pstm);
		}
		// DB에서 오류가 날 경우를 알려줌.
		return list;
	}
	
	// listBoard() 메서드에서 10개를 넘게 게시글이 쌓일 경우 페이지를 추가해서 한 페이지에 10개씩 계속 추가해서 
	// 담을 수 있게 게시글 갯수에 따라 페이지를 늘려주는 역할
//	public boolean nextPage(Connection conn, int pageNumber) {
//		
//		PreparedStatement pstm = null;
//		ResultSet rSet = null;
//		
//		try {
//			String query = "";
//			pstm = conn.prepareStatement(query);
//			pstm.setInt(1, getNext(conn) - (pageNumber - 1) * 10);
//			rSet = pstm.executeQuery();
//			
//			if(rSet.next()) {
//				return true;
//			}
//
//		} catch (SQLException e) {
//			throw new DataAccessException(ErrorCode.BP01, e);
//		}finally {
//			jdt.close(rSet, pstm);
//		}
//		return false;
//	}

	// 게시글을 수정하는 메서드 한 메서드에 update, delete 를 같이 못하면 따로 나눠서 할 예정
//	public int modifyBoard(Connection conn, String bdIdx) {
//
//		int res = 0;
//		PreparedStatement pstm = null;
//
//		try {
//			// modifyBoard.do 경로에서 수정 버튼을 누를시 수정,
//    		String query = "update BD_NOTICE set CONTENT = ? set TITLE = ? where BD_NO_IDX = ?";
//    		pstm = conn.prepareStatement(query);
//    		pstm.setString(1, board.getContent());
//    		pstm.setString(2, board.getTitle());
//    		pstm.setInt(3, board.getBdIdx());
//			res = pstm.executeUpdate();
//
//			// modifyBoard.do 경로에서 삭제 버튼을 누를시 삭제
//			String query = "delete * from BD_NOTICE where BD_NO_IDX";
//			pstm = conn.prepareStatement(query);
//			pstm.setInt(1, Integer.parseInt(bdIdx));
//			res = pstm.executeUpdate();
//			// BD_NOTICE 테이블에서 해당 번호의 게시글 삭제
//
//		} catch (Exception e) {
//			throw new DataAccessException(ErrorCode.BM01, e);
//			// throw new DataAccessException(ErrorCode.BM02, e);
//		} finally {
//			jdt.close(pstm);
//		}
//		return res;
//	}
	
	// 게시글 수정 기능
	public int modifyBoard(Connection conn, int bdIdx, String title, String content) {
		
		PreparedStatement pstm = null;
		
		try {
			String query = "update BD_NOTICE set TITLE = ?, CONTENT = ? where BD_NO_IDX = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, title);
			pstm.setString(2, content);
			pstm.setInt(3, bdIdx);
			
			return pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BM01, e);
		} finally {
			jdt.close(pstm);
		}
	}
	
	// 게시글 삭제 기능
	public int deleteBoard(Connection conn, int bdIdx) {
		
		PreparedStatement pstm = null;
		
		try {
			String query = "update BD_NOTICE set POST = 0 where BD_NO_IDX = ?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			return pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BM02, e);
		} finally {
			jdt.close(pstm);
		}
	}
	
	

	// 게시글 목록에서 게시글 제목을 눌렀을 때 볼 수 있는 게시글 상세 페이지
	public Board viewBoard(Connection conn, int bdIdx) {

		PreparedStatement pstm = null;
		ResultSet rSet = null;
		

		try {
			String query = "select * from BD_NOTICE where BD_NO_IDX = ?";
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, bdIdx);
			rSet = pstm.executeQuery();

			if (rSet.next()) {
				Board board = new Board();
				board.setBdIdx(rSet.getInt("BD_NO_IDX"));
				board.setKgName(rSet.getString("KG_NAME"));
				board.setTitle(rSet.getString("TITLE"));
				board.setRegDate(rSet.getDate("REG_DATE"));
				board.setContent(rSet.getString("CONTENT"));
				board.setPost(rSet.getInt("POST"));
				
				return board;
			}

		} catch (Exception e) {
			throw new DataAccessException(ErrorCode.BV01, e);
		}finally {
			jdt.close(rSet, pstm);
		}
		return null;
	}

}
