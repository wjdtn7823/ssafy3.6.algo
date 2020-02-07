package yun_prac;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_14502 {
	
	public static int minVirus = Integer.MAX_VALUE;
	
	public static int n;
	public static int m;
	
	public static boolean[][] visit;
	public static Queue<Integer> qx;
	public static Queue<Integer> qy;
	
	public static int[] dx = {1,0,0,-1};
	public static int[] dy = {0,1,-1,0};
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();
		int wall = 0;
		
		int[][] arr = new int[n][m];
		String[] str = new String[n];
		for(int i = 0; i < n ; i ++) {
			str = sc.nextLine().split(" ");
			for(int j = 0; j < m; j++) {
				arr[i][j] = str[j].charAt(0) - '0';
				
				if(arr[i][j] == 1) wall++;
			}
		}
		
		//n*m
		
		solve(arr, 0, 0, 0);
		
		if(minVirus == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(n*m - (wall+3) - minVirus);
		
	}

	public static void solve(int[][] arr, int x, int y, int cnt) {

		if(cnt == 3) {
			int virus = 0;
			visit = new boolean[n][m];
			qx = new LinkedList<Integer>();
			qy = new LinkedList<Integer>();
			
			for(int i = 0; i < n ; i ++) {
				for(int j = 0; j < m; j++) {
					if(arr[i][j] == 2) {
						qx.add(i);
						qy.add(j);
						visit[i][j] = true;
					}
				}
			}
			
			while(!qx.isEmpty() && !qy.isEmpty()) {
				int a = qx.poll();
				int b = qy.poll();
				virus++;
				
				for(int d = 0; d < 4; d++) {
					if(a+dx[d] >= 0 && a+dx[d] < n && b+dy[d] >= 0 && b+dy[d] < m) {
						if(!visit[a+dx[d]][b+dy[d]] && arr[a+dx[d]][b+dy[d]] == 0) {
							qx.add(a+dx[d]);
							qy.add(b+dy[d]);
							visit[a+dx[d]][b+dy[d]] = true;
						}
					}
				}
				
				if(virus > minVirus) return;
			}
			
			
			if(virus < minVirus) 
				minVirus = virus;
			
			return;
		}

		for(int i = x; i < n; i++) {
			for(int j = y; j < m; j++) {
				
				if(arr[i][j] == 0) {
					
					arr[i][j] = 1;	
					solve(arr,i,j+1,cnt+1);
					arr[i][j] = 0;
				}
			
			}
			y = 0;
		}

		
	}

}







