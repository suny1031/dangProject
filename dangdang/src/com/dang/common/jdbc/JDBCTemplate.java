package com.dang.common.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;


public class JDBCTemplate {

	
	//singleton 패턴
	//클래스의 인스턴스가 하나만 생성되어야 할 때 사용하는 디자인 패턴
	//클래스 이름으로 static변수
	private static JDBCTemplate instance;
	
	
	//기본생성자를 private으로 만들어서 외부에서 JDBCTemplate의 생성을 차단
	private JDBCTemplate() {


	}
	
	//클래스를 상속 받아야하나 ?
	public static JDBCTemplate getInstance() {
		if (instance == null) { //인스턴스가 null = 한번도 템플릿이 만들어진적이 없다
			instance = new JDBCTemplate(); //새로운 템플릿을 만들어서 인스턴스 레퍼런스에 넣어줌
		}
		return instance; //한번이라도 생성되면 이미 생성되어있는 인스턴스 반환
	}
	//더 많고 유용한 싱글톤 패턴은 서치

	//Connection 객체를 만들 메서드
	public Connection getConnection() {
	    String url = "jdbc:oracle:thin:@psuny1031_medium?TNS_ADMIN=C:/CODE/wallet/Wallet_psuny1031";
	    //String url = "jdbc:oracle:thin:@psuny1031_medium?TNS_ADMIN=/Users/miyoung/07_semi_wallet/Wallet_psuny1031";								

		String user = "ADMIN";
		String password = "Park10031212*";
		
		Properties info = new Properties();     
	    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, user);
	    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, password);          
		
		Connection conn = null;
		
		try {
			
			OracleDataSource ods = new OracleDataSource();
		    ods.setURL(url);    
		    ods.setConnectionProperties(info);
			conn = ods.getConnection();
	         //Transaction 관리를 개발자가 하기 위해 AutoCommit 설정 끄기
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return conn;
		

		
	}
	// commit 수행
	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close(ResultSet rset) {
		try {
			// 예외처리 필요
			// rset이 null이 아니고 rset이 닫히지 않을때만 실행
			// 아니면 닫힌거니까 아무것도 안해줘도 됨
			if (rset != null && !rset.isClosed()) {

				rset.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Statement stmt) {

		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(ResultSet rset, Statement stmt, Connection conn) {
		try {

			if (rset != null && !rset.isClosed()) {

				rset.close();
			}
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}

			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close(ResultSet rset, Statement stmt) {
		try {

			if (rset != null && !rset.isClosed()) {

				rset.close();
			}
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close(Statement stmt, Connection conn) {
		try {

			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}

			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
