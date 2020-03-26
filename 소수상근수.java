package AlgorithmPractice;

import java.util.Arrays;
import java.util.Scanner;

public class 소수상근수 {
	static int n;
	static boolean[] pcheck;
	static boolean[] scheck, visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		pcheck = new boolean[n+1];
		pcheck[0] = true;
		pcheck[1] = true;
		primeCheck();
		
		scheck = new boolean[1000001];
		visit = new boolean[1000000];
		for(int i = 2; i <= n; i++) {
			if(pcheck[i]) continue;
			if(!check(i)) continue;
			System.out.println(i);
		}
	}
	private static boolean check(int num) {
		if(num == 1) return true;
		
		if(scheck[num]) return scheck[num];
		if(visit[num]) return scheck[num];
		visit[num] = true;
		
		int nn = num;
		int change = 0;
		while(nn > 0) {
			change += (int) Math.pow(nn%10, 2);
			nn /= 10;
		}
		return scheck[num] = check(change);
	}
	private static void primeCheck() {
		for(int i = 2; i <= n; i++) {
			if(pcheck[i]) continue;
			for(int j = 2; j <= n; j++) {
				if(i*j > n) break;
				pcheck[i*j] = true;
			}
		}
	}
}
