package com.fiveand.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.admin.service.BlindListService;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.controller.Controller;

public class BlindListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BlindListService service = new BlindListService();
		List<ProductVO> list = service.blindList();
		
		request.setAttribute("list", list);
		return "/jsp/admin/blindList.jsp";
	}

}
