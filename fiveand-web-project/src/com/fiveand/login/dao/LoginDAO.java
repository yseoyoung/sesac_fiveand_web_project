package com.fiveand.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fiveand.member.vo.MemberVO;
import com.fiveand.util.ConnectionFactory;

public class LoginDAO {

	public MemberVO login(MemberVO memberVO) { 

	
	MemberVO userVO = null;
	
	System.out.println("member dao : " + memberVO);
	StringBuilder sql = new StringBuilder();
	sql.append("select id, pwd, type, warning_cnt ");
	sql.append("  from ftbl_member ");
	sql.append(" where id = ? and pwd = ? ");
	
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { 
				
				userVO = new MemberVO();
				
				userVO.setId(rs.getString("id"));
				userVO.setPwd(rs.getString("pwd"));
				userVO.setType(rs.getString("type"));
				userVO.setWarningCnt(rs.getInt("warning_cnt"));
				System.out.println("dao userVO : " + userVO);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return userVO;
	}
	
	public int checkWindBid(MemberVO user) {
		
		int cnt = 0;
		
		if (user.getType().equals('A'))
		{
			return cnt;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select p.pd_no, sug_id, due_date ");
		sql.append(" from ftbl_product p inner join ftbl_sold s ");
		sql.append(" on p.pd_no = s.pd_no ");
		sql.append(" where sug_id = ? and due_date < sysdate and payment = 0 ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, user.getId());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cnt++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
