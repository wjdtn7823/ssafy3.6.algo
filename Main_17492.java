package yun_prac;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_17492 {

	public static int n;
	public static boolean possible;
	public static int stone = 0;
	
	public static int[] dx = {-1,-1,-1,0,0,1,1,1};
	public static int[] dy = {-1,0,1,-1,1,-1,0,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		sc.nextLine();
		int[][] matrix = new int[n][n];
		StringTokenizer st;
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(sc.nextLine());
			for(int j = 0 ; j < n; j ++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if(matrix[i][j] == 2) stone++;
			}
		}
	
		game(matrix, stone);
		
		if(possible) System.out.println("Possible");
		else System.out.println("Impossible");
		
	}

	public static void game(int[][] matrix, int stone) {
		
		if(possible) return;
		
		if(stone <= 1) {
			possible = true;
			return ;
		}
		
		int x,y;
		for(int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < n; j ++) {
				if(matrix[i][j] == 2) {
					for(int d = 0 ; d < 8; d++) {
						x = i+dx[d];
						y = j+dy[d];
						
						if(x>0 && x < n-1 && y >0 && y < n-1 
								&& matrix[x][y] == 2 && matrix[x+dx[d]][y+dy[d]] == 0) {
							matrix[i][j] = 0;
							matrix[x][y] = 0;
							matrix[x+dx[d]][y+dy[d]] = 2;
							game(matrix, stone-1);
							matrix[i][j] = 2;
							matrix[x][y] = 2;
							matrix[x+dx[d]][y+dy[d]] = 0;
						}
					}
				}
			}
		}
		
		
	}
}









