package com.fdm.w5.tdd.bookshop;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.*;

public class StockTest {

	@Test
	public void checkStock_calls_readQuantity_from_DBReader_once() {
		// arrange
		String isbn = "778899";
		IntDBReader mockDBReader = mock(IntDBReader.class);
		Stock stock = new Stock(mockDBReader);
		
		// act
		stock.checkStock(isbn);
		
		// assert
		verify(mockDBReader, times(1)).readQuantity(anyString());
	}
	
	@Test
	public void pass_isbn_calls_dbreader_once_and_pass_isbn() {
		String isbn = "778899";
		IntDBReader mockDBReader = mock(IntDBReader.class);
		Stock stock = new Stock(mockDBReader);
		
		stock.checkStock(isbn);
		
		verify(mockDBReader, times(1)).readQuantity(isbn);
	}

	@Test
	public void return_three_when_check() {
		String isbn = "778899";
		IntDBReader mockDBReader = mock(IntDBReader.class);
		Stock stock = new Stock(mockDBReader);
		
		when(mockDBReader.readQuantity(isbn)).thenReturn(3);
		
		assertTrue(stock.checkStock(isbn));
		assertEquals(3, stock.checkNumber(isbn));
	}
}
