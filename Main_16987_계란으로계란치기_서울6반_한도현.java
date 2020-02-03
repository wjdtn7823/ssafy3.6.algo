package Study;
import java.io.*;
import java.util.*;

class Egg{
	int durability;
	int weight;
	Egg(){
		
	}
	Egg(int a, int b){
		durability = a;
		weight = b;
	}
}

public class Main_16987_계란으로계란치기_서울6반_한도현 {
	public static int N;
	public static int ans;
	public static int real_ans;
	public static Egg []egg = new Egg[10];
	public static boolean[] broken = new boolean[10];
	public static void start(int idx){ //0번째 에그부터 idx==N-1까지
		if(idx==N){
			int candi =0;
			for(int i=0;i<N;i++){
				if(egg[i].durability <=0) candi++;
			}
			ans = Math.max(ans, candi);
			return;
		}
		if(egg[idx].durability<=0) {
			start(idx+1);
			return;
		}
		//idx번째 계란으로 오른쪽에 있는 안깨진 계란 중에서 선택하는 모든 경우의 수
		boolean flag= false;
		for(int i=0;i<N;i++){
			if(egg[i].durability<=0) continue;
			if(idx==i) continue;
			flag=true;
			egg[idx].durability -= egg[i].weight;
			egg[i].durability -= egg[idx].weight;
			start(idx+1);
			egg[idx].durability += egg[i].weight;
			egg[i].durability += egg[idx].weight;
		}
		if(flag==false && idx==(N-1)){
			int candi =0;
			for(int i=0;i<N;i++){
				if(egg[i].durability <=0) candi++;
			}
			ans = Math.max(ans, candi);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for(int i=0;i<N;i++){
			int s = sc.nextInt();
			int w = sc.nextInt();
			egg[i] = new Egg(s,w);
		}
		
		start(0); 
		System.out.println(ans);
	}

}
