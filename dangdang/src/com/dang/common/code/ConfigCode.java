package com.dang.common.code;

public enum ConfigCode {
	
	DOMAIN("http://localhost:8484"),
	//EMAIL("suny10312@naver.com"),
	EMAIL("qkraldud5050@naver.com"),
	UPLOAD_PATH("C:\\semi\\dangWorkspace\\resources\\upload\\");
	//UPLOAD_PATH("/Users/miyoung/Desktop/semi/CODE/resources/upload/");
	//업로드 해서 저장할 위치
	
	public String desc;
	
	ConfigCode(String desc){
		this.desc = desc;
	}

	public String toString() {
		return desc;
	}
	
	
	
	
	
	
	
	
	
}
