package com.dang.member.school.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dang.common.code.ErrorCode;
import com.dang.common.exception.DataAccessException;
import com.dang.common.jdbc.JDBCTemplate;
import com.dang.member.school.model.vo.SchoolMember;

public class SchoolDao {
	
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	
	
	public SchoolMember memberAuthenticate(Connection conn, String kgId, String kgPw) {
		SchoolMember schoolMember = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try{
			
			String query = "select * from kindergarden where kg_id =? and kg_pw = ?";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kgId);
			pstm.setString(2, kgPw);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				
				schoolMember = new SchoolMember();
				schoolMember.setKgName(rset.getString("kg_name"));
				schoolMember.setKgId(rset.getString("kg_id"));
				schoolMember.setKgPw(rset.getString("kg_pw"));
				schoolMember.setKgClassName(rset.getString("kg_class_name"));
				schoolMember.setKgAdress(rset.getString("kg_address"));
				schoolMember.setKgTell(rset.getString("kg_tell"));
				schoolMember.setKgOperateTime(rset.getString("kg_operate_time"));
				schoolMember.setKgNotice(rset.getString("kg_notice"));
			
				

				
			} //select문은 commit이나 rollback 필요없음
			
			
		}catch(SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		}finally {
			jdt.close(rset, pstm);
		}

		return schoolMember; 
		
	}
	
	
	
	
	
	
	
	

}
