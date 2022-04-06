package com.fiveand.auction.payment.service;

import java.util.List;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.auction.payment.dao.PayDAO;

public class PayService {

	private PayDAO payDao;
	
	public PayService() {
		payDao = new PayDAO();
	}

	public ProductVO selectMyWin(String id, int pdNo) { 
		  ProductVO productVO = payDao.selectMyWin(id, pdNo);
		  return productVO;
	   }
	
	public void updatePayment(int pdNo) { 
		  payDao.updatePayment(pdNo);
	   }
	
	
}
