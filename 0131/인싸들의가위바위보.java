import java.util.*;
import java.io.*;
public class Main {
	
	public static int n,k;
	public static int [][] graph;
	public static int [][]pairs = new int[3][20];
	public static int canIwin= 0;
	public static boolean overflag = false;
	public static boolean v[];
	
	

	public static int whowins(int usr1 , int usr2, int pick1, int pick2) {
	
		if(pick1==pick2) return Math.max(usr1,usr2);
		else {
		
			int  t = graph[pick1][pick2];
	
			if(t==2) return usr1;
			if(t==0) return usr2;
		}
		
		return -1;
	
	}
	private static void fight(int []turncnt, int usr1, int usr2, int []wincnt, boolean [] v) {

		if(overflag) return;
		
		if(wincnt[0]==k) { //³ªÀÇ ½Â¸®
			canIwin = 1;
			overflag = true;
		
			return;
		}
		if(wincnt[1]==k || wincnt[2]==k) { // 1,2 ½Â¸®
	
			return;
		}
		
		
		if(usr1==0) { // 
			for(int i = 1 ;i<=n;i++) {
				if(v[i]) continue;
				v[i] = true;
		
				int pick1= i;
				int pick2 = pairs[usr2][turncnt[usr2]];
				
				int winner= whowins(usr1,usr2,pick1,pick2);
				
				wincnt[winner]++;
				turncnt[usr2]++;
				int next_fighter = 3-usr1-usr2;
			
				fight(turncnt,winner,next_fighter,wincnt,v);
				turncnt[usr2]--;
				v[i] = false;
				wincnt[winner]--;
			}
			
			
		}
		
		else if(usr2==0) {
			for(int i = 1 ;i<=n;i++) {
				if(v[i]) continue;
				v[i] = true;
			
				int pick1 = pairs[usr1][turncnt[usr1]];
				int pick2= i;
				
				int winner= whowins(usr1,usr2,pick1,pick2);
				wincnt[winner]++;
				turncnt[usr1]++;
				int next_fighter = 3-usr1-usr2;
			
				fight(turncnt,winner,next_fighter,wincnt,v);
				turncnt[usr1]--;
				v[i] = false;
				wincnt[winner]--;
			}
			
			
		}
		else {
			//System.out.println("´Ù¸¥ »ç¶÷³¢¸® ½Î¿ò\n");
			int pick1= pairs[usr1][turncnt[usr1]];
			int pick2 = pairs[usr2][turncnt[usr2]];
			
			int winner= whowins(usr1,usr2,pick1,pick2);
			wincnt[winner]++;
			int next_fighter = 3-usr1-usr2;
			turncnt[usr1]++;
			turncnt[usr2]++;
			fight(turncnt,winner,next_fighter,wincnt,v);
			turncnt[usr1]--;
			turncnt[usr2]--;
			wincnt[winner]--;
		}
	}


	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		graph = new int[n+1][n+1];
		v = new boolean[n+1];
		
		for(int i = 1 ;i <= n;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j= 1 ; j <= n;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			//	System.out.printf("i= %d j=%d graph[i][j] =%d\n",i,j,graph[i][j]);
			}
			
		}
		
		st = new StringTokenizer(bf.readLine());
		for(int i =0  ;i <20;i++) {
			pairs[1][i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(bf.readLine());
		for(int i =0  ;i <20;i++) {
			pairs[2][i] = Integer.parseInt(st.nextToken());
		}
		

		
		//input
		
		
		// solve
		
		fight(new int[3],0,1,new int[3], new boolean[n+1]);

		System.out.println(canIwin);
		
		return;
		
		
	}

}