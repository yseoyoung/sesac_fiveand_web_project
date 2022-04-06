package com.fiveand.signup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.signup.service.SignupService;

public class SignupProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		MemberVO memberVo = new MemberVO();
		memberVo.setId(id);
		memberVo.setPwd(pwd);
		memberVo.setName(name);
		memberVo.setPhone(phone);
		memberVo.setEmail(email);

		SignupService service = new SignupService();
		service.addMember(memberVo);

		
		return "redirect:/login.do";
	}

}
