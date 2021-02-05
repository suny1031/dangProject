package com.dang.reservation.model.vo;

import java.sql.Date;

public class Reservation {
	private int rsIdx;
	private String userId;
	private String protectorName;
	private String phoneNumber;
	private String dogBreed;
	private String dogAge;
	private String pickup;
	private String isApproved;
	private String requirements;
	private String kindergarten;
	private Date regDate;

	public Reservation() {

	}

	public Reservation(int rsIdx, String userId, String protectorName, String phoneNumber, String dogBreed,
			String dogAge, String pickup, String isApproved, String requirements, String kindergarten, Date regDate) {
		super();
		this.rsIdx = rsIdx;
		this.userId = userId;
		this.protectorName = protectorName;
		this.phoneNumber = phoneNumber;
		this.dogBreed = dogBreed;
		this.dogAge = dogAge;
		this.pickup = pickup;
		this.isApproved = isApproved;
		this.requirements = requirements;
		this.kindergarten = kindergarten;
		this.regDate = regDate;
	}

	public int getRsIdx() {
		return rsIdx;
	}

	public void setRsIdx(int rsIdx) {
		this.rsIdx = rsIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProtectorName() {
		return protectorName;
	}

	public void setProtectorName(String protectorName) {
		this.protectorName = protectorName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDogBreed() {
		return dogBreed;
	}

	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}

	public String getDogAge() {
		return dogAge;
	}

	public void setDogAge(String dogAge) {
		this.dogAge = dogAge;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(String kindergarten) {
		this.kindergarten = kindergarten;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Reservation [rsIdx=" + rsIdx + ", userId=" + userId + ", protectorName=" + protectorName
				+ ", phoneNumber=" + phoneNumber + ", dogBreed=" + dogBreed + ", dogAge=" + dogAge + ", pickup="
				+ pickup + ", isApproved=" + isApproved + ", requirements=" + requirements + ", kindergarten="
				+ kindergarten + ", regDate=" + regDate + "]";
	}

}
