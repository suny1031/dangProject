package com.dang.common.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.dang.common.code.ConfigCode;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;

public class MailSender {

	// 1. Session객체 생성
	// 2. 메세지 작성
	// 3. 메세지의 body부분을 작성하기 위해 multipart 객체 생성

	public void sendEmail(String subject, String text, String to) {
										  //session 대신 getSession메소드의 리턴값 넣기 
		MimeMessage msg = new MimeMessage(getSession()); 

		try {

			msg.setFrom(new InternetAddress(ConfigCode.EMAIL.desc));
			msg.setRecipients(Message.RecipientType.TO, to); //보낼 이메일 지정
			msg.setSubject(subject); //제목 작성
			msg.setContent(getMultipart(text)); //문자 작성

			Transport.send(msg);

		} catch (MessagingException e) {
			throw new ToAlertException(ErrorCode.MAIL01, e);
		}

	}

	//Session을 만드는 메소드
	private Session getSession() {
		// 1. 네이버 smtp 서버를 사용하기 위해 인증정보를 넣어야 한다.
		// 네이버 id, pw

		// PasswordAuthentication => 사용자 정보를 담기 위해서 사용한다.(세션을 만들 때)

		PasswordAuthentication pa = new PasswordAuthentication("suny10312@naver.com", "");
		//PasswordAuthentication pa  = new PasswordAuthentication("qkraldud5020@naver.com","1*qkrayddl");




		// 2. 사용할 smtp 서버 정보를 작성
		// smtp 서버이름, 포트, tls 통신 가능여부, 사용자 인증 여부
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.naver.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		//세션에 넣어주기(익명클래스로 간편하게 사용)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return pa;

			}

		});
		return session;

	}
	
	
	
	
	
	private Multipart getMultipart(String text) throws MessagingException {
		Multipart multipart = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart(); //어떤 타입의 텍스트롤 보내는지 타입 알려주기
		htmlPart.setContent(text, "text/html; charset=UTF-8");
		multipart.addBodyPart(htmlPart);

		return multipart;
	}
	
	
	
	
	

}
