package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일학년 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st= new StringTokenizer(br.readLine());
		int[] num = new int[n+1];
		for(int i = 1; i <= n; i++) 
			num[i] = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[n+1][21];
		dp[1][num[1]] = 1;
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j <= 20; j++) {
				if(dp[i-1][j] == 0) continue;
				if(j + num[i] <= 20) 
					dp[i][j+num[i]] += dp[i-1][j];
				if(j - num[i] >= 0)
					dp[i][j-num[i]] += dp[i-1][j];
			}
		}
		System.out.println(dp[n-1][num[n]]);
	}

}
