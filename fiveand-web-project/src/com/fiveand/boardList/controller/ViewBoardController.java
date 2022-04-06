package com.fiveand.boardList.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.vo.PagingVO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.boardList.service.BoardListService;
import com.fiveand.controller.Controller;

public class ViewBoardController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PagingVO pagingVO = new PagingVO();
		BoardListService service = new BoardListService();
		int totalCount = service.totalProductCnt();
		pagingVO.setTotalCount(totalCount);
		
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		pagingVO.setCurrentPage(currentPage);

		pagingVO.setEndPage( ((int) Math.ceil(pagingVO.getCurrentPage() / (double) pagingVO.getDisplayPage())) * pagingVO.getDisplayPage() );
		pagingVO.setBeginPage( pagingVO.getEndPage() - (pagingVO.getDisplayPage() - 1) );
		pagingVO.setTotalPage( (int) Math.ceil(pagingVO.getTotalCount() / (double) pagingVO.getDisplayRow()) );
		
		if (pagingVO.getEndPage() > pagingVO.getTotalPage()) {
			pagingVO.setEndPage(pagingVO.getTotalPage());
		} 
		
		
		List<ProductVO> viewList = service.pagingViewList(currentPage);

		
		request.setAttribute("viewList", viewList);
		request.setAttribute("pagingVO", pagingVO);
		request.setAttribute("page", pagingVO.getCurrentPage());
		request.setAttribute("beginPage", pagingVO.getBeginPage());
		request.setAttribute("endPage", pagingVO.getEndPage());
		request.setAttribute("totalPage", pagingVO.getTotalPage());
		request.setAttribute("displayPage", pagingVO.getDisplayPage());

		return "/jsp/boardList/viewList.jsp";
	}
}
