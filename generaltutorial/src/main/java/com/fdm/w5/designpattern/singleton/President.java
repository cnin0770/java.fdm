package com.fdm.w5.designpattern.singleton;

class President {
	static President president;

	private President() {
		super();
	}

	static President get() {
		if (president == null) {
			president = new President();
		}
		return president;
	}
}
