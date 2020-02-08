package AlgorithmPractice;

import java.util.Scanner;

public class 십자가찾기 {
	
	public static int n,m;
	public static char[][] arr;
	public static boolean[][] check;
	public static int cnt = 0;
	public static int[][] cross;
	
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();
		String str;
		
		arr = new char[n][m];

		for(int i = 0; i < n; i++) {
			str = sc.nextLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		check = new boolean[n][m];
		cross = new int[n*m][3];
		count();
		check();
		
		System.out.println(cnt);
		if(cnt != -1) {
			for(int[] a : cross) {
				if(a[2] == 0) break;
				System.out.println(a[0]+" "+a[1]+" "+a[2]);
			}
		}
		
	}

	public static void count() {
		
		int x = 0, y = 0, size = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] != '*') continue;
					
				if(cnt >= n*m) return;
				
				size = 0;
				loop:while(true) {
					for(int d= 0; d < 4; d++) {
						x = i + dx[d]*(size+1);
						y = j + dy[d]*(size+1);
						if(x < 0 || x >= n || y < 0 || y >= m || arr[x][y] != '*') break loop;
					}
					size++;
				}
				
				if(size > 0) {

					check[i][j] = true;
					for(int d= 0; d < 4; d++) {
						for(int s = size; s > 0; s--) {
							check[i+dx[d]*s][j+dy[d]*s] = true;
						}
					}
					
					cross[cnt][0] = i+1;
					cross[cnt][1] = j+1;
					cross[cnt++][2] = size;
				}
				
			}
		}
	}
	public static void check() {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == '*' && !check[i][j]) {
					cnt=-1;
					return;
				}
			}
		}
	}

}
