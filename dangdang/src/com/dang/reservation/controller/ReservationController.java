package com.dang.reservation.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.map.model.service.MapService;
import com.dang.map.model.vo.Kindergarten;
import com.dang.map.model.vo.Service;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;
import com.dang.reservation.model.service.ReservationService;
import com.dang.reservation.model.vo.Reservation;
import com.google.gson.Gson;

@WebServlet("/reservation/*")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MapService mapService = new MapService();
	private ReservationService reservationService = new ReservationService();
	Gson gson = new Gson();

	public ReservationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "reservation.do":
			reservation(request, response); 
			break;
		case "reservationimpl.do":
			reservationimpl(request, response);
			break;
		case "mngngRsrvt.do":
			mngngRsrvt(request, response);
			break;
		case "mngngRsrvt2.do":
			mngngRsrvt2(request, response);
			break;
		case "calendar.do":
			calendar(request, response);
			break;
		case "approved.do":
			approved(request, response);
			break;
		case "delete.do":
			delete(request, response);
			break;
		default:
			throw new ToAlertException(ErrorCode.CD_404);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 예약 신청 페이지 메서드
	private void reservation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String kgName = request.getParameter("kgName");

		Kindergarten kindergarten = mapService.selectkgName(kgName);
		Service service = mapService.selectService(kgName);

		request.setAttribute("kindergarten", kindergarten);
		request.setAttribute("service", service);

		request.getRequestDispatcher("/WEB-INF/view/reservation/reservation.jsp").forward(request, response);

	}

	// 예약 신청 처리 메서드
	private void reservationimpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserMember userMember = (UserMember) session.getAttribute("userMember");

		String userId = userMember.getUserId();
		String kindergarten = request.getParameter("kgName");
		String protectorName = request.getParameter("protectorName");
		String phoneNumber = request.getParameter("phoneNumber");
		String dogBreed = request.getParameter("dogBreed");
		String dogAge = request.getParameter("dogAge");
		String requestedTerm = request.getParameter("requestedTerm");
		String pickup = request.getParameter("pickup");
		String date = request.getParameter("date");

		java.sql.Date regDate = java.sql.Date.valueOf(date);

		Reservation reservation = new Reservation();

		reservation.setUserId(userId);
		reservation.setKindergarten(kindergarten);
		reservation.setProtectorName(protectorName);
		reservation.setPhoneNumber(phoneNumber);
		reservation.setDogAge(dogAge);
		reservation.setDogBreed(dogBreed);
		reservation.setRequirements(requestedTerm);
		reservation.setRegDate(regDate);
		reservation.setPickup(pickup);

		if (pickup != null) {
			reservation.setPickup(pickup);
		} else {
			reservation.setPickup("1");
		}

		request.setAttribute("alertMsg", "예약 신청이 완료 되었습니다");
		request.setAttribute("url", "/reservation/mngngRsrvt2.do");

		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

		reservationService.insertReservation(reservation);

	}

	// 유치원 예약 확인 메서드
	private void mngngRsrvt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");

		Service service = mapService.selectService(schoolMember.getKgName());
		String kgName = schoolMember.getKgName();

		request.setAttribute("service", service);
		request.setAttribute("kgName", kgName);

		request.getRequestDispatcher("/WEB-INF/view/reservation/mngngRsrvt.jsp").forward(request, response);
	}

	// 유저 예약 확인 메서드
	private void mngngRsrvt2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserMember userMember = (UserMember) session.getAttribute("userMember");

		request.setAttribute("userMember", userMember);

		request.getRequestDispatcher("/WEB-INF/view/reservation/mngngRsrvt2.jsp").forward(request, response);
	}

	// 캘린더 메서드
	private void calendar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		System.out.println(schoolMember.getKgName());

		ArrayList<Reservation> reservationList = reservationService.selectReservation(schoolMember.getKgName());

		for (int i = 0; i < reservationList.size(); i++) {
			System.out.println(reservationList.get(i));
		}

		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String today = f.format(now);

		request.setAttribute("reservationList", reservationList);
		request.setAttribute("today", today);

		request.getRequestDispatcher("/WEB-INF/view/reservation/calendar.jsp").forward(request, response);

	}

	// 예약 승인 처리 메서드
	private void approved(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String rsIdx = request.getParameter("rsIdx"); //{"userId":"test3","date":"2021-02-15","rsIdx":"100221","kgName":"키움반려견스쿨"}
		
		Map rsIdxIf = gson.fromJson(rsIdx, Map.class); //{userId=test3, date=2021-02-15, rsIdx=100221, kgName=키움반려견스쿨}

		UserMember userMember = reservationService.selectUserMember((String)rsIdxIf.get("userId"));

		reservationService.ReservationEmail(userMember,(String)rsIdxIf.get("date"),(String)rsIdxIf.get("kgName"));

		response.getWriter().print("success");

		reservationService.updateReservation((String)rsIdxIf.get("rsIdx"));
	}

	// 예약 삭제 처리 메서드
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dleRsIdx = request.getParameter("dleRsIdx"); //비동기 GSON Object //{"dleRsIdx":"100209"}

		Map rsIdx = gson.fromJson(dleRsIdx, Map.class); //{dleRsIdx=100209}

		int res = reservationService.deleteReservation((String)rsIdx.get("dleRsIdx"));
		System.out.println(res);
		if (res > 0) {
			response.getWriter().print("success");
		}
	}

}
