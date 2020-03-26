package AlgorithmPractice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class 모래성 {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x; this.y = y;
		}
	}
	static int h,w;
	static int[][] matrix;
	static int[][] memo;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static Queue<Point> q = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		String s;
		matrix = new int[h][w];
		for(int i = 0; i < h; i++) {
			s = br.readLine();
			for(int j = 0; j < w; j++) {
				if(s.charAt(j) == '.') matrix[i][j] = 0;
				else matrix[i][j] = s.charAt(j) - '0';
			}
		}
		int nx,ny;
		memo = new int[h][w];
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(matrix[i][j] == 0 || matrix[i][j] == 9) continue;
				for(int d = 0; d < 8; d++) {
					nx = i+dx[d];
					ny = j+dy[d];
					if(matrix[nx][ny] == 0) memo[i][j]++;
				}
				if(memo[i][j] < matrix[i][j]) continue;
				q.offer(new Point(i,j));
			}
		}
		System.out.println(wave());
	}
	public static int wave() {
		int nx,ny;
		int len;
		int count = 0;
		Point p;
		while(!q.isEmpty()) {
			count++;
			len = q.size();
			for(int i = 0; i < len; i++) {
				p = q.poll();
				if(matrix[p.x][p.y] == 0) continue;
				matrix[p.x][p.y] = 0;
				for(int d = 0; d < 8; d++) {
					nx = p.x+dx[d];
					ny = p.y+dy[d];
					if(matrix[nx][ny] > 0 && matrix[nx][ny] < 9) 
						memo[nx][ny]++;
					if(matrix[nx][ny] > 0 && memo[nx][ny] >= matrix[nx][ny] && memo[nx][ny] < 9) {
						memo[nx][ny] = 9;
						q.offer(new Point(nx,ny));
					}
								
				}
			}
		}
		return count;
	}
}