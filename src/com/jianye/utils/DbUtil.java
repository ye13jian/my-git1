package com.jianye.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * @author jianye
 *
 */
public class DbUtil {
	private String dbUrl = "jdbc:mysql://localhost:3306/db_book";
	
	private String dbUserName = "root";
	
	private String dbPassword = "123456";
	
	private String driver = "com.mysql.jdbc.Driver";
	
	public Connection getConn() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return conn;
	}
	
	public void closeCon(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}
