package com.dang.common.exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/exception")
public class ExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExceptionHandler() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String email = request.getParameter("email");
			System.out.println("ExceptionHandler : "+email);
			//request가 타고 여기 까지 넘어옴
			
			//request처리 도중 발생한 예외들은 request에 
			//javax.servlet.error.exception이라는 속성명으로 담겨온다
			//즉 getAttribute() 메서드를 사용해 꺼낼 수 있다

			CustomException e = (CustomException) request.getAttribute("javax.servlet.error.exception");

			request.setAttribute("alertMsg",e.error.errMsg());
			request.setAttribute("url",e.error.url());
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			
			
			
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
