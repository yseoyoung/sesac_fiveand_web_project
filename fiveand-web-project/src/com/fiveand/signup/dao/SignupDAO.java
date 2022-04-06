package com.fiveand.signup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fiveand.member.vo.MemberVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class SignupDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public void addMember(MemberVO memberVo) {
		
		try {
			Connection conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into FTBL_MEMBER(ID, PWD, NAME, PHONE, EMAIL) ");
			sql.append("values(?, ?, ?, ?, ?) ");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPwd());
			pstmt.setString(3, memberVo.getName());
			pstmt.setString(4, memberVo.getPhone());
			pstmt.setString(5, memberVo.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}
	
	public int checkId(MemberVO memberVo) {
		
		int id = 0;
		
		try {
			Connection conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select ID ");
			sql.append("from FTBL_MEMBER ");
			sql.append("where ID = ? ");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberVo.getId());
			
			id = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return id;
	}
}
