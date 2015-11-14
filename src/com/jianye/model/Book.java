package com.jianye.model;

/**
 * 图书实体类
 * @author jianye
 *
 */
public class Book {
	private int id;
	
	private String bookName;
	
	private String author;
	
	private String sex;
	
	private float price;
	
	private Integer bookTypeId;
	
	private String bookTypeName;
	
	private String bookDesc;

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
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the bookTypeId
	 */
	public Integer getBookTypeId() {
		return bookTypeId;
	}

	/**
	 * @param bookTypeId the bookTypeId to set
	 */
	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
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
	 * @return the bookDesc
	 */
	public String getBookDesc() {
		return bookDesc;
	}

	/**
	 * @param bookDesc the bookDesc to set
	 */
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	
}
