package πÈ¡ÿ;

import java.util.*;
import java.io.*;

class PuyoPoint {
    int x, y;

    public PuyoPoint(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

}

public class ª—ø‰ª—ø‰ {
    final public static int n = 12;
    final public static int m = 6;
    public static int[][] map = new int[n][m];

    public static void shiftMapDown() {

        for (int i = 0; i < m; i++) {

            Queue<Integer> q = new LinkedList<Integer>();
            for (int j = n - 1; j >= 0; j--) {
                if (map[j][i] != 0)
                    q.add(map[j][i]);

            }
            // System.out.printf("q.size=%d\n",q.size());
            int p = q.size();
            for (int j = 0; j < n - p; j++) {
                q.add(0);
            }
            // System.out.printf("q.size=%d\n",q.size());
            for (int j = n - 1; j >= 0; j--) {

                // System.out.printf("j =%d t=%d\n",j,t);
                map[j][i] = q.poll();
            }

        }

    }

    public static void print_map() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean ispuyo(int x, int y) {
        int block = map[x][y];
        boolean[][] visited = new boolean[n][m];
        final int[] dx = { -1, 1, 0, 0 };
        final int[] dy = { 0, 0, -1, 1 };
        Queue<PuyoPoint> q = new LinkedList<PuyoPoint>();
        PuyoPoint start = new PuyoPoint(x, y);
        q.add(start);
        ArrayList<PuyoPoint> dead = new ArrayList<PuyoPoint>();
        visited[x][y] = true;
        dead.add(start);
        while (!q.isEmpty()) {
            PuyoPoint p = q.poll();
            // System.out.printf("p.x =%d p.y=%d\n",p.x,p.y);
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (map[nx][ny] != block)
                    continue;
                PuyoPoint np = new PuyoPoint(nx, ny);
                visited[nx][ny] = true;
                q.add(np);
                dead.add(np);
            }
        }

        if (dead.size() >= 4) {
            for (int i = 0; i < dead.size(); i++) {
                map[dead.get(i).x][dead.get(i).y] = 0;
            }

            return true;

        } else
            return false;
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.setIn(new FileInputStream("res/input_puyo.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < n; i++) {
            String s = bf.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);
                if (c == '.')
                    map[i][j] = 0;
                else if (c == 'R')
                    map[i][j] = 1;
                else if (c == 'G')
                    map[i][j] = 2;
                else if (c == 'B')
                    map[i][j] = 3;
                else if (c == 'P')
                    map[i][j] = 4;
                else if (c == 'Y')
                    map[i][j] = 5;
            }
        }
        /*
         * print_map(); shiftMapDown(); System.out.println(); print_map();
         */

        int combo = 0;
        while (true) {
            // print_map();
            // System.out.println("??");
            boolean gameover = true;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0)
                        continue;
                    if (ispuyo(i, j))
                        gameover = false;
                }
            }

            if (gameover)
                break;
            // print_map();
            shiftMapDown();
            combo++;
        }

        System.out.println(combo);
    }

}
