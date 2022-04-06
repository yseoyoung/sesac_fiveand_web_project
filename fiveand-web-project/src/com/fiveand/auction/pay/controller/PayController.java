package com.fiveand.auction.pay.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.auction.payment.service.PayService;
import com.fiveand.controller.Controller;

public class PayController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		int pdNo = Integer.parseInt(request.getParameter("pdNo"));
		
		PayService service = new PayService();
		ProductVO product = service.selectMyWin(id,pdNo);
		
		
		request.setAttribute("product", product);
		
		return "/jsp/pay/kakao.jsp";
	}

}
