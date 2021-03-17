package com.dang.album.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
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

	// 유치원의 리뷰 사진 보여주는 메서드
	public ArrayList<FileVo> selectFile(String kgName) {
		Connection conn = jbt.getConnection();
		ArrayList<FileVo> fileList;
		try {
			fileList = albumDao.selectFile(conn, kgName);
		} finally {
			jbt.close(conn);
		}
		return fileList;

	}

	// 사진 추가 메서드
	public void insertAlbum(String kgName, HttpServletRequest request) {

		Connection conn = jbt.getConnection();

		// 게시글 저장
		Map<String, List> albumData = new FileUtil().fileUpload(request);
		System.out.println("albumData" + albumData);

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

	// 해당요일 사진 보여주는 메서드
	public ArrayList<FileVo> selectDate(Date selectDate, String kgName) {
		Connection conn = jbt.getConnection();
		ArrayList<FileVo> fileList;
		try {
			fileList = albumDao.selectDate(conn, selectDate, kgName);
		} finally {
			jbt.close(conn);
		}
		return fileList;

	}
	
	// 사진 삭제 메서드
	public int deleteAlbum(String fidx) {
		Connection conn = jbt.getConnection();
		int res = 0;
		try {
			res = albumDao.deleteAlbum(conn, fidx);
			jbt.commit(conn);
		} catch (DataAccessException e) {
			jbt.rollback(conn);
			throw new ToAlertException(e.error);
		} finally {
			jbt.close(conn);
		}
		return res;

	}

}
