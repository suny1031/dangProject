package com.dang.common.util.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;


public class HttpUtils {
	// HttpURLConnection : http, https 통신을 모두 지원해서 이걸로 사용!
	// http 통신을 위해 필요한 것들
	// 1. 시작줄(url,method)
	// 2. header
	// 3. body

	// 0. get/post 방식으로 통신할 메서드
	// 1. HttpURLConnection 객체 생성
	// 2. 헤더작성
	// 3. 바디작성
	// 4. 응답
	// 5. urlEncoded 포멧팅

	HttpURLConnection conn = null;

	
	
	// 헤더가 없어도 쓸수 있는 메서드
	public String get(String url) {
		String res = "";

		try {

			setConnection(url, "GET");
			// 컨넥션 생성

			res = getResponse();
			// 응답받아서 값 리턴

		} catch (IOException e) {
			throw new ToAlertException(ErrorCode.API01, e);

		}

		return res;

	}
	
	
	
	//get 방식으로 포스팅
	public String get(String url, Map<String, String> headers) {
		String res = "";

		try {

			setConnection(url, "GET");
			// 컨넥션 생성

			setHeaders(headers);
			// 헤더 설정

			res = getResponse();
			// 응답받아서 값 리턴

		} catch (IOException e) {
			throw new ToAlertException(ErrorCode.API01, e);

		}

		return res;

	}
	
	
	
	
	//post 방식으로 통신
	public String post(String url, String body, Map<String, String> headers) {

		String res = "";
		try {
			setConnection(url, "POST");
			setHeaders(headers);
			setBody(body);
			res = getResponse();
		} catch (IOException e) {
			throw new ToAlertException(ErrorCode.API01,e);
		}

		return res;

	}

	
	
	
	
	// 외부에서 body를 넘길때 인코딩
	public String urlEncodedForm(Map<String, String> params) {
		String res = "";
		try {
			for (String key : params.keySet()) {
				res += "&" + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(params.get(key), "UTF-8");
				// &name = value&name=value
				// 아스키코드표로 표현가능하게끔 문자를 인코딩

			}
			//res 에 null 값이 들어가 있을 수 있기 때문에
			if (res.length() > 0) {
				res = res.substring(1);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return res;

	}

	
	
	
	
	// 컨넥션 객체를 만들어줌
	private void setConnection(String url, String method) throws IOException {

		URL u = new URL(url);
		conn = (HttpURLConnection) u.openConnection();
		conn.setRequestMethod(method);

		// post일 경우에 doOutput옵션을 true로 지정해 출력스트림 사용여부를 true로지정
		if (method.equals("POST")) {
			conn.setDoOutput(true);

		}
		// 통신이 지연될 경우 통신을 종료할 시간을 10초로 지정
		// 10초가 지나면 conn을 강제로 닫는다
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);

	}

	
	
	
	// 헤더를 추가해줌
	private void setHeaders(Map<String, String> headers) {
		// Map.length = 0 돌지않는다

		for (String key : headers.keySet()) {
			conn.addRequestProperty(key, headers.get(key));
		}

	}

	
	
	
	
	// body부분을 작성
	private void setBody(String body) throws IOException {

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

		try {
			bw.write(body);
			bw.flush();
		} finally {
			bw.close();
		}

	}

	
	
	
	// 응답 데이터를 읽어옴
	private String getResponse() throws IOException {

		String res = "";
		BufferedReader br = null;
		// 응답코드가 200번대인지 확인
		int status = conn.getResponseCode();

		if (status >= 200 && status <= 300) {

			try {// body에 inputStream으로 읽어서 문자열에 저장
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line;
				// 한줄
				StringBuffer sb = new StringBuffer();
				// 여러줄
				while ((line = br.readLine()) != null) {
					sb.append(line);

				}
				res = sb.toString();
			} finally {
				br.close();
			}

		} else {
			throw new ToAlertException(ErrorCode.API01, new Exception(status + "이 응답되었습니다"));
		}

		return res;

	}
	
	

	
	
	
	
	

}
