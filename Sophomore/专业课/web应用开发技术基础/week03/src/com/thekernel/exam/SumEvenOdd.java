package com.thekernel.exam;

public class SumEvenOdd {

//	实现一个求解1~100内偶数和、奇数和的程序。
	
	public static void main(String[] args) {
		int odd = 0;
		int even = 0;
		
		for(int i = 1; i <= 100; i++) {
			if(i % 2 == 0) {
				even += i;
			} else {
				odd += i;
			}
		}
		
		System.out.println("From 1 to 100:");
		System.out.println("Odd Sum is: " + odd);
		System.out.println("Even Sum is: " + even);
	}
	
}
