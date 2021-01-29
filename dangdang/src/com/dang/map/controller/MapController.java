package com.dang.map.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;

@WebServlet("/map.do/*")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MapController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		System.out.println(Arrays.toString(uriArr));

		switch (uriArr[uriArr.length - 1]) {

		case "map.do":
			map(request, response);
			break;
			
		case "infrm":
			infrm(request, response);
			
		case "reviewwrite":
			reviewwrite(request, response);
			
		case "reviewview":
			reviewview(request, response);
		default:
			throw new ToAlertException(ErrorCode.CD_404);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void map(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/map/Map.jsp").forward(request, response);
	}
	private void infrm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/map/infrm.jsp").forward(request, response);
	}
	private void reviewwrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/map/reviewWrite.jsp").forward(request, response);
	}
	private void reviewview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/map/reviewView.jsp").forward(request, response);
	}
}
