package com.dang.reservation.model.service;

import java.sql.Connection;

import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.reservation.model.dao.ReservationDao;
import com.dang.reservation.model.vo.Reservation;

public class ReservationService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	ReservationDao reservationDao = new ReservationDao();
	
	
	
	public int insertReservation(Reservation reservation) {
		Connection conn = jdt.getConnection();
		int res = 0;

		try {
			res = reservationDao.insertReservation(conn, reservation);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		} finally {	
			jdt.close(conn);
		}
		return res;
	}
}
