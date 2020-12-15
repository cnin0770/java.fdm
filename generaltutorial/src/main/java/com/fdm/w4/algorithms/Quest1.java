package com.fdm.w4.algorithms;

import java.util.Arrays;
import java.util.Random;

class Quest1 {
	static int[] arrGenerator(int len) {
		
		Random rd = new Random();
		int[] arr = new int[len];
		for (byte i = 0; i < len; i++) {
			arr[i] = rd.nextInt(100);
		}
		Arrays.sort(arr);
		return arr;
	}
	
	static void mergeSort() {
		int[] arr1 = arrGenerator(10);
		int[] arr2 = arrGenerator(10);
		int[] arr3 = new int [arr1.length + arr2.length];
		
		int ind1 = 0, ind2 = 0;
		
		for (int ind3 = 0; ind3 < arr3.length; ++ind3) {
			if (ind2 >= arr2.length) {
				arr3[ind3] = arr1[ind1];
				++ind1;
			} else if (ind1 >= arr1.length) {
				arr3[ind3] = arr2[ind2];
				++ind2;
			} else if (arr1[ind1] < arr2[ind2]) {
				arr3[ind3] = arr1[ind1];
				++ind1;
			} 
			else {
				arr3[ind3] = arr2[ind2];
				++ind2;
			}
		}

		System.out.println("list1: ");
		System.out.println(Arrays.toString(arr1));
		System.out.println("list2: ");
		System.out.println(Arrays.toString(arr2));
		System.out.println("merged list: ");
		System.out.println(Arrays.toString(arr3));
	}
}
