package com.fdm.w4.algorithms.binarytree;

class Tree {
	Node root;
	
	Tree create() {
		Tree tr = new Tree();
		int[] nums = {6, 4, 8, 3, 5, 7, 9};
		
		for (int i: nums) tr.add(i);
		return tr;
	}
	
	private Node addRecur(Node current, int val) {
		if (current == null) return new Node(val);
		
		if (val < current.val) current.left = addRecur(current.left, val);
		else if (val > current.val) current.right = addRecur(current.right, val);
		else return current;
		
		return current;
	}
	
	public void add(int val) {
		root = addRecur(root, val);
	}
}
