package com.fiveand.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.mypage.service.MyPageService;

public class UpdateMyInfoController implements Controller {
	
	@Override 
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String id = request.getParameter("id");		
		
		MemberVO member = new MemberVO();
		member.setPwd(pwd);
		member.setPhone(phone);
		member.setEmail(email);
		member.setId(id);
		
		MyPageService service = new MyPageService();
		service.updateMyInfo(member);
		
		return "redirect:/myInfo.do?id="+id;
		
	}
}
