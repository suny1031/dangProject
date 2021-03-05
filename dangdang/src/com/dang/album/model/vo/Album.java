package com.dang.album.model.vo;

import java.sql.Date;

public class Album {
	private String bdAlIdx;
	private String kgName;
	private String className;
	private Date date;

	
	public Album() {
		
	}
	public Album(String bdAlIdx, String kgName, String className, Date date) {
		super();
		this.bdAlIdx = bdAlIdx;
		this.kgName = kgName;
		this.className = className;
		this.date = date;
	}

	public String getBdAlIdx() {
		return bdAlIdx;
	}

	public void setBdAlIdx(String bdAlIdx) {
		this.bdAlIdx = bdAlIdx;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Album [bdAlIdx=" + bdAlIdx + ", kgName=" + kgName + ", className=" + className + ", date=" + date + "]";
	}

}
