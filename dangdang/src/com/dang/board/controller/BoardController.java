package com.dang.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.board.model.service.BoardService;
import com.dang.board.model.vo.Board;
import com.dang.common.code.ConfigCode;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.dang.diary.model.vo.Diary;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	public BoardController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
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

		request.getRequestDispatcher("/WEB-INF/view/board/kboardView.jsp").forward(request, response);

	}

	private void userview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/board/uboardView.jsp").forward(request, response);

	}

	private void wirte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/board/write.jsp").forward(request, response);

	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setKgName(schoolMember.getKgName());

		boardService.insertBoard(board);

		request.setAttribute("alertMsg", "공지사항이 등록이 완료되었습니다");
		request.setAttribute("url", "/board/kindergardenview.do");

		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");

		String bdIdx = request.getParameter("bdIdx");

		Board board = boardService.selectDetail(bdIdx);

		request.setAttribute("board", board);
		request.setAttribute("schoolMember", schoolMember);

		request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(request, response);

	}
	private void mdfd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bdIdx = request.getParameter("bdIdx");
		System.out.println(bdIdx);
		Board board = boardService.selectDetail(bdIdx);
		System.out.println(board);
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/view/board/mdfd.jsp").forward(request, response);

	}
	
	private void mdfdimpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(bdIdx);
		System.out.println(title);
		System.out.println(content);
		
		int res = boardService.updateBoard(title, content, bdIdx);
		
		if (res > 0 ) {
			request.setAttribute("alertMsg", "알림장 수정이 완료되었습니다");
			request.setAttribute("url", "/board/kindergardenview.do");

			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);	
		}

	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));

		System.out.println(bdIdx);

		
		int res = boardService.deleteBoard(bdIdx);

		
		if (res > 0 ) {
			request.setAttribute("alertMsg", "알림장 삭제가 완료되었습니다");
			request.setAttribute("url", "/board/kindergardenview.do");

			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);	
		}

	}


}
