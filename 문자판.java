package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 문자판 {
	static int n,m,k;
	static char[][] mat;
	static String voca;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		mat = new char[n][m];
		for(int i = 0; i < n; i++) 
			mat[i] = br.readLine().toCharArray();
		voca = br.readLine();
		int count = 0;
		dp = new int[n][m][voca.length()];
		for(int i = 0; i < n; i++) for(int j = 0; j < m; j++) Arrays.fill(dp[i][j], -1);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(mat[i][j] == voca.charAt(0)) {
					count+=dfs(i,j,0);
				}
			}
		}
		System.out.println(count);
	}

	public static int dfs (int x, int y, int idx) {
		if(idx == voca.length()-1) return 1;
		if(dp[x][y][idx] != -1) return dp[x][y][idx];
		int nx,ny;
		int tmp = 0;
		for(int d = 0; d < 4; d++) {
			for(int ki = 1; ki <= k; ki++) {
				nx = x+dx[d]*ki;
				ny = y+dy[d]*ki;
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if(mat[nx][ny] == voca.charAt(idx+1)) {
					tmp += dfs(nx,ny,idx+1);
				}
			}
		}
		return dp[x][y][idx] = tmp;
	}
}
