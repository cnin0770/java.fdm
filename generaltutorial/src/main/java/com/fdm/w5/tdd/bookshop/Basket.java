package com.fdm.w5.tdd.bookshop;

import java.util.HashMap;
import java.util.Map;

class Basket {
	private Map<String, Book> book_list = new HashMap<String, Book>();

	Map<String, Book> getBooks(){
		return book_list;
	}
	
	void add(Book book) {
		book_list.put(book.getIsbn(), book);
	}
	
	void remove(Book book) {
		book_list.remove(book.getIsbn());
	}
	
	void remove(String isbn) {
		book_list.remove(isbn);
	}
}
