package com.fdm.w4.fizzbuzz1;

public class FizzBuzz {
	public static void main (String[] args) {
		int i = 0;
		
		while (i <= 3) 
		{
			if (i % 15 == 0) {
				System.out.println("FizzBuzz");
			}
			else if (i % 3 == 0) {
				System.out.println("Fizz");
			} 
			else if (i % 5 == 0) {
				System.out.println("Buzz");
			}
			else {
				System.out.println(i);
			}
			++i;
		}
		
		i = 100;
		i >>>= 1;
		System.out.println(i);
	}
}