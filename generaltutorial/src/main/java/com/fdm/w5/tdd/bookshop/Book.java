package com.fdm.w5.tdd.bookshop;

class Book {
	private String isbn;
	private String title;
	private String author;
	private double price;
	private int page_number;

	public Book() {
		super();
	}
	
	Book(String isbn, double price) {
		this.isbn = isbn;
		this.price = price;
	}
	
	public Book(String isbn, String title, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
	
	public Book(String isbn, String title, String author, double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getPageNumber() {
		return page_number;
	}

	public void setPageNumber(int page_number) {
		this.page_number = page_number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
