package com.fiveand.qna.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.google.gson.Gson;

public class QnAListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int pdNo = Integer.parseInt(request.getParameter("no"));
		
		QnAService service = new QnAService();
		List<QnAVO> list = service.selectAllBoard(pdNo);
		int totalCount = service.totalCount();
		/*List<QnAVO> list = new ArrayList<>();
		list.add(new QnAVO(1, "aaa", "good", "2022-01-26"));
		list.add(new QnAVO(1, "aaa", "good2", "2022-01-26"));
		*/

		//String json = new Gson().toJson(list);
		//System.out.println(json);

		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		
		
		
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
		
		return "/jsp/detail/detailQnA.jsp?no="+no;
	}

}
