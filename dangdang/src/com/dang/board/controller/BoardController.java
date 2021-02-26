package com.dang.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

		HttpSession KgNameSession = request.getSession();
		SchoolMember kgName = (SchoolMember) KgNameSession.getAttribute("schoolMember");

		// 한 페이지 당 보여줄 글 갯수
		int pageSize = 5;
		// 페이지그룹안의 페이지 갯수 ex) [이전] 1 2 3 4 5 [다음] 일 경우 페이지 갯수는 5
		 int pageGroupSize = 5;

		String pageNum = request.getParameter("pageNum");// 페이지 번호

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;// 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize;// 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;

		List<Board> list = null;
		
		BoardService boardService = new BoardService();// DB연동
		count = boardService.selectCountPage(kgName.getKgName());// 전체 글의 수

		if (count > 0) {
			if (endRow > count)
				endRow = count;
			list = boardService.selectBoardPage(startRow, endRow, kgName.getKgName());// 현재 페이지에 해당하는 글 목록

		} else {
			list = null;
		}

		number = count - (currentPage - 1) * pageSize;// 글목록에 표시할 글번호

		// 페이지그룹의 갯수
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'가 pageGroupCount 개 만큼 있다.
		int pageGroupCount = count / (pageSize * pageGroupSize) + (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);
		// 페이지 그룹 번호
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'의 페이지그룹번호는 1 이고 '[2][3][4]'의 페이지그룹번호는 2
		// 이다.
		int numPageGroup = (int) Math.ceil((double) currentPage / pageGroupSize);

		// 해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));

		request.setAttribute("number", new Integer(number));
		request.setAttribute("pageGroupSize", new Integer(pageGroupSize));
		request.setAttribute("numPageGroup", new Integer(numPageGroup));
		request.setAttribute("pageGroupCount", new Integer(pageGroupCount));
		request.setAttribute("list", list);

		request.getRequestDispatcher("/WEB-INF/view/board/kboardView.jsp").forward(request, response);

	}

	private void userview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession UserSession = request.getSession();
		UserMember userId = (UserMember) UserSession.getAttribute("userMember");
		
		// 한 페이지 당 보여줄 글 갯수
		int pageSize = 5;
		// 페이지그룹안의 페이지 갯수 ex) [이전] 1 2 3 4 5 [다음] 일 경우 페이지 갯수는 5
		 int pageGroupSize = 5;

		String pageNum = request.getParameter("pageNum");// 페이지 번호

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;// 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize;// 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;

		List<Board> list = null;
		
		BoardService boardService = new BoardService();// DB연동
		count = boardService.selectCountPage(userId.getKgName());// 전체 글의 수

		if (count > 0) {
			if (endRow > count)
				endRow = count;
			list = boardService.selectBoardPage(startRow, endRow, userId.getKgName());// 현재 페이지에 해당하는 글 목록

		} else {
			list = null;
		}

		number = count - (currentPage - 1) * pageSize;// 글목록에 표시할 글번호

		// 페이지그룹의 갯수
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'가 pageGroupCount 개 만큼 있다.
		int pageGroupCount = count / (pageSize * pageGroupSize) + (count % (pageSize * pageGroupSize) == 0 ? 0 : 1);
		// 페이지 그룹 번호
		// ex) pageGroupSize가 3일 경우 '[1][2][3]'의 페이지그룹번호는 1 이고 '[2][3][4]'의 페이지그룹번호는 2
		// 이다.
		int numPageGroup = (int) Math.ceil((double) currentPage / pageGroupSize);

		// 해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));

		request.setAttribute("number", new Integer(number));
		request.setAttribute("pageGroupSize", new Integer(pageGroupSize));
		request.setAttribute("numPageGroup", new Integer(numPageGroup));
		request.setAttribute("pageGroupCount", new Integer(pageGroupCount));
		request.setAttribute("list", list);
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

	private void mdfdimpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(bdIdx);
		System.out.println(title);
		System.out.println(content);

		int res = boardService.updateBoard(title, content, bdIdx);

		if (res > 0) {
			request.setAttribute("alertMsg", "공지사항 수정이 완료되었습니다");
			request.setAttribute("url", "/board/kindergardenview.do");

			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));

		System.out.println(bdIdx);

		int res = boardService.deleteBoard(bdIdx);

		if (res > 0) {
			request.setAttribute("alertMsg", "공지사항 삭제가 완료되었습니다");
			request.setAttribute("url", "/board/kindergardenview.do");

			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}

	}

}
