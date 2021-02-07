package com.dang.reservation.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.util.file.FileUtil;
import com.dang.common.util.file.FileVo;
import com.dang.map.model.vo.Kindergarten;
import com.dang.reservation.model.dao.ReservationDao;
import com.dang.reservation.model.vo.Reservation;
import com.dang.review.model.vo.Review;

public class ReservationService {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	ReservationDao reservationDao = new ReservationDao();

	public void insertReservation(Reservation reservation) {
		Connection conn = jdt.getConnection();

		try {

			reservationDao.insertReservation(conn, reservation);

			jdt.commit(conn);

		} catch (DataAccessException e) {

			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);

		} finally {
			jdt.close(conn);
		}

	}

	public List<Reservation> selectReservationPage(int startRow, int endRow, String kgName) {
		Connection conn = jdt.getConnection();
		List<Reservation> reservation;
		try {
			reservation = reservationDao.selectReservationPage(conn, startRow, endRow, kgName);

		} finally {
			jdt.close(conn);
		}
		return reservation;

	}

	public int selectCountPage(String kgName) {
		Connection conn = jdt.getConnection();

		int count;
		try {
			count = reservationDao.selectCountPage(conn,kgName);
		} finally {
			jdt.close(conn);
		}
		return count;

	}

}
