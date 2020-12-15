package com.fdm.w5.tdd.bookshop;

import java.util.List;

// use to replace Catalogue in "02-Java-Intermediate-Exercises-Mockito"
class Series {
	IntReadItemCMD reader;
	IntWriteItemCMD writer;
	
	Series(IntReadItemCMD reader, IntWriteItemCMD writer) {
		this.reader = reader;
		this.writer = writer;
	}
	
	List<Book> getBooks() {
		return this.reader.readAll();
	}
	
	void add(Book book) {
		writer.insertItem(book);
	}
}
