package com.fiveand.boardList.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fiveand.auction.board.vo.PagingVO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class BoardListDAO {

	public int totalProductCnt() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int totalProduct = 0; //전체 글 수 
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from (select * from ftbl_product order by pd_no desc) f ");
			pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			totalProduct = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return totalProduct;
	}

	public List<ProductVO> pagingList(int currentPage){
		
		PagingVO paging = new PagingVO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int startNum = ((currentPage - 1) * paging.getDisplayRow()) + 1;
        int endNum = currentPage * paging.getDisplayRow();
        
        try {
			
        	conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select rownum as row_num, board.* from ( select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt   ");
			sql.append(" from ftbl_product p, ( select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, ftbl_category c ");
			sql.append(" where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no ");
			sql.append(" order by pd_no desc) board) ");
			sql.append(" where row_num >= ? and row_num <= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				ProductFileVO productFVO = new ProductFileVO();
				
				//System.out.println("[[[[" + rs.getString("due_date") + "]");
				
				productVO.setPdNo(rs.getInt("pd_no"));
				productVO.setPdName(rs.getString("pd_name"));
				productVO.setStartPrice(rs.getInt("start_price"));
				productVO.setDueDate(rs.getString("due_date"));
				productVO.setcNo(rs.getInt("c_no"));
				productVO.setcName(rs.getString("category"));
				productVO.setFileSaveName(rs.getString("file_save_name"));
				productVO.setViewCnt(rs.getInt("view_cnt"));
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				
				//productFVO.setPdNo(rs.getInt("pd_no"));
				//productFVO.setFileSaveName(rs.getString("file_save_name"));
				
				list.add(productVO); //0, 2, 4, 6, 8
				//list.add(productFVO); //1, 3, 5, 7, 9
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
        
		return list;
	}

	public List<ProductVO> pagingViewList(int currentPage){
		
		PagingVO paging = new PagingVO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int startNum = ((currentPage - 1) * paging.getDisplayRow()) + 1;
        int endNum = currentPage * paging.getDisplayRow();
        
        try {
			
        	conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select rownum as row_num, board.* from ( select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt   ");
			sql.append(" from ftbl_product p, ( select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, ftbl_category c ");
			sql.append(" where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no ");
			sql.append(" order by view_cnt desc) board) ");
			sql.append(" where row_num >= ? and row_num <= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				
				productVO.setPdNo(rs.getInt("pd_no"));
				productVO.setPdName(rs.getString("pd_name"));
				productVO.setStartPrice(rs.getInt("start_price"));
				productVO.setDueDate(rs.getString("due_date"));
				productVO.setcNo(rs.getInt("c_no"));
				productVO.setcName(rs.getString("category"));
				productVO.setFileSaveName(rs.getString("file_save_name"));
				productVO.setViewCnt(rs.getInt("view_cnt"));
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				
				list.add(productVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
        
		return list;
	}

	public List<ProductVO> pagingHeartList(int currentPage){
		
		PagingVO paging = new PagingVO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int startNum = ((currentPage - 1) * paging.getDisplayRow()) + 1;
        int endNum = currentPage * paging.getDisplayRow();
        
        try {
			
        	conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select rownum as row_num, board.* from ( select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt   ");
			sql.append(" from ftbl_product p, ( select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, ftbl_category c ");
			sql.append(" where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no ");
			sql.append(" order by like_cnt desc) board) ");
			sql.append(" where row_num >= ? and row_num <= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				
				productVO.setPdNo(rs.getInt("pd_no"));
				productVO.setPdName(rs.getString("pd_name"));
				productVO.setStartPrice(rs.getInt("start_price"));
				productVO.setDueDate(rs.getString("due_date"));
				productVO.setcNo(rs.getInt("c_no"));
				productVO.setcName(rs.getString("category"));
				productVO.setFileSaveName(rs.getString("file_save_name"));
				productVO.setViewCnt(rs.getInt("view_cnt"));
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				
				list.add(productVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
        
		return list;
	}

	public List<Object> selectRecentList(){
			
			List<Object> list = new ArrayList<Object>();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt  ");
			sql.append("  from ftbl_product p, ( ");
			sql.append("  select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name ");
			sql.append("  from  ftbl_product_file) f, ftbl_category c ");
			sql.append("  where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no  ");
			sql.append("  order by reg_date desc ");
			
			try(
					Connection conn = new ConnectionFactory().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					ProductVO productVO = new ProductVO();
					ProductFileVO productFVO = new ProductFileVO();
					
					//System.out.println("[[[[" + rs.getString("due_date") + "]");
					
					productVO.setPdNo(rs.getInt("pd_no"));
					productVO.setPdName(rs.getString("pd_name"));
					productVO.setStartPrice(rs.getInt("start_price"));
					productVO.setDueDate(rs.getString("due_date"));
					productVO.setcNo(rs.getInt("c_no"));
					productVO.setcName(rs.getString("category"));
					productVO.setViewCnt(rs.getInt("view_cnt"));
					productVO.setLikeCnt(rs.getInt("like_cnt"));
					
					productFVO.setPdNo(rs.getInt("pd_no"));
					productFVO.setFileSaveName(rs.getString("file_save_name"));
					
					list.add(productVO); //0, 2, 4, 6, 8
					list.add(productFVO); //1, 3, 5, 7, 9
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

	public List<Object> selectViewList(){
		
		List<Object> list = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.view_cnt, p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt  ");
		sql.append("  from ftbl_product p, ( ");
		sql.append("  select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name ");
		sql.append("  from  ftbl_product_file) f, ftbl_category c ");
		sql.append("  where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no  ");
		sql.append("  order by view_cnt desc ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				ProductFileVO productFVO = new ProductFileVO();
				
				
				productVO.setPdNo(rs.getInt("pd_no"));
				productVO.setPdName(rs.getString("pd_name"));
				productVO.setStartPrice(rs.getInt("start_price"));
				productVO.setDueDate(rs.getString("due_date"));
				productVO.setcNo(rs.getInt("c_no"));
				productVO.setcName(rs.getString("category"));
				productVO.setViewCnt(rs.getInt("view_cnt"));
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				
				productFVO.setPdNo(rs.getInt("pd_no"));
				productFVO.setFileSaveName(rs.getString("file_save_name"));
				
				list.add(productVO);
				list.add(productFVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
		public List<Object> selectHeartList(){
		
		List<Object> list = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.like_cnt, p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt  ");
		sql.append("  from ftbl_product p, ( ");
		sql.append("  select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name ");
		sql.append("  from  ftbl_product_file) f, ftbl_category c ");
		sql.append("  where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no  ");
		sql.append("  order by like_cnt desc ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				ProductFileVO productFVO = new ProductFileVO();
				
				
				productVO.setPdNo(rs.getInt("pd_no"));
				productVO.setPdName(rs.getString("pd_name"));
				productVO.setStartPrice(rs.getInt("start_price"));
				productVO.setDueDate(rs.getString("due_date"));
				productVO.setcNo(rs.getInt("c_no"));
				productVO.setcName(rs.getString("category"));
				productVO.setViewCnt(rs.getInt("view_cnt"));
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				
				productFVO.setPdNo(rs.getInt("pd_no"));
				productFVO.setFileSaveName(rs.getString("file_save_name"));
				
				list.add(productVO);
				list.add(productFVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
