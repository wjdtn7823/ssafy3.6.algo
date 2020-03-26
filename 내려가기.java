package AlgorithmPractice;

import java.io.*;
import java.util.*;
public class 내려가기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int num;
		int[] arr = new int[3];
		int[][][] dp = new int[2][n][3];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				num = Integer.parseInt(st.nextToken());
				dp[0][i][j] = num;
				dp[1][i][j] = num;
				if(i==0) continue;
				
				switch(j) {
				case 0:
					dp[0][i][j] += Math.max(dp[0][i-1][0], dp[0][i-1][1]);
					dp[1][i][j] += Math.min(dp[1][i-1][0], dp[1][i-1][1]);
					break;
				case 1:
					dp[0][i][j] += Math.max(dp[0][i-1][0], Math.max(dp[0][i-1][1], dp[0][i-1][2]));
					dp[1][i][j] += Math.min(dp[1][i-1][0], Math.min(dp[1][i-1][1], dp[1][i-1][2]));
					break;
				case 2: 
					dp[0][i][j] += Math.max(dp[0][i-1][1], dp[0][i-1][2]);
					dp[1][i][j] += Math.min(dp[1][i-1][1], dp[1][i-1][2]);
					break;
				}
			}
		}
		
		System.out.print(Math.max(dp[0][n-1][0], Math.max(dp[0][n-1][1], dp[0][n-1][2])));
		System.out.println(" "+Math.min(dp[1][n-1][0], Math.min(dp[1][n-1][1], dp[1][n-1][2])));
		
	}

}
