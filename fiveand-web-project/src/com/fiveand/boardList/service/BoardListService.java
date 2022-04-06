package com.fiveand.boardList.service;

import java.util.List;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.boardList.dao.BoardListDAO;

public class BoardListService {
	
private BoardListDAO ListDao;

	public BoardListService() {
		ListDao = new BoardListDAO();
	}

	public int totalProductCnt() {
		
		int totalCount = ListDao.totalProductCnt();
		
		return totalCount;
	}

	public List<ProductVO> pagingList(int currentPage) {
		
		List<ProductVO> list = ListDao.pagingList(currentPage);
		
		return list;
	}

	public List<ProductVO> pagingViewList(int currentPage) {
		
		List<ProductVO> list = ListDao.pagingViewList(currentPage);
		
		return list;
	}

	public List<ProductVO> pagingHeartList(int currentPage) {
		
		List<ProductVO> list = ListDao.pagingHeartList(currentPage);
		
		return list;
	}

	public List<Object> selectRecentList() {
	
		List<Object> list = ListDao.selectRecentList();
		
		return list;
	}

	public List<Object> selectViewList() {
		
		List<Object> list = ListDao.selectViewList();
		
		return list;
	}

	public List<Object> selectHeartList() {
		
		List<Object> list = ListDao.selectHeartList();
		
		return list;
	}
}
