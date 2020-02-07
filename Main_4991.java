package yun_prac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_4991 {
	
	public static char[][] room;
	
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {1,0,-1,0};
	
	public static int[][] move;
	public static int minCount;
	
	public static ArrayList<Point> dust;
	
	public static int w;
	public static int h;
	public static int[][] mat;
	public static boolean[] v;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String str;
		
		while(true) {
			
			w = sc.nextInt();
			h = sc.nextInt();	
			
			if(w == 0 && h == 0) break;
			
			int x=0,y=0;
			sc.nextLine();
			
			minCount = Integer.MAX_VALUE;
			int idx = 0;
			dust = new ArrayList<>();
			ArrayList<Point> temp = new ArrayList<>();
			
			room = new char[h][w];
			for(int i = 0; i < h; i++) {
				str = sc.nextLine();
				for(int j = 0; j < w; j++) {
					room[i][j] = str.charAt(j);
					if(room[i][j] == 'o') {
						x = i;
						y = j;
						dust.add(new Point(x,y));
					}
					if(room[i][j] == '*') {
						temp.add(new Point(i,j));
					}
				}
			}
			
			for(Point p : temp)
				dust.add(p);
			
			//먼지끼리거리
			mat = new int[dust.size()][dust.size()];
			cleaning(x,y);
			
			if(minCount == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(minCount);
			
		}
		
	}
	
	public static void cleaning(int x, int y) {
	
		//먼저 먼지끼리 거리
		counting();
		
		
		//먼지 순서 정하기
		v = new boolean[dust.size()];
		order(0,0,0);
		
			
	}

	public static void order(int idx, int cnt, int sum) {
	
		if(cnt == dust.size()-1) {
			if(sum < minCount)
				minCount = sum;
			return;
		}
		
		for(int i = 1 ; i < dust.size(); i++) {
			if(!v[i]) {
				v[i] = true;
				if(cnt == 0 && mat[0][i] > 0) {
					order(i,cnt+1,sum+mat[0][i]);
				}
				else if(mat[idx][i] > 0) {
					order(i,cnt+1,sum+mat[idx][i]);
				}
				v[i] = false;
		
			}
		}	
		
	}

	public static void counting() {
		
		Point p;
		for(int idx = 0; idx < dust.size()-1; idx++) {
		
			Queue<Point> q = new LinkedList<Point>();
			
			q.offer(new Point(dust.get(idx).x,dust.get(idx).y));
			
			int i, j;
			
			move = new int[h][w];
							
			while(!q.isEmpty()) {
				p = q.poll();
				i = p.x;
				j = p.y;
				
				for(int d = 0; d < 4; d++) {
					
					if(i+dx[d] >= 0 && i+dx[d] < h && j+dy[d] >= 0 && j+dy[d] < w 
							&& room[i+dx[d]][j+dy[d]] != 'x' && move[i+dx[d]][j+dy[d]] == 0) {
						move[i+dx[d]][j+dy[d]] = move[i][j] + 1;
						q.offer(new Point(i+dx[d],j+dy[d]));				
					}	
					
				}
			}
		
			
			for(int idy = idx+1; idy < dust.size(); idy++) {
								
				mat[idx][idy] = move[dust.get(idy).x][dust.get(idy).y];
				mat[idy][idx] = move[dust.get(idy).x][dust.get(idy).y];
				
			}
		}
	
	}
	

}
