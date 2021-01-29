package com.dang.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	public EncodingFilter() {
	}

	// filter가 컨테이너에 의해서 삭제 될 때 호출
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter 실행");
		// 같은 url-patten으로 여러개의 filter를 지정할 수 있다
		// 해당 filter들은 filter-chain형태로 관리되며, web.xml에 등록한 순서대로 실행된다
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html ; charset=utf-8");
		// 필터 체인을 탐색해 다음 필터가 있다면 다음 필터의 doFilter 메서드 호출
		// 다음 필터가 없다면 servelt을 호출한다
		chain.doFilter(request, response);

	}

	// filter가 생성될 때 실행되는 메서드
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
