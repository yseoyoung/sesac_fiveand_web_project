package com.fiveand.auction.heart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fiveand.auction.heart.vo.HeartVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class AuctionHeartDAO {

	public boolean checkHeart(String id, int no) {
		boolean result = false;
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ftbl_heart where id = ? and pd_no = ? ");

		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		){
			pstmt.setString(1, id);
			pstmt.setInt(2, no);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				result = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int addHeart(HeartVO heart) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();

			conn.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();

			sql.append(" update ftbl_product ");
			sql.append(" set like_cnt = like_cnt + 1 ");
			sql.append(" where pd_no = ? ");
			pstmt1 = conn.prepareStatement(sql.toString());
			pstmt1.setInt(1, heart.getPdNo());
			int rs1 = pstmt1.executeUpdate();

			sql.setLength(0);
			sql.append(" insert into ftbl_heart(id, pd_no) ");
			sql.append(" values(?, ?) ");
			pstmt2 = conn.prepareStatement(sql.toString());
			pstmt2.setString(1, heart.getId());
			pstmt2.setInt(2, heart.getPdNo());
			int rs2 = pstmt2.executeUpdate();

			if (rs1 + rs2 == 2) {
				result = selectHeartCnt(conn, heart.getPdNo());
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.stmtClose(pstmt1);
			JDBCClose.stmtClose(pstmt2);
			JDBCClose.connClose(conn);
		}
		
		return result;
	}

	public int cancleHeart(HeartVO heart) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();

			sql.append(" update ftbl_product ");
			sql.append(" set like_cnt = like_cnt - 1 ");
			sql.append(" where pd_no = ? ");
			pstmt1 = conn.prepareStatement(sql.toString());
			pstmt1.setInt(1, heart.getPdNo());
			int rs1 = pstmt1.executeUpdate();

			sql.setLength(0);
			sql.append(" delete ftbl_heart ");
			sql.append(" where id = ? and pd_no = ? ");
			pstmt2 = conn.prepareStatement(sql.toString());
			pstmt2.setString(1, heart.getId());
			pstmt2.setInt(2, heart.getPdNo());
			int rs2 = pstmt2.executeUpdate();

			if (rs1 + rs2 == 2) {
				result = selectHeartCnt(conn, heart.getPdNo());
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.stmtClose(pstmt1);
			JDBCClose.stmtClose(pstmt2);
			JDBCClose.connClose(conn);
		}
		
		return result;
	}
	
	public int selectHeartCnt(Connection conn, int pdNo) {
		int cnt = -1;
		String sql = " select like_cnt from ftbl_product where pd_no = ? ";
		PreparedStatement pstmt = null;
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pdNo);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					cnt = rs.getInt("like_cnt");
				}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.stmtClose(pstmt);
		}
		return cnt;
	}
}