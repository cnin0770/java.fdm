package com.fdm.w5.designpattern.command;

class BuyStock implements IntOrder {

	private Stock abcStock;

	public BuyStock(Stock abcStock) {
		this.abcStock = abcStock;
	}

	public void execute() {
		abcStock.buy();
	}
}
