package com.jianye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianye.model.User;

/**
 * 用户数据访问对象
 * @author jianye
 *
 */
public class UserDao {
	
	public User login(Connection conn, User user) throws SQLException {
		User resultUser = null;
		
		String sql = "select * from t_user where userName = ? and password = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setPassword(rs.getString("password"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setId(rs.getInt("id"));
		}
		return resultUser;
	}
}
