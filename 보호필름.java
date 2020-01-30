import java.io.*;
import java.util.*;
 
public class 보호필름 {
    public static int D, W, K;
    public static int[][] film = new int[13][20];
    public static int ans; 
 
    public static void show() {
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(film[i][j] + " ");
            }
            System.out.println();
        }
    }
 
    public static void dfs(int row, int cnt) {
        if (row == D) { // 기저사레. 마지막 행까지 선택을 마쳤을 때
            // show();
            for (int i = 0; i < W; i++) {
                boolean flag = false;
                for (int j = 0; j <= D - K; j++) {
                    int x = film[j][i];
                    boolean suc = true;
                    for (int k = j; k < j + K; k++) {
                        if (film[k][i] != x) {
                            suc = false;
                            break;
                        }
                    }
                    if (suc == true) {
                        flag = true;
                    }
                }
                if (flag == false)
                    return;
            }
            ans = Math.min(ans, cnt);
            return;
        }
        if (cnt >= ans)
            return;
        // 1. row행을 바꾸지 않기
        dfs(row + 1, cnt);
        // 2. row행을 0이나 1로 바꾸기
        int[] backUp = new int[W];
        for (int i = 0; i < W; i++) {
            backUp[i] = film[row][i];
        }
        for (int i = 0; i < W; i++) {
            film[row][i] = 0;
        }
        dfs(row + 1, cnt + 1);
        for (int i = 0; i < W; i++) {
            film[row][i] = 1;
        }
        dfs(row + 1, cnt + 1);
        // 3.원래대로 바꾸어 줌
        for (int i = 0; i < W; i++) {
            film[row][i] = backUp[i];
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int cnt = 0;
                while (st.hasMoreTokens()) {
                    film[i][cnt++] = Integer.parseInt(st.nextToken());
                }
            } // --- 입력끝
 
            // 완탐(1. x행을 안바꾸기, 2. x행을 1로 바꾸기, 3. x행을 0으로 바꾸기
            // 현재 ans보다 큰 값이라면 더이상 진행할 필요 없음
            dfs(0, 0);
            System.out.println("#"+tc+" " + ans);
        }
    }
}