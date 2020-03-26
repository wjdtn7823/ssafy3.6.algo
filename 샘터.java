package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 샘터 {
	static int n,k;
	static Queue<Integer> q = new LinkedList<>();
	static int[] dx = {-1,1};
	static Set<Integer> visit = new HashSet<Integer>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int t;
		for(int i = 0; i < n; i++) {
			t = Integer.parseInt(st.nextToken());
			q.add(t);
			visit.add(t);
		}
		System.out.println(solve());
	}
	private static long solve() {
		long ans = 0, count = 0;
		int idx, len;
		long dist = 0;
		while(!q.isEmpty()) {
			len = q.size();
			for(int i = 0; i < len; i++) {
				idx = q.poll();
				ans += dist;
				count++;
	
				if(count == k + n) return ans;
				
				
				for(int d = 0; d < 2; d++) {
					if( idx+dx[d] < -100000000 || idx+dx[d] > 100000000) continue;
					if( visit.contains(idx+dx[d])) continue;
					
					q.add(idx+dx[d]);
					visit.add(idx+dx[d]);
				}
			}
			dist++;
		}
		
		return ans;
	}
}
