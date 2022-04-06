package com.fiveand.auction.suggest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.suggest.service.AuctionProcessService;
import com.fiveand.auction.suggest.vo.SuggestListVO;
import com.fiveand.controller.Controller;

public class AuctionSuggestController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("pdNo"));
		int sugPrice = Integer.parseInt(request.getParameter("sugPrice"));
		String id = request.getParameter("id");
		
		SuggestListVO suggest = new SuggestListVO();
		suggest.setPdNo(no);
		suggest.setSugPrice(sugPrice);
		suggest.setId(id);
		
		AuctionProcessService service = new AuctionProcessService();
		service.addSuggest(suggest);
		
		return "redirect:/auction/detail.do?no="+no;
	}

}
