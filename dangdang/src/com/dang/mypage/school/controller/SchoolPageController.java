package com.dang.mypage.school.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypage/school/*")
public class SchoolPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SchoolPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		switch(uriArr[uriArr.length-1]){
		case "schoolpage.do" : viewSchoolPage(request, response);
		
		
		
		
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	

	
	protected void viewSchoolPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/WEB-INF/view/mypage/school/schoolpage.jsp").forward(request, response);
		
	}
	
	

}
