package com.fiveand.auction.board.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.service.MainBoardListService;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.controller.Controller;

public class MainBoardListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MainBoardListService service = new MainBoardListService();
		List<Object> list = service.selectRecentList();

		// 0, 2, 4, 6, 8
		ProductVO[] recentList = new ProductVO[5];
		// 1, 3, 5, 7, 9
		ProductFileVO[] recentFileList = new ProductFileVO[5];
		
		for(int i = 0, j = 0; i < list.size(); i += 2, j++) {
			recentList[j] = (ProductVO)list.get(i);
			recentFileList[j] = (ProductFileVO)list.get(i+1);
		}
		
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
	
		request.setAttribute("recentList", recentList);
		request.setAttribute("recentFileList", recentFileList);

		List<ProductVO> sugList = service.selectTopSugList();
		request.setAttribute("sugList", sugList);

		List<ProductVO> topLike = service.selectTopLike();
		request.setAttribute("topLike", topLike);

		List<ProductVO> today = service.selectToday();
		request.setAttribute("today", today);
		
		List<ProductVO> deadLine = service.selectDeadLine();
		request.setAttribute("deadLine", deadLine);
		
		return "/main.jsp";
	}
}
