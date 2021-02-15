package com.dang.diary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.diary.model.service.DiaryService;
import com.dang.diary.model.vo.Diary;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;

@WebServlet("/diary/*")
public class DiaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DiaryService diaryService = new DiaryService();

	public DiaryController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "kindergardenview.do":
			kindergardenview(request, response);
			break;
		case "userview.do":
			userview(request, response);
			break;
		case "write.do":
			wirte(request, response);
			break;
		case "upload.do":
			upload(request, response);
			break;
		case "detail.do":
			detail(request, response);
			break;
		case "mdfd.do":
			mdfd(request, response);
			break;
		case "mdfdimpl.do":
			mdfdimpl(request, response);
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

	private void kindergardenview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/diary/kindergardenView.jsp").forward(request, response);

	}

	private void userview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/diary/userView.jsp").forward(request, response);

	}

	private void wirte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/diary/write.jsp").forward(request, response);

	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Diary diary = new Diary();
		diary.setTitle(title);
		diary.setContent(content);
		diary.setKgName(schoolMember.getKgName());

		diaryService.insertDiary(diary);

		request.setAttribute("alertMsg", "알림장 등록이 완료되었습니다");
		request.setAttribute("url", "/diary/kindergardenview.do");

		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		
		String bdIdx = request.getParameter("bdIdx");
		
		Diary diary = diaryService.selectDetail(bdIdx);
		
		request.setAttribute("diary", diary);
		request.setAttribute("schoolMember", schoolMember);

		request.getRequestDispatcher("/WEB-INF/view/diary/detail.jsp").forward(request, response);

	}
	
	private void mdfd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bdIdx = request.getParameter("bdIdx");
		System.out.println(bdIdx);
		Diary diary = diaryService.selectDetail(bdIdx);
		System.out.println(diary);
		request.setAttribute("diary", diary);
		request.getRequestDispatcher("/WEB-INF/view/diary/mdfd.jsp").forward(request, response);

	}
	
	private void mdfdimpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(bdIdx);
		System.out.println(title);
		System.out.println(content);
		
		int res = diaryService.updateDiary(title, content, bdIdx);

		
		if (res > 0 ) {
			request.setAttribute("alertMsg", "알림장 수정이 완료되었습니다");
			request.setAttribute("url", "/diary/kindergardenview.do");

			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);	
		}

	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));

		System.out.println(bdIdx);

		
		int res = diaryService.deleteDiary(bdIdx);

		
		if (res > 0 ) {
			request.setAttribute("alertMsg", "알림장 삭제가 완료되었습니다");
			request.setAttribute("url", "/diary/kindergardenview.do");

			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);	
		}

	}

}
