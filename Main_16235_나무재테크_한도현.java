
package Study;

import java.io.*;
import java.util.*;

public class Main_16235_나무재테크_한도현 {
	public static int N, M, K;
	public static ArrayList<Integer>[][] namoo = new ArrayList[11][11];
	public static int[][] nutrients = new int[11][11];
	public static int[][] S2D2 = new int[11][11];
	public static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	public static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = 1;
			while (st.hasMoreTokens()) {
				S2D2[i][cnt++] = Integer.parseInt(st.nextToken());
			}
			for(int j=1;j<=N;j++){
				namoo[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			namoo[y][x].add(age);
		}
		// 초기 영양분을 5로
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				nutrients[i][j] = 5;
			}
		}
		while (K != 0) {
			int[][] plus = new int[11][11]; // 봄에 나무가 죽을 경우 여름에 양분이 될 것을 저장
			// 봄 , 자신의 나이만큼 양분을 먹고 나이가 1증가, 어린 나무부터 먹는다.
			// 양분이 부족하면 조금도 먹지 못하고 즉시 죽는다. 이 때, 나이/2만큼이 양분이 된다
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (namoo[i][j].size() > 0) { // 양분을 먹음
						//Collections.sort(namoo[i][j]);
						// 어린 나무부터 양분을 먹음
						int size = namoo[i][j].size();
						for (int k = 0; k < size; k++) {
							int age = namoo[i][j].get(k);
							if (nutrients[i][j] < age) { // 죽는경우
								int cnt = 0;
								while (cnt != (size - k)) {
									plus[i][j] += namoo[i][j].get(k) / 2;
									namoo[i][j].remove(k);
									cnt++;
								}
								break;
							}
							// 죽지 않는 경우는 영양분을 섭취하고 나이가 1 증가
							namoo[i][j].set(k, age + 1);
							nutrients[i][j] -= age;
						}
					}
				}
			}
			// 여름이 되었다. 죽은 나무를 양분으로 추가한다.
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					nutrients[i][j] += plus[i][j];
				}
			}

			// 가을이 되었다. 나무가 번식을 한다. 나이가 5의 배수인 나무가 인접 8칸에 나이 1인 나무 생성
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int namoo_cnt = namoo[i][j].size();
					if (namoo_cnt == 0)
						continue;
					for (int k = 0; k < namoo_cnt; k++) {
						if (namoo[i][j].get(k) % 5 != 0)
							continue;
						// 번식하는 경우 (8방향 확인)
						for (int dir = 0; dir < 8; dir++) {
							int ny = i + dy[dir];
							int nx = j + dx[dir];
							if (ny < 1 || nx < 1 || ny > N || nx > N)
								continue;
							namoo[ny][nx].add(0,1);
						}
					}
				}
			}
			// 겨울이 되었다. 땅에 양분S2D2를 추가한다.
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					nutrients[i][j] += S2D2[i][j];
				}
			}
			K--;
		}
		int ans =0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				ans+=namoo[i][j].size();
			}
		}
		System.out.println(ans);
	}
}
=======
package Study;

import java.io.*;
import java.util.*;

public class Main_16235_나무재테크_한도현 {
	public static int N, M, K;
	public static ArrayList<Integer>[][] namoo = new ArrayList[11][11];
	public static int[][] nutrients = new int[11][11];
	public static int[][] S2D2 = new int[11][11];
	public static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	public static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = 1;
			while (st.hasMoreTokens()) {
				S2D2[i][cnt++] = Integer.parseInt(st.nextToken());
			}
			for(int j=1;j<=N;j++){
				namoo[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			namoo[y][x].add(age);
		}
		// 초기 영양분을 5로
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				nutrients[i][j] = 5;
			}
		}
		while (K != 0) {
			int[][] plus = new int[11][11]; // 봄에 나무가 죽을 경우 여름에 양분이 될 것을 저장
			// 봄 , 자신의 나이만큼 양분을 먹고 나이가 1증가, 어린 나무부터 먹는다.
			// 양분이 부족하면 조금도 먹지 못하고 즉시 죽는다. 이 때, 나이/2만큼이 양분이 된다
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (namoo[i][j].size() > 0) { // 양분을 먹음
						//Collections.sort(namoo[i][j]);
						// 어린 나무부터 양분을 먹음
						int size = namoo[i][j].size();
						for (int k = 0; k < size; k++) {
							int age = namoo[i][j].get(k);
							if (nutrients[i][j] < age) { // 죽는경우
								int cnt = 0;
								while (cnt != (size - k)) {
									plus[i][j] += namoo[i][j].get(k) / 2;
									namoo[i][j].remove(k);
									cnt++;
								}
								break;
							}
							// 죽지 않는 경우는 영양분을 섭취하고 나이가 1 증가
							namoo[i][j].set(k, age + 1);
							nutrients[i][j] -= age;
						}
					}
				}
			}
			// 여름이 되었다. 죽은 나무를 양분으로 추가한다.
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					nutrients[i][j] += plus[i][j];
				}
			}

			// 가을이 되었다. 나무가 번식을 한다. 나이가 5의 배수인 나무가 인접 8칸에 나이 1인 나무 생성
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int namoo_cnt = namoo[i][j].size();
					if (namoo_cnt == 0)
						continue;
					for (int k = 0; k < namoo_cnt; k++) {
						if (namoo[i][j].get(k) % 5 != 0)
							continue;
						// 번식하는 경우 (8방향 확인)
						for (int dir = 0; dir < 8; dir++) {
							int ny = i + dy[dir];
							int nx = j + dx[dir];
							if (ny < 1 || nx < 1 || ny > N || nx > N)
								continue;
							namoo[ny][nx].add(0,1);
						}
					}
				}
			}
			// 겨울이 되었다. 땅에 양분S2D2를 추가한다.
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					nutrients[i][j] += S2D2[i][j];
				}
			}
			K--;
		}
		int ans =0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				ans+=namoo[i][j].size();
			}
		}
		System.out.println(ans);
	}
}

