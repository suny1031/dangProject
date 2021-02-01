package com.dang.map.model.vo;

public class Kindergarten {
	private String kgAddress;
	private String kgClassName;
	private String kgId;
	private String kgIdx;
	private String kgLat;
	private String kgLag;
	private String kgName;
	private String kgNotice;
	private String kgOperateTime;
	private String kgPw;
	private String kgTell;

	public Kindergarten() {

	}

	public Kindergarten(String kgAddress, String kgClassName, String kgId, String kgIdx, String kgLat, String kgLag,
			String kgName, String kgNotice, String kgOperateTime, String kgPw, String kgTell) {
		super();
		this.kgAddress = kgAddress;
		this.kgClassName = kgClassName;
		this.kgId = kgId;
		this.kgIdx = kgIdx;
		this.kgLat = kgLat;
		this.kgLag = kgLag;
		this.kgName = kgName;
		this.kgNotice = kgNotice;
		this.kgOperateTime = kgOperateTime;
		this.kgPw = kgPw;
		this.kgTell = kgTell;
	}

	public String getKgAddress() {
		return kgAddress;
	}

	public void setKgAddress(String kgAddress) {
		this.kgAddress = kgAddress;
	}

	public String getKgClassName() {
		return kgClassName;
	}

	public void setKgClassName(String kgClassName) {
		this.kgClassName = kgClassName;
	}

	public String getKgId() {
		return kgId;
	}

	public void setKgId(String kgId) {
		this.kgId = kgId;
	}

	public String getKgIdx() {
		return kgIdx;
	}

	public void setKgIdx(String kgIdx) {
		this.kgIdx = kgIdx;
	}

	public String getKgLat() {
		return kgLat;
	}

	public void setKgLat(String kgLat) {
		this.kgLat = kgLat;
	}

	public String getKgLag() {
		return kgLag;
	}

	public void setKgLag(String kgLag) {
		this.kgLag = kgLag;
	}

	public String getKgName() {
		return kgName;
	}

	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	public String getKgNotice() {
		return kgNotice;
	}

	public void setKgNotice(String kgNotice) {
		this.kgNotice = kgNotice;
	}

	public String getKgOperateTime() {
		return kgOperateTime;
	}

	public void setKgOperateTime(String kgOperateTime) {
		this.kgOperateTime = kgOperateTime;
	}

	public String getKgPw() {
		return kgPw;
	}

	public void setKgPw(String kgPw) {
		this.kgPw = kgPw;
	}

	public String getKgTell() {
		return kgTell;
	}

	public void setKgTell(String kgTell) {
		this.kgTell = kgTell;
	}

	@Override
	public String toString() {
		return "Kindergarten [kgAddress=" + kgAddress + ", kgClassName=" + kgClassName + ", kgId=" + kgId + ", kgIdx="
				+ kgIdx + ", kgLat=" + kgLat + ", kgLag=" + kgLag + ", kgName=" + kgName + ", kgNotice=" + kgNotice
				+ ", kgOperateTime=" + kgOperateTime + ", kgPw=" + kgPw + ", kgTell=" + kgTell + "]";
	}

}
