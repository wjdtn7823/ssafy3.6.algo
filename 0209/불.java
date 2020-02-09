import java.util.*;
import java.io.*;

class Point {
	int x, y, t;

	Point() {

	}

	Point(int x, int y, int t) {
		this.x = x;
		this.y = y;
		this.t = t;
	}
}

public class บา {
	static void print_map(int n, int m, int map[][]) {
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
		System.setIn(new FileInputStream("res/input_fire.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		Queue<Point> jq = new LinkedList<Point>();
		Queue<Point> fq = new LinkedList<Point>();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int map[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = bf.readLine();

			for (int j = 0; j < m; j++) {
				char c = s.charAt(j);
				if (c == '#')
					map[i][j] = 1;
				if (c == 'J') {
					map[i][j] = 7;
					jq.add(new Point(i, j, 0));
				}
				if (c == 'F') {
					map[i][j] = 9;
					fq.add(new Point(i, j, 0));

				}
				if (c == '.')
					map[i][j] = 0;
			}
		}
		int[] dx = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };
		boolean flag = false;
		int answer = 0;
		wh: while (true) {

			int ss = fq.size();
			for (int i = 0; i < ss; i++) {
				Point f = fq.poll();

				for (int k = 0; k < 4; k++) {
					int nx = f.x + dx[k];
					int ny = f.y + dy[k];
					if (nx < 0 || ny < 0 || nx >= n || ny >= m)
						continue;

					if (map[nx][ny] == 0 || map[nx][ny] == 7) {
						map[nx][ny] = 9;

						fq.offer(new Point(nx, ny, f.t + 1));
					}

				}
			}
			int s = jq.size();
			for (int k = 0; k < s; k++) {
				Point j = jq.poll();

				for (int i = 0; i < 4; i++) {
					int nx = j.x + dx[i];
					int ny = j.y + dy[i];
					if (nx < 0 || ny < 0 || nx >= n || ny >= m) {

						flag = true;
						answer = j.t + 1;

						break wh;

					}
					if (map[nx][ny] != 0)
						continue;

					map[nx][ny] = 7;
					jq.offer(new Point(nx, ny, j.t + 1));
				}

			}
			if (jq.isEmpty())
				break;
			answer++;

		}

		if (!flag)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(answer);
	}

}
