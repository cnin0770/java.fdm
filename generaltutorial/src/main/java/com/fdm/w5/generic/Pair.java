package com.fdm.w5.generic;

class Pair<T, U> {
	private T first_element;
	private U second_element;
	
	public Pair(T first_element, U second_element) {
		this.first_element = first_element;
		this.second_element = second_element;
	}

	public T getFirst() {
		return first_element;
	}
	
	public U getSecond() {
		return second_element;
	}
}
