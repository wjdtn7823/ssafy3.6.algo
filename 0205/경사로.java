package 백준;

import java.util.*;
import java.io.*;

public class 경사로 {

    public static int n, l;

    public static boolean look_forward(int[] a, int j) {
        // System.out.printf("j=%d l=%d\n",j,l);
        for (int i = j + 1; i < l + j + 1; i++) {
            if (i >= n)
                return false;
            // System.out.printf("i=%d j=%d a[i]=%d a[j]=%d\n",i,j,a[i],a[j]);
            if (a[i] + 1 != a[j])
                return false;
        }
        return true;
    }

    public static int check_road(int[][] map) {
        int retval = 0;

        for (int i = 0; i < n; i++) {
            boolean possible = true;
            int[] temp = new int[n];
            boolean[] iscurve = new boolean[n];
            temp = map[i].clone();
            // System.out.printf("i=%d\n", i);
            int consecutive = 1;
            for (int j = 0; j < n - 1; j++) {
                // System.out.printf("j=%d consecutive =%d tmp[j= %d tmp[j+1] =%d\n", j,
                // consecutive,temp[j],temp[j+1]);
                if (Math.abs(temp[j + 1] - temp[j]) >= 2) { // 높이 2차이
                    possible = false;
                    break;
                } else if (temp[j] == temp[j + 1]) { // 같음
                    consecutive++;
                    continue;
                } else {
                    if (temp[j] > temp[j + 1]) { // 앞에것이 더 큼
                        // System.out.println("앞에께 더 큼");
                        if (look_forward(temp, j)) {
                            // System.out.println("true\n");
                            j = j + l - 1;
                            consecutive = 0;
                        } else {
                            // System.out.println("possilbe =false\n");
                            possible = false;
                            break;
                        }

                    } else { // 뒤에것이 더 큼
                        if (consecutive >= l) {
                            consecutive = 1;
                        } else {
                            possible = false;
                            break;
                        }
                    }

                }

            }

            if (possible) {
                // System.out.printf("------------possible i=%d\n------------", i);
                retval++;
                continue;
            }

        }
        return retval;
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        int[][] mnt = new int[n][n];
        int[][] mnt2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                mnt[i][j] = Integer.parseInt(st.nextToken());
                mnt2[j][i] = mnt[i][j];
            }
        }
        int answer = 0;
        // System.out.println("가로방향");
        answer += check_road(mnt);
        // System.out.printf("\n\n");
        // System.out.println("세로방향");
        answer += check_road(mnt2);

        System.out.println(answer);

    }

}
