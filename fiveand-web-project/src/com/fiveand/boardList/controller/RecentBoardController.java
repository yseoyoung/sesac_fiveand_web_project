package com.fiveand.boardList.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.service.MainBoardListService;
import com.fiveand.auction.board.vo.PagingVO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.boardList.service.BoardListService;
import com.fiveand.controller.Controller;

public class RecentBoardController implements Controller {
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
		
		
		List<ProductVO> list = service.pagingList(currentPage);

		// 0, 2, 4, 6, 8
		//List<ProductVO> recentList = new ArrayList<ProductVO>();
		// 1, 3, 5, 7, 9
		//List<ProductFileVO> recentFileList = new ArrayList<ProductFileVO>();
		
//		for(int i = 0, j = 0; i < list.size(); i += 2, j++) {
//			recentList.add((ProductVO)list.get(i));
//			recentFileList.add((ProductFileVO)list.get(i+1));
//		}
		
		//System.out.println(Arrays.toString(recentList));
		//System.out.println(Arrays.toString(recentFileList));
		
		//[ProductVO [pdNo=0, id=null, pdName=입력테스트2, hopePrice=0, startPrice=1200, regDate=null, dueDate=2022-01-27 00:00:00, pdSimpleInfo=null, pdInfo=null, cNo=1, viewCnt=0, likeCnt=0, sugCnt=0], 
		//ProductVO [pdNo=0, id=null, pdName=dsf, hopePrice=0, startPrice=1000, regDate=null, dueDate=2022-01-26 00:00:00, pdSimpleInfo=null, pdInfo=null, cNo=3, viewCnt=0, likeCnt=0, sugCnt=0], 
		//ProductVO [pdNo=0, id=null, pdName=sfdsfd, hopePrice=0, startPrice=543, regDate=null, dueDate=2022-01-26 00:00:00, pdSimpleInfo=null, pdInfo=null, cNo=1, viewCnt=0, likeCnt=0, sugCnt=0], 
		//ProductVO [pdNo=0, id=null, pdName=sfdfsd, hopePrice=0, startPrice=1000, regDate=null, dueDate=2022-01-26 00:00:00, pdSimpleInfo=null, pdInfo=null, cNo=1, viewCnt=0, likeCnt=0, sugCnt=0], 
		//ProductVO [pdNo=0, id=null, pdName=sdfsfd, hopePrice=0, startPrice=1000, regDate=null, dueDate=2022-01-26 00:00:00, pdSimpleInfo=null, pdInfo=null, cNo=1, viewCnt=0, likeCnt=0, sugCnt=0]]
		
		//[ProductFileVO [no=0, pdNo=13, fileOriName=null, fileSaveName=sesac519838e8-285c-4597-b1d3-5024f0407a70.png, fileSize=0], 
		//ProductFileVO [no=0, pdNo=14, fileOriName=null, fileSaveName=null, fileSize=0], 
		//ProductFileVO [no=0, pdNo=15, fileOriName=null, fileSaveName=null, fileSize=0], 
		//ProductFileVO [no=0, pdNo=17, fileOriName=null, fileSaveName=null, fileSize=0], 
		//ProductFileVO [no=0, pdNo=16, fileOriName=null, fileSaveName=null, fileSize=0]]
		
		request.setAttribute("list", list);
		//request.setAttribute("recentList", recentList);
		//request.setAttribute("recentFileList", recentFileList);
		request.setAttribute("pagingVO", pagingVO);
		request.setAttribute("page", pagingVO.getCurrentPage());
		request.setAttribute("beginPage", pagingVO.getBeginPage());
		request.setAttribute("endPage", pagingVO.getEndPage());
		request.setAttribute("totalPage", pagingVO.getTotalPage());
		request.setAttribute("displayPage", pagingVO.getDisplayPage());

		return "/jsp/boardList/recentList.jsp";
	}
}
