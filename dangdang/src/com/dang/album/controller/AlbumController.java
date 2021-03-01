package com.dang.album.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;

@WebServlet("/album/*")
public class AlbumController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		System.out.println("k들어옴");
		request.getRequestDispatcher("/WEB-INF/view/album/kAlbumView.jsp").forward(request, response);


	}
	private void uAlbumview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/album/uAlbumView.jsp").forward(request, response);


	}
}
