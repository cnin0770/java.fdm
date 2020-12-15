package com.fdm.w4.fizzbuzz2;

class FizzBuzzRunner {
	void fizzBuzz(int ceil) {
		int i = 1;

		while (i <= ceil) {
			System.out.println(
					(fizzbuzz(i)) ? "FizzBuzz" : (fizz(i)) ? "Fizz" : (buzz(i)) ? "Buzz" : Integer.toString(i)
							);
			++i;
		}
	}

	private boolean fizz(int num) {
		return num % 3 == 0 && num % 5 != 0;
	}

	private boolean buzz(int num) {
		return num % 3 != 0 && num % 5 == 0;
	}

	private boolean fizzbuzz(int num) {
		return num % 15 == 0;
	}
}
