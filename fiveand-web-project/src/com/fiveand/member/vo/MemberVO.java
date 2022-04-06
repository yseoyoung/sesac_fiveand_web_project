package com.fiveand.member.vo;

public class MemberVO {

	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private String type;  //'A':관리자, 'U':일반사용자
	private int warningCnt;  //경고수 default:0
	
	public MemberVO() {
		super();
	}

	public MemberVO(String id, String pwd, String name, String phone, String email, String type, int warningCnt) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.type = type;
		this.warningCnt = warningCnt;
	}
	
	//로그인
	public MemberVO(String id, String pwd, String type) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.type = type;
	}

	//회원가입
	public MemberVO(String id, String pwd, String name, String phone, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	//관리자 회원 조회
	public MemberVO(String id, String name, String phone, String email, int warningCnt) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.warningCnt = warningCnt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWarningCnt() {
		return warningCnt;
	}

	public void setWarningCnt(int warningCnt) {
		this.warningCnt = warningCnt;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", type=" + type + ", warningCnt=" + warningCnt + "]";
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return pwd;
	}
	
}
