package com.fiveand.login.service;

import com.fiveand.login.dao.LoginDAO;
import com.fiveand.member.vo.MemberVO;

public class LoginService {
	private LoginDAO loginDao;
	
	public LoginService() {
		loginDao = new LoginDAO();
	}
	
	public MemberVO login(MemberVO memberVO) {
		return loginDao.login(memberVO);
	}
	
	public int checkWinBid(MemberVO user) {
		return loginDao.checkWindBid(user);
	}
}
