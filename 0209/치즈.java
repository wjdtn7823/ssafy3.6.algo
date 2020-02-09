import java.util.*;
import java.io.*;

class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static void print_map(int n, int m, int[][] map) {
		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// System.setIn(new FileInputStream("res/input_cheese.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<Point> cheese = new ArrayList<Point>();
		int[][] map = new int[n][m];
		int left = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());

			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					left++;
			}
		}
		int answer = 0;
		final int[] dx = { -1, 1, 0, 0 };
		final int dy[] = { 0, 0, -1, 1 };

		while (left > 0) {
			Queue<Point> air = new LinkedList<Point>();
			int[][] v = new int[n][m];
			air.add(new Point(0, 0));
			// print_map(n,m,map);
			while (!air.isEmpty()) {
				Point p = air.poll();
				for (int i = 0; i < 4; i++) {
					int nx = dx[i] + p.x;
					int ny = dy[i] + p.y;
					if (nx < 0 || ny < 0 || nx >= n || ny >= m)
						continue;
					if (v[nx][ny] > 0 && map[nx][ny] == 0)
						continue;
					v[nx][ny]++;
					if (map[nx][ny] == 0)
						air.add(new Point(nx, ny));
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1 && v[i][j] >= 2) {
						map[i][j] = 0;
						left--;
					}
				}
			}

			answer++;
		}

		System.out.println(answer);
	}

}
