package com.dang.diary.model.vo;

import java.sql.Date;

public class Diary {

	private int bdDiaryIdx;
	private String kgName;
	private String className;
	private String title;
	private Date regDate;
	private String content;
	private String isDel;

	public Diary() {

	}

	public Diary(int bdDiaryIdx, String kgName, String className, String title, Date regDate, String content,
			String isDel) {
		super();
		this.bdDiaryIdx = bdDiaryIdx;
		this.kgName = kgName;
		this.className = className;
		this.title = title;
		this.regDate = regDate;
		this.content = content;
		this.isDel = isDel;
	}

	public int getBdDiaryIdx() {
		return bdDiaryIdx;
	}

	public void setBdDiaryIdx(int bdDiaryIdx) {
		this.bdDiaryIdx = bdDiaryIdx;
	}

	public String getKgName() {
		return kgName;
	}

	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Diary [bdDiaryIdx=" + bdDiaryIdx + ", kgName=" + kgName + ", className=" + className + ", title="
				+ title + ", regDate=" + regDate + ", content=" + content + ", isDel=" + isDel + "]";
	}

}
