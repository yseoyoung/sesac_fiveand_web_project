package com.fiveand.auction.detail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.service.AuctionBoardService;
import com.fiveand.controller.Controller;

public class ViewCntController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int no = Integer.parseInt(request.getParameter("no"));
		AuctionBoardService service = new AuctionBoardService();
		
		service.addViewCnt(no);
		return "redirect:/auction/detail.do?no="+no;
	}

}
