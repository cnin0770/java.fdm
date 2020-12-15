package com.fdm.w5.designpattern.command;

import java.util.ArrayList;
import java.util.List;

class Broker {
	private List<IntOrder> orderList = new ArrayList<IntOrder>();

	public void takeOrder(IntOrder order) {
		orderList.add(order);
	}

	public void placeOrders() {

		for (IntOrder order : orderList) {
			order.execute();
		}
		orderList.clear();
	}
}
