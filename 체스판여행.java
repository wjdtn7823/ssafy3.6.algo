package AlgorithmPractice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 체스판여행 {
	static class Point {
		int x;
		int y;
		int count;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n;
	static int r2,c2;
	static int[] dx = {-2,-2,0,0,2,2};
	static int[] dy = {-1,1,-2,2,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int r1 = sc.nextInt();
		int c1 = sc.nextInt();
		r2 = sc.nextInt();
		c2 = sc.nextInt();
		System.out.println(move(new Point(r1,c1)));
	}
	public static int move(Point start) {

		boolean[][] visit = new boolean[n][n];
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visit[start.x][start.y] = true;
		
		Point p , tmp;
		while(!q.isEmpty()) {
			p = q.poll();
			
			if(p.x == r2 && p.y == c2) {
				return p.count;
			}
			
			for(int d = 0; d < 6; d++) {
				if(p.x+dx[d]>=0 && p.x+dx[d]<n && p.y+dy[d]>=0 && p.y+dy[d]<n
						&& !visit[p.x+dx[d]][p.y+dy[d]]) {
					tmp = new Point(p.x+dx[d], p.y+dy[d]);
					tmp.count = p.count+1;
					q.add(tmp);
					visit[p.x+dx[d]][p.y+dy[d]] = true;
				}
			}
		}
		return -1;
	}
}
