package com.fiveand.auction.board.vo;

public class ProductFileVO {
	private int no; // 제품 시퀀스 번호
	private int pdNo; // 제품번호
	private String fileOriName; // 파일 오리지널 이름
	private String fileSaveName; // 파일 저장명
	private int fileSize; // 파일 크기
	
	// 생성자
	public ProductFileVO() {
		
	}
	
	public ProductFileVO(int pdNo, String fileSaveName) {
		super();
		this.pdNo = pdNo;
		this.fileSaveName = fileSaveName;
	}

	public ProductFileVO(int no, int pdNo, String fileOriName, String fileSaveName, int fileSize) {
		super();
		this.no = no;
		this.pdNo = pdNo;
		this.fileOriName = fileOriName;
		this.fileSaveName = fileSaveName;
		this.fileSize = fileSize;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPdNo() {
		return pdNo;
	}

	public void setPdNo(int pdNo) {
		this.pdNo = pdNo;
	}

	public String getFileOriName() {
		return fileOriName;
	}

	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}

	public String getFileSaveName() {
		return fileSaveName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	// toString
	@Override
	public String toString() {
		return "ProductFileVO [no=" + no + ", pdNo=" + pdNo + ", fileOriName=" + fileOriName + ", fileSaveName="
				+ fileSaveName + ", fileSize=" + fileSize + "]";
	}
	
}
