import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class PAIR4{
	int x, y, bit, cnt;
	PAIR4(){}
	PAIR4(int x1, int y1, int b1, int c1){
		x = x1; y = y1; bit = b1; cnt = c1;
	}
}

public class Main {

	static int n, m, map2[][];
	static int visited[][][];
	static char map[][];
	final static int dx[] = {-1, 1, 0, 0};
	final static int dy[] = {0, 0, -1, 1};
	static Queue<PAIR4> q = new LinkedList<PAIR4>(); 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); n = Integer.parseInt(st.nextToken());
			if(n == 0 && m == 0) break;
			map = new char[n][m]; int cnt = 1; map2 = new int[n][m];
			visited = new int[n][m][1 << 10];
			for(int i=0; i<n; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0; j < m; j++) {
					for(int k =0; k < (1 << 10); k++) {
						visited[i][j][k] = 0x7fffffff;
					}
					map2[i][j] = 0;
					if(map[i][j] == 'o') {
						q.add(new PAIR4(i, j, 0, 0)); map[i][j] = '.';
						visited[i][j][0] = 0;
					}else if(map[i][j] == '*') {
						map2[i][j] = cnt++;
					}else if(map[i][j] == 'x') map2[i][j] = -1;
					
				}
			}
			cnt -= 1;
			int Ans = 0x7fffffff;
			while(!q.isEmpty()) {
				PAIR4 cur = q.poll();
				if(cur.cnt >= Ans) continue;
				if(cur.bit == ((1 << cnt) - 1)) {
					Ans = Math.min(Ans, cur.cnt); continue;
				}
				for(int dir = 0; dir < 4; dir++) {
					int new_x = cur.x + dx[dir], new_y = cur.y + dy[dir];
					if(new_x < 0 || new_x >= n || new_y < 0 || new_y >=m) continue;
					if(map2[new_x][new_y] == -1) continue;
					if(visited[new_x][new_y][cur.bit] <= cur.cnt + 1) continue;
					
					if(map2[new_x][new_y] > 0) {
						int garbage_num = map2[new_x][new_y] - 1;
						int new_bit = (cur.bit | (1 << garbage_num));
						if(visited[new_x][new_y][new_bit] <= cur.cnt + 1) continue;
						visited[new_x][new_y][new_bit] = cur.cnt + 1;
						q.add(new PAIR4(new_x,new_y, new_bit, cur.cnt + 1));
					}else {
						visited[new_x][new_y][cur.bit] = cur.cnt + 1;
						q.add(new PAIR4(new_x,new_y, cur.bit, cur.cnt + 1));
					}
				}
			}
			if(Ans == 0x7fffffff) Ans = -1;
			System.out.println(Ans);
		}
	}

}