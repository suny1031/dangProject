package com.dang.reservation.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.map.model.service.MapService;
import com.dang.map.model.vo.Kindergarten;

@WebServlet("/reservation/*")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MapService mapService = new MapService();

	public ReservationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		System.out.println(Arrays.toString(uriArr));
		switch (uriArr[uriArr.length - 1]) {
		case "reservation.do":
			reservation(request, response);
			break;
		default:
			throw new ToAlertException(ErrorCode.CD_404);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void reservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kgName = request.getParameter("kgName");
		System.out.println(kgName);
		
		Kindergarten kindergarten = mapService.selectkgName(kgName);
		request.setAttribute("kindergarten", kindergarten);
		System.out.println(kindergarten);
		
		request.getRequestDispatcher("/WEB-INF/view/reservation/reservation.jsp").forward(request, response);

	}
}
