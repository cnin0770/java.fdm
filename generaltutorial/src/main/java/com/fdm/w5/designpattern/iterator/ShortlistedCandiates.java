package com.fdm.w5.designpattern.iterator;

class ShortlistedCandiates implements IntContainer {
	private final String candidates[] = {"Gabrielius Rush", "Maysa Day", "Asa Mora", "Niall Romero", "Jenna Valentine", "Ari Little"};

	public IntIterator getIterator() {
		return new NameIterator();
	}

	// iterator must be in container?
	private class NameIterator implements IntIterator {
		int index;
		
		public boolean hasNext() {
			if (index < candidates.length) return true;
			return false;
		}

		public Object next() {
			// here, must be index++, or ++index - 1
			if (this.hasNext()) return candidates[index++];
			return null;
		}
	}
}
