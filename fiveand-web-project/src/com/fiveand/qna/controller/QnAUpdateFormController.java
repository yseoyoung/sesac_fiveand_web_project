package com.fiveand.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.qna.service.QnAService;
import com.fiveand.qna.vo.QnAVO;

public class QnAUpdateFormController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		System.out.println("수정할 qna게시글 번호 : " + bNo);
		QnAService service = new QnAService();
		
		QnAVO update = service.updateFormBoard(bNo);
		
		request.setAttribute("update", update);
	
		return "/jsp/qna/updateForm.jsp";
	}

}
