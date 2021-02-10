package com.dang.reservation.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

@WebServlet("/reservation/*")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MapService mapService = new MapService();
	private ReservationService reservationService = new ReservationService();

	public ReservationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		// System.out.println(Arrays.toString(uriArr));
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

	private void reservation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String kgName = request.getParameter("kgName");

		Kindergarten kindergarten = mapService.selectkgName(kgName);
		Service service = mapService.selectService(kgName);

		request.setAttribute("kindergarten", kindergarten);
		request.setAttribute("service", service);

		request.getRequestDispatcher("/WEB-INF/view/reservation/reservation.jsp").forward(request, response);

	}

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
		
		if (pickup != null) {
			reservation.setPickup(pickup);
		}

		// 사용자에게 보여줄 view page 지정
		request.setAttribute("alertMsg", "예약 신청이 완료 되었습니다");
		request.setAttribute("url", "/main.do");

		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

		reservationService.insertReservation(reservation);

	}

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
		request.setAttribute("today",today);

		request.getRequestDispatcher("/WEB-INF/view/reservation/calendar.jsp").forward(request, response);

	}

	private void approved(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");

		String date = request.getParameter("date");

		String kgName = request.getParameter("kgName");

		String rsIdx = request.getParameter("rsIdx");

		System.out.println("userId : " + userId + " / date : " + date + " / kgName : " + kgName + " / rsIdx : " + rsIdx);
		UserMember userMember = reservationService.selectUserMember(userId);

		reservationService.ReservationEmail(userMember, date, kgName);

		response.getWriter().print("success");

		reservationService.updateReservation(rsIdx);

	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dleRsIdx = request.getParameter("dleRsIdx");
		System.out.println(dleRsIdx);
		
		int res = reservationService.deleteReservation(dleRsIdx);
		System.out.println(res);
		if (res > 0) {
			response.getWriter().print("success");
		}
	}

}
