package com.dang.common.mail.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/mail.do")
public class MailHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MailHandler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String template = request.getParameter("template");
		String url = "/WEB-INF/view/mail-temp/" + template + ".jsp";
		//해당 jsp에서 만들어진 html코드가 응답될 수 있도록
		request.getRequestDispatcher(url).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}



}
