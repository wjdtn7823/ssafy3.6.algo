package Study;

import java.io.*;
import java.util.*;

public class Main_14502_연구소_서울6반_한도현 {
	public static int N, M;
	public static int safe_zone;
	public static int ans;
	public static int[] dy = { -1, 0, 1, 0 };
	public static int[] dx = { 0, 1, 0, -1 };
	public static int[][] board = new int[9][9];
	public static boolean[][] visited = new boolean[9][9];

	public static void init_visited() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = false;
			}
		}
	}

	public static int dfs(int y, int x) {
		visited[y][x] = true;
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int ny = dy[i] + y;
			int nx = dx[i] + x;
			if (ny < 0 || nx < 0 || ny >= N || nx >= M)
				continue;
			if (visited[ny][nx])
				continue;
			if (board[ny][nx] == 1)
				continue;
			if (board[ny][nx] == 2)
				continue;
			ret += dfs(ny, nx);
		}
		return ret + 1;
	}

	public static void simulation() {
		int candi = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 2) { // 바이러스를 만나면 퍼뜨리기
					candi += dfs(i, j);
				}
			}
		}
		ans = Math.max(ans, safe_zone - candi);
	}

	public static void select(int y, int x, int cnt) {
		if (cnt == 3) { // 벽을 3개 세웠을 때
			init_visited();
			simulation();
			
			return;
		}
		for (int i = y; i < N; i++) {
			for (int j = x; j < M; j++) {
				if (board[i][j] == 2 || board[i][j] == 1)
					continue;
				board[i][j] = 1; // 벽 세우기
				select(i, j + 1, cnt + 1);
				board[i][j] = 0;
			}
			x = 0;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = 0;
			while (st.hasMoreTokens()) {
				board[i][cnt++] = Integer.parseInt(st.nextToken());
				if (board[i][cnt - 1] == 0 || board[i][cnt - 1] == 2) {
					safe_zone++;
				}
			}
		}
		// System.out.println(safe_zone);
		safe_zone -= 3;
		select(0, 0, 0);
		System.out.println(ans);
	}

}
