package com.dang.board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dang.board.model.service.BoardService;
import com.dang.common.code.ConfigCode;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
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
		case "writeboard.do": writeBoard(request, response);
			break;
		case "addboard.do": addBoard(request,response);
			break;
		case "modifyboard.do": modifyBoard(request,response);
			break;	
		case "viewboard.do": viewBoard(request,response);
			break;
		case "listboard1.do": listBoard1(request, response);
			break;
		case "listboard2.do": listBoard2(request, response);
			break;
		case "board.do": boardAction(request, response);
			break;
		default:
			throw new ToAlertException(ErrorCode.CD_404);
		}
		request.getRequestDispatcher("/WEB-INF/view/main/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void writeBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardAdd.jsp").forward(request, response);
	}
	
	private void listBoard1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardList1.jsp").forward(request, response);
	}

	private void listBoard2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/student/BoardList2.jsp").forward(request, response);
	}
	
	private void addBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardAdd.jsp").forward(request, response);
	}
	
	private void modifyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/BoardModify.jsp").forward(request, response);
	}
	
	private void viewBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/student/BoardView.jsp").forward(request, response);
	}	
	
	private void boardAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/view/board/kindergarten/board.jsp").forward(request, response);
	}
	
}
