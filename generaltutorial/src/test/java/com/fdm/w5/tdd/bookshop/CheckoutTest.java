package com.fdm.w5.tdd.bookshop;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CheckoutTest {
	Basket basket = new Basket();
	Checkout checko;
	private Book b1 = new Book("0-312-85182-0", "A Fire Upon the Deep", "Vernor Vinge", 50.25);
	private Book b2 = new Book("0156027607", "Solaris", "Stanistaw Lem", 30.10);
	private Book b3 = new Book("0-575-07068-4", "Schild's Ladder", "Greg Egan", 22.57);

	public static double[] random_generator(int len) {
		Random rand = new Random();

		double[] array = new double[len + 1];
		for (int i = 0; i < len; i++) {
			array[i] = rand.nextDouble() * 100;
			array[len] += array[i];
		}
		return array;
	}

	@Test
	public void getBooksInBasket_returns_empty_list_no_book_added() {
		assertEquals(0, basket.getBooks().size());
	}

	@Test
	public void add_book_return_one_length() {
		basket.add(new Book());
		assertEquals(1, basket.getBooks().size());
	}

	@Test
	public void calc_price_return_zero_double_empty_basket() {
		checko = new Checkout(basket);
		assertEquals(0, checko.calculate(), .01);
	}

	@Test
	public void calc_price_return_book_price_when_one_book_added() {
		basket.add(b1);
		checko = new Checkout(basket);
		assertEquals(b1.getPrice(), checko.calculate(), .01);
	}

	@Test
	public void calc_prices_return_two_books_prices() {
		basket.add(b1);
		basket.add(b2);
		checko = new Checkout(basket);
		assertEquals(b1.getPrice() + b2.getPrice(), checko.calculate(), .01);
	}

	@Test
	public void calc_prices_return_three_books_with_discount() {
		basket.add(b1);
		basket.add(b2);
		basket.add(b3);
		checko = new Checkout(basket);
		assertEquals((b1.getPrice() + b2.getPrice() + b3.getPrice()) * .99, checko.calculate(), .01);
	}

	@Test
	public void calc_price_return_seven_books_with_discount() {
		double[] price_list = random_generator(7);
		for (int i = 0; i < 7; i++) {
			basket.add(new Book(Double.toString(price_list[i]), price_list[i]));
		}
		checko = new Checkout(basket);
		assertEquals(7, basket.getBooks().size());
		assertEquals(price_list[7] * .98, checko.calculate(), .01);
	}

	@Test
	public void calc_price_return_ten_books_with_discounts() {
		double[] price_list = random_generator(10);
		for (int i = 0; i < 10; i++) {
			basket.add(new Book(Double.toString(price_list[i]), price_list[i]));
		}
		checko = new Checkout(basket);
		assertEquals(10, basket.getBooks().size());
		assertEquals(price_list[10] * (1 - .13), checko.calculate(), .01);
	}

	@Test
	public void calc_price_return_fifty_books_with_discounts() {
		double[] price_list = random_generator(50);
		for (int i = 0; i < 50; i++) {
			basket.add(new Book(Double.toString(price_list[i]), price_list[i]));
		}
		checko = new Checkout(basket);
		assertEquals(50, basket.getBooks().size());
		assertEquals(price_list[50] * (1 - .26), checko.calculate(), .01);
	}

	@Test
	public void calc_price_return_FREE_with_270_books() {
		double[] price_list = random_generator(270);
		for (int i = 0; i < 270; i++) {
			basket.add(new Book(Double.toString(price_list[i]), price_list[i]));
		}
		checko = new Checkout(basket);
		assertEquals(270, basket.getBooks().size());
		assertEquals(0, checko.calculate(), .01);
	}
	
	/* 
	 * If every book in the Basket is different, apply an additional 5% to the whole basket. 
	 * If the basket contains 2 of the same book, apply a unique discount of 50% to those two books only
	 */
}
