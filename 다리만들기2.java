package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2 {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int dist;
		public Edge(int start, int end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(dist, e.dist);
		}
	}
	static int n,m;
	static int[][] mat;
	static int[] parent;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int island;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		mat = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		island = mapChange();
		parent = new int[island+1];
		for(int i = 1; i < parent.length; i++)
			parent[i] = i;

		buildBridge();
		System.out.println(kruskal());
		
	}
	public static int kruskal() {
		Edge e;
		int minDist = 0;
		int countb = 0;

		while(!pq.isEmpty()) {
			e = pq.poll();
			if(findSet(e.start) != findSet(e.end)) {
				countb++;
				minDist += e.dist;
				union(e.start, e.end);
			}
		}
		
		if(countb != island-1) minDist = -1;
		return minDist;
	}
	public static void union(int start, int end) {
		start = findSet(start);
		end = findSet(end);
		if(start != end) parent[end] = start;
	}
	public static int findSet(int x) {
		if(x == parent[x]) return x;
		return parent[x] = findSet(parent[x]);
	}
	public static void buildBridge() {
		
		int ii, jj, leng;
		for(int i = 0; i < n; i ++) {
			for( int j = 0; j < m; j++) {
				if(mat[i][j] != 0) {
					for(int d = 0; d < 4; d++) {
						leng = 0;
						ii = i+dx[d];
						jj = j+dy[d];
						while(ii >= 0 && ii < n && jj >= 0 && jj < m) {
							if(mat[ii][jj] == 0) {
								leng++;
								ii = ii + dx[d];
								jj = jj + dy[d];
								continue;
							}
							if(mat[i][j] == mat[ii][jj]) break;
							if(mat[ii][jj] != 0) {
								if(leng >= 2) {
									pq.offer(new Edge(mat[i][j], mat[ii][jj], leng));
								}
								break;
							}
						}
					}
				}
			}
		}
	}
	public static int mapChange() {
		int island = 0;
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[n][m];
		
		Point p;
		for(int i = 0; i < n; i ++) {
			for( int j = 0; j < m; j++) {
				if(mat[i][j] == 1 && !visit[i][j]) {
					island++;
					q.add(new Point(i,j));
					mat[i][j] = island;
					visit[i][j] = true;
					
					while(!q.isEmpty()) {
						p = q.poll();
						for(int d = 0; d < 4; d++) {
							if(p.x+dx[d] >= 0 && p.x+dx[d] <n && p.y+dy[d] >= 0 && p.y+dy[d] <m) {
								if(mat[p.x+dx[d]][p.y+dy[d]] == 1 && !visit[p.x+dx[d]][p.y+dy[d]]) {
									q.add(new Point(p.x+dx[d], p.y+dy[d]));
									mat[p.x+dx[d]][p.y+dy[d]] = island;
									visit[p.x+dx[d]][p.y+dy[d]] = true;
								}
							}
						}
					}
				}
			}
		}
		return island;
	}
}
