package com.fiveand.search.service;

import java.util.List;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.search.dao.SearchDAO;

public class SearchService {

	private SearchDAO searchDao;

	public SearchService() {
		searchDao = new SearchDAO();
	}

	public int totalProductCnt() {
		
		int totalCount = searchDao.totalProductCnt();
		
		return totalCount;
	}
	
	public List<ProductVO> searchList(int currentPage, String findStr) {
		
		List<ProductVO> list = searchDao.searchList(currentPage, findStr);
		System.out.println(list);
		return list;
	}
	

}
