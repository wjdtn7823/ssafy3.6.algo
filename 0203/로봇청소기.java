package 백준;

import java.util.*;
import java.io.*;

class Robot {

    int x, y;
    int mask;
    int distance;

    Robot() {

    }

    Robot(int x, int y, int mask, int distance) {
        this.x = x;
        this.y = y;
        this.mask = mask;
        this.distance = distance;
    }

}

public class 로봇청소기 {
    public static int n, m;
    public static int[][] room;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    public static void print_room() {

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.setIn(new FileInputStream("res/input_청소기.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        outer: while (true) {

            StringTokenizer st = new StringTokenizer(bf.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0)
                break; // 프로그램 종료

            room = new int[m][n]; // 방 정보 저장 int로 바꿔서 저장
            int dustCnt = 0; // 먼지 갯수

            Robot start = new Robot();

            char c;
            for (int i = 0; i < m; i++) {
                String s = bf.readLine();
                for (int j = 0; j < n; j++) {
                    c = s.charAt(j);

                    if (c == 'o') { // 시작점
                        start = new Robot(i, j, 0, 0);
                        room[i][j] = -2;
                    } else if (c == '*') { // 먼지
                        room[i][j] = ++dustCnt;
                    } else if (c == 'x') { // 벽
                        room[i][j] = -1;
                    } else
                        room[i][j] = 0; // 빈공간
                }
            }

            Queue<Robot> rq = new LinkedList<Robot>();

            rq.offer(start);
            boolean[][][] visited = new boolean[1024][m][n];
            visited[start.mask][start.x][start.y] = true;

            int complete = (1 << dustCnt) - 1; // 다 청소했을때의 비트 마스크

            while (!rq.isEmpty()) { // BFS
                Robot r = rq.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = r.x + dx[i];
                    int ny = r.y + dy[i];
                    int mask = r.mask;
                    int ndist = r.distance + 1;
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                        continue; // 방에서 벗어남
                    if (room[nx][ny] == -1)
                        continue; // 벽
                    if (visited[mask][nx][ny])
                        continue; // 같은 비트마스크일때 중복 방문하면 안됨
                    visited[mask][nx][ny] = true; // 방문처리
                    if (room[nx][ny] >= 1 && room[nx][ny] <= 10) {// 먼지 만낫을경우 비트마스트 처리

                        mask |= (1 << room[nx][ny] - 1);
                        // System.out.printf("먼지 at r.x=%d r.y=%d mask=%d\n",nx,ny,mask);
                    }
                    if (mask == complete) { // 다 청소할경우
                        System.out.println(ndist);
                        continue outer;
                    }
                    rq.offer(new Robot(nx, ny, mask, ndist));

                }
            }
            
            // 다 청소 실패
            System.out.println("-1");

        }

    }

}
