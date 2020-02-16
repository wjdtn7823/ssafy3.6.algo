package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TwoDots {
	static class Point {
		int x;
		int y;
		int px, py;
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n,m;
	static char[][] matrix;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		matrix = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			matrix[i] = br.readLine().toCharArray();
		}

		if(isCycle()) System.out.println("Yes");
		else System.out.println("No");
		
		br.close();
	}
	public static boolean isCycle() {

		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		Queue<Point> q = new LinkedList<>();
		
		Point p, tmp;
		char color;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j]) {
					q.offer(new Point(i,j));
					color = matrix[i][j];
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						p = q.poll();
						
						for(int d= 0 ; d < 4; d++) {
							if(p.x+dx[d] >= 0 && p.x+dx[d] < n && p.y+dy[d] >= 0 && p.y+dy[d] < m && matrix[p.x+dx[d]][p.y+dy[d]] == color) {
								if(visited[p.x+dx[d]][p.y+dy[d]] && p.px != p.x+dx[d] && p.py != p.y+dy[d]) {
									return true;
								} else if(!visited[p.x+dx[d]][p.y+dy[d]]) {
									tmp = new Point(p.x+dx[d], p.y+dy[d]);
									tmp.px = p.x;
									tmp.py = p.y;
									q.offer(tmp);
									visited[p.x+dx[d]][p.y+dy[d]] = true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

}
