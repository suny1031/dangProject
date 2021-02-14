package com.dang.map.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileVo;
import com.dang.map.model.dao.MapDao;
import com.dang.map.model.vo.Kindergarten;
import com.dang.map.model.vo.Service;

public class MapService {
	MapDao mapDao = new MapDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public MapService() {

	}

	// 지도에 유치원 보여주는 메서드
	public ArrayList<Kindergarten> selectKindergarten() {
		Connection conn = jdt.getConnection();
		ArrayList<Kindergarten> kindergartenList;
		try {
			kindergartenList = mapDao.selectKindergarten(conn);
		} finally {
			jdt.close(conn);
		}
		return kindergartenList;

	}

	// 지도 검색창 페이징 메서드
	public List<Kindergarten> selectKindergartenPage(int startRow, int endRow) {
		Connection conn = jdt.getConnection();
		List<Kindergarten> kindergartenList;
		try {
			kindergartenList = mapDao.selectKindergartenPage(conn, startRow, endRow);
		} finally {
			jdt.close(conn);
		}
		return kindergartenList;

	}

	// 지도 유치원 총 개수 메서드
	public int selectCountPage() {
		Connection conn = jdt.getConnection();

		int count;
		try {
			count = mapDao.selectCountPage(conn);
		} finally {
			jdt.close(conn);
		}
		return count;

	}

	// ifrm에 찾은 유치원명 보낼 메서드
	public Kindergarten selectkgName(String kgName) {
		Connection conn = jdt.getConnection();
		Kindergarten res;
		try {
			res = mapDao.selectkgName(conn, kgName);
		} finally {
			jdt.close(conn);
		}
		return res;

	}

	// 지도 키워드 검색 페이징 메서드
	public List<Kindergarten> selectSearchKindergarten(String keyword, int startRow, int endRow) {
		Connection conn = jdt.getConnection();
		List<Kindergarten> keywordList;
		try {
			keywordList = mapDao.selectSearchKindergarten(conn, startRow, endRow, keyword);
		} finally {
			jdt.close(conn);
		}
		return keywordList;

	}

	// 지도 키워드 유치원 총 개수 메서드
	public int selectSearchCount(String keyword) {
		Connection conn = jdt.getConnection();
		int count;
		try {
			count = mapDao.selectSearchCount(conn, keyword);
		} finally {
			jdt.close(conn);
		}
		return count;

	}

	// 유치원의 서비스를 찾아주는 메서드
	public Service selectService(String kgName) {
		Connection conn = jdt.getConnection();
		Service res;
		try {
			res = mapDao.selectService(conn, kgName);
		} finally {
			jdt.close(conn);
		}
		return res;

	}

	// 유치원의 대표 사진을 찾아주는 메서드
	public ArrayList<FileVo> selectFile(String kgIdx) {
		Connection conn = jdt.getConnection();
		ArrayList<FileVo> fileList;
		try {
			fileList = mapDao.selectFile(conn, kgIdx);
		} finally {
			jdt.close(conn);
		}
		return fileList;

	}

}
