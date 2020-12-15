package com.fdm.w5.designpattern.singleton;

class SingletonApp {
	public static void main(String[] args) {
		// President president = new President(); // no use
		President president = President.get();
		System.out.println(president);
	}
}
