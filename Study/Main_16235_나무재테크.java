package baekjoon;

import java.io.*;
import java.util.*;

class TREE implements Comparable<TREE>{
	int x, y, age;
	TREE(){}
	TREE(int x1, int y1, int a1){
		x = x1; y = y1; age = a1;
	}
	@Override
	public int compareTo(TREE o) {
		return Integer.compare(this.age, o.age);
	}
}

public class Main_16235_나무재테크 {
	
	static int n, m, k, arr[][], map[][], a, b, c;
	static TREE tree[];
	static Queue<TREE> q1 = new LinkedList<TREE>(), q2 = new LinkedList<TREE>(), q3 = new LinkedList<TREE>();
	final static int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	final static int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken()); 
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][n]; tree = new TREE[m]; map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) { 
				arr[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()); b = Integer.parseInt(st.nextToken());  c = Integer.parseInt(st.nextToken());
			tree[i] = new TREE(a - 1, b - 1, c);
		}Arrays.sort(tree);
		for(int i=0; i<m; i++) q1.add(tree[i]);
		
		for(int day_ = 0; day_ < k; day_++) {
			// 봄
			while(!q1.isEmpty()) {
				TREE cur = q1.poll();
				if(cur.age > map[cur.x][cur.y]) q2.add(cur);
				else {
					map[cur.x][cur.y] -= cur.age; cur.age++;
					q3.add(cur);
				}
			}
			
			// 여름
			while(!q2.isEmpty()) {
				TREE cur = q2.poll();
				map[cur.x][cur.y] += cur.age / 2;
			}
			
			// 가을
			while(!q3.isEmpty()) {
				TREE cur = q3.poll(); q2.add(cur);
				if(cur.age % 5 == 0) {
					for(int dir = 0; dir < 8; dir++) {
						int new_x = cur.x + dx[dir], new_y = cur.y + dy[dir];
						if(new_x < 0 || new_x >= n || new_y < 0 || new_y >= n) continue;
						q1.add(new TREE(new_x, new_y, 1));
					}
				}
			}
			
			// 겨울
			for(int i=0; i<n; i++) for(int j=0; j<n; j++) map[i][j] += arr[i][j];
			while(!q2.isEmpty()) {
				q1.add(q2.poll());
			}
		}
		System.out.println(q1.size());
	
	}

}
