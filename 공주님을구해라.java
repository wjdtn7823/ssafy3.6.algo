package AlgorithmPractice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class 공주님을구해라 {
	static class Point {
		int x; 
		int y;
		int time;
		boolean sword;
		public Point(int x, int y) {
			this.x=x; this.y=y;
		}
	}
	static int n,m,t;
	static int[][] map;
	static int[] dx = {1,0,0,-1};
	static int[] dy = {0,-1,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = move();
		if(time==0) System.out.println("Fail");
		else System.out.println(time);
	}
	public static int move() {
		boolean[][][] visit = new boolean[2][n+1][m+1];
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(1,1));
		visit[0][1][1] = true;
		Point p, tmp;
		while(!q.isEmpty()) {
			p = q.poll();
			if(p.time > t) return 0;
			if(p.x == n && p.y == m) return p.time;
			if(map[p.x][p.y] == 2) {
				visit[1][p.x][p.y] = true;
				p.sword = true;
			}
			for(int d = 0; d < 4; d++) {
				if(p.x+dx[d] <= 0 || p.x+dx[d] > n || p.y+dy[d] <= 0 || p.y+dy[d] > m) continue;
				if(!p.sword && visit[0][p.x+dx[d]][p.y+dy[d]]) continue;
				if(p.sword && visit[1][p.x+dx[d]][p.y+dy[d]]) continue;
				
				if(!p.sword && map[p.x+dx[d]][p.y+dy[d]] == 1) continue;

				tmp = new Point(p.x+dx[d], p.y+dy[d]);
				tmp.time = p.time + 1;
				tmp.sword = p.sword;
				q.offer(tmp);
				if(!p.sword) visit[0][p.x+dx[d]][p.y+dy[d]] = true;
				if(p.sword) visit[1][p.x+dx[d]][p.y+dy[d]] = true;
			}
		}
		return 0;
	}
}