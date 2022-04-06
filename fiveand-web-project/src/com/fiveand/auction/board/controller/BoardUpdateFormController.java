package com.fiveand.auction.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.auction.detail.service.AuctionDetailService;
import com.fiveand.auction.suggest.service.AuctionProcessService;
import com.fiveand.auction.suggest.vo.SuggestListVO;
import com.fiveand.controller.Controller;

public class BoardUpdateFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("no"));
		AuctionDetailService auctionDetailService = new AuctionDetailService();
		
		Map<String, Object> result = auctionDetailService.selectBoardNo(no);
		
		ProductVO product = (ProductVO)result.get("product");
		
		request.setAttribute("product", product);		
		
		return "/jsp/auction/updateForm.jsp";
	}

}
