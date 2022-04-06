package com.fiveand.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fiveand.auction.board.vo.PagingVO;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.member.vo.MemberVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class MyPageDAO {

	public MemberVO selectMyInfo(String id) {
		
		MemberVO member = null;	
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, phone, email, warning_cnt ");
			sql.append(" from ftbl_member " );
			sql.append(" where id = ? " );
			

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int warningCnt = rs.getInt("warning_cnt");

				member = new MemberVO(id, name, phone, email, warningCnt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return member;

	}

	public void updateMyInfo(MemberVO member) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try { 
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" update ftbl_member ");
			sql.append(" set pwd = ? , phone = ? , email = ? ");
			sql.append(" where id = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}

	public int deleteMyInfo(MemberVO member) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try { 
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from ftbl_member ");
			sql.append(" where id = ? and pwd = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return result;
	}

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
	

	public List<ProductVO> pagingMyAuction(int currentPage, String id){
		
		PagingVO paging = new PagingVO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int startNum = ((currentPage - 1) * paging.getDisplayRow()) + 1;
        int endNum = currentPage * paging.getDisplayRow();
        
        try {
			
        	conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select rownum as row_num, board.* from ( select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt ");
			sql.append(" from ftbl_product p, ( select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, ftbl_category c ");
			sql.append(" where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no and id = ? ");
			sql.append(" order by pd_no desc) board) ");
			sql.append(" where row_num >= ? and row_num <= ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
		
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

	public List<ProductVO> selectMyHeart(int currentPage, String id) {

		PagingVO paging = new PagingVO();
		List<ProductVO> list = new ArrayList<ProductVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		int startNum = ((currentPage - 1) * paging.getDisplayRow()) + 1;
		int endNum = currentPage * paging.getDisplayRow();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select rownum as row_num, board.* from ( select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, p.view_cnt, p.like_cnt  ");
			sql.append(" from ftbl_product p, ( select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, ftbl_category c, (select * from ftbl_heart where id= ?) h ");
			sql.append(" where row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no and p.pd_no = h.pd_no ");
			sql.append(" order by pd_no desc) board) ");
			sql.append(" where row_num >= ? and row_num <= ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
		
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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

	public List<ProductVO> selectMySugg(String id){
		
			//System.out.println("dao id : " + id);
			List<ProductVO> list = new ArrayList<ProductVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
				conn = new ConnectionFactory().getConnection();
				StringBuilder sql = new StringBuilder();
				
				sql.append(" select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, a.id, a.a_row_num ");
				sql.append(" from ftbl_product p, ");
				sql.append(" ( select pd_no, row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f,  ");
				sql.append(" ftbl_category c, ");
				sql.append(" (select * from (select row_number() over(partition by pd_no order by pd_no) a_row_num, pd_no, id, sug_price, sug_date from ftbl_auction where id= ? ) where a_row_num = 1 ) a ");
				sql.append(" where f.row_num = 1 and p.pd_no = f.pd_no and p.c_no = c.c_no and a.pd_no = p.pd_no ");
				sql.append(" order by a.sug_date desc ");
				/*
				sql.append(" select p.pd_no, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, a.id  "
						+ "from ftbl_product p, "
						+ "( select pd_no, row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, "
						+ "ftbl_category c, "
						+ "(select row_number() over(partition by pd_no order by pd_no) row_num, a.* from ftbl_auction a ) a "
						+ "where a.id = ? "
						+ "and f.row_num = 1 and a.row_num = 1 "
						+ "and p.pd_no = f.pd_no and p.c_no = c.c_no and a.pd_no = p.pd_no "
						+ "order by a.sug_date desc ");
				*/
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				
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
					
					list.add(productVO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCClose.close(pstmt, conn);
			}
			
	        System.out.println("dao list : " +list);
			return list;
		}
	
	public List<ProductVO> selectMyWin(String id){
		
			//System.out.println("dao id : " + id);
			List<ProductVO> list = new ArrayList<ProductVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
				conn = new ConnectionFactory().getConnection();
				StringBuilder sql = new StringBuilder();
				sql.append(" select p.pd_no, p.pd_name, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, s.sug_id, s.sug_price, s.payment  "
						+ "from ftbl_product p, "
						+ "( select pd_no, row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, "
						+ "ftbl_category c, "
						+ "( select * from ftbl_sold) s "
						+ "where s.sug_id = ? and p.due_date < sysdate "
						+ "and f.row_num = 1 "
						+ "and p.pd_no = f.pd_no and p.c_no = c.c_no and s.pd_no = p.pd_no "
						+ "order by s.sug_date desc ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ProductVO productVO = new ProductVO();
					
					productVO.setPdNo(rs.getInt("pd_no"));
					productVO.setPdName(rs.getString("pd_name"));
					productVO.setDueDate(rs.getString("due_date"));
					productVO.setcNo(rs.getInt("c_no"));
					productVO.setcName(rs.getString("category"));
					productVO.setFileSaveName(rs.getString("file_save_name"));
					productVO.setSugId(rs.getString("sug_id"));
					productVO.setSugPrice(rs.getInt("sug_price"));
					productVO.setPayment(rs.getInt("payment"));
					
					list.add(productVO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCClose.close(pstmt, conn);
			}
			
	        //System.out.println("dao list : " +list);
			return list;
		}

}
