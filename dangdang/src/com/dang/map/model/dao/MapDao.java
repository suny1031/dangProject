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

	JDBCTemplate jdt = JDBCTemplate.getInstance(); // 템플릿 생성

	
	
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
				// 멤버를 받아올때마다 List에 넣기
				kindergartenList.add(kindergarten);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return kindergartenList;

	}

	public List<Kindergarten> selectKindergartenPage(Connection conn, int startRow, int endRow) {
		// 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
		String query = "select * from (select rownum rn, KG_ADDRESS,  KG_IDX, KG_LAG,KG_LAT,KG_NAME,KG_NOTICE,KG_OPERATE_TIME,KG_TELL from"
				+ "(select * from KINDERGARDEN order by KG_IDX asc)) where rn between ? and ?";
		System.out.println(startRow + ":" + endRow);

		List<Kindergarten> list = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			pstm = conn.prepareStatement(query);

			pstm.setInt(1, startRow); // sql 물음표에 값 매핑
			pstm.setInt(2, endRow);

			rset = pstm.executeQuery(); // sql 실행

			if (rset.next()) { // 데이터베이스에 데이터가 있으면 실행
				list = new ArrayList<>(); // list 객체 생성
				do {
					// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
					Kindergarten kindergarten = new Kindergarten();
					kindergarten.setKgName(rset.getString("kg_name"));
					kindergarten.setKgAddress(rset.getString("KG_ADDRESS"));
					kindergarten.setKgNotice(rset.getString("KG_NOTICE"));
					kindergarten.setKgOperateTime(rset.getString("KG_OPERATE_TIME"));
					kindergarten.setKgOperateTime(rset.getString("KG_TELL"));
					kindergarten.setKgLat(rset.getString("kg_lat"));
					kindergarten.setKgLag(rset.getString("kg_lag"));
					list.add(kindergarten); // list에 0번 인덱스부터 board 객체의 참조값을 저장
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return list; // list 반환
	}

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

	public Kindergarten selectkgName(Connection conn, String kgName) {

		Kindergarten kindergarten = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {

			String query = "select * from KINDERGARDEN  where kg_name = ? ";

			// 3. 쿼리문 실행용 객체를 생성
			pstm = conn.prepareStatement(query);

			pstm.setString(1, kgName);
			// 4. 쿼리문 작성

			// 5. 쿼리문 실행하고 결과(resultSet)를 받음
			rset = pstm.executeQuery(); // rset로 쿼리 결과에 접근가능함

			if (rset.next()) { // 데이터가 담겨왔으면 member에 담아 줄 수 있다
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

			if (rset.next()) { // 데이터베이스에 데이터가 있으면 실행
				list = new ArrayList<>(); // list 객체 생성
				do {
					// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
					Kindergarten kindergarten = new Kindergarten();
					kindergarten.setKgName(rset.getString("kg_name"));
					kindergarten.setKgAddress(rset.getString("KG_ADDRESS"));
					kindergarten.setKgNotice(rset.getString("KG_NOTICE"));
					kindergarten.setKgOperateTime(rset.getString("KG_OPERATE_TIME"));
					kindergarten.setKgOperateTime(rset.getString("KG_TELL"));
					kindergarten.setKgLat(rset.getString("kg_lat"));
					kindergarten.setKgLag(rset.getString("kg_lag"));
					list.add(kindergarten); // list에 0번 인덱스부터 board 객체의 참조값을 저장
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return list;

	}

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
	
	
	public ArrayList<FileVo> selectFile(Connection conn, String kgIdx) {

		ArrayList<FileVo> fileList = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;
		String kkgIdx = "k"+kgIdx;
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
