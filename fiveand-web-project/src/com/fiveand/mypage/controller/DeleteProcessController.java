package com.fiveand.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.mypage.service.MyPageService;

public class DeleteProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO member = new MemberVO();
		
		member.setId(id);
		member.setPwd(pwd);
		
		MyPageService service = new MyPageService();
		int result = service.deleteMyInfo(member);
		
		String msg="";
		String url="";
		if(result == 0) {
			msg = "패스워드가 일치하지 않습니다.";
			url = "/deleteMyInfoForm.do?id=" + id;
		} else {
			msg = "회원 탈퇴가 완료되었습니다.";
			url = "/main.do";
			HttpSession session = request.getSession();
			session.invalidate();
			}

		return "redirect:" + url;
	}

}
