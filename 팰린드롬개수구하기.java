package AlgorithmPractice;

import java.util.Scanner;
public class 팰린드롬개수구하기 {
	static final int MOD = 10007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int[][] dp = new int[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++) {
			dp[i][i] = 1;

			if (i != s.length() - 1) {
				if (s.charAt(i) == s.charAt(i + 1))
					dp[i][i + 1] = 3;
				else
					dp[i][i + 1] = 2;
			}
		}

		for (int len = 2; len < s.length(); len++) {
			for (int i = 0; i < s.length(); i++) {
				if (i + len >= s.length())
					break;

				dp[i][i + len] = (dp[i + 1][i + len]+ dp[i][i + len - 1]- dp[i + 1][i + len - 1]) % MOD;
				if (s.charAt(i) == s.charAt(i + len))
					dp[i][i + len] += (dp[i + 1][i + len - 1] + 1) % MOD;
				
				dp[i][i+len] = (dp[i][i+len] + MOD) % MOD;
				
			}
		}
		System.out.println(dp[0][s.length() - 1]);
	}
}
