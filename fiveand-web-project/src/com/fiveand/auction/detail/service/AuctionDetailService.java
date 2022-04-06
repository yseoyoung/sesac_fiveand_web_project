package com.fiveand.auction.detail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fiveand.auction.board.dao.AuctionBoardDAO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;

public class AuctionDetailService {
	
	private AuctionBoardDAO auctionBoardDao;
	
	public AuctionDetailService() {
		auctionBoardDao = new AuctionBoardDAO();
	}
	
	public Map<String, Object> selectBoardNo(int no){
		ProductVO product = auctionBoardDao.selectProductByNo(no);
		List<ProductFileVO> files = auctionBoardDao.selectFileByNo(no);
		
		Map<String, Object> data = new HashMap<>();
		data.put("product", product);
		data.put("fileList", files);
		
		return data;
	}
}
