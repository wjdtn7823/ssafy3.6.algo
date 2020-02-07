package yun_prac;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_14890 {
	public static boolean possible;
	public static int answer;
	public static int[] dx = {0,1};
	public static int[] dy = {1,0};
	public static int[][] arr;
	public static boolean[] road;
	public static int n,l;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		n = sc.nextInt();
		l = sc.nextInt();
		sc.nextLine();
		arr = new int[n][n];
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(sc.nextLine());
			for(int j =0 ; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < n; i++) {
			//오
			check(i,0,0);
			//아
			check(0,i,1);
		}
		System.out.println(answer);
	}

	public static void check(int x, int y, int d) {
		int xx= x;
		int yy= y;
		int cnt = 1;
		int a,b;
		for(int i = 0; i < n-1; i++) {
			if(Math.abs(arr[x][y]-arr[x+dx[d]][y+dy[d]]) > 1) {
				return ;
			}
			if(arr[x][y] == arr[x+dx[d]][y+dy[d]]) {
				cnt++;
			}
			else if(arr[x][y] > arr[x+dx[d]][y+dy[d]]) {
				a = x+dx[d];
				b = y+dy[d];
				for(int j =0; j < l-1; j++) {
					x+=dx[d];
					y+=dy[d];
					
					if(x+dx[d] >= n || y+dy[d] >= n || arr[x+dx[d]][y+dy[d]] != arr[a][b]) {
						return;
					}
					i++;
				}
				cnt = 0;
			}
			else {
				if(cnt < l) return;		
				cnt = 1;
			}
			x += dx[d];
			y += dy[d];
		}
		answer++;
	}
}