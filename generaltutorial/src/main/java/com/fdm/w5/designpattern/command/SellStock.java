package com.fdm.w5.designpattern.command;

class SellStock implements IntOrder {

	private Stock abcStock;

	public SellStock(Stock abcStock) {
		this.abcStock = abcStock;
	}

	public void execute() {
		abcStock.sell();
	}

}
