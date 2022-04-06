package com.fiveand.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.admin.service.MemberListService;
import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;

public class MemberListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberListService service = new MemberListService();
		List<MemberVO> list = service.selectAllMember();
		
		request.setAttribute("list", list);
		
		return "/jsp/admin/memberList.jsp";
	}

}
