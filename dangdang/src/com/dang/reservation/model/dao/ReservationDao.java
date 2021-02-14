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
import com.dang.member.user.model.vo.UserMember;
import com.dang.reservation.model.vo.Reservation;

public class ReservationDao {

	public ReservationDao() {

	}

	JDBCTemplate jdt = JDBCTemplate.getInstance(); 

	//예약 신청 메서드
	public void insertReservation(Connection conn, Reservation reservation) {

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
			System.out.println("pick"+reservation.getPickup());
			insert = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RE01, e);
		} finally {
			jdt.close(pstm);
		}

	}

	//유치원 예약 확인 페이지 페이징
	public List<Reservation> selectReservationPage(Connection conn, int startRow, int endRow, String kgName) {
		String query = "select * from (select rownum rn, USER_ID, RS_IDX ,PROTECTOR_NAME, PHONE_NUMBER,DOG_BREED,DOG_AGE,PICKUP,IS_APPROVED,KINDERGARTEN, REG_DATE, REQUIREMENTS, IS_DEL from (select * from RESERVATION where KINDERGARTEN = ? and IS_DEL = 0 order by RS_IDX asc)) where rn between ? and ?";

		List<Reservation> list = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			pstm.setInt(2, startRow);
			pstm.setInt(3, endRow);

			rset = pstm.executeQuery(); 
			if (rset.next()) { 
				list = new ArrayList<>(); 
				do {
					Reservation reservation = new Reservation();
					reservation.setRsIdx(rset.getInt("RS_IDX"));
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
					reservation.setIsDel(rset.getString("IS_DEL"));
					list.add(reservation);
				} while (rset.next());
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RE02, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return list;
	}

	//유치원 예약 총 개수 메서드
	public int selectCountPage(Connection conn, String kgName) {

		int count = 0;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		String query = "select count(*) from RESERVATION where KINDERGARTEN = ? and IS_DEL = 0";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgName);
			rset = pstm.executeQuery();
			if (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new DataAccessException(ErrorCode.RE02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return count; // 총 레코드 수 리턴
	}

	
	//유저 정보를 찾아주는 메서드
	public UserMember selectUserMember(Connection conn, String userId) {

		UserMember userMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {

			String query = "select * from MEMBER where USER_ID = ? ";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, userId);

			rset = pstm.executeQuery(); 

			if (rset.next()) {
				userMember = new UserMember();
				userMember.setEmail(rset.getString("EMAIL"));
				userMember.setUserId(rset.getString("USER_ID"));
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return userMember;

	}

	//예약 승인해주는 메서드
	public int updateReservation(Connection conn, String rsIdx) {

		int update = 0;

		PreparedStatement pstm = null;

		try {
			
			String query = "update RESERVATION set IS_APPROVED  =  0  where RS_IDX = ? "; // 승인 0 / 미승인 1

			pstm = conn.prepareStatement(query);

			pstm.setString(1, rsIdx);

			update = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RE03, e);
		} finally {
			jdt.close(pstm);
		}

		return update;

	}

	//캘린더 예약 내용 불러오기
	public ArrayList<Reservation> selectReservation(Connection conn, String kgName) {

		ArrayList<Reservation> reservationList  = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from RESERVATION where KINDERGARTEN = ? and IS_APPROVED = 0 and IS_DEL = 0"; // 승인되고 삭제되지 않은 예약만 보여준다

			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, kgName);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Reservation reservation = new Reservation();
				reservation.setRsIdx(rset.getInt("RS_IDX"));
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
				reservation.setIsDel(rset.getString("IS_DEL"));
				reservationList.add(reservation);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.CL01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return reservationList;

	}
	
	//예약 삭제 메서드
	public int deleteReservation(Connection conn, String rsIdx) {
		
		int delete = 0;

		PreparedStatement pstm = null;

		try {

			String query = "update RESERVATION set IS_DEL = 1 where RS_IDX = ?"; // 비삭제 0 / 삭제 1

			pstm = conn.prepareStatement(query);

			pstm.setString(1, rsIdx);
			
			delete = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RE04, e);
		} finally {
			jdt.close(pstm);

		}

		return delete;

	}
	
	//유치원 예약 미리보기
	public ArrayList<Reservation> selectReservationPreview(Connection conn, String kgName) {

		ArrayList<Reservation> reservationList  = new ArrayList<>();

		PreparedStatement pstm = null;

		ResultSet rset = null;

		try {

			String query = "select * from reservation where KINDERGARTEN = ? and IS_APPROVED = 1 and IS_DEL = 0 and ROWNUM BETWEEN 1 and 5 order by RS_IDX asc";
			// 승인이 되지 않고 삭제되지 않은 예약만 보여준다
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, kgName);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Reservation reservation = new Reservation();
				reservation.setRsIdx(rset.getInt("RS_IDX"));
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
				reservation.setIsDel(rset.getString("IS_DEL"));
				reservationList.add(reservation);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.RE02, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return reservationList;

	}
	
		//유저 예약 미리보기
		public ArrayList<Reservation> selectUserPreview(Connection conn, String userId) {

			ArrayList<Reservation> reservationList  = new ArrayList<>();

			PreparedStatement pstm = null;

			ResultSet rset = null;
			try {

				String query = "select * from reservation where USER_ID = ? and IS_DEL = 0 and ROWNUM BETWEEN 1 and 5 order by RS_IDX asc";
				//삭제 되지않은 예약만 보여준다 
				
				pstm = conn.prepareStatement(query);
				
				pstm.setString(1, userId);

				rset = pstm.executeQuery();

				while (rset.next()) {
					Reservation reservation = new Reservation();
					reservation.setRsIdx(rset.getInt("RS_IDX"));
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
					reservation.setIsDel(rset.getString("IS_DEL"));
					reservationList.add(reservation);
				}

			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.RE02, e);
			} finally {
				jdt.close(rset, pstm);
			}

			return reservationList;

		}
		
		//유저 예약 확인 페이지 페이징
		public List<Reservation> selectReservationUserPage(Connection conn, int startRow, int endRow, String userId) {

			String query = "select * from (select rownum rn, USER_ID, RS_IDX ,PROTECTOR_NAME, PHONE_NUMBER,DOG_BREED,DOG_AGE,PICKUP,IS_APPROVED,KINDERGARTEN, REG_DATE, REQUIREMENTS, IS_DEL from (select * from RESERVATION where USER_ID = ? and IS_DEL = 0 order by RS_IDX asc)) where rn between ? and ?";

			List<Reservation> list = null;

			PreparedStatement pstm = null;

			ResultSet rset = null;

			try {

				pstm = conn.prepareStatement(query);
				pstm.setString(1, userId);
				pstm.setInt(2, startRow);
				pstm.setInt(3, endRow);

				rset = pstm.executeQuery(); // sql 실행

				if (rset.next()) { // 데이터베이스에 데이터가 있으면 실행
					list = new ArrayList<>(); // list 객체 생성
					do {
						// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
						Reservation reservation = new Reservation();
						reservation.setRsIdx(rset.getInt("RS_IDX"));
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
						reservation.setIsDel(rset.getString("IS_DEL"));
						list.add(reservation);
					} while (rset.next());
				}
			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.RE02, e);
			} finally {
				jdt.close(rset, pstm);
			}
			return list; // list 반환
		}
		
		//유치원 예약 총 개수 메서드
		public int selectCountPageUser(Connection conn, String userId) {

			int count = 0;

			PreparedStatement pstm = null;

			ResultSet rset = null;

			String query = "select count(*) from RESERVATION where USER_ID = ? and IS_DEL = 0";
			try {
				pstm = conn.prepareStatement(query);
				pstm.setString(1, userId);
				rset = pstm.executeQuery();
				if (rset.next()) {
					count = rset.getInt(1);
				}
			} catch (Exception e) {
				throw new DataAccessException(ErrorCode.RE02, e);
			} finally {
				jdt.close(rset, pstm);
			}

			return count; // 총 레코드 수 리턴
		}

}
