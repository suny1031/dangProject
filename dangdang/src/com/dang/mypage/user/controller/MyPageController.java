package com.dang.mypage.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypage/*")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		switch(uriArr[uriArr.length-1]){
		case "userpage.do" : viewUserPage(request, response);
		break;
		case "schoolpage.do" : viewSchoolPage(request, response);
		break;
		
		
		
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	protected void viewUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/userpage.jsp").forward(request, response);
		
		
	}
	
	protected void viewSchoolPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/schoolpage.jsp").forward(request, response);
		
		
	}
	
	
	
	

}
