package com.dang.album.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dang.album.model.dao.AlbumDao;
import com.dang.album.model.vo.Album;
import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileUtil;
import com.dang.common.util.file.FileVo;

public class AlbumService {
	AlbumDao albumDao = new AlbumDao();
	JDBCTemplate jbt = JDBCTemplate.getInstance();

	// 사진 추가 메서드
	public void insertAlbum(String kgName, HttpServletRequest request) {

		Connection conn = jbt.getConnection();

		// 게시글 저장
		Map<String, List> albumData = new FileUtil().fileUpload(request);
		System.out.println("albumData"+albumData);

		Album album = new Album();
		album.setKgName(kgName);

		try {
			albumDao.insertAlbum(conn, album);

			for (FileVo fileData : (List<FileVo>) albumData.get("fileData")) {
				albumDao.insertFile(conn, fileData);
			}

			jbt.commit(conn);
		} catch (DataAccessException e) {
			jbt.rollback(conn);
			throw new ToAlertException(e.error, e);
		} finally {
			jbt.close(conn);
		}

	}

}
