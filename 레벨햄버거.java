package AlgorithmPractice;

import java.util.Arrays;
import java.util.Scanner;

public class 레벨햄버거 {
	static long[] size;
	static long[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long x = sc.nextLong();
		
		size = new long[n+1];
		p =  new long[n+1];
		size[0] = 1;
		p[0] = 1;
		for(int i = 1; i <= n; i++) {
			size[i] = 2 * size[i-1]+3;
			p[i] = 2 * p[i-1] + 1;
		}
		System.out.println(count(n,x));
	}
	public static long count(int n, long x) {
		if(x <= n) return 0;
		if(n == 0) return 1;
		if(x < size[n-1]+1) return count(n-1, x-1);
		if(x == size[n-1]+1) return p[n-1];
		if(x == size[n-1]+2) return 1+p[n-1];
		else return 1+p[n-1]+count(n-1,x-size[n-1]-2);
	}
	
}
