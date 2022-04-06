package com.fiveand.auction.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.service.AuctionBoardService;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.controller.Controller;

public class BoardUpdateController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		String pdName = request.getParameter("pdName");
		int pdNo = Integer.parseInt(request.getParameter("pdNo"));
		String id = request.getParameter("id");
		String pdSimpleInfo = request.getParameter("pdSimpleInfo");
		String pdInfo = request.getParameter("pdInfo");
		int cNo = Integer.parseInt(request.getParameter("cNo"));
		
		ProductVO product = new ProductVO();
		product.setPdName(pdName);
		product.setId(id);
		product.setPdNo(pdNo);
		product.setPdSimpleInfo(pdSimpleInfo);
		product.setPdInfo(pdInfo);
		product.setcNo(cNo);
		
		AuctionBoardService service = new AuctionBoardService();
		service.updateBoard(product);
		
		return "redirect:/auction/detail.do?no="+pdNo;
	}

}
