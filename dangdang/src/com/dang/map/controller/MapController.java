package com.dang.map.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.map.model.service.MapService;
import com.dang.map.model.vo.Kindergarten;

@WebServlet("/map/*")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MapService mapService = new MapService();

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
		case "infrm.do":
			infrm(request, response);
			break;
		case "infrmimpl.do":
			infrmimpl(request, response);
			break;
		default:
			throw new ToAlertException(ErrorCode.CD_404);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void map(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Kindergarten> map = mapService.selectKindergarten();

		request.setAttribute("mapList", map);

		request.getRequestDispatcher("/WEB-INF/view/map/Map.jsp").forward(request, response);
	}

	private void infrmimpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String kgName = request.getParameter("kgName");
		System.out.println("비동기 통신으로 넘어온 값 " + kgName);

		Kindergarten kindergarten = mapService.selectMapkgName(kgName);

		System.out.println(kindergarten);

		request.setAttribute("kindergarten", kindergarten);

//		request.setAttribute("url","/map/infrm.do");
//		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

		request.getRequestDispatcher("/WEB-INF/view/map/Infrm.jsp").forward(request, response);

	}

	private void infrm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String kgName = request.getParameter("kgName");
		System.out.println("비동기 통신으로 넘어온 값 " + kgName);

		Kindergarten kindergarten = mapService.selectMapkgName(kgName);

		System.out.println(kindergarten);

		request.setAttribute("kindergarten", kindergarten);

		request.getRequestDispatcher("/WEB-INF/view/map/Infrm.jsp").forward(request, response);
	}

}
