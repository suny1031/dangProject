package com.dang.member.user.model.vo;

import java.sql.Date;

public class UserMember {
	
	private String userId;
	private String kgName;
	private String password;
	private String userName;
	private String email;
	private Date birth;
	private String phoneNumber;
	private String nickname;
	private String className;
	private int isleave;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKgName() {
		return kgName;
	}
	public void setKgName(String kgName) {
		this.kgName = kgName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getIsleave() {
		return isleave;
	}
	public void setIsleave(int isleave) {
		this.isleave = isleave;
	}
	
	
	
	@Override
	public String toString() {
		return "UserMember [userId=" + userId + ", kgName=" + kgName + ", password=" + password + ", userName="
				+ userName + ", email=" + email + ", birth=" + birth + ", phoneNumber=" + phoneNumber + ", nickname="
				+ nickname + ", className=" + className + ", isleave=" + isleave + "]";
	}
	
	
	


}
