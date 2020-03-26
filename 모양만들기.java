package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모양만들기 {
	static class Point {
		int x; int y;
		public Point(int x, int y) {
			this.x = x; this.y = y;
		}
	}
	static int n, m;
	static int[][] matrix;
	static List<Integer> island = new ArrayList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
 	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		matrix = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j ++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		island.add(0);
		changeMatrix();
		
//		for(int i = 0; i < n; i++) System.out.println(Arrays.toString(matrix[i]));
//		for(int i : island) System.out.println(i+" ");
		
		System.out.println(maxShape());
	}
	public static int maxShape() {
		int max = 0;
		int cnt = 1;
		int[] check = new int[island.size()];
		int nx, ny;
		int tmp;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j ++) {
				if(matrix[i][j] > 0) continue;
				tmp = 1;
				for(int d = 0; d < 4; d++) {
					nx = i+dx[d];
					ny = j+dy[d];
					if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
					if(check[matrix[nx][ny]] == cnt) continue;
					tmp += island.get(matrix[nx][ny]);
					check[matrix[nx][ny]] = cnt;
				}
				if(max < tmp) max = tmp;
				cnt++;
			}
		}
		
		return max;
	}
	public static void changeMatrix() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[n][m];
		int idx = 1;
		int size = 0;
		int nx, ny;
		Point p;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j ++) {
				if(visit[i][j]) continue;
				if(matrix[i][j] == 0) continue;
				q.offer(new Point(i,j));
				visit[i][j] = true;
				
				while(!q.isEmpty()) {
					p = q.poll();
					matrix[p.x][p.y] = idx;
					size++;
					for(int d = 0; d < 4; d++) {
						nx = p.x + dx[d];
						ny = p.y + dy[d];
						if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
						if(matrix[nx][ny] == 0 || visit[nx][ny]) continue;
						q.offer(new Point(nx,ny));
						visit[nx][ny] = true;
					}
				}
				island.add(size);
				size = 0;
				idx++;
			}
		}
	}
}
