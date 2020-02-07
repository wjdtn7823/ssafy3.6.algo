import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int dust_index, answer;
    public static int[][] dist = new int [500][500];
    public static char[][] board = new char[20][20];
    public static int[][] dust = new int [10][2];

    public static int[] arr = new int[11];
    public static int[] v = new int[11];

    public static int[] dx = {-1, 0, 0, 1};
    public static int[] dy = {0, -1, 1, 0};

    public static void dfs(int index) {
        if (index == dust_index) {
            //System.out.println(Arrays.toString(arr));
            int temp = 0;
            int first = dust[0][0] * m + dust[0][1];
            for (int i = 1 ; i < index ; i++) {
                int cur = arr[i-1];
                int cur_d = dust[cur][0]*m + dust[cur][1];
                int next = arr[i];
                int next_d = dust[next][0]*m + dust[next][1];
                if (dist[cur_d][next_d] == 987654321)
                    return;
                //System.out.printf("%d %d %d\n", cur, next, dist[cur_d][next_d]);
                temp += dist[cur_d][next_d];
            }
            answer = Math.min(answer, temp);

            return;
        }
        for (int i = 1 ; i < dust_index ; i++) {
            if (v[i] == 0) {
                v[i] = 1;
                arr[index] = i;
                dfs(index + 1);
                v[i] = 0;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String[] read = br.readLine().split(" ");
            m = Integer.parseInt(read[0]);
            n = Integer.parseInt(read[1]);

            if (n == 0 && m == 0)
                break;
            dust_index = 1;
            answer = 987654321;
            for (int i = 0 ; i < n ; i++) {
                String s = br.readLine();
                for (int j = 0 ; j < m ; j++) {
                    board[i][j] = s.charAt(j);
                    if (board[i][j] == '*') {
                        dust[dust_index][0] = i;
                        dust[dust_index][1] = j;
                        dust_index += 1;
                    }
                    if (board[i][j] == 'o') {
                        dust[0][0] = i;
                        dust[0][1] = j;
                    }
                }
            }
            int M = n*m;
            for (int i = 0 ; i < M ; i++) {
                for (int j = 0 ; j < M ; j++) {
                    dist[i][j] = 987654321;
                }
            }
            
            for(int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (board[i][j] == 'x')
                        continue;
                    for (int k = 0 ; k < 4 ; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                            continue;
                        if (board[nx][ny] != 'x') {
                            dist[i*m + j][nx*m + ny] = dist[nx*m + ny][i*m + j] = 1;
                        }
                    }
                }
            }
            for (int k = 0 ; k < M ; k++) {
                for (int i = 0 ; i < M ; i++) {
                    for (int j = 0 ; j < M ; j++) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
            dfs(1);
            System.out.println(answer == 987654321 ? -1 : answer);
        }
    }
}