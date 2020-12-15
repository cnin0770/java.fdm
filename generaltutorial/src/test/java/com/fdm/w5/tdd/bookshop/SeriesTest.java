package com.fdm.w5.tdd.bookshop;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class SeriesTest {
	@Mock
	IntReadItemCMD mockReader;
	@Mock
	IntWriteItemCMD mockWriter;
	@Mock
	Book mockBook;

	Series series;
	
	@Before
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		series = new Series(mockReader, mockWriter);
	}
	
	@Test
	public void getBooks_returns_empty_list_when_no_book_added() {
		assertEquals(0, series.getBooks().size());
	}
	
	@Test
	public void getBooks_calls_readAll_from_ReadItemCMD() {
		series.getBooks();
		
		verify(mockReader, times(1)).readAll();
	}
	
	@Test
	public void getBooks_returns_same_bookList_from_readAll() {
		List<Book> book_list = new ArrayList<Book>();
		book_list.add(mockBook);
		when(mockReader.readAll()).thenReturn(book_list);
		
		assertEquals(book_list, series.getBooks());
	}
	
	@Test
	public void add_book_calls_insertItem_of_WriteItemCMD() {	
		series.add(null);
		
		verify(mockWriter, times(1)).insertItem(null);
	}
	
	@Test
	public void add_book_gives_same_book_to_insertItem() {
		series.add(mockBook);
		
		verify(mockWriter, times(1)).insertItem(mockBook);
	}

}
