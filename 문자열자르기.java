package study;

public class Solution {
	public static int solution(String s) {
        int answer = 0;
        
        int min = 0x7fffffff;
        
        for (int i = 1; i <= s.length()/2; i++) {
        	int len = s.length();
        	int idx = 0;
        	int flag = 0;
        	String sub = s.substring(idx,idx+i); //i 길이
        	idx+=i;
        	System.out.println(">>"+i);
        	System.out.println("기준:"+sub);
        	while(s.length()-i>=idx) {
				String nextSub = s.substring(idx, idx+i);
				System.out.println(nextSub);
        		if(sub.equals(nextSub)) {
        			flag++;
        			len-=i;
        			idx += i;
        		}else {
        			len+=(int)(Math.log10(flag)+1);
        			flag = 0;
        			sub = s.substring(idx, idx+i);
        			System.out.println("새 기준:"+sub);
        		}
			}
        	min = min>len?len:min;
		}
        answer = min;
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(solution("abab"));
	}
}
