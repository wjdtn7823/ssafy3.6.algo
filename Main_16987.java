package yun_prac;
import java.util.Scanner;
public class Main_16987 {
	
	public static int max = 0;
	public static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[] s = new int[n];
		int[] w = new int[n];
		for(int i = 0; i < n; i++) {
			s[i] = sc.nextInt();
			w[i] = sc.nextInt();
		}
		eggCount(0, s, w, 0);
		System.out.println(max);
	}

	public static void eggCount(int idx, int[] s, int[] w, int cnt) {
		if(cnt > max) {
			max = cnt;
		}
		if(idx == n) {
			return ;
		}
		if(s[idx] <= 0) {
			eggCount(idx+1,s,w,cnt);
			return ;
		}
		for(int i = 0; i < n; i++) {
			if( i == idx ) continue;
			
			if(s[i] > 0) {
				s[idx] -= w[i];
				s[i] -= w[idx];
				if(s[idx] <= 0 && s[i] <= 0) 
					eggCount(idx+1, s, w, cnt+2);
				else if(s[idx] <= 0 || s[i] <= 0)
					eggCount(idx+1, s, w, cnt+1);
				else 
					eggCount(idx+1, s, w, cnt);
				s[idx] += w[i];
				s[i] += w[idx];
			}
		}
	}
}