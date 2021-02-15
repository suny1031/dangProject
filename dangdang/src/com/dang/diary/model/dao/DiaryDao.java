package com.dang.diary.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.diary.model.vo.Diary;
import com.dang.reservation.model.vo.Reservation;

public class DiaryDao {

	public DiaryDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance();

	// 알림장 작성 메서드
	public void insertDiary(Connection conn, Diary diary) {

		int insert = 0;

		PreparedStatement pstm = null;

		try {

			String query = "insert into BD_DIARY(BD_DIARY_IDX,KG_NAME,TITLE,CONTENT) Values(SC_BD_DIARY_IDX.nextval,?,?,?)";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, diary.getKgName());
			pstm.setString(2, diary.getTitle());
			pstm.setString(3, diary.getContent());
			insert = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DA01, e);
		} finally {
			jdt.close(pstm);
		}

	}

	// 알림장 페이지 페이징
	public List<Diary> selectDiaryPage(Connection conn, int startRow, int endRow, String kgName) {
		String query = "select * from (select rownum rn, BD_DIARY_IDX, KG_NAME,CLASS_NAME,TITLE,REG_DATE,CONTENT, IS_DEL from (select * from BD_DIARY where KG_NAME = ? and IS_DEL = 0 order by BD_DIARY_IDX desc)) where rn between ? and ?";

		List<Diary> list = null;

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
					Diary diary = new Diary();
					diary.setBdDiaryIdx(rset.getInt("BD_DIARY_IDX"));
					diary.setKgName(rset.getString("KG_NAME"));
					diary.setTitle(rset.getString("TITLE"));
					diary.setContent(rset.getString("CONTENT"));
					diary.setRegDate(rset.getDate("REG_DATE"));
					diary.setIsDel(rset.getString("IS_DEL"));
					list.add(diary);
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DA02, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return list;
	}

	// 알림장 총 개수 메서드
	public int selectCountPage(Connection conn, String kgName) {

		int count = 0;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		String query = "select count(*) from BD_DIARY where KG_NAME = ? and IS_DEL = 0 ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();
			if (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException(ErrorCode.DA02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return count; // 총 레코드 수 리턴
	}

	// 유치원 알람 미리보기
	public ArrayList<Diary> selectDiaryPreview(Connection conn, String kgName) {

		ArrayList<Diary> DiaryList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from BD_DIARY where KG_NAME = ? and IS_DEL = 0 and ROWNUM BETWEEN 1 and 5 order by BD_DIARY_IDX desc";
			// 삭제되지 않은 알림만 보여준다
			pstm = conn.prepareStatement(query);

			pstm.setString(1, kgName);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Diary diary = new Diary();
				diary.setBdDiaryIdx(rset.getInt("BD_DIARY_IDX"));
				diary.setKgName(rset.getString("KG_NAME"));
				diary.setTitle(rset.getString("TITLE"));
				diary.setContent(rset.getString("CONTENT"));
				diary.setRegDate(rset.getDate("REG_DATE"));
				diary.setIsDel(rset.getString("IS_DEL"));
				DiaryList.add(diary);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DA02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return DiaryList;

	}

	// 상세 페이지
	public Diary selectDetail(Connection conn, String bdIdx) {

		Diary diary = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from BD_DIARY where BD_DIARY_IDX = ?";
			pstm = conn.prepareStatement(query);

			pstm.setString(1, bdIdx);

			rset = pstm.executeQuery();

			while (rset.next()) {
				diary = new Diary();
				diary.setBdDiaryIdx(rset.getInt("BD_DIARY_IDX"));
				diary.setKgName(rset.getString("KG_NAME"));
				diary.setTitle(rset.getString("TITLE"));
				diary.setContent(rset.getString("CONTENT"));
				diary.setRegDate(rset.getDate("REG_DATE"));
				diary.setIsDel(rset.getString("IS_DEL"));

			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DA02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return diary;

	}
	
		//알림 수정 해주는 메서드
		public int updateDiary(Connection conn, String title, String content ,int bdIdx) {

			int update = 0;

			PreparedStatement pstm = null;

			try {
				
				String query = "update BD_DIARY set TITLE  =  ? , CONTENT = ? where BD_DIARY_IDX = ? ";

				pstm = conn.prepareStatement(query);

				pstm.setString(1, title);
				pstm.setString(2, content);
				pstm.setInt(3, bdIdx);

				update = pstm.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.DA03, e);
			} finally {
				jdt.close(pstm);
			}

			return update;

		}
		
		//알림 삭제 메서드
		public int deleteDiary(Connection conn, int bdIdx) {
			
			int delete = 0;

			PreparedStatement pstm = null;

			try {

				String query = "update BD_DIARY set IS_DEL = 1 where BD_DIARY_IDX = ?"; // 비삭제 0 / 삭제 1

				pstm = conn.prepareStatement(query);

				pstm.setInt(1, bdIdx);
				
				delete = pstm.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.DA04, e);
			} finally {
				jdt.close(pstm);

			}

			return delete;

		}
}
