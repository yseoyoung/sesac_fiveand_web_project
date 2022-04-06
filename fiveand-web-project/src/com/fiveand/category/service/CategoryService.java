package com.fiveand.category.service;

import java.util.List;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.category.CategoryDAO;

public class CategoryService {
	
	private CategoryDAO ListDao;
	
	public CategoryService() {
		ListDao = new CategoryDAO();
	}
	
	/**
	 * 1. 전체 물품 수 체크 서비스
	 */
	public int totalProductCnt() {
		
		int totalCount = ListDao.totalProductCnt();
		
		return totalCount;
	}
	
	/**
	 * 2. 페이징 처리한 제품 정보 리스트 가져오기
	 */
	public List<ProductVO> pagingList(int currentPage) {
		
		List<ProductVO> list = ListDao.pagingList(currentPage);
		
		return list;
	}
	

	
	//1. 카테고리-디지털기기
	public List<ProductVO> selectDigital() {
		
		List<ProductVO> list = ListDao.selectDigital();
		
		return list;
	}
	
	//2. 카테고리-생활가전
		public List<ProductVO> selectElectronics() {
			
			List<ProductVO> list = ListDao.selectElectronics();
			
			return list;
		}
	
	//3. 카테고리-가구/인테리어
		public List<ProductVO> selectFurniture() {
				
			List<ProductVO> list = ListDao.selectFurniture();
					
			return list;
		}		
		
	//4. 카테고리-의류잡화
		public List<ProductVO> selectClothes() {
					
			List<ProductVO> list = ListDao.selectClothes();
					
			return list;
				}
	//5. 카테고리-생활가전
		public List<ProductVO> selectBeauty() {
					
			List<ProductVO> list = ListDao.selectBeauty();
					
			return list;
				}		
		
	//6. 카테고리-도서, 음반
		public List<ProductVO> selectBooks() {
			
			List<ProductVO> list = ListDao.selectBooks();
			
			return list;
		}
	
	//7. 카테고리-기타
		public List<ProductVO> selectEtc() {
			
			List<ProductVO> list = ListDao.selectEtc();
			
			return list;
		}
		
		
}
