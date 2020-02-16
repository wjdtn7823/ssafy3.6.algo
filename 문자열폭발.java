package AlgorithmPractice;
import java.util.Scanner;
import java.util.Stack;
public class 문자열폭발 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] str = sc.nextLine().toCharArray();
		char[] bomb = sc.nextLine().toCharArray();
		boolean flag = true;
		Stack<Character> st = new Stack<>();
		for(int i = 0; i < str.length; i++) {
			st.push(str[i]);
			if(str[i] == bomb[bomb.length-1]) {
				if(st.size() < bomb.length) continue;
				for(int j = 1; j < bomb.length; j++) {
					if(st.get(st.size()-1-j) != bomb[bomb.length-1-j] ) {
						flag = false;
						break;
					}
				}
				if(flag) {
					for(int j = 0; j < bomb.length; j++) {
						st.pop();
					}
					
				} else {
					flag = true; 
				}
				
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(st.size() == 0) System.out.println("FRULA");
		else {
			while(!st.isEmpty()) sb.append(st.pop());
		}
		
		System.out.println(sb.reverse());
		
	}
}