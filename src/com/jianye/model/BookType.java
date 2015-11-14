package com.jianye.model;

/**
 * 图书类别实体
 * @author jianye
 *
 */
public class BookType {
	
	private int id;
	
	private String bookTypeName;
	
	private String bookTypeDesc;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the bookTypeName
	 */
	public String getBookTypeName() {
		return bookTypeName;
	}

	/**
	 * @param bookTypeName the bookTypeName to set
	 */
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	/**
	 * @return the bookTypeDesc
	 */
	public String getBookTypeDesc() {
		return bookTypeDesc;
	}

	/**
	 * @param bookTypeDesc the bookTypeDesc to set
	 */
	public void setBookTypeDesc(String bookTypeDesc) {
		this.bookTypeDesc = bookTypeDesc;
	}
	
	@Override
	public String toString() {
		return bookTypeName;
	}
}
