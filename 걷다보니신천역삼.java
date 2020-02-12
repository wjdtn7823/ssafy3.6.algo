package AlgorithmPractice;

import java.util.Scanner;
public class 걷다보니신천역삼 {
	public static int n;
	public static int count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		counting(0, "");
		
		System.out.println(count);
	}
	public static void counting(int idx, String nstr) {
		if(idx == n) {
			int num = Integer.parseInt(nstr);
			if(num%3 == 0) count++;
			return;
		}
		for(int i = 0; i <=2; i++) {
			if(idx == 0 && i == 0) continue; 
			counting(idx+1, nstr+i);
		}
	}
}
