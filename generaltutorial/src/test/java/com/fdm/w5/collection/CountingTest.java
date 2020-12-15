package com.fdm.w5.collection;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import org.junit.Test;

public class CountingTest {
	private String sentence = "hey you hey there hey whoever wherever whatever";
	LinkedList<Integer> list = new LinkedList<Integer>();
	
	public static double[] random_generator() {
		Random rand = new Random();

	    double[] array = new double[500];
	    for(int i =0; i < array.length; i++) {
	        array[i] = rand.nextDouble() * 1000;
	    }
	    return array;
	}

	@Test
	public void count_letters() {
		assertEquals(12, CountingLiteracy.countingLetters(sentence).get('e'), .01);
	}

	@Test
	public void count_words() {
		assertEquals(3, CountingLiteracy.countingWords(sentence).get("hey"), .01);
	}
	
	@Test
	public void reverse_list() {
		list.add(1);
		list.add(2);
		list.add(3);
		assertEquals(3, CountingLiteracy.reverseList(list).getFirst(), .01);
	}
	
	@Test
	public void descending_treeset() {
		assertEquals("hey", CountingLiteracy.tokenizedText(sentence).last());
	}
	
	@Test
	public void descending_double() {
		double[] nums = random_generator();
		System.out.println(Arrays.stream(nums).max());
		assertEquals(Arrays.stream(nums).max().orElse(0), CountingLiteracy.descendingNum(nums).peek(), .01);
	}
}
