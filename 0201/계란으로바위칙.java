package 백준2;
import java.util.*;
import java.io.*;

class Egg{
	int dur;
	int w;
	Egg(int w, int s){
		this.dur = w; //내구도
		this.w = s; //무게
	}
}
public class 계란으로바위칙 {
	public static int answer =0,n ;
	public static Egg []eggs;
	public static boolean []v;
	public static void crashEgg(int curr_idx) {
		if(curr_idx==n) {
			int partial_answer = 0 ;
			for(int i = 0;i<n;i++) {
				if(eggs[i].dur<=0) partial_answer++;
			}
			if(answer<partial_answer) answer = partial_answer;
			return;
		}
		
		if(eggs[curr_idx].dur<=0) crashEgg(curr_idx+1);
		
		
		else {
			boolean hit = false;
			for(int i = 0 ;i <n;i++) {
				if(i==curr_idx || eggs[i].dur<=0) continue;
				eggs[i].dur -= eggs[curr_idx].w;
				eggs[curr_idx].dur -= eggs[i].w;
				hit = true;
				crashEgg(curr_idx+1);
				eggs[i].dur += eggs[curr_idx].w;
				eggs[curr_idx].dur += eggs[i].w;
			
			}
			
			if(!hit) crashEgg(n);
			
		}
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/Input_계란.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		
		eggs = new Egg[n];
		v = new boolean[n];
		
		StringTokenizer st;
		for(int i =0  ;i <n;i++) {
			int w,s;
			st = new StringTokenizer(bf.readLine());
			w=Integer.parseInt(st.nextToken());
			s=Integer.parseInt(st.nextToken());
			eggs[i] = new Egg(w,s);
		}
		
		
		v[0] = true;
		crashEgg(0 );
		
		System.out.println(answer);
		
		
		
		
		
		
		
	}

}
