package com.jianye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jianye.model.Book;
import com.jianye.utils.StringUtils;

/**
 * 图书Dao
 * @author jianye
 *
 */
public class BookDao {
	/**
	 * 图书添加
	 * @param conn
	 * @param book
	 * @return
	 * @throws SQLException 
	 */
	public int add(Connection conn, Book book) throws SQLException {
		String sql = "insert into t_book values(null, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getBookTypeId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 图书信息查询
	 * @param conn
	 * @param book
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet list(Connection conn, Book book) throws SQLException {
		StringBuffer sql = new StringBuffer("select * from t_book b, t_bookType bt where b.bookTypeId = bt.id");
		if (StringUtils.isNotEmpty(book.getBookName())) {
			sql.append(" and b.bookName like '%" + book.getBookName() +"%'");
		}
		if (StringUtils.isNotEmpty(book.getAuthor())) {
			sql.append(" and b.author like '%" + book.getAuthor() + "%'");
		}
		if (book.getBookTypeId() != null && book.getBookTypeId() != -1) {
			sql.append(" and b.bookTypeId = " + book.getBookTypeId());
		}
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 删除图书
	 * @param conn
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deleteBook(Connection conn, String id) throws SQLException {
		String sql = "delete from t_book where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 更新图书信息
	 * @param conn 连接
	 * @param book 图书信息
	 * @return
	 * @throws SQLException
	 */
	public int updateBook(Connection conn, Book book) throws SQLException {
		String sql = "update t_book set bookName = ?, author=?, sex = ?, price = ?, bookDesc = ?, bookTypeId = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getBookTypeId());
		pstmt.setInt(7, book.getId());
		return pstmt.executeUpdate();
	}
}
