package AlgorithmPractice;
import java.io.*;
import java.util.*;
public class 페그솔리테어 {
	static int minP, minMove;
	static char[][] matrix = new char[5][9];
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String s;
		for(int t = 1; t <= n; t++) {
			if(t!=1) br.readLine();
			int pin = 0;
			for(int i = 0; i < 5; i++) {
				s = br.readLine();
				for(int j = 0; j < 9; j++) {
					matrix[i][j] = s.charAt(j);
					if(matrix[i][j] == 'o') {
						pin++;
					}
				}
			}
			minP = 9;
			minMove = 0;
			for(int i = 0; i < 5; i++) 
				for(int j = 0; j < 9; j++) 
					if(matrix[i][j] == 'o')
						move(i, j, 0, pin);
			
			sb.append(minP).append(" ").append(minMove).append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void move(int x, int y, int cnt, int pin) {
		if(pin == minP) 
			if(minMove != 0)
				minMove = Math.min(cnt, minMove);
			
		if(pin < minP) {
			minP = pin;
			minMove = cnt;
		}
		
		int nx,ny, destx, desty;
		for(int d = 0; d < 4; d++) {
			nx = x + dx[d];
			ny = y + dy[d];
			destx = x + dx[d]*2;
			desty = y + dy[d]*2;
			if(destx < 0 || destx >= 5 || desty < 0 || desty >= 9) continue;
			if(matrix[nx][ny] != 'o') continue;
			if(matrix[destx][desty] !='.') continue;
			
			matrix[x][y] = '.';
			matrix[nx][ny] = '.';
			matrix[destx][desty] = 'o';
			
			for(int i = 0; i < 5; i++) 
				for(int j = 0; j < 9; j++) 
					if(matrix[i][j] == 'o')
						move(i, j, cnt+1, pin-1);
			
			matrix[x][y] = 'o';
			matrix[nx][ny] = 'o';
			matrix[destx][desty] = '.';
		}
		
	}
}
