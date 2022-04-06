package com.fiveand.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fiveand.qna.vo.PagingVO;
import com.fiveand.qna.vo.QnAVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class QnADAO {

	public List<QnAVO> selectAllBoard(int pdNo) {

		List<QnAVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append(" from (select b_no, id, pd_no, title, to_char(reg_date, 'yyyy-mm-dd') as reg_date, group_id, depth, pos from ftbl_qna_board ");
			sql.append(" where pd_no = ? order by group_id desc, depth asc) ftbl_qna_board ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pdNo);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int bNo = rs.getInt("b_no");
				String title = rs.getString("title");
				String id = rs.getString("id");
				String regDate = rs.getString("reg_date");
				int depth = rs.getInt("depth");

				QnAVO qna = new QnAVO(bNo, title, id, regDate, depth);
				list.add(qna);
				System.out.println(list);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return list;
	}

	public List<QnAVO> selectPagingBoard(int pdNo, int currnetPage) {
		
		PagingVO paging = new PagingVO();
		List<QnAVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int startNum = ((currnetPage - 1) * paging.getDisplayRow()) + 1;
        int endNum = currnetPage * paging.getDisplayRow();

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT * from ( SELECT ROWNUM AS row_num, b_no, id, pd_no, title, to_char(reg_date, 'yyyy-mm-dd') as reg_date, depth ");
			sql.append(" FROM ( SELECT * FROM ftbl_qna_board ORDER BY group_id DESC ) board ) ");
			sql.append(" WHERE row_num >= ? AND row_num <= ? and pd_no = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			pstmt.setInt(3, pdNo);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int bNo = rs.getInt("b_no");
				String title = rs.getString("title");
				String id = rs.getString("id");
				String regDate = rs.getString("reg_date");
				int depth = rs.getInt("depth");

				QnAVO qna = new QnAVO(bNo, title, id, regDate, depth);
				list.add(qna);
				System.out.println(list);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return list;
	}

	public int totalCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int totalList = 0;
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from ( ");
			sql.append(" 	select * from ftbl_qna_board order by b_no desc)");
			pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			totalList = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return totalList;
	}

	public QnAVO detailBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		QnAVO qna = null;
		

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select q.b_no, q.pd_no, q.title, q.id  as qna_id , to_char(q.reg_date, 'yyyy-mm-dd') as reg_date , q.content, q.group_id, q.depth, q.pos, p.id as product_id ");
			sql.append(" from ftbl_qna_board q, ftbl_product p ");
			sql.append(" where q.pd_no = p.pd_no and b_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				qna = new QnAVO();
				qna.setbNo(rs.getInt("b_no"));
				qna.setId(rs.getString("qna_id"));
				qna.setPdNo(rs.getInt("pd_no"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegDate(rs.getString("reg_date"));
				qna.setProductId(rs.getString("product_id"));
				
			}
			
			StringBuilder sql2 = new StringBuilder();
			sql2.append(" select count(*) from ftbl_qna_comment ");
			sql2.append(" where b_no = ? ");
			
			pstmt = conn.prepareStatement(sql2.toString());
			pstmt.setInt(1, boardNo);

			ResultSet rs2 = pstmt.executeQuery();
			if(rs2.next()) {
				qna.setComCount(rs.getLong(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return qna;
	}

	public void insertBoard(QnAVO qna) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//int result = 0;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into ftbl_qna_board ");
			sql.append(" values(seq_ftbl_qna_board_b_no.nextval, ?, ?, ?, ?, sysdate, seq_ftbl_qna_board_b_no.currval, 0, 0 ) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, qna.getId());
			pstmt.setInt(2, qna.getPdNo());
			pstmt.setString(3, qna.getTitle());
			pstmt.setString(4, qna.getContent());
			
			//result = pstmt.executeUpdate();
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		//return result;
	}

	public QnAVO updateFormBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		QnAVO qna = null;
		

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select q.b_no, q.id as qna_id , q.pd_no, q.title, q.content, to_char(q.reg_date, 'mm-dd') as reg_date, p.id as product_id ");
			sql.append(" from ftbl_qna_board q, ftbl_product p where q.pd_no = p.pd_no and b_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				qna = new QnAVO();
				qna.setbNo(rs.getInt("b_no"));
				qna.setId(rs.getString("qna_id"));
				qna.setPdNo(rs.getInt("pd_no"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegDate(rs.getString("reg_date"));
				qna.setProductId(rs.getString("product_id"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		System.out.println("가져온 내용: " +qna);
		return qna;
	}

	public void updateBoard(QnAVO qna) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" update ftbl_qna_board ");
			sql.append(" set title = ?, content = ? ");
			sql.append(" where b_no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, qna.getTitle());
			pstmt.setString(2, qna.getContent());
			pstmt.setInt(3, qna.getbNo());

			int rs = pstmt.executeUpdate();
			System.out.println(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}

	public void deleteBoard(int boardNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from ftbl_qna_board ");
			sql.append("where b_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

	}

	public void upPos(int groupId, int pos) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("update ftbl_qna_board set pos = pos + 1 ");
			sql.append(" where group_id = ? and pos >= ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, groupId);
			pstmt.setInt(2, pos);
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}
	
	public void replyboard(QnAVO qna) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into ftbl_qna_board ");
			sql.append(" values(seq_ftbl_qna_board_b_no.nextval, ?, ?, ?, ?, sysdate, ?, ?, ? ) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, qna.getId());
			pstmt.setInt(2, qna.getPdNo());
			pstmt.setString(3, qna.getTitle());
			pstmt.setString(4, qna.getContent());
			pstmt.setInt(5, qna.getGroupId());
			pstmt.setInt(6, qna.getDepth()+1); //깊이 +1
			pstmt.setInt(7, qna.getPos()+1); //답글 순서 +1
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}
	
	
	
	
}


