package com.fdm.w5.generic;

import java.util.ArrayList;

class Catalog<Item extends IntItem> {
	private ArrayList<Item> items_list = new ArrayList<Item>();

	ArrayList<Item> getAll(){
		return items_list;
	}
	
	void addItem(Item item) {
		items_list.add(item);
	}

	<T extends Number> Item getItem(T id) {
		for (Item i: items_list) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}
}
