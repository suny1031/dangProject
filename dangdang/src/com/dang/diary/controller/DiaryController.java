package com.dang.diary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		request.setAttribute("url", "/diary/view.do");

		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

	}

}
