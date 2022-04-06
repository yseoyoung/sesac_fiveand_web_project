package com.fiveand.auction.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.util.ConnectionFactory;

public class MainBoardListDAO {

	public List<Object> selectRecentList(){
		
		List<Object> list = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(   ");
		sql.append("  select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt ");
		sql.append("  	 from ftbl_product p, (  ");
		sql.append("  select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name ");
		sql.append("  from  ftbl_product_file) f, ftbl_category c ");
		sql.append("  where row_num=1 and p.pd_no = f.pd_no and p.c_no = c.c_no ");
		sql.append("  order by reg_date desc) ");
		sql.append("  where rownum <= 5 ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				ProductFileVO productFVO = new ProductFileVO();
				
				//set 순서 x, 타입 알 수 없음
				//List<Object>
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

	public List<ProductVO> selectTopSugList(){
		
		List<ProductVO> sugList = new ArrayList<ProductVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(   ");
		sql.append("  select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt ");
		sql.append("  from ftbl_product p, ( select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from ftbl_product_file) f, ftbl_category c  ");
		sql.append("  , (select count(*) as sug_cnt, pd_no from (select * from ftbl_auction where  sug_date >= to_char(sysdate-7, 'yyyy-mm-dd')) group by pd_no) a ");
		sql.append("  where row_num=1 and p.pd_no = f.pd_no and p.c_no = c.c_no and a.pd_no = p.pd_no ");
		sql.append("  order by a.sug_cnt desc)");
		sql.append(" where rownum <= 5 ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				
				productVO.setPdNo(rs.getInt("pd_no"));
				productVO.setPdName(rs.getString("pd_name"));
				productVO.setStartPrice(rs.getInt("start_price"));
				productVO.setDueDate(rs.getString("due_date"));
				productVO.setcNo(rs.getInt("c_no"));
				productVO.setcName(rs.getString("category"));
				productVO.setViewCnt(rs.getInt("view_cnt"));
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				
				productVO.setFileSaveName(rs.getString("file_save_name"));
				
				sugList.add(productVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sugList;
	}

	public List<ProductVO> selectTopLike(){
		
		List<ProductVO> topLike = new ArrayList<ProductVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(   ");
		sql.append("  select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.like_cnt  ");
		sql.append("  from ftbl_product p,   ");
		sql.append("  (select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f,  ");
		sql.append("  ftbl_category c  ");
		sql.append("  where row_num=1 and p.pd_no = f.pd_no and p.c_no = c.c_no ");
		sql.append(" order by like_cnt desc) ");
		sql.append(" where rownum <= 4 ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
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
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				
				topLike.add(productVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println(topLike);
		return topLike;
	}

	public List<ProductVO> selectToday(){
		
		List<ProductVO> today = new ArrayList<ProductVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(   ");
		sql.append("  select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name  ");
		sql.append("  from ftbl_product p,   ");
		sql.append("  (select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f,  ");
		sql.append("  ftbl_category c  ");
		sql.append("  where row_num=1 and p.pd_no = f.pd_no and p.c_no = c.c_no ");
		sql.append(" and to_char(reg_date, 'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd') ) ");
		sql.append(" where rownum <= 4 ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
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
				
				today.add(productVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return today;
	}

	public List<ProductVO> selectDeadLine(){
		
		List<ProductVO> deadLine = new ArrayList<ProductVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(   ");
		sql.append("  select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name  ");
		sql.append("  from ftbl_product p,   ");
		sql.append("  (select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f,  ");
		sql.append("  ftbl_category c  ");
		sql.append("  where row_num=1 and p.pd_no = f.pd_no and p.c_no = c.c_no and due_date > to_char(sysdate, 'yyyy-mm-dd') ");
		sql.append(" order by due_date) ");
		sql.append(" where rownum <= 4 ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
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
				
				deadLine.add(productVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return deadLine;
	}
	
}
