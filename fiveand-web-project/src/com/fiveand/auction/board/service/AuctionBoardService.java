package com.fiveand.auction.board.service;

import java.util.List;

import com.fiveand.auction.board.dao.AuctionBoardDAO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;

public class AuctionBoardService {
	
	private AuctionBoardDAO auctionBoardDao;

	public AuctionBoardService() {
		auctionBoardDao = new AuctionBoardDAO();
	}

	public int insertBoard(ProductVO product, List<ProductFileVO> fileList) {
		
		// 제품번호 설정
		int pdNo = auctionBoardDao.selectProductNo();
		product.setPdNo(pdNo);
		
		// ftbl_product에 저장
		auctionBoardDao.insertProduct(product);
		
		// ftbl_product_file에 저장
		for (ProductFileVO fileVO : fileList) {
			fileVO.setPdNo(pdNo);
			auctionBoardDao.insertFile(fileVO);
		}
		return pdNo;
	}

	public void addViewCnt(int pdNo) {
		auctionBoardDao.addViewCnt(pdNo);
	}

	public void updateBoard(ProductVO product) {
		auctionBoardDao.updateProduct(product);
	}
	
	public List<Object> relatedList(String id) {
		List<Object> list = auctionBoardDao.relatedList(id);
		return list;
	}
}
