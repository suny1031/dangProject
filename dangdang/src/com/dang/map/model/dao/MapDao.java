package com.dang.map.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.standard.Severity;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileVo;
import com.dang.map.model.vo.Kindergarten;
import com.dang.map.model.vo.Service;

public class MapDao {

	public MapDao() {
	}

	JDBCTemplate jdt = JDBCTemplate.getInstance();

	// 지도에 유치원 보여주는 메서드
	public ArrayList<Kindergarten> selectKindergarten(Connection conn) {

		ArrayList<Kindergarten> kindergartenList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from kindergarden";

			pstm = conn.prepareStatement(query);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Kindergarten kindergarten = new Kindergarten();
				kindergarten.setKgName(rset.getString("kg_name"));
				kindergarten.setKgAddress(rset.getString("KG_ADDRESS"));
				kindergarten.setKgNotice(rset.getString("KG_NOTICE"));
				kindergarten.setKgOperateTime(rset.getString("KG_OPERATE_TIME"));
				kindergarten.setKgOperateTime(rset.getString("KG_TELL"));
				kindergarten.setKgLat(rset.getString("kg_lat"));
				kindergarten.setKgLag(rset.getString("kg_lag"));
				kindergartenList.add(kindergarten);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return kindergartenList;

	}

	// 지도 검색창 페이징 메서드
	public List<Kindergarten> selectKindergartenPage(Connection conn, int startRow, int endRow) {

		String query = "select * from (select rownum rn, KG_ADDRESS,  KG_IDX, KG_LAG,KG_LAT,KG_NAME,KG_NOTICE,KG_OPERATE_TIME,KG_TELL from"
				+ "(select * from KINDERGARDEN order by KG_IDX asc)) where rn between ? and ?";
		System.out.println(startRow + ":" + endRow);

		List<Kindergarten> list = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			pstm = conn.prepareStatement(query);

			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);

			rset = pstm.executeQuery();

			if (rset.next()) {
				list = new ArrayList<>();
				do {
					Kindergarten kindergarten = new Kindergarten();
					kindergarten.setKgName(rset.getString("kg_name"));
					kindergarten.setKgAddress(rset.getString("KG_ADDRESS"));
					kindergarten.setKgNotice(rset.getString("KG_NOTICE"));
					kindergarten.setKgOperateTime(rset.getString("KG_OPERATE_TIME"));
					kindergarten.setKgOperateTime(rset.getString("KG_TELL"));
					kindergarten.setKgLat(rset.getString("kg_lat"));
					kindergarten.setKgLag(rset.getString("kg_lag"));
					list.add(kindergarten);
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return list;
	}

	// 지도 유치원 총 개수 메서드
	public int selectCountPage(Connection conn) {

		int count = 0;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		String query = "select count(*) from KINDERGARDEN";
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			if (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return count; // 총 레코드 수 리턴
	}

	// ifrm에 찾은 유치원명 보낼 메서드
	public Kindergarten selectkgName(Connection conn, String kgName) {

		Kindergarten kindergarten = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {

			String query = "select * from KINDERGARDEN  where kg_name = ? ";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, kgName);

			rset = pstm.executeQuery();

			if (rset.next()) {
				kindergarten = new Kindergarten();
				kindergarten.setKgName(rset.getString("kg_name"));
				kindergarten.setKgAddress(rset.getString("KG_ADDRESS"));
				kindergarten.setKgNotice(rset.getString("KG_NOTICE"));
				kindergarten.setKgOperateTime(rset.getString("KG_OPERATE_TIME"));
				kindergarten.setKgTell(rset.getString("KG_TELL"));
				kindergarten.setKgLat(rset.getString("kg_lat"));
				kindergarten.setKgLag(rset.getString("kg_lag"));
				kindergarten.setKgIdx(rset.getString("KG_IDX"));

			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return kindergarten;

	}

	// 지도 키워드 검색 페이징 메서드
	public List<Kindergarten> selectSearchKindergarten(Connection conn, int startRow, int endRow, String keyword) {

		List<Kindergarten> list = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			String query = "select * from (select rownum rn, KG_ADDRESS, KG_IDX, KG_LAG,KG_LAT,KG_NAME,KG_NOTICE,KG_OPERATE_TIME,KG_TELL from (select * from KINDERGARDEN where KG_NAME like ? order by KG_IDX asc)) where rn between ? and ?";

			pstm = conn.prepareStatement(query);

			String setKeyword = "%" + keyword + "%";

			pstm.setString(1, setKeyword);
			pstm.setInt(2, startRow);
			pstm.setInt(3, endRow);

			rset = pstm.executeQuery();

			if (rset.next()) {
				list = new ArrayList<>();
				do {
					Kindergarten kindergarten = new Kindergarten();
					kindergarten.setKgName(rset.getString("kg_name"));
					kindergarten.setKgAddress(rset.getString("KG_ADDRESS"));
					kindergarten.setKgNotice(rset.getString("KG_NOTICE"));
					kindergarten.setKgOperateTime(rset.getString("KG_OPERATE_TIME"));
					kindergarten.setKgOperateTime(rset.getString("KG_TELL"));
					kindergarten.setKgLat(rset.getString("kg_lat"));
					kindergarten.setKgLag(rset.getString("kg_lag"));
					list.add(kindergarten);
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return list;

	}

	// 지도 키워드 유치원 총 개수 메서드
	public int selectSearchCount(Connection conn, String keyword) {

		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			String query = "select count(*) from KINDERGARDEN where KG_NAME like ? ";

			pstm = conn.prepareStatement(query);
			String setKeyword = "%" + keyword + "%";
			pstm.setString(1, setKeyword);

			rset = pstm.executeQuery();

			if (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return count;

	}

	// 유치원의 서비스를 찾아주는 메서드
	public Service selectService(Connection conn, String kgName) {

		Service service = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {

			String query = "select * from services where KG_NAME = ? ";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, kgName);

			rset = pstm.executeQuery();

			if (rset.next()) {
				service = new Service();
				service.setKgName(rset.getString("KG_NAME"));
				service.setSvIdx(rset.getInt("SV_IDX"));
				service.setIsKg(rset.getInt("IS_KG"));
				service.setIsCafe(rset.getInt("IS_CAFE"));
				service.setIsHotel(rset.getInt("IS_HOTEL"));
				service.setIsMedic(rset.getInt("IS_MEDIC"));
				service.setIsPickup(rset.getInt("IS_PICKUP"));
				service.setIsSpa(rset.getInt("IS_SPA"));
				service.setIsAcademy(rset.getInt("IS_ACADEMY"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return service;

	}

	// 유치원의 대표 사진을 찾아주는 메서드
	public ArrayList<FileVo> selectFile(Connection conn, String kgIdx) {

		ArrayList<FileVo> fileList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;
		String kkgIdx = "k" + kgIdx;
		System.out.println(kkgIdx);

		try {

			String query = "select * from D_FILE where TYPE_IDX = ?";

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kkgIdx);
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
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return fileList;

	}

}
