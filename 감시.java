package AlgorithmPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Poi {
	int x;
	int y;
	
	public Poi (int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class 감시 {

	public static int n,m;
	public static int minCount = Integer.MAX_VALUE;
	public static ArrayList<Poi> cctv;
	
	public static int[] dx= {0,0,-1,1};
	public static int[] dy= {1,-1,0,0};

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		int[][] arr = new int[n][m];
		cctv = new ArrayList<>();
		
		for(int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < m; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] > 0 && arr[i][j] < 6) 
					cctv.add(new Poi(i,j));
			}
		}

		moveCctv(arr, 0);
		System.out.println(minCount);
		
	}

	public static void moveCctv(int[][] arr, int idx) {

		int count = 0;
		if(cctv.size() == idx) {	
			for(int i = 0 ; i < n; i++) {
				for(int j = 0 ; j < m; j++) {
					if(arr[i][j] == 0) {
						count++;
					}
				}
			}
			if(count < minCount)
				minCount = count;
			return;
		}
		
		int x = cctv.get(idx).x;
		int y = cctv.get(idx).y;
		int ctype = arr[x][y];
		int[][] copy = new int[n][m];
		switch(ctype) {
		case 1:
			for(int d = 0; d < 4; d++) {
				for(int i = 0; i < n; i++) copy[i] = Arrays.copyOf(arr[i], m);
				//바꾸기
				change(copy,x,y,d);
				//돌리기
				moveCctv(copy, idx+1);
			}
			break;
		case 2:
			for(int d = 0; d < 4; d+=2) {
				for(int i = 0; i < n; i++) copy[i] = Arrays.copyOf(arr[i], m);
				change(copy,x,y,d);
				change(copy,x,y,d+1);
				moveCctv(copy, idx+1);
			}
			break;
		case 3:
			for(int d1 = 0; d1 < 2; d1++) {
				for(int d2 = 2; d2 < 4; d2++) {
					for(int i = 0; i < n; i++) copy[i] = Arrays.copyOf(arr[i], m);
					change(copy,x,y,d1);
					change(copy,x,y,d2);
					moveCctv(copy, idx+1);
				}
			}
			break;
		case 4:
			for(int d = 0; d < 4; d++) {
				for(int i = 0; i < n; i++) copy[i] = Arrays.copyOf(arr[i], m);
				change(copy,x,y,d%4);
				change(copy,x,y,(d+1)%4);
				change(copy,x,y,(d+2)%4);
				moveCctv(copy, idx+1);
			}
			break;
		case 5:
			for(int i = 0; i < n; i++) copy[i] = Arrays.copyOf(arr[i], m);
			for(int d = 0; d < 4; d++) {
				change(copy,x,y,d);
			}
			moveCctv(copy, idx+1);
			break;
		}
	}
	public static void change(int[][] arr, int x, int y, int d) {
		int size = 1;
		while(true) {
			if(x+dx[d]*size < 0 || x+dx[d]*size >= n || y+dy[d]*size < 0 || y+dy[d]*size >= m) break;
			if(arr[x+dx[d]*size][y+dy[d]*size] == 6) break;
			if(arr[x+dx[d]*size][y+dy[d]*size] == 0) {
				arr[x+dx[d]*size][y+dy[d]*size] = 1;
			}
			size++;
		}
	}

}
