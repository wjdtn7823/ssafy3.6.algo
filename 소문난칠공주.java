package AlgorithmPractice;
import java.util.*;
import java.io.*;
public class 소문난칠공주 {
	//1부터25까지
	static int[] board = new int[26];
	static int[] arr = new int[7];
	static int[] offset = {-5,-1,1,5};
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		for(int i = 0; i < 5; i ++) {
			s = br.readLine();
			for(int j = 0 ; j < 5; j++) {
				board[i*5+j+1] = s.charAt(j);
			}
		}
		solve(1,0,0);
		System.out.println(answer);
	}
	public static void solve(int idx, int cnt, int cnts) {
		if(cnt == 7) {
			if(cnts < 4) return;
			Queue<Integer> q = new LinkedList<>();
			int[] check = new int[26];

			for(int i = 0; i < 7; i++) {
				check[arr[i]] = 1;
			}
			
			q.add(arr[0]);
			check[arr[0]] = 2;
			int connect = 1;
			int n;
			while(!q.isEmpty()) {
				n = q.poll();
				for(int d = 0; d < 4; d++) {
					if(n+offset[d] <= 0 || n+offset[d] > 25) continue;
					if(n%5==0 && d == 2) continue;
					if(n%5==1 && d == 1) continue;
					if(check[n+offset[d]] == 0 || check[n+offset[d]] == 2) continue;
					
					check[n+offset[d]] = 2;
					q.add(n+offset[d]);
					connect++;
				}
			}
			
			if(connect == 7) answer++;
			return;
		}
		
		//조합
		for(int i = idx; i < 26; i++) {
			arr[cnt] = i;
			if(board[i] == 'S') solve(i+1, cnt+1, cnts+1);
			else solve(i+1, cnt+1, cnts);
		}
	}

}



