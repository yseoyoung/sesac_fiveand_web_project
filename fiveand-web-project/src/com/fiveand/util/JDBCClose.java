package com.fiveand.util;

import java.sql.Connection;
import java.sql.Statement;

public class JDBCClose {

	public static void close(Statement stmt, Connection conn) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void stmtClose(Statement stmt)
	{
		if (stmt != null)
		{
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void connClose(Connection conn)
	{
		if (conn != null)
		{
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
