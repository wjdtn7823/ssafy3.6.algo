import java.util.*;
import java.io.*;

public class Main {

    public static int n, k;
    public static int[][] arr = new int[10][10];
    public static int[] A = new int[11];
    public static int[] B = new int[20];
    public static int[] C = new int[20];
    public static int[] visited = new int[20];
    public static boolean possible = false;

    public static void solve(int index) {
        if (index == n) {
            //System.out.println(Arrays.toString(A));
            int aindex = 0, bindex = 0, cindex = 0;
            boolean ab = true, bc = false, ac = false;
            int awin = 0, bwin = 0, cwin = 0;
            if(possible)
                return;
            while(true) {
                if (awin >= k) {
                    possible = true;
                    break;
                }
                if (bwin >= k || cwin >= k) {
                    break;
                }
                if (aindex >= n)
                    break;
                if (ab) {
                    ab = false;
                    if (arr[A[aindex++]][B[bindex++]] <= 1) {
                        bc = true;
                        bwin += 1;
                    }
                    else {
                        ac = true;
                        awin += 1;
                    }
                }
                else if (bc) {
                    bc = false;
                    if (arr[B[bindex++]][C[cindex++]] <= 1) {
                        ac = true;
                        cwin += 1;
                    }
                    else {
                        bwin += 1;
                        ab = true;
                    }
                }
                else if (ac){
                    ac = false;
                    if (arr[A[aindex++]][C[cindex++]] <= 1) {
                        cwin += 1;
                        bc = true;
                    }
                    else {
                        awin += 1;
                        ab = true;
                    }
                }
            }

            return;
        }
        for (int i = 1 ; i<= n ; i++) {
            if (visited[i] == 0) {
                A[index] = i;
                visited[i] = 1;
                solve(index + 1);
                visited[i] = 0;
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read = br.readLine().split(" ");
        n = Integer.parseInt(read[0]);
        k = Integer.parseInt(read[1]);
        for (int i = 1 ; i <= n ; i++) {
            read = br.readLine().split(" ");
            for (int j = 1 ; j <= n ; j++) {
                arr[i][j] = Integer.parseInt(read[j-1]);
            }
        }
        read = br.readLine().split(" ");
        for (int i = 0 ; i < 20 ; i++) {
            B[i] = Integer.parseInt(read[i]);
        }
        read = br.readLine().split(" ");
        for (int i = 0 ; i < 20 ; i++) {
            C[i] = Integer.parseInt(read[i]);
        }

        solve(0);
        System.out.println(possible ? 1 : 0);
    }

}