package com.fdm.w5.tdd.bookshop;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class CatalogueTest {
	private Catalogue cata = new Catalogue();
	Map<String, Book> cata_books;
	private Book b1 = new Book("0-312-85182-0", "A Fire Upon the Deep", "Vernor Vinge");
	private Book b2 = new Book("0156027607", "Solaris", "Stanistaw Lem");
	private Book b3 = new Book("0-575-07068-4", "Schild's Ladder", "Greg Egan");

	@Test
	public void getAllBooks_returns_a_list_of_zero_length_when_no_book_added() {
		cata_books = cata.getAllBooks();
		assertEquals(0, cata_books.size());
	}

	@Test
	public void add_a_book_list_returns_len_one() {
		cata.add(new Book());
		cata_books = cata.getAllBooks();
		assertEquals(1, cata_books.size());
	}
	
	@Test
	public void add_a_book_cata_contains_it() {
		cata.add(b1);
		cata_books = cata.getAllBooks();
		assertTrue(cata_books.get(b1.getIsbn()).equals(b1));
	}
	
	@Test
	public void add_book_and_remove_it_by_isbn() {
		cata.add(b2);
		cata.remove(b2.getIsbn());
		cata_books = cata.getAllBooks();
		assertNull(cata_books.get(b2.getIsbn()));
		assertEquals(0, cata_books.size());
	}
}
