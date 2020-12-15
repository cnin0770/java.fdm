package com.fdm.w5.designpattern.iterator;

class Demo {

	public static void main(String[] args) {		
		ShortlistedCandiates candidates = new ShortlistedCandiates();
		
		// for (IntIterator iter = name_repository.getIterator(); iter.hasNext();)
		// System.out.println("name: " + (String)iter.next());
		IntIterator iter = candidates.getIterator();
		while (iter.hasNext())
			System.out.println("candidate: " + (String) iter.next());
	}
}
