package com.dang.album.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.album.model.service.AlbumService;
import com.dang.album.model.vo.Album;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.common.util.file.FileVo;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;

@WebServlet("/album/*")
public class AlbumController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlbumService albumService = new AlbumService();

	public AlbumController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "kAlbumview.do":
			kAlbumview(request, response);
			break;
		case "uAlbumview.do":
			uAlbumview(request, response);
			break;
		case "addphoto.do":
			addPhoto(request, response);
			break;
		case "addphotoimpl.do":
			addPhotoimpl(request, response);
			break;
		case "selectdate.do":
			selectDate(request, response);
			break;
		default:
			throw new ToAlertException(ErrorCode.CD_404);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void kAlbumview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");

		String kgName = schoolMember.getKgName();

		ArrayList<FileVo> albumList = albumService.selectFile(kgName);

		request.setAttribute("albumList", albumList);

		System.out.println(albumList);

		request.getRequestDispatcher("/WEB-INF/view/album/kAlbumView.jsp").forward(request, response);

	}

	private void uAlbumview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserMember userMember = (UserMember) session.getAttribute("userMember");

		String kgName = userMember.getKgName();

		ArrayList<FileVo> albumList = albumService.selectFile(kgName);

		request.setAttribute("albumList", albumList);

		System.out.println(albumList);

		request.getRequestDispatcher("/WEB-INF/view/album/uAlbumView.jsp").forward(request, response);

	}

	private void addPhoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/album/kAlbumAdd.jsp").forward(request, response);

	}

	private void addPhotoimpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");

		String kgName = schoolMember.getKgName();

		albumService.insertAlbum(kgName, request);

		request.setAttribute("alertMsg", "사진 등록이 완료되었습니다");

		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

	}

	private void selectDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserMember userMember = (UserMember) session.getAttribute("userMember");

		String kgName = userMember.getKgName();

		String date = request.getParameter("date");
		System.out.println(date);

		java.sql.Date selectDate = java.sql.Date.valueOf(date);

		ArrayList<FileVo> albumList = albumService.selectDate(selectDate, kgName);
		System.out.println(albumList);
	}
}
