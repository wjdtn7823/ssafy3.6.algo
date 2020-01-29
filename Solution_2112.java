package yun_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112 {
	
	public static int d,w,k; 
	public static int[][] film;
	
	public static boolean[] v;
	public static int[][] arr;
	public static int n;
	public static int[][] f;

	public static int answer=0;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc = 1; tc <= t; tc++) {
			answer=0;
			
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			film = new int[d][w];
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(k > 1 && !test(film)) {
				for(int i = 1; i < k; i++) {
					v = new boolean[d];
					arr = new int[i][2];
					chemical(0, 0, i);
					
					if(answer > 0) break;
				}
				
				if(answer == 0) answer = k;
			}
			
			System.out.println("#"+tc+" "+answer);
		}
		
	}
	

	public static void chemical(int start, int cnt, int n) {
		if(cnt == n) {
			f = new int[d][w];
			
			for(int i = 0; i < d; i++) {
				for(int j = 0; j < w; j++) {
					f[i][j] = film[i][j];
				}
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < w; j++) {
					f[arr[i][0]][j] = arr[i][1];
				}
			}
			
			
			if(test(f)) { 
				answer = n;
				return ;
			}
			
		}
		
		else {
			for(int i = start; i < d; i++) {
				if(!v[i]) {
					v[i] = true;
					arr[cnt][0] = i;
					arr[cnt][1] = 0;
					chemical(i, cnt+1, n);
					arr[cnt][1] = 1;
					chemical(i, cnt+1, n);
					v[i] = false;	
				}
				
			}
			
		}
			
	}

	public static boolean test(int[][] array) {
		int before, count;
		for(int j = 0; j < w; j++) {
			before = array[0][j];
			count = 1;
			for(int i = 1; i < d; i++) {
				if(array[i][j] == before) {
					count++;
				}
				else {
					before = array[i][j];
					count=1;
				}
				
				if(count == k) break;
			}
			
			if(count < k) return false;
		}
		
		return true;
	}

}
