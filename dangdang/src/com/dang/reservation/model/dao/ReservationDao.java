package com.dang.reservation.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.map.model.vo.Kindergarten;
import com.dang.reservation.model.vo.Reservation;

public class ReservationDao {

	public ReservationDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance(); // 템플릿 생성

	public void insertReservation(Connection conn, Reservation reservation) {
		// jdbc코딩을 진행

		int insert = 0;

		PreparedStatement pstm = null;
		
		try {

			String query = "insert into RESERVATION(RS_IDX,USER_ID,PROTECTOR_NAME,PHONE_NUMBER,DOG_BREED,DOG_AGE,PICKUP,REQUIREMENTS,KINDERGARTEN,REG_DATE) Values(SC_RS_IDX.nextval,?,?,?,?,?,?,?,?,?)";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, reservation.getUserId());
			pstm.setString(2, reservation.getProtectorName());
			pstm.setString(3, reservation.getPhoneNumber());
			pstm.setString(4, reservation.getDogBreed());
			pstm.setString(5, reservation.getDogAge());
			pstm.setString(6, reservation.getPickup());
			pstm.setString(7, reservation.getRequirements());
			pstm.setString(8, reservation.getKindergarten());
			pstm.setDate(9, reservation.getRegDate());
			insert = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RE01, e);
		} finally {
			jdt.close(pstm);
		}

	}
	
	public List<Reservation> selectReservationPage(Connection conn, int startRow, int endRow, String kgName) {
		// 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
		String query = "select * from (select rownum rn, USER_ID,  PROTECTOR_NAME, PHONE_NUMBER,DOG_BREED,DOG_AGE,PICKUP,IS_APPROVED,KINDERGARTEN, REG_DATE, REQUIREMENTS from (select * from RESERVATION where KINDERGARTEN = ? order by RS_IDX asc)) where rn between ? and ?";

		List<Reservation> list = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, kgName);
			pstm.setInt(2, startRow);
			pstm.setInt(3, endRow);

			rset = pstm.executeQuery(); // sql 실행

			if (rset.next()) { // 데이터베이스에 데이터가 있으면 실행
				list = new ArrayList<>(); // list 객체 생성
				do {
					// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
					Reservation reservation = new Reservation();
					reservation.setUserId(rset.getString("USER_ID"));
					reservation.setProtectorName(rset.getString("PROTECTOR_NAME"));
					reservation.setPhoneNumber(rset.getString("PHONE_NUMBER"));
					reservation.setDogBreed(rset.getString("DOG_BREED"));
					reservation.setDogAge(rset.getString("DOG_AGE"));
					reservation.setPickup(rset.getString("PICKUP"));
					reservation.setIsApproved(rset.getString("IS_APPROVED"));
					reservation.setKindergarten(rset.getString("KINDERGARTEN"));
					reservation.setRegDate(rset.getDate("REG_DATE"));
					reservation.setRequirements(rset.getString("REQUIREMENTS"));
					list.add(reservation); 
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.API01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return list; // list 반환
	}

	public int selectCountPage(Connection conn,String kgName) {

		int count = 0;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		String query = "select count(*) from RESERVATION where KINDERGARTEN = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
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
	
	
	
	
	
	
	
}
