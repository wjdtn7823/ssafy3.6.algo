import java.util.*;
import java.io.*;

class hPoint {
	int x;
	int y;

	hPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class 치킨배달 {
	public static int n, m;
	public static int[][] map;
	public static int[][] distancemap;
	public static boolean[] visited;
	public static LinkedList<hPoint> hlist;
	public static LinkedList<hPoint> clist;

	public static int answer = 9999;

	public static void dfs(int idx, int cnt) {

		if (cnt == m) {

			int partial_answer = 0;

			for (int i = 0; i < hlist.size(); i++) {
				int ith_min_distance = 9999;

				for (int j = 0; j < clist.size(); j++) {

					if (!visited[j])
						continue;

					ith_min_distance = Math.min(ith_min_distance, distancemap[j][i]);

				}
				partial_answer += ith_min_distance;

			}

			if (answer > partial_answer)
				answer = partial_answer;
			return;
		}

		for (int i = idx; i < clist.size(); i++) {
			visited[i] = true;
			dfs(i + 1, cnt + 1);
			visited[i] = false;

		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		hlist = new LinkedList<hPoint>();
		clist = new LinkedList<hPoint>();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		distancemap = new int[100][100];
		visited = new boolean[15];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					clist.add(new hPoint(i, j));
				if (map[i][j] == 1)
					hlist.add(new hPoint(i, j));
			}
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				distancemap[i][j] = 99999;
			}
		}
		for (int i = 0; i < clist.size(); i++) {
			for (int j = 0; j < hlist.size(); j++) { // i번째 치킨집에서 j번째 집까지의 거리
				hPoint c = clist.get(i);
				hPoint h = hlist.get(j);

				distancemap[i][j] = Math.abs(c.x - h.x) + Math.abs(c.y - h.y);
			}
		}
		dfs(0, 0);

		System.out.println(answer);
	}

}