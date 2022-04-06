package com.fiveand.qna.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fiveand.qna.dao.QnADAO;
import com.fiveand.qna.vo.QnAVO;

public class QnAService {

	private QnADAO dao;

	public QnAService() {
		dao = new QnADAO();
	}

	public List<QnAVO> selectAllBoard(int pdNo){
		List<QnAVO> list = dao.selectAllBoard(pdNo);
		
		return list;
	}
	public List<QnAVO> selectPagingBoard(int pdNo, int currentPage) {


		List<QnAVO> list = dao.selectPagingBoard(pdNo,currentPage);
		//System.out.println(list);
		return list;
	}

	public int totalCount() {

		int totalCount = dao.totalCount();
		return totalCount;
	}

	public QnAVO detailBoard(int bNo) {

		QnAVO qna = dao.detailBoard(bNo);
		return qna;
	}

	public void insertBoard(QnAVO qna) {

		dao.insertBoard(qna);
	}

	public QnAVO updateFormBoard(int bNo) {
		QnAVO qna = dao.updateFormBoard(bNo);
		return qna;
	}

	public void updateBoard(QnAVO qna) {
		
		dao.updateBoard(qna);
	}

	public void deleteBoard(int bNo) {

		dao.deleteBoard(bNo);
	}
	
	public void replyBoard(QnAVO qna) {
		dao.upPos(qna.getGroupId(), qna.getPos());
		dao.replyboard(qna);
	}
	
	
	
}
