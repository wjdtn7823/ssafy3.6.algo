package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Po {
	int x;
	int y;

	public Po(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class 불 {
	public static int r, c;
	public static char[][] map;
	public static Queue<Po> fire = new LinkedList<>();
	public static int[] dx = { 0, 0, 1, -1 };
	public static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] starr;
		starr = br.readLine().split(" ");
		r = Integer.parseInt(starr[0]);
		c = Integer.parseInt(starr[1]);
		map = new char[r][c];
		int x = 0, y = 0;
		for (int i = 0; i < r; i++) {
			starr[0] = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = starr[0].charAt(j);
				if (map[i][j] == 'F')
					fire.offer(new Po(i, j));
				if (map[i][j] == 'J') {
					x = i;
					y = j;
					map[i][j] = '.';
				}
			}
		}
		int answer = escape(x, y);
		if (answer == 0)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(answer);
	}

	public static int escape(int x, int y) {
		Queue<Po> q = new LinkedList<>();
		q.offer(new Po(x, y));
		int[][] time = new int[r][c];
		int a, b;
		Po p;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				p = q.poll();
				if (map[p.x][p.y] == 'F')
					continue;
				if (p.x == 0 || p.x == r - 1 || p.y == 0 || p.y == c - 1) {
					return time[p.x][p.y] + 1;
				}
				for (int d = 0; d < 4; d++) {
					a = p.x + dx[d];
					b = p.y + dy[d];
					if (map[a][b] == '.' && time[a][b] == 0) {
						time[a][b] = time[p.x][p.y] + 1;
						q.offer(new Po(a, b));
					}
				}
			}
			int fsize = fire.size();
			while (fsize-- > 0) {
				p = fire.poll();

				for (int d = 0; d < 4; d++) {
					a = p.x + dx[d];
					b = p.y + dy[d];
					if (a >= 0 && a < r && b >= 0 && b < c && map[a][b] == '.') {
						map[a][b] = 'F';
						fire.offer(new Po(a, b));
					}
				}
			}
		}
		return 0;
	}
}