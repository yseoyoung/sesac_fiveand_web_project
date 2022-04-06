package com.fiveand.auction.heart.service;

import com.fiveand.auction.heart.dao.AuctionHeartDAO;
import com.fiveand.auction.heart.vo.HeartVO;

public class AuctionHeartService {
	private AuctionHeartDAO heartDao;
	
	public AuctionHeartService() {
		heartDao = new AuctionHeartDAO();
	}
	public boolean checkHeart(String id, int no) {
		
		boolean result = heartDao.checkHeart(id, no);
		
		return result;
	}

	public int addHeart(HeartVO heart) {
		int result = heartDao.addHeart(heart);
		
		return result;
	}
	
	public int cancleHeart(HeartVO heart) {
		int result = heartDao.cancleHeart(heart);
		
		return result;
	}
}
