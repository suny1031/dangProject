package com.dang.album.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dang.album.model.vo.Album;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileVo;

public class AlbumDao {

	public AlbumDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance(); // 템플릿 생성
	
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

		String sql = "insert into D_FILE (F_IDX,TYPE_IDX,ORIGIN_FILE_NAME,RENAME_FILE_NAME, SAVE_PATH) values(sc_f_idx.nextVal,"
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
}
