package com.fiveand.mypage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.controller.Controller;
import com.fiveand.mypage.service.MyPageService;
import com.fiveand.mypage.service.MyPageService2;

public class MySuggestController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		//System.out.println("로그인 중인 아이디: " +id);
		
		MyPageService service = new MyPageService();
		List<ProductVO> suggList = service.selectMySugg(id);
		List<ProductVO> winList = service.selectMyWin(id);
		
		
		request.setAttribute("suggList", suggList);
		request.setAttribute("winList", winList);
		
		
		return "/jsp/member/mySuggList.jsp";
	}

}
