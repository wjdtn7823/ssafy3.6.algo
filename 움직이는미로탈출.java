package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class 움직이는미로탈출 {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static char[][] mat;
	public static Queue<Point> wall;
	public static int[] dx = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
	public static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		mat = new char[8][8];
		wall = new LinkedList<>();
		String s;
		for (int i = 0; i < 8; i++) {
			mat[i] = br.readLine().toCharArray();
		}
		
		for(int i = 7; i >= 0; i--) {
			for(int j = 0; j < 8; j++) {
				if(mat[i][j] == '#') wall.add(new Point(i,j));
			}
		}
		System.out.println(move());
	}
	public static int move() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit;
		q.offer(new Point(7, 0));
		Point p;
		int leng;
		int wleng;
		Point wa;
		while (!q.isEmpty()) {
			leng = q.size();
			
			if(wall.isEmpty()) return 1;
			else visit = new boolean[8][8];
			
			for (int i = 0; i < leng; i++) {
				p = q.poll();
				if (mat[p.x][p.y] == '#')
					continue;
				if (p.x == 0 && p.y == 7) {
					return 1;
				}
				for (int d = 0; d < 9; d++) {
					if (p.x + dx[d] >= 0 && p.x + dx[d] <= 7 && p.y + dy[d] >= 0 && p.y + dy[d] <= 7
							&& !visit[p.x+dx[d]][p.y+dy[d]] && mat[p.x + dx[d]][p.y + dy[d]] == '.') {
						q.offer(new Point(p.x + dx[d], p.y + dy[d]));
						visit[p.x+dx[d]][p.y+dy[d]] = true;
					}
				}
			}
			wleng = wall.size();
			for (int w = 0; w < wleng; w++) {
				wa = wall.poll();
				mat[wa.x][wa.y] = '.';
				if (wa.x < 7) {
					wall.offer(new Point(wa.x + 1, wa.y));
					mat[wa.x + 1][wa.y] = '#';
				}
			}
		}
		return 0;
	}
}
