package com.jianye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianye.model.BookType;
import com.jianye.utils.StringUtils;

public class BookTypeDao {
	
	public int add(Connection conn, BookType bookType) throws SQLException {
		String sql = "insert into t_bookType values(null, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		return pstmt.executeUpdate();
	}
	
	public ResultSet list(Connection conn, BookType bookType) throws SQLException {
		StringBuffer sb = new StringBuffer("select * from t_bookType");
		if (StringUtils.isNotEmpty(bookType.getBookTypeName())) {
			sb.append(" and bookTypeName like '%" + bookType.getBookTypeName() + "%'");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replace("and", " where "));
		return pstmt.executeQuery();
	}
	
	/**
	 * 根据id删除图书类型
	 * @param conn 连接类型
	 * @param id 主键编号
	 * @return
	 * @throws SQLException 
	 */
	public int deleteBookType(Connection conn, String id) throws SQLException {
		String sql = "delete from t_bookType where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新图书类别信息
	 * @param conn 连接
	 * @param bookType 图书类型
	 * @return 更新结果
	 * @throws SQLException
	 */
	public int updateBookType(Connection conn, BookType bookType) throws SQLException {
		String sql = "update t_bookType set bookTypeName = ?, bookTypeDesc = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		pstmt.setInt(3, bookType.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询图书类别下是否有图书
	 * @param conn
	 * @param booTypeId
	 * @return
	 * @throws SQLException 
	 */
	public boolean existBookType(Connection conn, String bookTypeId) throws SQLException {
		String sql = "select * from t_book where bookTypeId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);
		ResultSet rs = pstmt.executeQuery();
		// 返回结果集
		return rs.next();
	}
	
}
