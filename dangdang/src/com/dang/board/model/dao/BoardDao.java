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
import com.dang.diary.model.vo.Diary;

public class BoardDao {

	public BoardDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance();

	// 공지사항 추가
	public void insertBoard(Connection conn, Board board) {

		int insert = 0;

		PreparedStatement pstm = null;

		try {

			String query = "insert into BD_NOTICE(BD_NO_IDX,KG_NAME,TITLE,CONTENT) Values(SC_BD_NO_IDX.nextval,?,?,?)";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, board.getKgName());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			insert = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BO01, e);
		} finally {
			jdt.close(pstm);
		}

	}

	// 공지사항 페이지 페이징
	public List<Board> selectBoardPage(Connection conn, int startRow, int endRow, String kgName) {
		String query = "select * from (select rownum rn, BD_NO_IDX, KG_NAME,TITLE,REG_DATE,CONTENT, POST from (select * from BD_NOTICE where KG_NAME = ? and POST = 0 order by BD_NO_IDX desc)) where rn between ? and ?";

		List<Board> list = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			pstm.setInt(2, startRow);
			pstm.setInt(3, endRow);

			rset = pstm.executeQuery();
			if (rset.next()) {
				list = new ArrayList<>();
				do {
					Board board = new Board();
					board.setBdIdx(rset.getInt("BD_NO_IDX"));
					board.setKgName(rset.getString("KG_NAME"));
					board.setTitle(rset.getString("TITLE"));
					board.setContent(rset.getString("CONTENT"));
					board.setRegDate(rset.getDate("REG_DATE"));
					board.setPost(rset.getInt("POST"));
					list.add(board);
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BO02, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return list;
	}

	// 공지사항 총 개수 메서드
	public int selectCountPage(Connection conn, String kgName) {

		int count = 0;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		String query = "select count(*) from BD_NOTICE where KG_NAME = ? and POST = 0 ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();
			if (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException(ErrorCode.BO02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return count; // 총 레코드 수 리턴
	}

	// 유치원 공지사항 미리보기
	public ArrayList<Board> selectBoardPreview(Connection conn, String kgName) {

		ArrayList<Board> boardList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from BD_NOTICE where KG_NAME = ? and POST = 0 and ROWNUM BETWEEN 1 and 7 order by BD_NO_IDX desc";
			// 삭제되지 않은 알림만 보여준다
			pstm = conn.prepareStatement(query);

			pstm.setString(1, kgName);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Board board = new Board();
				board.setBdIdx(rset.getInt("BD_NO_IDX"));
				board.setKgName(rset.getString("KG_NAME"));
				board.setContent(rset.getString("CONTENT"));
				board.setTitle(rset.getString("TITLE"));
				board.setRegDate(rset.getDate("REG_DATE"));
				board.setPost(rset.getInt("POST"));
				boardList.add(board);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BO02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return boardList;

	}

	// 상세 페이지
	public Board selectDetail(Connection conn, String bdIdx) {

		Board board = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from BD_NOTICE where BD_NO_IDX = ?";
			pstm = conn.prepareStatement(query);

			pstm.setString(1, bdIdx);

			rset = pstm.executeQuery();

			while (rset.next()) {
				board = new Board();
				board.setBdIdx(rset.getInt("BD_NO_IDX"));
				board.setKgName(rset.getString("KG_NAME"));
				board.setTitle(rset.getString("TITLE"));
				board.setContent(rset.getString("CONTENT"));
				board.setRegDate(rset.getDate("REG_DATE"));
				board.setPost(rset.getInt("POST"));

			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BO02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return board;

	}

	// 공시사항 수정
	public int updateBoard(Connection conn, String title, String content, int bdIdx) {

		int update = 0;

		PreparedStatement pstm = null;

		try {

			String query = "update BD_NOTICE set TITLE  =  ? , CONTENT = ? where BD_NO_IDX = ? ";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, title);
			pstm.setString(2, content);
			pstm.setInt(3, bdIdx);

			update = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BO04, e);
		} finally {
			jdt.close(pstm);
		}

		return update;

	}

	// 공지사항 삭제
	public int deleteBoard(Connection conn, int bdIdx) {

		int delete = 0;

		PreparedStatement pstm = null;

		try {

			String query = "update BD_NOTICE set POST = 1 where BD_NO_IDX = ?";

			pstm = conn.prepareStatement(query);

			pstm.setInt(1, bdIdx);

			delete = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.BO03, e);
		} finally {
			jdt.close(pstm);

		}

		return delete;

	}
}
