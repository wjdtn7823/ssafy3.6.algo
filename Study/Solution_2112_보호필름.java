package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름 {

	static int T, D, W, K, map[][] = new int[14][21], ans;	
	static String st[] = {};
	
	static boolean test_(int a[][]) {
		for (int i = 0; i < W; i++) {
	        boolean is_okay = false;
	        for (int j = 0; j < D - (K - 1); j++) {
	            int tmp = 0;
	            for (int k = 0; k < K; k++) {
	                tmp += a[j + k][i];
	            }
	            if (tmp == 0 || tmp == K) {
	                is_okay = true; break;
	            }
	        }if (!is_okay)return false;
	    }
		return true;
	}
	
	static void dfs(int idx, int cnt, int a[][]) {
	    // 테스트하는 영역
	    if (cnt < ans) {
	        if (test_(a)) {
	            ans = Math.min(cnt, ans);
	            return;
	        }
	    }
	    if (cnt == ans - 1) return;
	     
	    int tmp_map[] = new int[21];
	     
	    for (int i = idx; i < D; i++) {
	        for (int j = 0; j < W; j++) {
	            tmp_map[j] = a[i][j]; a[i][j] = 1;
	        }
	        dfs(i + 1, cnt + 1, a);
	         
	        for (int j = 0; j < W; j++) a[i][j] = 0;
	        dfs(i + 1, cnt + 1, a);
	        for (int j = 0; j < W; j++) a[i][j] = tmp_map[j];
	    }
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = br.readLine().split(" ");
			D = Integer.parseInt(st[0]); W = Integer.parseInt(st[1]); K = Integer.parseInt(st[2]);
		    ans = K;
		    for (int i = 0; i < D; i++) {
		    	st = br.readLine().split(" ");
		    	for (int j = 0; j < W; j++) {
		    		map[i][j] = Integer.parseInt(st[j]);
		    	}
		    }
		    dfs(0, 0, map);
		    System.out.printf("#%d %d\n", test_case, ans);
		}
		
	}

}
