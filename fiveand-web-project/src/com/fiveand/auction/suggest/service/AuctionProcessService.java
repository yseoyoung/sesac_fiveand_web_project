package com.fiveand.auction.suggest.service;

import java.util.ArrayList;
import java.util.List;

import com.fiveand.auction.detail.dao.AuctionSuggestDAO;
import com.fiveand.auction.suggest.vo.SuggestListVO;

public class AuctionProcessService {
	
	private AuctionSuggestDAO auctionSuggestDao;
	
	public AuctionProcessService() {
		auctionSuggestDao = new AuctionSuggestDAO();
	}

	public List<SuggestListVO> selectSuggestNo(int no) {
		
		List<SuggestListVO> suggestList = auctionSuggestDao.selectSuggestNo(no);		
		
		return suggestList;
	}
	
	public void addSuggest(SuggestListVO suggest) {
		
		auctionSuggestDao.insertSuggest(suggest);
		
	}
	
}
