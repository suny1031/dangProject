package com.dang.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.common.util.file.FileVo;
import com.dang.map.model.service.MapService;
import com.dang.map.model.vo.Kindergarten;
import com.dang.member.user.model.vo.UserMember;
import com.dang.review.model.service.ReviewService;
import com.dang.review.model.vo.Review;

@WebServlet("/review/*")
public class reviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReviewService reviewService = new ReviewService();
	MapService mapService = new MapService();

	public reviewController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		System.out.println(Arrays.toString(uriArr));

		switch (uriArr[uriArr.length - 1]) {

		case "write.do":
			write(request, response);
			break;

		case "upload.do":
			upload(request, response);
			break;

		case "view.do":
			view(request, response);
			break;
		default:
			throw new ToAlertException(ErrorCode.CD_404);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String kgName = request.getParameter("kgName");
		request.setAttribute("kgName", kgName);
		System.out.println(kgName);
		request.getRequestDispatcher("/WEB-INF/view/review/Write.jsp").forward(request, response);
	}

	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kgName = request.getParameter("kgName");

		ArrayList<Review> reviewList = reviewService.selectReview(kgName);
		Kindergarten kindergarten = mapService.selectkgName(kgName);
		ArrayList<FileVo> fileList = reviewService.selectFile(kgName);

		
		System.out.println("후기 리스트 " + reviewList);
		System.out.println("유치원 리스트" + kindergarten);
		System.out.println("파일 리스트" + fileList);

		
		
		
		
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("kindergarten", kindergarten);
		request.setAttribute("fileList", fileList);


		request.getRequestDispatcher("/WEB-INF/view/review/View.jsp").forward(request, response);
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		UserMember userMember = (UserMember) session.getAttribute("userMember");
		
		String kgName = request.getParameter("kgName");
		
		reviewService.insertReview(userMember.getNickname(), kgName, request);
		
		request.setAttribute("alertMsg", "후기 등록이 완료되었습니다");
		request.setAttribute("url", "/review/view.do?kgName="+kgName);
		
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		
	}

}
