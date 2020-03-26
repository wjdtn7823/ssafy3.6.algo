package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점프 {
	static int n;
	static int[][] rocks;
	static boolean[] small;
	static final int INF = 987654321;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int r;
		rocks = new int[n+1][1000];
		small = new boolean[n+1];
		for(int i = 0 ; i < m; i++) {
			r = Integer.parseInt(br.readLine());
			small[r] = true;
		}
		
		int result = jump(1,0);
		if(result == INF) result = -1;
		System.out.println(result);
	}
	public static int jump(int idx, int speed) {
		if(idx == n) {
			return 0;
		}
		
		if(rocks[idx][speed] > 0) return rocks[idx][speed];
		
		rocks[idx][speed] = INF;
		
		if(idx+speed+1 <= n && !small[idx+speed+1]) 
			rocks[idx][speed] = Math.min(rocks[idx][speed], 1+jump(idx+speed+1, speed+1));
		if(speed >= 1 && idx+speed <= n && !small[idx+speed]) 
			rocks[idx][speed] = Math.min(rocks[idx][speed], 1+jump(idx+speed, speed));
		if(speed >= 2 && speed-1 > 0 && idx+speed-1 <= n && !small[idx+speed-1]) 
			rocks[idx][speed] = Math.min(rocks[idx][speed], 1+jump(idx+speed-1, speed-1));
		return rocks[idx][speed];
		
	}
}
