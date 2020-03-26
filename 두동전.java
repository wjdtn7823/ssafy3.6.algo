package AlgorithmPractice;

import java.io.*;
import java.util.*;

public class 두동전 {
	static class Point {
		int x; int y;
		public Point(int x, int y) {
			this.x= x; this.y=y;
		}
	}
	static int n,m;
	static char[][] board;
	static int min=99;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		String s;
		board = new char[n][m];
		Point c1 = null,c2 = null;
		for(int i = 0; i < n; i ++) {
			s = br.readLine();
			for(int j = 0; j<m; j++) {
				board[i][j] = s.charAt(j);
				if(board[i][j] == 'o') {
					if(c1 == null) c1 = new Point(i,j);
					else c2 = new Point(i,j);
				}
			}
		}
		
		game(c1.x, c1.y,c2.x, c2.y, 0);
		if(min == 99) min = -1;
		System.out.println(min);
	}
	public static void game(int c1x, int c1y, int c2x, int c2y, int cnt) {
		if(cnt > 10) return;
		if(cnt >= min) return;
		if(!rangeCheck(c1x, c1y) && !rangeCheck(c2x, c2y)) return;
		if(!rangeCheck(c1x, c1y) || !rangeCheck(c2x, c2y)) {
			min = cnt;
			return;
		}
		int nx,ny;
		int tmp1x, tmp1y, tmp2x, tmp2y;
		for(int d = 0; d < 4; d++) {
			nx = c1x+dx[d];
			ny = c1y+dy[d];
			if(rangeCheck(nx,ny) && board[nx][ny] == '#') {
				tmp1x = c1x;
				tmp1y = c1y;
			}
			else {
				tmp1x = nx;
				tmp1y = ny;
			}
			nx = c2x+dx[d];
			ny = c2y+dy[d];
			if(rangeCheck(nx,ny) && board[nx][ny] == '#') {
				tmp2x = c2x;
				tmp2y = c2y;
			}
			else {
				tmp2x = nx;
				tmp2y = ny;
			}
			game(tmp1x, tmp1y, tmp2x, tmp2y, cnt+1);
		}
	}
	public static boolean rangeCheck(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= m) return false;
		return true;
	}

}
