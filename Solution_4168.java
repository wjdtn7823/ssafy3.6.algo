package yun_prac;

import java.util.Scanner;

class Clothes {
	int p;
	int s;
	public Clothes(int p, int s) {
		this.p = p;
		this.s = s;
	}
}
public class Solution_4168 {
	public static int n,m;
	public static Clothes[] cArr; 
	public static boolean[] buy;
	public static boolean[] visit;
	public static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int p,s;
		for(int t = 1; t <= tc; t++) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			cArr = new Clothes[m];
			visit = new boolean[m];
			buy = new boolean[m];
			max = 0;
			
			for(int i = 0; i < m; i++) {
				p = sc.nextInt();
				s = sc.nextInt();
				cArr[i] = new Clothes(p,s);
			}
			
			shopping(0, 0, 0);
			
			System.out.print("#"+t+" ");
			for(int i = 0 ; i < m ; i ++) {
				if(buy[i]) System.out.print(i+" ");
			}
			System.out.println(max);
		}
	}

	public static void shopping(int idx, int price, int sat) {
		
		if(idx == m) {
			if(sat > max) {
				max = sat;
				System.arraycopy(visit,0, buy, 0, m);
			}
			return;
		}
		
		shopping(idx+1, price, sat);
		
		if(price + cArr[idx].p <= n) {
			visit[idx] = true;
			shopping(idx+1, price+cArr[idx].p, sat+cArr[idx].s);
			visit[idx] = false;
		}
		
	}

}
