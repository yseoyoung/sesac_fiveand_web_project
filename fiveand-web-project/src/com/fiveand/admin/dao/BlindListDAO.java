package com.fiveand.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class BlindListDAO {


	public List<ProductVO> blindList() {
		
		List<ProductVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select  pd_no, id, pd_name, category, hope_price, start_price, ");
			sql.append("to_char(reg_date, 'yyyy-mm-dd') reg_date, to_char(due_date, 'yyyy-mm-dd') due_date, to_char(del_date, 'yyyy-mm-dd') del_date ");
			sql.append("from ftbl_category c, ftbl_blind b ");
			sql.append("where c.c_no = b.c_no ");
			sql.append("order by del_date desc ");
			
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int pdNo = rs.getInt("pd_no");
				String id = rs.getString("id");
				String pdName = rs.getString("pd_name");
				String cName = rs.getString("category");
				int hopePrice = rs.getInt("hope_price");
				int startPrice = rs.getInt("start_price");
				String regDate = rs.getString("reg_date");
				String dueDate = rs.getString("due_date");
				String delDate = rs.getString("del_date");
				
				ProductVO product = new ProductVO(pdNo, id, pdName, cName, hopePrice, startPrice, regDate, dueDate, delDate);
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return list;
	}

	public void insertBlind(int pdNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into FTBL_BLIND(PD_NO, ID, PD_NAME, HOPE_PRICE, START_PRICE, ");
			sql.append("REG_DATE, DUE_DATE, PD_SIMPLE_INFO, PD_INFO, C_NO, VIEW_CNT, LIKE_CNT, SUG_CNT, WARN_CNT) ");
			sql.append("select * from FTBL_PRODUCT ");
			sql.append("where PD_NO = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pdNo);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}

	public void addWarnCnt(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" update ftbl_member set ");
			sql.append(" warning_cnt = warning_cnt + 1 ");
			sql.append(" where id = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}
	
}
