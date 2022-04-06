package com.fiveand.auction.board.service;

import java.util.List;
import java.util.Set;

import com.fiveand.auction.board.dao.MainBoardListDAO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;

public class MainBoardListService {
	private MainBoardListDAO ListDao;

	public MainBoardListService() {
		ListDao = new MainBoardListDAO();
	}

	public List<Object> selectRecentList() {
	
		List<Object> list = ListDao.selectRecentList();
		
		return list;
	}

	public List<ProductVO> selectTopSugList() {
		
		List<ProductVO> sugList = ListDao.selectTopSugList();
		
		return sugList;
	}

	public List<ProductVO> selectTopLike() {
		
		List<ProductVO> topLike = ListDao.selectTopLike();
		
		return topLike;
	}

	public List<ProductVO> selectToday() {
		
		List<ProductVO> today = ListDao.selectToday();
		
		return today;
	}
	
	public List<ProductVO> selectDeadLine() {
		
		List<ProductVO> deadLine = ListDao.selectDeadLine();
		
		return deadLine;
	}
}
