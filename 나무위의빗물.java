package AlgorithmPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무위의빗물 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		int[] child = new int[n+1];
		int u,v;
		for(int i = 0 ; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			child[u]++;
			child[v]++;
		}
		
		int cnt = 0;
		for(int i = 2; i <= n; i++) {
			if(child[i] == 1) cnt++;
		}
		
		System.out.println((double)w/cnt);
	}

}
