import java.io.*;
import java.util.*;

public class Main {

	static class MyScanner{
		BufferedReader br;
		StringTokenizer st;
		
		public MyScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		public String next() { 
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
	
	static int n, m, t, x, d, k, arr[][];
	static boolean tmp_map[][];
	static Queue<Integer[]> q = new LinkedList<Integer[]>();
	
	static void rotate(int xx, int dir) {
		if(dir == 0) {
			int tmp = arr[xx][m];
			for(int i=m; i>1; i--) arr[xx][i] = arr[xx][i - 1];
			arr[xx][1] = tmp;
		}else {
			int tmp = arr[xx][1];
			for(int i=1; i<m; i++) arr[xx][i] = arr[xx][i + 1];
			arr[xx][m] = tmp;
		}
	}
	
	public static void main(String[] args) throws IOException{
		MyScanner sc = new MyScanner();
		
		n = sc.nextInt(); m = sc.nextInt(); t = sc.nextInt();
		arr = new int[n + 1][m + 1]; tmp_map = new boolean[n + 1][m + 1];
		
		for(int i=1; i<=n; i++)  for(int j=1; j<=m; j++) arr[i][j] = sc.nextInt();
		
		
		for(int i=0; i<t; i++) {
			x = sc.nextInt(); d = sc.nextInt(); k = sc.nextInt();
			for(int j = x; j <= n; j+=x) {
				for(int ia = 0; ia < k; ia++) {
					rotate(j, d);
				}
			}
			
			for(int ia=1; ia <=n; ia++) for(int ib = 1; ib <= m; ib++) {
				tmp_map[ia][ib] = false;
			}
			
			boolean is_updated = false;
			// 원판에서 인접한거 삭제하기
			for(int ia=1; ia <=n; ia++) {
				for(int ib = 1; ib <= m; ib++) {
					if(ib == m) {
						if(arr[ia][m] != 0 && arr[ia][m] == arr[ia][1]) {
							is_updated = true;
							tmp_map[ia][m] = true; tmp_map[ia][1] = true;
						}
					}else if(arr[ia][ib] != 0 && arr[ia][ib] == arr[ia][ib + 1]) {
						is_updated = true;
						tmp_map[ia][ib] = true; tmp_map[ia][ib + 1] = true;
					}
				}
			}
			
			for(int ia = 1; ia <= m; ia++) {
				for(int ib = 1; ib < n; ib++) {
					if(arr[ib][ia] != 0 && arr[ib][ia] == arr[ib + 1][ia]) {
						is_updated = true;
						tmp_map[ib][ia] = true; tmp_map[ib + 1][ia] = true;
					}
				}
			}
			
			
			for (int ia = 1; ia <= n; ia++) {
				for (int ib = 1; ib <= m; ib++) {
					if (tmp_map[ia][ib] == true) arr[ia][ib] = 0;
				}
			}
			
			
			
			if(!is_updated) {
				float tmp = 0; int cnt = 0;
				for(int ia=1; ia <=n; ia++) for(int ib = 1; ib <= m; ib++) {
					if(arr[ia][ib] != 0) {
							cnt++; tmp += arr[ia][ib];
					}
				}
				
				tmp /= cnt;
				for(int ia=1; ia <=n; ia++) for(int ib = 1; ib <= m; ib++) {
					if(arr[ia][ib] != 0) {
						if(arr[ia][ib] > tmp) arr[ia][ib] -= 1;
						else if(arr[ia][ib] < tmp) arr[ia][ib] += 1;
					}
				}
			}
			
		}
		
		int answer = 0;
		for(int ia=1; ia <=n; ia++) for(int ib = 1; ib <= m; ib++) {
			answer += arr[ia][ib];
		}
		
		System.out.println(answer);
		
	}

}
