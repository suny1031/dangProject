package com.dang.map.model.vo;

public class Service {
	private String kgName;
	private int svIdx;
	private int isAcademy;
	private int isCafe;
	private int isHotel;
	private int isKg;
	private int isMedic;
	private int isPickup;
	private int isSpa;

	public Service() {
	}

	public Service(String kgName, int svIdx, int isAcademy, int isCafe, int isHotel, int isKg, int isMedic,
			int isPickup, int isSpa) {
		super();
		this.kgName = kgName;
		this.svIdx = svIdx;
		this.isAcademy = isAcademy;
		this.isCafe = isCafe;
		this.isHotel = isHotel;
		this.isKg = isKg;
		this.isMedic = isMedic;
		this.isPickup = isPickup;
		this.isSpa = isSpa;
	}

	public String getKgName() {
		return kgName;
	}

	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	public int getSvIdx() {
		return svIdx;
	}

	public void setSvIdx(int svIdx) {
		this.svIdx = svIdx;
	}

	public int getIsAcademy() {
		return isAcademy;
	}

	public void setIsAcademy(int isAcademy) {
		this.isAcademy = isAcademy;
	}

	public int getIsCafe() {
		return isCafe;
	}

	public void setIsCafe(int isCafe) {
		this.isCafe = isCafe;
	}

	public int getIsHotel() {
		return isHotel;
	}

	public void setIsHotel(int isHotel) {
		this.isHotel = isHotel;
	}

	public int getIsKg() {
		return isKg;
	}

	public void setIsKg(int isKg) {
		this.isKg = isKg;
	}

	public int getIsMedic() {
		return isMedic;
	}

	public void setIsMedic(int isMedic) {
		this.isMedic = isMedic;
	}

	public int getIsPickup() {
		return isPickup;
	}

	public void setIsPickup(int isPickup) {
		this.isPickup = isPickup;
	}

	public int getIsSpa() {
		return isSpa;
	}

	public void setIsSpa(int isSpa) {
		this.isSpa = isSpa;
	}

	@Override
	public String toString() {
		return "Service [kgName=" + kgName + ", svIdx=" + svIdx + ", isAcademy=" + isAcademy + ", isCafe=" + isCafe
				+ ", isHotel=" + isHotel + ", isKg=" + isKg + ", isMedic=" + isMedic + ", isPickup=" + isPickup
				+ ", isSpa=" + isSpa + "]";
	}

}
