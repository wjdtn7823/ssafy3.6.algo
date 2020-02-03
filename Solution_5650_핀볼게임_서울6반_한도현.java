package Study; //16시 50분 시작

import java.io.*;
import java.util.*;


class Position{
	int y;
	int x;
	Position(){
		
	}
	Position(int a, int b){
		y = a; x =b;
	}
}

public class Solution_5650_핀볼게임_서울6반_한도현 {
	public static int N;
	public static int[] dy = { -1, 0, 1, 0 };
	public static int[] dx = { 0, 1, 0, -1 };
	public static int[][] board = new int[104][104];
	public static int[][][] warmHoll = new int[5][2][2];
	public static int ans;
	public static int start_y, start_x;
	public static boolean flag;

	public static int dfs(int y, int x, int dir) {
		Queue<Position> q = new LinkedList<>();
		q.add(new Position(y,x));
		int ret = 0;
		while(true){
			Position p = q.poll();
			if(p.y == start_y && start_x==p.x){
				if (flag == true)
					return ret;
				flag = true;
			}
			int ny = p.y + dy[dir];
			int nx = p.x + dx[dir];
			//System.out.println("현재 위치 : ("+ny +"," +nx +")");

			int next = board[ny][nx];
			
			if (next >= 1 && next <= 5) { // 블록일 경우
				ret++;
				if (dir == 0) {
					if (next == 1 || next == 4 || next == 5) { // 왔던 길을 다시 가게 되는 경우						
						dir= (dir+2)%4;
						q.add(new Position(ny,nx));
					} else if (next == 2) { // 1번 방향으로 꺾임
						dir =1;
						q.add(new Position(ny,nx));
					} else if (next == 3) {
						dir =3;
						q.add(new Position(ny,nx));
					}
				} else if (dir == 1) {
					if (next == 1 || next == 2 || next == 5) {
						dir= (dir+2)%4;
						q.add(new Position(ny,nx));
					} else if (next == 3) {
						dir =2;
						q.add(new Position(ny,nx));
					} else if (next == 4) {
						dir =0;
						q.add(new Position(ny,nx));
					}
				} else if (dir == 2) {
					if (next == 2 || next == 3 || next == 5) {
						dir= (dir+2)%4;
						q.add(new Position(ny,nx));
					} else if (next == 1) {
						dir =1;
						q.add(new Position(ny,nx));
					} else if (next == 4) {
						dir =3;
						q.add(new Position(ny,nx));
					}
				} else if (dir == 3) {
					if (next == 3 || next == 4 || next == 5) {
						dir= (dir+2)%4;
						q.add(new Position(ny,nx));
					} else if (next == 1) {
						dir =0;
						q.add(new Position(ny,nx));
					} else if (next == 2) {
						dir =2;
						q.add(new Position(ny,nx));
					}
				}
			} else if (next >= 6 && next <= 10) { // 웜홀일 경우
				if (warmHoll[next - 6][0][0] == ny && warmHoll[next - 6][0][1] == nx) {
					ny = warmHoll[next - 6][1][0];
					nx = warmHoll[next - 6][1][1];
				} else {
					ny = warmHoll[next - 6][0][0];
					nx = warmHoll[next - 6][0][1];
				}
				q.add(new Position(ny,nx));
				continue;
			} else if (next == -1) { // 블랙홀일 경우
				return ret;
			} else { // 그냥 0일경우
				q.add(new Position(ny,nx));
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int A = 1; A <= tc; A++) {
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < 5; i++) {
				warmHoll[i][0][0] = -1;
			}
			for (int i = 0; i <= N + 1; i++) {
				board[0][i] = 5;
				board[N + 1][i] = 5;
				board[i][0] = 5;
				board[i][N + 1] = 5;
			}
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int cnt = 1;
				while (st.hasMoreTokens()) {
					board[i][cnt++] = Integer.parseInt(st.nextToken());
					if (board[i][cnt - 1] >= 6 && board[i][cnt - 1] <= 10) {
						// 등록되지 않은 웜홀이면
						if (warmHoll[board[i][cnt - 1] - 6][0][0] == -1) {
							warmHoll[board[i][cnt - 1] - 6][0][0] = i;
							warmHoll[board[i][cnt - 1] - 6][0][1] = cnt - 1;
						}
						// 등록된(쌍이 될 것이 존재할 때)
						else {
							warmHoll[board[i][cnt - 1] - 6][1][0] = i;
							warmHoll[board[i][cnt - 1] - 6][1][1] = cnt - 1;
						}
					}
				}
			}
			int candi = 0;
			ans = 0;
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (board[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							start_y = i;
							start_x = j;
							flag = false;
							candi = dfs(i, j, k);
							ans = Math.max(ans, candi);
						}
					}
				}
			}
			System.out.println("#" +A+" "+ ans);
		}
	}

}
