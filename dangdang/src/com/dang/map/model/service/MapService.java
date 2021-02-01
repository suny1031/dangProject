package com.dang.map.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dang.common.jdbc.JDBCTemplate;
import com.dang.map.model.dao.MapDao;
import com.dang.map.model.vo.Kindergarten;

public class MapService {
	MapDao mapDao = new MapDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public MapService() {

	}

	public ArrayList<Kindergarten> selectKindergarten() {
		Connection conn = jdt.getConnection();
		ArrayList<Kindergarten> kindergartenList = mapDao.selectKindergarten(conn);
		jdt.close(conn);
		return kindergartenList;

	}

	public List<Kindergarten> selectKindergartenPage(int startRow, int endRow) {
		Connection conn = jdt.getConnection();
		List<Kindergarten> kindergartenList = mapDao.selectKindergartenPage(conn,startRow,endRow);
		jdt.close(conn);
		return kindergartenList;

	}
	public int selectCount() {
		Connection conn = jdt.getConnection();
		int count = mapDao.selectCount(conn);
		jdt.close(conn);
		return count;

	}
	
	public Kindergarten selectMapkgName(String kgName) {
		Connection conn = jdt.getConnection();
		Kindergarten res = mapDao.selectMapkgName(conn, kgName);
		jdt.close(conn);
		return res;

	}

}
