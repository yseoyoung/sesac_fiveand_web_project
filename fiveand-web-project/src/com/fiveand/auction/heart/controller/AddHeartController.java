package com.fiveand.auction.heart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.heart.service.AuctionHeartService;
import com.fiveand.auction.heart.vo.HeartVO;
import com.fiveand.controller.Controller;

public class AddHeartController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		int pdNo = Integer.parseInt(request.getParameter("pdNo"));
		HeartVO heart = new HeartVO(id, pdNo);
		
		AuctionHeartService service = new AuctionHeartService();
		int result = service.addHeart(heart);
		
		request.setAttribute("result", result);
		
		return "/jsp/auction/heartResult.jsp";
	}

}
