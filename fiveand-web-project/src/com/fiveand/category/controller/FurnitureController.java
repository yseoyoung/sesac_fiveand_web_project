package com.fiveand.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.vo.PagingVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.category.service.CategoryService;
import com.fiveand.controller.Controller;

public class FurnitureController implements Controller {
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PagingVO pagingVO = new PagingVO();
		CategoryService service = new CategoryService();
		int totalCount = service.totalProductCnt();
		pagingVO.setTotalCount(totalCount); //전체 물품 수
		
		
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		pagingVO.setCurrentPage(currentPage); //현재 페이지(기본 = 1)
		
		//마지막 페이지, 시작 페이지, 전체 페이지 설정
		pagingVO.setEndPage( ((int) Math.ceil(pagingVO.getCurrentPage() / (double) pagingVO.getDisplayPage())) * pagingVO.getDisplayPage() );
		pagingVO.setBeginPage( pagingVO.getEndPage() - (pagingVO.getDisplayPage() - 1) );
		pagingVO.setTotalPage( (int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()) );
		
		if (pagingVO.getEndPage() > pagingVO.getTotalPage()) {
			pagingVO.setEndPage(pagingVO.getTotalPage());
		} //마지막 페이지 전체 물품이 끝나는 지점으로 재설정
		
		
		List<ProductVO> list = service.selectFurniture();

		
		request.setAttribute("list", list);
		request.setAttribute("pagingVO", pagingVO);
		request.setAttribute("page", pagingVO.getCurrentPage());
		request.setAttribute("beginPage", pagingVO.getBeginPage());
		request.setAttribute("endPage", pagingVO.getEndPage());
		request.setAttribute("totalPage", pagingVO.getTotalPage());
		request.setAttribute("displayPage", pagingVO.getDisplayPage());

		return "/jsp/category/furniture.jsp";
		
		
		}
}
