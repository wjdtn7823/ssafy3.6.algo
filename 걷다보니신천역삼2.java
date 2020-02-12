package AlgorithmPractice;
import java.util.Scanner;

public class 걷다보니신천역삼2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println((long)counting(n));
	}
	public static long counting(int n) {
		if(n == 1) return 0;
		long result = 2;
		long m = 1000000009;
		for(int i = 2; i < n; i++) {
			result = (long) result * (long) 3;
			result %= m;
		}
		
		return result;
	}
}
