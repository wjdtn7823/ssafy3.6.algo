package AlgorithmPractice;

import java.util.Scanner;
import java.util.StringTokenizer;
public class 견우와직녀 {
	public static int n,m;
	public static int minTime = Integer.MAX_VALUE;
	public static int[][] matrix;
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	public static boolean[][] visit;
	public static boolean cliff;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();
		StringTokenizer st; 
		matrix = new int[n][n]; 
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(sc.nextLine());
			for(int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean crossV, crossH;
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(matrix[i][j] == 0) {
					crossV = false;
					crossH = false;
					//겹치는오작교 부분이면 continue;
					for(int d = 0 ; d<4; d++) {
						if(i+dx[d] >= 0 && i+dx[d] < n && j+dy[d] >= 0 && j+dy[d] < n && matrix[i+dx[d]][j+dy[d]] != 1) {
							if(d <= 1) crossV = true;
							else crossH = true;
						}
					}
					if(crossV && crossH) continue;
					
					if(!cliff) cliff = true;
					matrix[i][j] = m;
					visit = new boolean[n][n];
					move(0,0,0,false);
					matrix[i][j] = 0;
				}
			}
		}
		if(!cliff) {
			visit = new boolean[n][n];
			move(0,0,0,false);
		}
		System.out.println(minTime);
	}
	public static void move(int x, int y, int time, boolean bridge) {
		if(time >= minTime) return;
		if(x == n-1 && y == n-1) {
			minTime = time;
			return;
		}
		int a,b;
		int t;
		for(int d = 0 ; d<4; d++) {
			a = x + dx[d];
			b = y + dy[d];
			if(a>=0 && a<n && b>=0 && b<n && !visit[a][b] && matrix[a][b] != 0) {
				visit[x][y] = true;
				if(matrix[a][b] > 1) {
					if(bridge) return;
					t = time;
					while((t+1)%matrix[a][b]!=0) t++;
					move(a,b,t+1,true);
				}
				else {
					move(a,b,time+1,false);
				}
				visit[x][y] = false;
			}
		}
	}
}