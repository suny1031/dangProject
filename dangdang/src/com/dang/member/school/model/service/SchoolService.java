package com.dang.member.school.model.service;

import java.sql.Connection;

import com.dang.common.jdbc.JDBCTemplate;
import com.dang.member.school.model.dao.SchoolDao;
import com.dang.member.school.model.vo.SchoolMember;

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
		SchoolMember res = null;
		
		try {
			//Dao에게 Connection 주입
			//Dao는 주입받은 Connection객체로 Statement 객체를 만들어 쿼리를 실행하게 되고
			//따라서 Service에서 해당 쿼리를 commit, rollback처리를 할 수 있게 된다.
			//Dao에서 schoolMembe 객체가 반환된다.
			 res = schoolDao.memberAuthenticate(conn, kgId, kgPw);
			
		}finally {
			jdt.close(conn);
		}
		
		
		return res;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
