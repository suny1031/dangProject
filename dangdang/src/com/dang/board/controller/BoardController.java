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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "addboardimpl.do": addBoardImpl(request,response); // 글 작성 추가 메서드 (오류)
			break;
		case "addboard.do": addBoard(request, response); // 글 작성 view 페이지 (오류)
			break;
		case "modifyboard.do": modifyBoard(request,response); // 글 수정 페이지 (오류)
			break;
		case "modifyboardimpl.do": modifyBoardImpl(request,response); // 글 수정 페이지 (오류)
		break;	
		case "viewboard1.do": viewBoard1(request,response); // 업주용 글 상세 페이지
			break;
		case "viewboard2.do": viewBoard2(request, response); // 견주용 글 상세 페이지
			break;
		case "listboard1.do": listBoard1(request, response); // 업주용 글 목록 페이지
			break;
		case "listboard2.do": listBoard2(request, response); // 견주용 글 목록 페이지
			break;
		case "delete.do": deleteBoard(request, response); // 글을 삭제 했을 때 한번 거쳐주는 액션 페이지
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listBoard1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Board> boardlist = boardService.listBoard();
		request.setAttribute("boardList", boardlist);
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardList1.jsp").forward(request, response);
	}

	private void listBoard2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserMember userMember = (UserMember) session.getAttribute("userMember");
		String kgName = userMember.getKgName();
		request.setAttribute("kgName", kgName);
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/board/student/BoardList2.jsp").forward(request, response);
	}
	
	private void addBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardAdd.jsp").forward(request, response);
	}
	
	private void addBoardImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		String kgName = schoolMember.getKgName();
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		boardService.addBoard(boardTitle, kgName, boardContent);
		
		request.setAttribute("alertMsg", "게시글 등록이 완료되었습니다");
		request.setAttribute("url", "/board/listboard1.do");
		
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
	}
	
	private void modifyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		request.setAttribute("bdIdx", bdIdx);
		Board board = boardService.viewBoard(bdIdx);
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardModify.jsp").forward(request, response);
	}
	
	private void modifyBoardImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		String modifyboardTitle = request.getParameter("modifyboardTitle");
		String modifyboardContent = request.getParameter("modifyboardContent");
		boardService.modifyBoard(bdIdx, modifyboardTitle, modifyboardContent);


		request.setAttribute("alertMsg", "게시글 수정이 완료되었습니다");
		request.setAttribute("url", "/board/listboard1.do");
		
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
	}
	
	private void viewBoard1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SchoolMember schoolMember = (SchoolMember) session.getAttribute("schoolMember");
		String kgName = schoolMember.getKgName();
		request.setAttribute("kgName", kgName);
		int bdIdx =  Integer.parseInt(request.getParameter("bdIdx"));
		request.setAttribute("bdIdx", bdIdx);
		Board board = boardService.viewBoard(bdIdx);
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardView1.jsp").forward(request, response);
	}
	
	private void viewBoard2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bdIdx =  Integer.parseInt(request.getParameter("bdIdx"));
		request.setAttribute("bdIdx", bdIdx);
		Board board = boardService.viewBoard(bdIdx);
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/view/board/student/BoardView2.jsp").forward(request, response);
	}
	
	private void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
		request.setAttribute("bdIdx", bdIdx);
		boardService.deleteBoard(bdIdx);
		
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardView1.jsp").forward(request, response);
	}
	
}
