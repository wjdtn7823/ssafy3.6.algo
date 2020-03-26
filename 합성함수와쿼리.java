package AlgorithmPractice;

import java.io.*;
import java.util.*;

public class 합성함수와쿼리 {
	static int f[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		f = new int[500001][19];
		for(int i = 1; i <= m; i ++) 
			f[i][0] = Integer.parseInt(st.nextToken());
		
		for(int j = 1; j < 19; j++) {
			for(int i = 1; i <= m; i++) {
				f[i][j] = f[f[i][j-1]][j-1];
			}
		}
		
		int q = Integer.parseInt(br.readLine());
		int n,x;
		for(int i = 1; i <= q; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			
			int idx = 0;
			for(int j = 18; j >= 0; j--) {
				if(n >= 1<<j) {
					n -= 1<<j;
					x = f[x][j];
				}
			}
			sb.append(x).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
