package algostudy2;

import java.util.*;
import java.io.*;
public class Swea_보호필름 {
	
	public static int answer = 9999;
	public static int d,w,k;
	public static int [][]film;
	
	public static boolean check_consecutiveK() {
		
		for(int i= 0 ; i <w;i++) { //w개의 열에 대해 검사
			//System.out.println("i= "+ i );
			boolean columnflag = true; //단일 열 검사 플래그
			boolean completeflag = false; //열 전체 검사 플래그
			
			for(int j =0 ;j<=d-k;j++) { //연속된 k열이 연속된 숫자인지 체크
				columnflag = true;
				for(int l = j+1;l<j+k;l++) {
					if(film[j][i]!=film[l][i]) {
						columnflag = false; 
						//System.out.println("j= "+ j + "l ="+ l);
						
					}
				}
				if(columnflag) {
			//		System.out.println("i= "+i+"j = "+ j);
					completeflag = true;
					break;
				}
				
			}
			
			
			if(completeflag == false) return false;
		}
		return true;
	}
	
	
	public static void dfs(int cnt, int idx) { //cnt 행개에 약품 투입한것임 // idx 행까지 탐색 투입 완료.
		//print_film();
		if(cnt>=answer) return;
		
		if(check_consecutiveK()==true) {
		//	System.out.println("-----------------idx ="+ idx+ "cnt = --------------------" + cnt);
			
			if(answer>cnt)
			answer = cnt;
			return;
		}
		if(idx==d) return;
		int [][]filmcopy = new int[d][w];
		//약품 투입 X
		
		for(int i = 0 ; i < d;i++) { //원본 저장
			for(int j= 0 ; j <w;j++) {
				filmcopy[i][j] = film[i][j];
			}
		}
		//System.out.println("약품 투입 X idx = "+ idx);
		dfs(cnt,idx+1);

		for(int i = 0 ; i <w;i++) {//A 약품 투입
			film[idx][i] = 0;
		}
		
		//print_film();
		//System.out.println("A약품 투입 idx = "+ idx);
		dfs(cnt+1,idx+1);
		
		
		
		for(int i = 0 ; i <w;i++) {//B약품 투입
			film[idx][i] = 1;
		}
		//System.out.println("B약품 투입 idx = "+ idx);
		dfs(cnt+1,idx+1);
		
		for(int i = 0 ; i < d;i++) {
			for(int j= 0 ; j <w;j++) {
				film[i][j] = filmcopy[i][j];
			}
		}
		
		
	}
	
	public static void print_film() {
		
		for(int i = 0 ;i <d;i++) {
			for(int j=0;j<w;j++) {
				System.out.print(film[i][j]+" " );
			}
			System.out.println();
		}
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test_num = Integer.parseInt(bf.readLine());
		
		for(int tc = 1 ; tc <= test_num; tc++) {
			
			
			answer = 15;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			film = new int[d][w];
			
			for(int i = 0 ;i <d;i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j= 0 ;j  <w;j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
				
			}
			//System.out.println("k= "+k);
			//print_film();
			//System.out.println(check_consecutiveK());
			if(k==1) {
				System.out.println("#"+tc+" "+0);
				continue;
			}
			dfs(0,0);
			
			
			System.out.println("#"+tc+" "+answer);
			
			
			
			
	
		}
		
	}

}