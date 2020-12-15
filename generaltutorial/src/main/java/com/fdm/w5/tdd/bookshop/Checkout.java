package com.fdm.w5.tdd.bookshop;

class Checkout {
	double total = 0;
	private Basket basket = new Basket();
	
	Checkout() {
		super();
	}
	
	Checkout(Basket basket) {
		super();
		this.basket = basket;
	}
	
	double calculate() {
		for (Book i: this.basket.getBooks().values()) this.total += i.getPrice();
		return this.total * (1 - discounted_accumulated() - discounted_once());
	}

	double discounted_accumulated() {
		return ((int)this.basket.getBooks().size() / 3) * .01;
	}
	
	double discounted_once() {
		return (this.basket.getBooks().size() >= 10) ? .1: 0;
	}
}
