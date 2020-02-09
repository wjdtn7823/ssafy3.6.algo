package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 치즈 {
	
	public static int n,m;
	public static int[][] arr;
	public static int cheeseCount = 0;
	public static boolean[][] air;
	
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) cheeseCount++;
			}
		}
		System.out.println(cheese());
	}
	public static void check(int x, int y) {
		air[x][y] = true;
		int a,b;
		for(int d = 0; d < 4; d++) {
			a = x+dx[d];
			b = y+dy[d];
			if(a>=0 && a<n && b>=0 && b<m && arr[a][b] == 0 && !air[a][b]) {
				check(a,b);
			}
		}
	}
	public static int cheese() {
		int day = 1;
		int count = 0;
		while(true) {
			air = new boolean[n][m];
			check(0,0);
			for(int i = 0; i < n; i++) {
				for(int j = 0 ; j < m; j++) {
					if(arr[i][j] == 1) {
						count = 0;
						for(int d = 0; d < 4; d++) {
							if(air[i+dx[d]][j+dy[d]]) count++;
						}
						if(count >= 2) {
							arr[i][j] = 0;
							cheeseCount--;
						}
					}
				}
			}
			if(cheeseCount == 0) break;
			day++;
		}
		return day;
	}
}
