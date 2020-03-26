package AlgorithmPractice;

import java.io.*;
import java.util.*;

public class 텔레포트 {
	static class City {
		int x; int y;
		boolean special;
		public City() {}
		public City(int x, int y, boolean special) {
			this.x =x; this.y = y;
			this.special = special;
		}
	}
	static int n;
	static List<City> clist;
	static int[] near;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		clist = new ArrayList<>();
		clist.add(new City());
		int s,x,y;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			clist.add(new City(x,y,s==1?true:false));
		}
		
		near = new int[n+1];
		nearSpecial();
		int m = Integer.parseInt(br.readLine());
		int a,b;
		int time1, time2;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
		
			time1 = Math.abs(clist.get(a).x - clist.get(b).x)+Math.abs(clist.get(a).y - clist.get(b).y);
			
			time2 = Math.abs(clist.get(a).x - clist.get(near[a]).x)+Math.abs(clist.get(a).y - clist.get(near[a]).y);
			time2 += Math.abs(clist.get(b).x - clist.get(near[b]).x)+Math.abs(clist.get(b).y - clist.get(near[b]).y) + t;
			sb.append(Math.min(time1, time2));
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	public static void nearSpecial() {
		
		int minD;
		int dist;
		for(int i = 1; i <= n; i++) {
			minD = 987654321;
//			if(clist.get(i).special) {
//				near[i] = i;
//				continue;
//			}
			for(int j = 1; j <= n; j++) {
				if(!clist.get(j).special) continue;
				dist = Math.abs(clist.get(i).x - clist.get(j).x)+Math.abs(clist.get(i).y - clist.get(j).y);
				if(dist < minD) {
					minD = dist;
					near[i] = j;
				}
			}
		}
		
	}
}
