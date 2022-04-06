package com.fiveand.mypage.service;

import java.util.List;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.mypage.dao.MyPageDAO;

public class MyPageService {

	private MyPageDAO myInfoDao; //mypagedao 받아와서
	
	public MyPageService() {
		myInfoDao = new MyPageDAO();
	}

	public MemberVO selectMyInfo(String id) {
		MemberVO member = myInfoDao.selectMyInfo(id);
		return member;
	}

	public void updateMyInfo(MemberVO member) {
		myInfoDao.updateMyInfo(member);
	}

	public int deleteMyInfo(MemberVO member) {
		int result = myInfoDao.deleteMyInfo(member);
		return result;
	}

	public int totalProductCnt() {
		
		int totalCount = myInfoDao.totalProductCnt();
		
		return totalCount;
	}

	public List<ProductVO> pagingMyAuction(int currentPage, String id) {
		
		List<ProductVO> list = myInfoDao.pagingMyAuction(currentPage, id);
		
		return list;
	}

	public List<ProductVO> selectMyHeart(int currentPage, String id) {
		
		List<ProductVO> list = myInfoDao.selectMyHeart(currentPage, id);
		
		return list;
	}

	public List<ProductVO> selectMySugg(String id) { 
		  List<ProductVO> list = myInfoDao.selectMySugg(id);
		  //System.out.println("service List : " + list);
		  return list;
	   }
	
	public List<ProductVO> selectMyWin(String id) { 
		  List<ProductVO> list = myInfoDao.selectMyWin(id);
		  //System.out.println("service List : " + list);
		  return list;
	   }
	
	
}

