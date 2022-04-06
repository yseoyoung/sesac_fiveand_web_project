package com.fiveand.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fiveand.controller.Controller;
import com.fiveand.qna.service.QnAService;
import com.fiveand.qna.vo.QnAVO;

public class QnAReplyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int pdNo = Integer.parseInt(request.getParameter("pdNo"));
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		int pos = Integer.parseInt(request.getParameter("pos"));
		
		//System.out.println("[[답글]]"+id+","+title+","+content+","+pdNo+","+groupId+","+depth+","+pos);
		
		QnAVO qna = new QnAVO();
		qna.setTitle(title);
		qna.setId(id);
		qna.setContent(content);
		qna.setPdNo(pdNo);
		qna.setGroupId(groupId);
		qna.setDepth(depth);
		qna.setPos(pos);
		
		QnAService service = new QnAService();
		
		service.replyBoard(qna);
		
		return "redirect:/auction/detail.do?no=" + pdNo;
	}

}
