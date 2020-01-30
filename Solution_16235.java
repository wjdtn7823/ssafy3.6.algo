import java.io.*;
import java.util.*;


public class Solution_16235 {

	public static int[][] food = new int [11][11];
	public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static int[][] add = new int[11][11];
	public static Deque<Integer>[][] alive = new Deque[11][11];
	public static Queue<Integer>[][] dead = new Queue[11][11];
	public static int n, temporary, k;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first = br.readLine().split(" ");
		n = Integer.parseInt(first[0]);
		temporary = Integer.parseInt(first[1]);
		k = Integer.parseInt(first[2]);
		for (int i = 1 ; i <= n ; i++) {
			String[] second = br.readLine().split(" ");
			for (int j = 1 ; j <= n ; j++) {
				add[i][j] = Integer.parseInt(second[j-1]);
			}
		}
		
		for (int i = 1 ; i <= n ;i++) {
			for (int j = 1 ; j<= n ; j++) {
				alive[i][j] = new LinkedList<Integer>();
				dead[i][j] = new LinkedList<Integer>();
				food[i][j] = 5;
			}
		}
		
		for (int i = 0 ; i< temporary ; i++) {
			String[] second = br.readLine().split(" ");
			int x, y, z;
			x = Integer.parseInt(second[0]);
			y = Integer.parseInt(second[1]);
			z = Integer.parseInt(second[2]);
			alive[x][y].offer(z);
		}
		while(true) {
			if (k == 0)
				break;
			// Spring
			for (int i = 1 ; i <= n ;i++) {
				for (int j = 1 ; j <= n ; j++) {
					int s = alive[i][j].size();
					for (int k = 0 ; k < s; k++) {
						int age = alive[i][j].poll();
						if (food[i][j] >= age) {
							food[i][j] -= age;
							age += 1;
							alive[i][j].add(age);
						}
						else {
							dead[i][j].add(age);
						}
					}
				}
			}
			// Summer
			for (int i = 1 ; i <= n ;i++) {
				for (int j = 1 ; j <= n ; j++) {
					while(!dead[i][j].isEmpty()) {
						int age = dead[i][j].poll();
						food[i][j] += age / 2;
					}
				}
			}
			
			// Autumn
			for (int i = 1 ; i <= n ; i++) {
				for (int j = 1; j <= n ; j++) {
					int s = alive[i][j].size();
					for (int k = 0 ; k < s ; k++) {
						int age = alive[i][j].pop();
						if (age % 5 == 0) {
							for (int dir = 0 ; dir < 8 ; dir++) {
								int nx = i + dx[dir];
								int ny = j + dy[dir];
								if (0 < nx && nx <= n && 0 < ny && ny <= n) {
									alive[nx][ny].addFirst(1);
								}
							}
						}
						alive[i][j].addLast(age);
					}
				}
			}
			
			// Winter
			for (int i = 1 ; i <= n ;i++) {
				for (int j = 1 ; j <= n ; j++) {
					food[i][j] += add[i][j];
				}
			}
			
			
			k -= 1;
		}
		int answer = 0;
		for (int i = 1 ; i <= n ;i++) {
			for (int j = 1 ;j <= n ;j++) {
				answer += alive[i][j].size();
			}
		}
		System.out.println(answer);
	}

}
