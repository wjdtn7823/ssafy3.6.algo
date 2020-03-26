package AlgorithmPractice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class 성곽 {
	static class Point {
		int x; int y;
		public Point(int x, int y) {
			this.x = x; this.y = y;
		}
	}
	static int n, m;
	static int[][] castle;
	static int[][] roomNum;
	static List<Integer> room = new ArrayList<>();
	static int maxSize = 0;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
 	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		castle = new int[m][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j ++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		room.add(0);
		roomNum = new int[m][n];
		countRoomNum();
		System.out.println(room.size()-1);
		System.out.println(maxSize);
		System.out.println(makeBigRoom());
	}
	public static int makeBigRoom() {
		int max = maxSize;
		boolean[][] visit = new boolean[m][n];
		int nx, ny;
		int tmp;
		int bit;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j ++) {
				visit[i][j] = true;
				bit = 1;
				for(int d = 0; d < 4; d++) {
					nx = i+dx[d];
					ny = j+dy[d];
					if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
					if((castle[i][j]&bit) == bit && roomNum[i][j] != roomNum[nx][ny] && !visit[nx][ny]) {
						tmp = room.get(roomNum[i][j]) + room.get(roomNum[nx][ny]);
						if(max < tmp) max = tmp;
					}
					bit = bit<<1;
				}
			}
		}
		return max;
	}
	public static void countRoomNum() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[m][n];
		int idx = 1;
		int size = 0;
		int nx, ny;
		Point p;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j ++) {
				if(visit[i][j]) continue;
				q.offer(new Point(i,j));
				visit[i][j] = true;
				while(!q.isEmpty()) {
					p = q.poll();
					roomNum[p.x][p.y] = idx;
					size++;
					for(int d = 0; d < 4; d++) {
						nx = p.x + dx[d];
						ny = p.y + dy[d];
						if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
						if(visit[nx][ny]) continue;
						if((castle[p.x][p.y]&(1<<d)) == 0 && roomNum[nx][ny] == 0) {
							q.offer(new Point(nx,ny));
							visit[nx][ny] = true;
						}
					}
				}
				room.add(size);
				if(maxSize < size) maxSize = size;
				size = 0;
				idx++;
			}
		}
	}
}
