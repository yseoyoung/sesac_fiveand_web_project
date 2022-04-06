package com.fiveand.auction.detail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fiveand.auction.suggest.vo.SuggestListVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class AuctionSuggestDAO {


	public List<SuggestListVO> selectSuggestNo(int pdNo){
		List<SuggestListVO> suggestList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a_no, pd_no, id, sug_price, sug_date ");
		sql.append(" from (select * from ftbl_auction where pd_no = ? order by sug_price desc) ");
		sql.append(" where rownum <= 3 ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());				
		){
			pstmt.setInt(1, pdNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SuggestListVO suggest = new SuggestListVO();
				suggest.setaNo(rs.getInt("a_no"));
				suggest.setPdNo(rs.getInt("pd_no"));
				suggest.setId(rs.getString("id"));
				suggest.setSugPrice(rs.getInt("sug_price"));
				suggest.setSugDate(rs.getString("sug_date"));
				suggestList.add(suggest);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return suggestList;
	}

	public void insertSuggest(SuggestListVO suggest) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		
		
		try {
			conn = new ConnectionFactory().getConnection();

			conn.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append(" insert into ftbl_auction ( ");
			sql.append(" a_no, pd_no, id, sug_price ) ");
			sql.append(" values( seq_ftbl_auction_a_no.nextval, ?, ?, ?) ");

			pstmt1 = conn.prepareStatement(sql.toString());
			pstmt1.setInt(1, suggest.getPdNo());
			pstmt1.setString(2, suggest.getId());
			pstmt1.setInt(3, suggest.getSugPrice());
			pstmt1.executeUpdate();

			sql.setLength(0);
			
			sql.append(" update ftbl_product ");
			sql.append(" set sug_cnt = sug_cnt + 1 ");
			sql.append(" where pd_no = ? ");

			pstmt2 = conn.prepareStatement(sql.toString());
			pstmt2.setInt(1, suggest.getPdNo());
			pstmt2.executeUpdate();

			sql.setLength(0);

			sql.append(" merge into ftbl_sold s using dual ");
			sql.append(" on (s.pd_no = ? ) ");
			sql.append(" when matched then ");
			sql.append(" update set s.sug_id = ? , s.sug_price = ? , s.sug_date = sysdate where s.sug_price < ? ");
			sql.append(" when not matched then ");
			sql.append(" insert (s_no, pd_no, sug_id, sug_price, sug_date) values(seq_ftbl_sold_s_no.nextval, ?, ?, ?, sysdate) ");

			
			pstmt3 = conn.prepareStatement(sql.toString());
			pstmt3.setInt(1, suggest.getPdNo());
			pstmt3.setString(2, suggest.getId());
			pstmt3.setInt(3, suggest.getSugPrice());
			pstmt3.setInt(4, suggest.getSugPrice());
			pstmt3.setInt(5, suggest.getPdNo());
			pstmt3.setString(6, suggest.getId());
			pstmt3.setInt(7, suggest.getSugPrice());
			pstmt3.executeUpdate();
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.stmtClose(pstmt1);
			JDBCClose.stmtClose(pstmt2);
			JDBCClose.connClose(conn);
		}
		
	}
}
