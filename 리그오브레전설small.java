package AlgorithmPractice;

import java.math.BigInteger;
import java.util.Scanner;

public class 리그오브레전설small {
	static final BigInteger MOD = new BigInteger("1000000007");
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long m = sc.nextLong();
		
		BigInteger answer = new BigInteger("1");
		for(long i = 1; i <= n/m; i++) {
			answer = answer.add(comb(n-(m*i)+i, i));
			answer = answer.mod(MOD);
		}
		
		System.out.println(answer);
	}

	public static BigInteger comb(long a, long b) {
		if(a-b < b) b = a-b;
		if(a == b) return new BigInteger("1");
		
		BigInteger res1 = new BigInteger("1");
		BigInteger res2 = new BigInteger("1");
		for(int i = 0; i < b; i++) {
			res1 = res1.multiply(new BigInteger(String.valueOf(a-i)));
			res2 = res2.multiply(new BigInteger(String.valueOf(b-i)));
		}
		
		res1 = res1.divide(res2).mod(MOD);
		return res1;
	}

}
