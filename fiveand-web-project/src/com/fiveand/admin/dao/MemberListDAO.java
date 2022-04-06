package com.fiveand.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fiveand.member.vo.MemberVO;
import com.fiveand.util.ConnectionFactory;
import com.fiveand.util.JDBCClose;

public class MemberListDAO {

	public List<MemberVO> selectAllMember() {

		List<MemberVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, phone, email, warning_cnt ");
			sql.append(" from ftbl_member");
			sql.append(" where type = 'U' ");

			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int warningCnt = rs.getInt("warning_cnt");

				MemberVO member = new MemberVO(id, name, phone, email, warningCnt);
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return list;
	}
}
