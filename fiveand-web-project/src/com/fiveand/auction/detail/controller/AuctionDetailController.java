package com.fiveand.auction.detail.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiveand.auction.board.service.AuctionBoardService;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.auction.detail.service.AuctionDetailService;
import com.fiveand.auction.heart.service.AuctionHeartService;
import com.fiveand.auction.suggest.service.AuctionProcessService;
import com.fiveand.auction.suggest.vo.SuggestListVO;
import com.fiveand.controller.Controller;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.qna.service.QnAService;
import com.fiveand.qna.vo.QnAVO;

public class AuctionDetailController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");

		
		
		int no = Integer.parseInt(request.getParameter("no"));
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("userVO");
		System.out.println("로그인 정보 : "+user);

		AuctionDetailService auctionDetailService = new AuctionDetailService();		
		Map<String, Object> result = auctionDetailService.selectBoardNo(no);
		
		ProductVO product = (ProductVO)result.get("product");
		List<ProductFileVO> fileList = (List<ProductFileVO>)result.get("fileList");

		AuctionProcessService auctionProcessService = new AuctionProcessService();
		List<SuggestListVO> suggestList = auctionProcessService.selectSuggestNo(no); 

		boolean isHeart = false;
		if (user != null)
		{
			String id = user.getId();
			System.out.println("디테일 창에 들어온 id : "+id);
			AuctionHeartService auctionHeartService = new AuctionHeartService();
			isHeart = auctionHeartService.checkHeart(id, no);
			System.out.println("마음 찍었는지? : "+isHeart);
		}

		request.setAttribute("product", product);
		request.setAttribute("fileList", fileList);
		request.setAttribute("suggestList", suggestList);
		request.setAttribute("isHeart", isHeart);


		QnAService service = new QnAService();
		List<QnAVO> list = service.selectAllBoard(no);
		int totalCount = service.totalCount();
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		System.out.println(list);


		AuctionBoardService relatedService = new AuctionBoardService();
		List<Object> relatedList = relatedService.relatedList(product.getId());
		request.setAttribute("relatedList", relatedList);
		
		return "/jsp/detail/detail.jsp?no="+no;
	}
}
