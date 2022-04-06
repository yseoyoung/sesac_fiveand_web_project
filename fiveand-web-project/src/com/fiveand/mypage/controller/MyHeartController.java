package com.fiveand.mypage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.vo.PagingVO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.mypage.service.MyPageService;

public class MyHeartController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");

		PagingVO pagingVO = new PagingVO();
		MyPageService service = new MyPageService();
		int totalCount = service.totalProductCnt();
		pagingVO.setTotalCount(totalCount);

		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		pagingVO.setCurrentPage(currentPage);

		pagingVO.setEndPage(((int) Math.ceil(pagingVO.getCurrentPage() / (double) pagingVO.getDisplayPage()))
				* pagingVO.getDisplayPage());
		pagingVO.setBeginPage(pagingVO.getEndPage() - (pagingVO.getDisplayPage() - 1));
		pagingVO.setTotalPage((int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()));

		if (pagingVO.getEndPage() > pagingVO.getTotalPage()) {
			pagingVO.setEndPage(pagingVO.getTotalPage());
		}

		List<ProductVO> list = service.selectMyHeart(currentPage, id);

		request.setAttribute("pagingVO", pagingVO);
		request.setAttribute("page", pagingVO.getCurrentPage());
		request.setAttribute("beginPage", pagingVO.getBeginPage());
		request.setAttribute("endPage", pagingVO.getEndPage());
		request.setAttribute("totalPage", pagingVO.getTotalPage());
		request.setAttribute("displayPage", pagingVO.getDisplayPage());
		request.setAttribute("list", list);

		return "/jsp/member/myHeart.jsp";

	}
}
