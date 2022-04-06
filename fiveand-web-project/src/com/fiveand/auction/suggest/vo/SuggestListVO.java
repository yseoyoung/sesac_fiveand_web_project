package com.fiveand.auction.suggest.vo;

public class SuggestListVO {
	private int aNo; // 옥션넘버(pk)
	private int pdNo; // 제품번호(fk)
	private String id; // 제시 아이디(fk)
	private int sugPrice; // 제시가
	private String sugDate; //제시한 날
	
	
	// 생성자
	public SuggestListVO() {
		super();
	}

	public SuggestListVO(int aNo, int pdNo, String id, int sugPrice, String sugDate) {
		super();
		this.aNo = aNo;
		this.pdNo = pdNo;
		this.id = id;
		this.sugPrice = sugPrice;
		this.sugDate = sugDate;
	}
	
	// getter, setter
	public int getaNo() {
		return aNo;
	}

	public void setaNo(int aNo) {
		this.aNo = aNo;
	}

	public int getPdNo() {
		return pdNo;
	}

	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSugPrice() {
		return sugPrice;
	}

	public void setSugPrice(int sugPrice) {
		this.sugPrice = sugPrice;
	}

	public String getSugDate() {
		return sugDate;
	}

	public void setSugDate(String sugDate) {
		this.sugDate = sugDate;
	}

	// toString
	@Override
	public String toString() {
		return "SuggestListVO [aNo=" + aNo + ", pdNo=" + pdNo + ", id=" + id + ", sugPrice=" + sugPrice + ", sugDate="
				+ sugDate + "]";
	}
	
}
