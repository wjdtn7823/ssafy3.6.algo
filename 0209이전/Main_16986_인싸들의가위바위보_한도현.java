
package Study;
import java.io.*;
import java.util.*;
public class Main_16986_인싸들의가위바위보_한도현 {
	public static int N,K; // 손동작 수, 필요한 승수
	public static int [][] whatWin = new int [10][10]; //가위바위보정보
	public static int [][] info = new int [4][21]; // 두 친구가 낼 정보
	public static boolean [] visited = new boolean[10]; //이미 냈던 것 저장
	public static int [] winCount = new int[4];
	public static boolean ans;
	public static int [] order = new int [4];
	public static void dfs(int round, int one, int two){
		if(winCount[1]>=K){
			ans = true;
			return;
		}
		if(winCount[2]>=K || winCount[3]>=K){
			return;
		}
		if(order[1]>N) return;		
		if(ans == true) return;
		//System.out.println(order[one]+ " " + order[two]);
		//one이나 two가 나라면 내가 낼 수 있는 것은 N개중 내가 이미 냈던 것 제외하고 모든 것
		int next_1=-1, next_2=-1; //one과 two가 다음번에 낼 것
		if(one!=1){
			next_1 = info[one][order[one]];
		}
		if(two!=1){
			next_2 = info[two][order[two]];
		}
		
		if(next_1==-1 || next_2==-1){ //one이나 two가 지우라면
			for(int i=1;i<=N;i++){
				if(visited[i]) continue;
				int temp = (next_1==-1)? whatWin[i][next_2]: whatWin[i][next_1];
				int winner = 0;
				if(next_1== -1){
					winner = (temp==2) ? one : two;
				}
				if(next_2 ==-1){
					winner = (temp==2) ? two: one;
				}
				visited[i] = true;
				winCount[winner]++;
				int next = 0;
				if(winner==1){
					if(one==1)
						next = (two==2)? 3 : 2;
					else if(two ==1){
						next = (one==2) ? 3 : 2;
					}
				}else{
					if(one ==1)
						next = (two==2)? 3: 2;
					else if(two==1)
						next = (one==2)? 3 : 2;
				}
				order[one]++;
				order[two]++;
				dfs(round+1,winner,next);
				visited[i] =false;
				winCount[winner]--;
				
				order[two]--;			
				order[one]--;
				
			}
		}
		else{ //둘중에 지우 포함되지 않았다면
			int temp = whatWin[next_1][next_2];
			int winner =0;
			if(temp==2){
				winner = one;
			}
			else if(temp ==0){
				winner = two;
			}
			else{ //비기는 경우 숫자가 큰 쪽이 이김
				winner = (next_1 > next_2) ? one : two;
			}
			winCount[winner]++;
			order[one]++;
			order[two]++;
			dfs(round+1,1,winner);
			winCount[winner]--;
			order[one]--;
			order[two]--;
		}
	}
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N =Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine()," ");
			int cnt =1;
			while(st.hasMoreTokens()){
				whatWin[i][cnt++] = Integer.parseInt(st.nextToken());
			}
		}
	
		for(int i=2;i<=3;i++){
			st = new StringTokenizer(br.readLine()," ");
			for(int j=1;j<=20;j++){
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = false;
		order[2] = order[3]= 1;
		dfs(1,1,2);
		if(ans){
			System.out.println(1);
		}
		else{
			System.out.println(0);
		}
	}

}
