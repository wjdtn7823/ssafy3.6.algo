package AlgorithmPractice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DSLR {
	static class Num {
		int n;
		String cmd = "";

		public Num(int n) {
			this.n = n;
		}
	}
	static int b;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		int a;
		String ans;
		for (int t = 0; t < tc; t++) {
			a = sc.nextInt();
			b = sc.nextInt();
			ans = register(a);
			System.out.println(ans);
		}
	}
	public static String register(int a) {
		boolean[] visit = new boolean[10001];
		Queue<Num> q = new LinkedList<>();
		q.add(new Num(a));
		visit[a] = true;
		Num num = null;
		Num change;
		while (!q.isEmpty()) {
			num = q.poll();
			if (num.n == b)
				break;
			// d => 2*n % 10000
			change = new Num(2 * num.n % 10000);
			if (!visit[change.n]) {
				change.cmd = new StringBuilder(num.cmd).append("D").toString();
				q.add(change);
				visit[change.n] = true;
			}
			// s => (n-1) -> 0이라면 9999
			if (num.n == 0)
				change = new Num(9999);
			else
				change = new Num(num.n - 1);
			if (!visit[change.n]) {
				change.cmd = new StringBuilder(num.cmd).append("S").toString();
				q.add(change);
				visit[change.n] = true;
			}
			// l => d2 d3 d4 d1
			change = new Num((num.n % 1000 * 10) + (num.n / 1000));
			if (!visit[change.n]) {
				change.cmd = new StringBuilder(num.cmd).append("L").toString();
				q.add(change);
				visit[change.n] = true;
			}
			// r => d4 d1 d2 d3
			change = new Num((num.n % 10 * 1000) + (num.n / 10));
			if (!visit[change.n]) {
				change.cmd = new StringBuilder(num.cmd).append("R").toString();
				q.add(change);
				visit[change.n] = true;
			}
		}
		return num.cmd.toString();
	}
}
