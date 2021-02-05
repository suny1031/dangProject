package com.dang.reservation.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.reservation.model.vo.Reservation;

public class ReservationDao {

	public ReservationDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance(); // 템플릿 생성

	public int insertReservation(Connection conn, Reservation reservation) {
		// jdbc코딩을 진행

		int insert = 0;

		PreparedStatement pstm = null;
		
		try {

			String query = "insert into RESERVATION(RS_IDX,USER_ID,PROTECTOR_NAME,PHONE_NUMBER,DOG_BREED,DOG_AGE,PICKUP,REQUIREMENTS,KINDERGARTEN) Values(SC_RS_IDX.nextval,?,?,?,?,?,?,?,?)";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, reservation.getUserId());
			pstm.setString(2, reservation.getProtectorName());
			pstm.setString(3, reservation.getPhoneNumber());
			pstm.setString(4, reservation.getDogBreed());
			pstm.setString(5, reservation.getDogAge());
			pstm.setString(6, reservation.getPickup());
			pstm.setString(7, reservation.getRequirements());
			pstm.setString(8, reservation.getKindergarten());
			
			insert = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RE01, e);
		} finally {
			jdt.close(pstm, conn);
		}
		return insert;

	}
}
