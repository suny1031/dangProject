package com.dang.member.school.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dang.common.code.ConfigCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.exception.ToAlertException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.common.mail.MailSender;
import com.dang.common.util.file.FileUtil;
import com.dang.common.util.file.FileVo;
import com.dang.map.model.vo.Service;
import com.dang.member.school.model.dao.SchoolDao;
import com.dang.member.school.model.vo.SchoolMember;
import com.dang.member.user.model.vo.UserMember;

	//service 단에서는 비즈니스 로직을 작성하게 된다.
	//비즈니스 로직에 필요한 데이터를 Controller에게 전달받고 추가적으로 필요한 데이터를 Dao에게 요청하여
	//핵심 로직인 비즈니스 로직을 작성한다.
	//비즈니스 로직을 service가 담당하기 때문에 transaction관리도 service가 담당.
	

public class SchoolService {

	SchoolDao schoolDao = new SchoolDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	
	// 로그인시 아이디와 비밀번호를 확인하는 비즈니스 로직
	public SchoolMember memberAuthenticate(String kgId, String kgPw) {
		//connection 생성
		Connection conn = jdt.getConnection();
		SchoolMember schoolMember = null;
		
		try {
			//Dao에게 Connection 주입
			//Dao는 주입받은 Connection객체로 Statement 객체를 만들어 쿼리를 실행하게 되고
			//따라서 Service에서 해당 쿼리를 commit, rollback처리를 할 수 있게 된다.
			//Dao에서 schoolMembe 객체가 반환된다.
			schoolMember = schoolDao.memberAuthenticate(conn, kgId, kgPw);
			
		}finally {
			jdt.close(conn);
		}
		
		
		return schoolMember;
		
		
	}

	
	
	
	
	public Service SchoolproService(String kgName) {
		Connection conn = jdt.getConnection();
		Service schoolProService = null;
		
		
		try {
			schoolProService = schoolDao.SchoolproService(conn, kgName);
		}finally {
			jdt.close(conn);
		}
		
		return schoolProService;
	}
	
	
	
	
	
	
	
	
	
	public SchoolMember findSchoolId(String schoolName, String schoolPhone) {
		
		Connection conn = jdt.getConnection();
		SchoolMember res = null;
		
		try {
			res = schoolDao.findSchoolId(conn, schoolName, schoolPhone);
		} finally {
			jdt.close(conn);
		}
		
		return res;
		
		
	}
	
	
	

	public SchoolMember findSchoolPw(String kgId, String kgEmail) {
		
		Connection conn = jdt.getConnection();
		SchoolMember schoolMember = null;
		
		try{
			schoolMember = schoolDao.findSchoolPw(conn, kgId, kgEmail);
		 
		}finally {
			jdt.close(conn);
		}
		return schoolMember;
		
	}
	
	
	
	
	//비밀번호 찾기시 이메일보내기
			public void finSchoolPwEmail(SchoolMember schoolMember) {
				
				String subject = "임시 비밀번호가 발급되었습니다.";
				String htmlText ="<h1>'${param.userPw}'</h1><h1>입니다."
								+ "</h1><h1>아래의 링크를 클릭하시면 로그인 창으로 이동합니다."
								+ "</h1><h1>로그인 후 임시비밀번호를 바꿔주세요.</h1>";
				
				htmlText += "<a href='"+ ConfigCode.DOMAIN + "/school/login.do\'>홈페이지 이동하기</a>";
				String to = schoolMember.getKgEmail();
				
				new MailSender().sendEmail(subject, htmlText, to);
				
			}
	
	
	
	
	
	
	public int modifySchoolInfo(String kgId, String kgName, String kgAddress, String kgTell, String kgOperateTime, String kgNotice, String kgEmail) {
		
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = schoolDao.modifySchoolInfo(conn, kgId, kgName, kgAddress, kgTell, kgOperateTime, kgNotice, kgEmail);
			jdt.commit(conn);
			
		}catch(DataAccessException e){
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	
	
	public int modifySchoolService(String kgName, int isKg, int isCafe, int isHotel, int isPickup, int isMedic, int isAcademy, int isSpa) {
		Connection conn = jdt.getConnection();
		int res = 0;
		
		
		try {
			res = schoolDao.modifySchoolService(conn, kgName, isKg, isCafe, isHotel, isPickup, isMedic, isAcademy, isSpa);
			jdt.commit(conn);
		}catch(DataAccessException e){
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		System.out.println("schoolServcie" + res);
		return res ;
		
	}





	public int uploadSchoolPhoto(HttpServletRequest request) {
		Connection conn = jdt.getConnection();
		int res = 0;
		
		//게시글 저장
		Map<String,List> PhotoData = new FileUtil().fileUpload(request);
		
		try {
			
			for(FileVo fileData : (List<FileVo>)PhotoData.get("fileData")) {
				schoolDao.uploadSchoolPhoto(conn, fileData);
			}
			jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error,e);
		}finally {
			jdt.close(conn);
		}
		System.out.println(res);
		return res;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
