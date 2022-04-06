package com.fiveand.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.mypage.service.MyPageService;
 

public class MyInfoController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		MyPageService service = new MyPageService();
		MemberVO member = service.selectMyInfo(id);
		
		request.setAttribute("member", member);
		
		return "/jsp/member/myInfo.jsp";
	}
}
