package com.fiveand.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.qna.service.QnAService;
import com.fiveand.qna.vo.QnAVO;

public class QnAUpdateController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int pdNo = Integer.parseInt(request.getParameter("pdNo"));
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		System.out.println("수정할 내용 title : "+ title+",id : "+id+", content : "+content+", no : "+pdNo);
		
		QnAVO qna = new QnAVO();
		qna.setTitle(title);
		qna.setId(id);
		qna.setContent(content);
		qna.setPdNo(pdNo);
		qna.setbNo(bNo);
		
		QnAService service = new QnAService();
		
		service.updateBoard(qna);
		
		return "redirect:/qna/list.do?no=" + pdNo;
	}

}
