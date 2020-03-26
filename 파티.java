package AlgorithmPractice;

import java.io.*;
import java.util.*;

public class 파티 {
	static final int INF = 987654321;
	static int n, m, x;
	static int[][] bridge;
	static int[][] dist;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		dist = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				dist[i][j] = i==j? 0 : INF;
			}
		}
		int a,b;
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			dist[a][b] = Integer.parseInt(st.nextToken());
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		
		int tmp, max = 0;
		for(int i = 1; i <= n; i ++) {
			tmp = dist[i][x] + dist[x][i];
			if(max < tmp)
				max = tmp;
		}
		
		System.out.println(max);
		
	}
}
