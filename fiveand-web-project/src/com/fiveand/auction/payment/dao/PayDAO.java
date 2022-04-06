package com.fiveand.auction.payment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class PayDAO {

	public ProductVO selectMyWin(String id, int pdNo){
			ProductVO productVO = new ProductVO();
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
				conn = new ConnectionFactory().getConnection();
				StringBuilder sql = new StringBuilder();
				sql.append(" select p.pd_no, p.pd_name, to_char(p.due_date, 'mm-dd') as due_date , p.c_no, c.category, f.file_save_name, s.sug_id, s.sug_price  "
						+ "from ftbl_product p, "
						+ "( select pd_no, row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, "
						+ "ftbl_category c, "
						+ "( select * from ftbl_sold) s "
						+ "where s.sug_id = ? and p.due_date < sysdate and p.pd_no = ?"
						+ "and f.row_num = 1 "
						+ "and p.pd_no = f.pd_no and p.c_no = c.c_no and s.pd_no = p.pd_no "
						+ "order by s.sug_date desc ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				pstmt.setInt(2, pdNo);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					productVO.setPdNo(rs.getInt("pd_no"));
					productVO.setPdName(rs.getString("pd_name"));
					productVO.setDueDate(rs.getString("due_date"));
					productVO.setcNo(rs.getInt("c_no"));
					productVO.setcName(rs.getString("category"));
					productVO.setFileSaveName(rs.getString("file_save_name"));
					productVO.setSugId(rs.getString("sug_id"));
					productVO.setSugPrice(rs.getInt("sug_price"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCClose.close(pstmt, conn);
			}
			
			return productVO;
		}

	public void updatePayment(int pdNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" update ftbl_sold set payment = 1 where pd_no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pdNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		
	}
	
}
