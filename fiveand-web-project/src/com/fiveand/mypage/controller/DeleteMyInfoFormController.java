package com.fiveand.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;

public class DeleteMyInfoFormController implements Controller {

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// return "redirect:/myInfo.do?id="+id;
		return "/jsp/member/deleteMyInfo.jsp";

	}
}