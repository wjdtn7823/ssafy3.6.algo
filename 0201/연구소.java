package 백준2;
import java.util.*;
import java.io.*;
public class 연구소 {
	public static int n,m;
	public static int [][]labMap;
	public static int answer = 0;
	public static int wallidx[] = new int[3];
	
	public static int CalSafetyArea() {

		int [][]labCopy = new int[n+1][m+1];
		Queue<Integer> virusq = new LinkedList<Integer>();
		for(int i = 0 ;i <n;i++) {
			for(int j= 0 ;j <m;j++) {
				labCopy[i][j] = labMap[i][j];
				if(labCopy[i][j] ==2) {
				
					virusq.add(m*i+j);}
			}
		}
		for(int i = 0 ;i<wallidx.length;i++) {
			int x = wallidx[i]/m;
			int y = wallidx[i]%m;
			
			labCopy[x][y] = 1;
		}
		
		int partial_answer = 0 ;
		int []dx = {-1,1,0,0};
		int []dy = {0,0,-1,1};
	
		while(!virusq.isEmpty()) {
			int p = virusq.poll();
			int px = p/m;
			int py = p%m;
		
			for(int i = 0;i<4;i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];

				if(nx<0||ny<0||nx>=n||ny>=m) continue;
				if(labCopy[nx][ny]==0) {
					labCopy[nx][ny] = 2;
					virusq.add(nx*m+ny);
				}		
			}
			
		}
		
		  for(int i = 0 ;i <n;i++) { 
			  for(int j= 0 ;j <m;j++) { 

			  if(labCopy[i][j]==0) partial_answer++; 
			  }
		  }
	
		  return partial_answer;

}
	
	public static void dfs(int idx, int cnt) {
		
		if(cnt==3) {
		
			int partial_answer = CalSafetyArea();
			
			if(partial_answer>answer) {
				 answer =partial_answer;
			}
			
			return;
		}
		
		else {
			for(int i = idx ;i <n*m;i++) {
				int x = i/m;
				int y=  i%m;
				if(labMap[x][y]!=0) continue;
				wallidx[cnt] = i;		
				dfs(i+1,cnt+1);
	
			}
		}
			
	}
	
	
	public static void main(String[] args) throws Exception{
	
		System.setIn(new FileInputStream("res/input_lab.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		labMap = new int[n+1][m+1];
		
		for(int i = 0 ;i < n;i++) {
			st = new StringTokenizer(bf.readLine()); 
			for(int j = 0 ;j <m;j++) {
				labMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	

		dfs(0,0);
		
		System.out.println(answer);
		
	}

}
