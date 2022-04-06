package com.fiveand.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.admin.service.BlindListService;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.controller.Controller;

public class RemoveController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int pdNo = Integer.parseInt(request.getParameter("no"));
		String id = request.getParameter("id");
		BlindListService service = new BlindListService();
		
		ProductVO product = new ProductVO();
		product.setPdNo(pdNo);
		product.setId(id);
		service.insertBlind(product);
		return "redirect:/main.do";
	}

}
