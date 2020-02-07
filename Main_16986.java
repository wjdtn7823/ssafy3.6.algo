package yun_prac;
import java.util.Scanner;

public class Main_16986 {
	public static boolean isPossible;
	public static int[][] arr;
	public static int[][] friend;
	public static int n, k;
	public static boolean[] visit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		arr = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		friend = new int[2][20];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 20; j++) {
				friend[i][j] = sc.nextInt();
			}
		}
		visit = new boolean[n+1];
		game(0,0,0,0,0,0,0);
		if(isPossible) System.out.println("1");
		else System.out.println("0");
		
	}

	public static void game(int cnt, int f0, int f1, int fi, int win, int f0win, int f1win) {
		if(f0win >= k || f1win >= k) {
			return;
		}
		if(win == k) {
			isPossible = true;
			return;
		}
		if(cnt == n) {
			return;
		}
		int[] f = new int[2];
		for(int m = 1; m <= n; m++) {
			if(!visit[m]) {
				visit[m] = true;
				f[0] = f0;
				f[1] = f1;
				if (arr[m][friend[fi][f[fi]++]] == 2) {
					if (fi == 0) game(cnt+1, f[0], f[1], 1, win+1, f0win, f1win);
					else game(cnt+1, f[0], f[1], 0, win+1, f0win, f1win);
				}
				else { 
					if(arr[friend[0][f[0]++]][friend[1][f[1]++]] == 2) {
						if (fi == 0) game(cnt+1, f[0], f[1], 0, win, f0win+2, f1win);
						else game(cnt+1, f[0], f[1], 0, win, f0win+1, f1win+1);
					} else {
						if (fi == 0) game(cnt+1, f[0], f[1], 1, win, f0win+1, f1win+1);
						else game(cnt+1, f[0], f[1], 1, win, f0win, f1win+2);
					}
				}
				visit[m] = false;
			}
		}
	}
}