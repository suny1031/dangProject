package com.dang.common.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;



public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//사용자 확인을 위한 세션 꺼내는 작업
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		// 로그인이 안된 사용자일 경우 회원 정보 페이지에 접근할 수 없게 막기
		// 로그인이 안된 사용자 == session에 user라는 속성값이 없는 사용자
		String[] uriArr = httpRequest.getRequestURI().split("/");
		System.out.println(Arrays.toString(uriArr));

		// 사용자가 접근한 url 경로 확인
		if (uriArr.length > 0) {
			// 빈배열이 넘어와서 0보다 클때 그리고 session에 값이 없을때 
			// 1depth
			switch (uriArr[1]) {
			case "user":
				// 2depth
				switch (uriArr[2]) {
				 case "userpage.do":
					if (session.getAttribute("userMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}break; 
					
				case "joinimpl.do":
					if (session.getAttribute("persistUser") == null) {
						throw new ToAlertException(ErrorCode.AUTH02);
					}break; 
					
				case "login.do":
					if(session.getAttribute("userMember") != null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}break;
				
				}
				break;
				
			case "school":
				// 2depth
				switch (uriArr[2]) {
				case "schoolpage.do":
					if (session.getAttribute("schoolMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
						
					}break;
					
				case "schoolprofile.do":
					if (session.getAttribute("schoolMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}break;
					
				case "login.do":
					if(session.getAttribute("schoolMember") != null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}break;

				}
				break;
				
			//mypage에 로그인 안한 계정들의 접근 막아주기
			case "mypage":
				if(session.getAttribute("userMember") == null) {
					throw new ToAlertException(ErrorCode.AUTH01);
				} else if(session.getAttribute("schoolMember") == null)	{
					throw new ToAlertException(ErrorCode.AUTH01);
				}break;
				
				
				
			case "board":
				switch (uriArr[2]) {
				case "addboard.do":
					if (session.getAttribute("schoolMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH08);
					}
					break;
				case "modifyboard.do":
					if (session.getAttribute("schoolMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH08);
					}
					break;
				case "listboard1.do":
					if(session.getAttribute("schoolMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH08);
					}
					break;
				case "listboard2.do":
					if(session.getAttribute("userMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH03);
					}
					break;
				case "viewboard2.do":
					if(session.getAttribute("userMember") == null) {
						throw new ToAlertException(ErrorCode.AUTH03);
					}
					break;
				}
				break;	
				
				case "review":
					switch (uriArr[2]) {
					case "write.do":
						if (session.getAttribute("userMember") == null) {
							throw new ToAlertException(ErrorCode.AUTH04);
						}
						break;
					case "upload.do":
						if (session.getAttribute("userMember") == null) {
							throw new ToAlertException(ErrorCode.AUTH04);
						}
						break;
					}
					break;

				case "diary":
					switch (uriArr[2]) {
					case "kindergardenview.do":
						if (session.getAttribute("schoolMember") == null) {
							throw new ToAlertException(ErrorCode.AUTH09);
						}
						break;
					case "userview.do":
						if (session.getAttribute("userMember") == null) {
							throw new ToAlertException(ErrorCode.AUTH09);
						}
						break;
					}
					break;
					
				case "reservation":
					switch (uriArr[2]) {
					case "calendar.do":
						if (session.getAttribute("schoolMember") == null) {
							throw new ToAlertException(ErrorCode.AUTH07);
						}
						break;
					}
					break;
			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
