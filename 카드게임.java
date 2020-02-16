package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class 카드게임 {
	public static int n;
	public static int[][] card;
	public static int[][] f;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		card = new int[2][n];
		f = new int[n][n];
		for(int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				card[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(f[j], -1);
			}
		}
		System.out.println(game(0,0));
	}
	public static int game(int lx, int rx) {
		
		if(lx == n || rx == n) return 0;
		if(f[lx][rx] != -1) return f[lx][rx];
		
		f[lx][rx] = Math.max(game(lx+1, rx), game(lx+1, rx+1));
		if(card[0][lx] > card[1][rx]) f[lx][rx] = Math.max(f[lx][rx], game(lx,rx+1)+card[1][rx]);
		
		return f[lx][rx];
		
	}
}