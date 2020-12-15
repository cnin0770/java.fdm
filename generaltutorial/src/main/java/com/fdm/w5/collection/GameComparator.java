package com.fdm.w5.collection;

import java.util.Comparator;

class GameComparator implements Comparator<Game> {

	public int compare(Game min, Game max) {
		if (min.getRating() != max.getRating()) return max.getRating() - min.getRating();
		else if (min.getPrice() != max.getPrice()) {
			return (int) ((max.getPrice() - max.getPrice()) * 100);
		}
		else return min.getId() - max.getId();
	}
}
