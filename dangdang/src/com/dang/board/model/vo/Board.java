package com.dang.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int bdIdx;
	private String kgName;
	private String title;
	private Date regDate;
	private String content;
	public int getBdIdx() {
		return bdIdx;
	}
	
	public void setBdIdx(int bdIdx) {
		this.bdIdx = bdIdx;
	}
	
	public String getKgName() {
		return kgName;
	}
	
	public void setKgName(String kgName) {
		this.kgName = kgName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
