package AlgorithmPractice;

import java.io.*;
import java.util.*;

public class 세훈이의선물가게 {
	static int[][] time = new int[86401][2];
	static int a,b,n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		int t,m;
		char c;
		int total = 0;
		for(int i = 1; i <= n; i++) {
			st= new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			c = st.nextToken().charAt(0);
			m = Integer.parseInt(st.nextToken());
			total += m;
			
			if(c == 'B') {
				if(a==0) {
					time[t][0] = m;
					continue;
				}
				while(time[t][0] != 0) {
					t++;
				}
				while(m>0) {
					for(int x = 1; x <= a; x++) {
						time[t++][0] = x;
					}
					m--;
				}
				
			} else if(c == 'R') {
				if(b==0) {
					time[t][1] = m;
					continue;
				}
				while(time[t][1] != 0) {
					t++;
				}
				while(m>0) {
					for(int x = 1; x <= b; x++) {
						time[t++][1] = x;
					}
					m--;
				}
			}
		}
		
		List<Integer> blue = new ArrayList<>();
		List<Integer> red = new ArrayList<>();
		int cnt = 0;
		for(int i = 0; i < time.length; i++) {
			if(a==0 && time[i][0] > 0) {
				for(int j = 0; j < time[i][0]; j++) {
					blue.add(cnt+1);
					cnt++;
				}
				continue;
			}
			if(b==0 && time[i][1] > 0) {
				for(int j = 0; j < time[i][1]; j++) {
					red.add(cnt+1);
					cnt++;
				}
				continue;
			}
			
			if(time[i][0] == 1) {
				blue.add(cnt+1);
				cnt++;
			}
			if(time[i][1] == 1) {
				red.add(cnt+1);
				cnt++;
			}
			
			if(cnt == total) break; 
		}
		
		System.out.println(blue.size());
		for(int i : blue) {
			System.out.print(i +" ");
		}
		System.out.println();
		System.out.println(red.size());
		for(int i : red) {
			System.out.print(i +" ");
		}
	}

}
