package algostudy2;
import java.util.*;
import java.io.*;
public class 인싸들의가위바위보 {
	
	public static int n,k;
	public static int [][] graph;
	public static int [][]pairs = new int[3][20];
	public static int canwin;
	public static boolean overflag = false;
	public static boolean v[];
	
	
	
	public static int whowins(int usr1 , int usr2, int pick1, int pick2) {
		int winner = 0 ;
		
		if(pick1==pick2) return Math.max(usr1,usr2);
		else {
		
			int  t = graph[pick1][pick2];
	
			if(t==2) return usr1;
			if(t==0) return usr2;
		}
		
		return winner;
		
	
	}
	private static void fight(int matchcnt, int []turncnt, int usr1, int usr2, int []win, boolean [] v) {
		
		for(int i =0 ;i <3;i++)
	
		if(overflag) return;
		
		if(win[0]==k) {
			canwin = 1;
			overflag = true;
		//	System.out.println("내가 이김");
			return;
		}
		if(win[1]==k || win[2]==k) {
	//		System.out.println("다른 누군가가 이김");
			return;
		}
		
		
		if(usr1==0) {
			for(int i = 1 ;i<=n;i++) {
				if(v[i]) continue;
				v[i] = true;
		
				int pick1= i;
				int pick2 = pairs[usr2][turncnt[usr2]];
				
				int winner= whowins(usr1,usr2,pick1,pick2);
				
				win[winner]++;
				turncnt[usr2]++;
				int next_fighter = 3-usr1-usr2;
			
				fight(matchcnt+1,turncnt,winner,next_fighter,win,v);
				turncnt[usr2]--;
				v[i] = false;
				win[winner]--;
			}
			
			
		}
		
		else if(usr2==0) {
			for(int i = 1 ;i<=n;i++) {
				if(v[i]) continue;
				v[i] = true;
			//	System.out.printf("usr1=%d usr2=%d 나의 패 =%d\n",usr1,usr2,i);
				int pick1 = pairs[usr1][turncnt[usr1]];
				int pick2= i;
				
				int winner= whowins(usr1,usr2,pick1,pick2);
				win[winner]++;
				turncnt[usr1]++;
				int next_fighter = 3-usr1-usr2;
			
				fight(matchcnt+1,turncnt,winner,next_fighter,win,v);
				turncnt[usr1]--;
				v[i] = false;
				win[winner]--;
			}
			
			
		}
		else {
			//System.out.println("다른 사람 싸우기\n");
			int pick1= pairs[usr1][turncnt[usr1]];
			int pick2 = pairs[usr2][turncnt[usr2]];
			
			int winner= whowins(usr1,usr2,pick1,pick2);
			win[winner]++;
			int next_fighter = 3-usr1-usr2;
			turncnt[usr1]++;
			turncnt[usr2]++;
			fight(matchcnt+1,turncnt,winner,next_fighter,win,v);
			turncnt[usr1]--;
			turncnt[usr2]--;
			win[winner]--;
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
		
		canwin = 0;
		
		//input
		
		
		// solve
		
		fight(0,new int[3],0,1,new int[3], new boolean[n+1]);
		
		
		
			
		
		
		
		
		
		
		//solve
		
		
		
		
		System.out.println(canwin);
		
		return;
		
		
	}

}
