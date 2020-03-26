package AlgorithmPractice;

import java.io.*;
import java.util.*;

public class 팀원모집 {
	static int n, m;
	static boolean[][] stu;
	static boolean possible;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		stu = new boolean[m+1][n+1];
		int k;
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			for(int l = 0; l < k; l++) {
				stu[i][Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		for(int r = 1; r <= m; r++) {
			team(1,0,r,0,new boolean[n+1]);
			if(possible) {
				System.out.println(r);
				break;
			}
		}
		if(!possible) System.out.println(-1); 
	}
	public static void team(int idx, int cnt, int r, int solve, boolean[] check) {
		if(possible) return;
		if(cnt == r) {
			if(solve == n) possible = true;
			return;
		}
		
		int p;
		boolean[] copy;
		for(int i = idx; i <= m; i++) {
			if(possible) return;
			p = 0;
			copy = check.clone();
//			i번째 사람을 고르면 
			for(int c = 1; c <= n; c++) {
				if(stu[i][c] && !copy[c]) {
					p++;
					copy[c] = true;
				}
			}
			team(i+1, cnt+1, r, solve+p, copy);
		}
	}
}
