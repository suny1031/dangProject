package com.dang.album.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dang.album.model.vo.Album;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileVo;
import com.dang.review.model.vo.Review;

public class AlbumDao {

	public AlbumDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance(); // 템플릿 생성

	// 앨범 보여주기
	public ArrayList<Album> selectAlbum(Connection conn, String kgName) {

		ArrayList<Album> albumList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from d_file f join BD_ALBUM a on(type_idx = BD_AL_IDX) where kg_name = ? ORDER by a.BD_AL_IDX desc";

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Album album = new Album();
				album.setBdAlIdx(rset.getString("BD_AL_IDX"));
				album.setRegDate(rset.getDate("DATE"));
				album.setKgName(rset.getString("KG_NAME"));
				albumList.add(album);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RV01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return albumList;

	}

	// 유치원의 사진 보여주는 메서드
	public ArrayList<FileVo> selectFile(Connection conn, String kgName) {

		ArrayList<FileVo> fileList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from d_file f join BD_ALBUM a on(type_idx = BD_AL_IDX) where kg_name = ? ORDER by a.BD_AL_IDX desc";
			// 해당 유치원의 타입 인덱스와 일치하는 파일테이블에서 전부 가져온다
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();

			while (rset.next()) {
				FileVo file = new FileVo();
				file.setFidx(rset.getInt(1));
				file.setTypeIdx(rset.getString(2));
				file.setOriginFileName(rset.getString(3));
				file.setRenameFileName(rset.getString(4));
				file.setSavePath(rset.getString(5));
				file.setRegDate(rset.getDate(6));
				file.setIsDel(rset.getInt(7));
				fileList.add(file);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RV01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return fileList;

	}

	// 앨범 추가 메서드
	public int insertAlbum(Connection conn, Album album) {
		int res = 0;
		String sql = "insert into BD_ALBUM (BD_AL_IDX,KG_NAME) Values('a' || SC_BD_AL_IDX.nextVal, ?)";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, album.getKgName());
			res = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RV02, e);
		} finally {
			jdt.close(pstm);
		}

		return res;

	}

	// 앨범 사진 추가 메서드
	public int insertFile(Connection conn, FileVo fileData) {
		int res = 0;
		String bdAlIdx = "";
		if (fileData.getTypeIdx() == null) {

			bdAlIdx = " 'a' || SC_BD_AL_IDX.currval";

		} else {

			// 2. 수정할 때 사용자가 파일을 추가 등록해서 파일 정보 저장
			// 수정할 게시글의 bdIdx값
			bdAlIdx = "'" + fileData.getTypeIdx() + "'";

		}
		System.out.println(fileData);

		String sql = "insert into D_FILE (F_IDX,TYPE_IDX,ORIGIN_FILE_NAME,RENAME_FILE_NAME, SAVE_PATH) values(SC_F_IDX.nextVal,"
				+ bdAlIdx + ",?,?,?)";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileData.getOriginFileName());
			pstm.setString(2, fileData.getRenameFileName());
			pstm.setString(3, fileData.getSavePath());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RV02, e);
		} finally {
			jdt.close(pstm);
		}

		return res;
	}

	// 해당요일 사진 보여주는 메서드
	public ArrayList<FileVo> selectDate(Connection conn, Date selectDate, String kgNmae) {

		ArrayList<FileVo> fileList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "SELECT * FROM D_FILE WHERE TYPE_IDX IN(SELECT BD_AL_IDX FROM BD_ALBUM WHERE KG_NAME = ? AND TO_DATE(REG_DATE,'YY/MM/DD') = ? ) ORDER BY F_IDX DESC";
			// 해당 유치원의 타입 인덱스와 일치하는 파일테이블에서 전부 가져온다
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgNmae);
			pstm.setDate(2, selectDate);
			rset = pstm.executeQuery();

			while (rset.next()) {
				FileVo file = new FileVo();
				file.setFidx(rset.getInt(1));
				file.setTypeIdx(rset.getString(2));
				file.setOriginFileName(rset.getString(3));
				file.setRenameFileName(rset.getString(4));
				file.setSavePath(rset.getString(5));
				file.setRegDate(rset.getDate(6));
				file.setIsDel(rset.getInt(7));
				fileList.add(file);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RV01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return fileList;

	}

}
