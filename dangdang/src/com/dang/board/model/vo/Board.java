package com.dang.board.model.vo;

public class Board {
	
	private int bdIdx;
	private String kgName;
	private String title;
	private String regDate;
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
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", kgName=" + kgName + ", title=" + title + ", regDate=" + regDate
				+ ", content=" + content + "]";
	}
	
	

}
