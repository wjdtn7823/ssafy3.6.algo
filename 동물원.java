package AlgorithmPractice;

import java.util.Scanner;

public class 동물원 {
	static final int MOD = 9901;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 3;
		for(int i = 2; i <= n; i++) {
			dp[i] = (dp[i-1] * 2 + dp[i-2]) % MOD;
		}
		System.out.println(dp[n]);
	}

}
