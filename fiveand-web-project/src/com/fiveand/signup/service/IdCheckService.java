package com.fiveand.signup.service;

import com.fiveand.member.vo.MemberVO;
import com.fiveand.signup.dao.SignupDAO;

public class IdCheckService {

	private SignupDAO signupDao;
	
	public IdCheckService() {
		signupDao = new SignupDAO();
	}
	
	public int checkId(MemberVO memberVo) {
		int result = signupDao.checkId(memberVo);
		
		return result;
	}
}
