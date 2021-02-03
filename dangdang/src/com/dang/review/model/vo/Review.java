package com.dang.review.model.vo;

import java.sql.Date;

public class Review {
	private String content;
	private String kgName;
	private String rvIdx;
	private int starRate;
	private Date regDate;
	private String title;
	private String userName;

	public Review() {

	}

	public Review(String content, String kgName, String rvIdx, int starRate, Date regDate, String title,
			String userName) {
		super();
		this.content = content;
		this.kgName = kgName;
		this.rvIdx = rvIdx;
		this.starRate = starRate;
		this.regDate = regDate;
		this.title = title;
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKgName() {
		return kgName;
	}

	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	public String getRvIdx() {
		return rvIdx;
	}

	public void setRvIdx(String rvIdx) {
		this.rvIdx = rvIdx;
	}

	public int getStarRate() {
		return starRate;
	}

	public void setStarRate(int starRate) {
		this.starRate = starRate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Review [content=" + content + ", kgName=" + kgName + ", rvIdx=" + rvIdx + ", starRate=" + starRate
				+ ", regDate=" + regDate + ", title=" + title + ", userName=" + userName + "]";
	}

}
