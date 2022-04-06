package com.fiveand.auction.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.auction.payment.service.PayService;
import com.fiveand.controller.Controller;

public class PayCompController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int pdNo = Integer.parseInt(request.getParameter("pdNo"));
		
		PayService service = new PayService();
		service.updatePayment(pdNo);
		
		return "/jsp/pay/payComplete.jsp";
	}

}
