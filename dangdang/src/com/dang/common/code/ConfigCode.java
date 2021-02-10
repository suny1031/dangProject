package com.dang.common.code;

public enum ConfigCode {
	
	//설정과 관련된 코드를 관리하는 ENUM
	
	DOMAIN("http://localhost:8484"),
	EMAIL("suny10312@naver.com"),
	//EMAIL("qkraldud5020@naver.com"),
	//업로드 해서 저장할 위치
	UPLOAD_PATH("C:\\semi\\dangWorkspace\\resources\\upload\\"); //선영
	//UPLOAD_PATH("/Users/miyoung/Desktop/semi/CODE/resources/upload/");
	
	
	public String desc;
	
	ConfigCode(String desc){
		this.desc = desc;
	}

	public String toString() {
		return desc;
	}
	
	
	
	
	
	
	
	
	
}
