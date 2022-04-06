package com.fiveand.signup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.signup.service.IdCheckService;

public class IdCheckController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		
		IdCheckService service = new IdCheckService();
		int result = service.checkId(memberVO);

		
		request.setAttribute("result", result);
		
		return "/jsp/member/result.jsp";
	}

}
