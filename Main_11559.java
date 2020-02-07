package yun_prac;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Poi {
	int x;
	int y;
	public Poi(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_11559 {

	public static char[][] carr;
	public static boolean[][] visited;
	public static int answer = 0;
	public static boolean relocate;
	public static boolean cnt;
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String str;
		carr = new char[12][6];
		for(int i = 0; i < carr.length; i++) {
			str = sc.nextLine();
			for(int j = 0 ; j < carr[i].length; j++) {
				carr[i][j] = str.charAt(j);
			}
		}
		game();
		System.out.println(answer);
	}

	public static void game() {
		while(true) {
			relocate = false;
			visited = new boolean[carr.length][carr[0].length];
			for(int i = 0; i < carr.length; i++) {
				for(int j = 0 ; j < carr[i].length; j++) {
					if(carr[i][j] != '.' && !visited[i][j]) {
						counting(i, j);
					}
				}
			}
			
			if(!relocate) return;
			int idx;
			for(int j = 0; j < carr[0].length; j++) {
				idx = carr.length-1;
				while(idx > 0 && carr[idx][j] != '.') {
					idx--;
				}
				if(idx == 0) continue;
				for(int i = idx-1; i>=0; i--) {
					if(carr[i][j] != '.') {
						carr[idx--][j] = carr[i][j]; 
						carr[i][j] = '.';
					}
				}
			}	
			answer++;
		}
	}

	public static void counting(int x, int y) {
		
		Queue<Poi> qMove = new LinkedList<Poi>();
		Queue<Poi> qCount = new LinkedList<Poi>();

		qMove.offer(new Poi(x,y));
		qCount.offer(new Poi(x,y));
		Poi p;
		
		while(!qMove.isEmpty()) {
			p = qMove.poll();
			visited[p.x][p.y] = true;
			for(int d = 0; d < 4; d++) {
				if(p.x+dx[d] >= 0 && p.x+dx[d] < carr.length && p.y+dy[d] >= 0 && p.y+dy[d] < carr[0].length) {
					if(carr[p.x+dx[d]][p.y+dy[d]] == carr[x][y] && !visited[p.x+dx[d]][p.y+dy[d]]) {
						qMove.offer(new Poi(p.x+dx[d], p.y+dy[d]));
						qCount.offer(new Poi(p.x+dx[d], p.y+dy[d]));
					}
				}
			}	
		}
		
		if(qCount.size() >= 4) {
			relocate = true;
			while(!qCount.isEmpty()) {
				p = qCount.poll();
				carr[p.x][p.y] = '.';
			}
		}
	
	}
}