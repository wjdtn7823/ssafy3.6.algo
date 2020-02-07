package yun_prac;

import java.util.Scanner;

public class Main_1103 {

	public static int[][] board;
	public static boolean[][] visited;
	public static int maxCount = Integer.MIN_VALUE;
	public static int[][] memo;
	public static boolean loop;

	public static int[] dx = { 0, 1, -1, 0 };
	public static int[] dy = { 1, 0, 0, -1 };

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();

		board = new int[n][m];
		String str;

		for (int i = 0; i < n; i++) {
			str = sc.nextLine();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == 'H') 
					board[i][j] = 0;
				else board[i][j] = str.charAt(j) - '0';
			}
		}
		visited = new boolean[n][m];
		visited[0][0] = true;
		memo = new int[n][m];

		maxCount = Counting(0, 0);
		if (loop) maxCount = -1;
		System.out.println(maxCount);

	}

	public static int Counting(int x, int y) {
		
		if(loop) return Integer.MIN_VALUE;
		
		if(board[x][y] == 0) {
			return -1;
		}
		
		int max = 0;
		int tmp = 0;
		int i,j;
		
		for(int d = 0; d < 4; d++) {
			i = x+(dx[d]*board[x][y]);
			j = y+(dy[d]*board[x][y]);
			if(i >= 0 && j >= 0 && i < board.length && j < board[0].length) {
				if(visited[i][j]) {
					loop = true;
					return -1;
				}
				else {
					
					visited[i][j] = true;
					
					if(memo[i][j] != 0) {
						tmp = memo[i][j];
					} else {
						tmp = Counting(i, j);
					}
					
					visited[i][j] = false;
				}
			}
			
			if(tmp > max) {
				max = tmp;
			}
			
		}
		
		memo[x][y] = 1 + max;
		
		return memo[x][y];
		
	}

}
