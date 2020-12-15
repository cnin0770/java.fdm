package com.fdm.w5.tdd.bookshop;

class Stock {
	private IntDBReader reader;
	
	Stock(IntDBReader reader) {
		this.reader = reader;
	}
	
	boolean checkStock(String isbn) {
		return reader.readQuantity(isbn) > 0;
	}
	
	int checkNumber(String isbn) {
		return reader.readQuantity(isbn) > 0 ? reader.readQuantity(isbn) : 0;
	}
}
