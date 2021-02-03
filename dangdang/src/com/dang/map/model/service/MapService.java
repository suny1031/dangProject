package com.dang.map.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dang.common.jdbc.JDBCTemplate;
import com.dang.map.model.dao.MapDao;
import com.dang.map.model.vo.Kindergarten;
import com.dang.map.model.vo.Service;

public class MapService {
	MapDao mapDao = new MapDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public MapService() {

	}

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

}
