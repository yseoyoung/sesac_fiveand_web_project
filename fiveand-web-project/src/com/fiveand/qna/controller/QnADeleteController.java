package com.fiveand.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.qna.service.QnAService;
import com.fiveand.qna.vo.QnAVO;

public class QnADeleteController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int pdNo = Integer.parseInt(request.getParameter("pdNo"));
		System.out.println("삭제할 qna게시글 번호 : " + bNo);
		System.out.println("돌아갈 제품 번호 : " + pdNo);
		QnAService service = new QnAService();
		
		service.deleteBoard(bNo);
		
		return "redirect:/qna/list.do?no=" + pdNo;
	}

}
