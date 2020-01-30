package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree> {
	int x;
	int y;
	int age;
	
	@Override
	public int compareTo(Tree o) {
		if(this.age == o.age) return 0;
		return this.age-o.age;
	}
	
	public String toString() {
		return x+", "+y+" 나이 "+age;
	}

}

public class Main_16235 {
	
	private static final int[] dx={-1,-1,0,1,1, 1, 0,-1};
	private static final int[] dy={ 0, 1,1,1,0,-1,-1,-1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] map = new int[n+1][n+1];
		int[][] amount = new int[n+1][n+1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = 5;
				amount[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Tree> tlist = new ArrayList<Tree>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Tree t = new Tree();
			t.x = Integer.parseInt(st.nextToken());
			t.y = Integer.parseInt(st.nextToken());
			t.age = Integer.parseInt(st.nextToken());
			tlist.add(t);
		}
		
//		int a,b;
		Tree tmp;
		
		while (k > 0) {
			//봄
			Collections.sort(tlist);
						
			for (int i = 0; i < tlist.size(); i++) {
				if(tlist.get(i).age < 0) continue;
				
				tmp = tlist.get(i);
				
				if(map[tmp.x][tmp.y] >= tmp.age) {
					map[tmp.x][tmp.y] -= tmp.age;
					tmp.age++;
				} else {
					map[tmp.x][tmp.y] += tmp.age/2;
					tmp.age = -1;
				}
				
			}
			
			System.out.println(tlist.size());
			Collections.sort(tlist); System.out.println("가을전 나무근황");
			for(int i = 0 ; i < tlist.size(); i++) {
				System.out.println( tlist.get(i).toString());
			}System.out.println();
			
			
			int size = tlist.size();
			for (int i = 0; i < size; i++) { 
				if(tlist.get(i).age > 0 && tlist.get(i).age%5 == 0) {
					for(int j = 0; j < 8; j++) {
						int a = tlist.get(i).x+dx[j];
						int b = tlist.get(i).y+dy[j];
						if(0<a && a <= n && 0<b && b<=n) {
							tmp = new Tree();
							tmp.x = a;
							tmp.y = b;
							tmp.age = 1;
							tlist.add(tmp);
						}
					}
				}
			}
			
			// 겨
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					map[i][j] += amount[i][j];
				}
			}
			k--;
//			
			System.out.println(tlist.size());
			Collections.sort(tlist); System.out.println("겨울후 나무근황");
			for(int i = 0 ; i < tlist.size(); i++) {
				System.out.println( tlist.get(i).toString());
			}System.out.println();
			
			for(int i = 0; i <=n; i++)
				System.out.println(Arrays.toString(map[i]));
			
		}
		
//		 System.out.println(tlist.size());
		int count = 0;
		for(Tree t : tlist) {
			if(t.age > 0) count++;
		}
		System.out.println(count);

	}

}