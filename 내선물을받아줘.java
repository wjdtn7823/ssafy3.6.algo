package AlgorithmPractice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class 내선물을받아줘 {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x; this.y = y;
		}
	}
	static int n,m;
	static int[][] map;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		String s;
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			s = br.readLine();
			for(int j = 0; j<m; j++) {
				switch(s.charAt(j)) {
				case 'N':
					map[i][j] = 0;
					break;
				case 'W':
					map[i][j] = 1;
					break;
				case 'E':
					map[i][j] = 2;
					break;
				case 'S':
					map[i][j] = 3;
					break;
				}
			}
		}
		System.out.println(count());
	}
	public static int count() {
		int count = 0;
		boolean[][] visit = new boolean[n][m];
		Queue<Point> q = new LinkedList<>();
		Point p;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(visit[i][j]) continue;
				q.offer(new Point(i,j));
				visit[i][j] = true;
				
				while(!q.isEmpty()) {
					p = q.poll();
					for(int d = 0; d < 4; d++) {
						if(p.x+dx[d] < 0 || p.x+dx[d] >= n || p.y+dy[d] < 0 || p.y+dy[d] >= m) continue;
						if(visit[p.x+dx[d]][p.y+dy[d]]) continue;
						if(d == map[p.x][p.y] || 
								(p.x+dx[d]+dx[map[p.x+dx[d]][p.y+dy[d]]] == p.x && p.y+dy[d]+dy[map[p.x+dx[d]][p.y+dy[d]]] == p.y)) {
							q.offer(new Point(p.x+dx[d], p.y+dy[d]));
							visit[p.x+dx[d]][p.y+dy[d]] = true;
						}
					}
				}
				count++;
			}
		}
		return count;
	}
}
