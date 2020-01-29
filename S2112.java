import java.io.*;

public class S2112 {

    public static int d, w, k, answer;
    public static int[][] board = new int[13][20];
    public static int[] to_change = new int[13];
    public static int[][] temp = new int[13][20];

    public static boolean possible() {
        for (int i = 0 ; i < d ; i++) {
            for (int j = 0 ; j < w ; j++) {
                temp[i][j] = board[i][j];
            }
        }
        for (int i = 0 ; i < d ; i++) {
            if (to_change[i] == 0) {
                for (int j = 0 ; j < w ; j++) {
                    temp[i][j] = 0;
                }
            }
            else if (to_change[i] == 1) {
                for (int j = 0 ; j < w ; j++) {
                    temp[i][j] = 1;
                }
            }
        }

        for (int j = 0 ; j < w ; j++) {
            boolean row = false;
            for (int i = 0 ; i <= d - k ; i++) {
                boolean check = true;
                int target = temp[i][j];
                for (int l = 1 ; l < k ; l++) {
                    if (temp[i+l][j] != target) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    row = true;
                    break;
                }
            }
            if (!row){
                return false;
            }
        }
        return true;
    }

    public static void solve(int index, int change) {
        if (index == d) {
            if (change < answer) {
                if (possible()) {
                    answer = change;
                }
            }
            return;
        }
        if (change > k)
            return;
        to_change[index] = 2;
        solve(index + 1, change);
        to_change[index] = 0;
        solve(index + 1, change + 1);
        to_change[index] = 1;
        solve(index + 1, change + 1);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        for (int t =1 ; t <= testcase ; t++) {

            for (int i = 0 ; i < 13 ; i++) {
                to_change[i] = 2;
            }

            String[] first = br.readLine().split(" ");
            d = Integer.parseInt(first[0]);
            w = Integer.parseInt(first[1]);
            k = Integer.parseInt(first[2]);
            for (int i = 0 ; i < d; i++) {
                String[] s = br.readLine().split(" ");
                for (int j = 0 ; j < s.length ; j++) {
                    board[i][j] = Integer.parseInt(s[j]);
                }
            }

            answer = k;
            solve(0, 0);
            System.out.printf("#%d %d\n", t, answer);

        }
    }
}
