package AlgorithmPractice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Chicken {
	int x;
	int y;
	ArrayList<Integer> distance;
	
	public Chicken(int x, int y) {
		this.x = x;
		this.y = y;
		this.distance = new ArrayList<>();
	}
}
public class 치킨배달 {
	public static int n,m;
	public static int[][] map;
	public static ArrayList<Chicken> chickenList = new ArrayList<>();
	public static int[] chicArr;
	public static int minDistance = Integer.MAX_VALUE;
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chickenList.add(new Chicken(i,j));
				}
			}
		}
		chicArr = new int[m];
		counAllDistance();
		chickenDistance(-1,0,chicArr);
		System.out.println(minDistance);
	}

	public static void counAllDistance() {
		for(Chicken c : chickenList) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <=n; j++) {
					if(map[i][j] == 1) {
						c.distance.add(Math.abs(c.x - i) + Math.abs(c.y - j));
					}
				}
			}
			
		}
	}
	public static void chickenDistance(int idx, int cnt, int[] chicArr) {
		if(cnt == m) {
			int totaldist = 0;
			int tmp;
			int dist;
			idx = 0;
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <=n; j++) {
					if(map[i][j] == 1) {
						tmp = Integer.MAX_VALUE;
						for(int k = 0; k < chicArr.length; k++) {
							dist = chickenList.get(chicArr[k]).distance.get(idx);
							if(dist < tmp) {
								tmp = dist;
							}
						}
						totaldist += tmp;
						idx++;
					}
				}
			}
			if(totaldist < minDistance) {
				minDistance = totaldist;
			}
			return;
		}
		for(int i = idx+1; i < chickenList.size(); i++) {
			chicArr[cnt] = i;
			chickenDistance(i, cnt+1, chicArr);
		}
	}
}