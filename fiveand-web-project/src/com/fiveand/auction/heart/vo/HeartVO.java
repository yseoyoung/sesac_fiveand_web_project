package com.fiveand.auction.heart.vo;

public class HeartVO {
	private String id;
	private int pdNo;

	public HeartVO() {
		super();
	}

	public HeartVO(String id, int pdNo) {
		super();
		this.id = id;
		this.pdNo = pdNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPdNo() {
		return pdNo;
	}

	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}

	@Override
	public String toString() {
		return "HeartVO [id=" + id + ", pdNo=" + pdNo + "]";
	}
	
}
