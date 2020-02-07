package yun_prac;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_17265 {
	public static int[] dx = {1,0};
	public static int[] dy = {0,1};
	public static int min = Integer.MAX_VALUE;
	public static int max = Integer.MIN_VALUE;
	public static int n;
	public static char[][] carr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		StringTokenizer st;
		carr = new char[n+1][n+1];
		for(int i = 1 ; i <= n; i++) {
			st = new StringTokenizer(sc.nextLine());
			for(int j =1; j <= n ; j++) {
				carr[i][j] = st.nextToken().charAt(0);
			}
		}
		solve(1,1,0, ' ');
		System.out.println(max+" "+min);
	}

	public static void solve(int x, int y, int val, char op) {
		//숫자
		if( (x%2 == 0 && y%2 == 0) || (x%2==1 && y%2==1) ) {
			int temp = val;
			switch(op) {
			case '+':
				temp += carr[x][y] - '0';
				break;
			case '-':
				temp -= carr[x][y] - '0';
				break;
			case '*':
				temp *= carr[x][y] - '0';
				break;
			case '/':
				temp /= carr[x][y] - '0';
				break;
			default:
				temp = carr[x][y] - '0';
				break;
			}
			for(int i = 0 ; i < 2; i++) {
				if(x+dx[i] > 0 && x+dx[i] <= n && y+dy[i] > 0 && y+dy[i] <= n)
					solve(x+dx[i], y+dy[i], temp, carr[x+dx[i]][y+dy[i]]);
			}
			if(x == n && y == n) {
				if(min > temp) min = temp;
				if(max < temp) max = temp;
				return;
			}
		}
		//문자
		else {
			for(int i = 0 ; i < 2; i++) {
				if(x+dx[i] > 0 && x+dx[i] <= n && y+dy[i] > 0 && y+dy[i] <= n)
					solve(x+dx[i], y+dy[i], val, carr[x][y]);
			}
		}
	}
}