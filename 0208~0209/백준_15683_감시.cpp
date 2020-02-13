#include <iostream>
using namespace std;

struct cctv
{
    int x;
    int y;
    int type;

    cctv() {}
    cctv(int x, int y, int type) : x(x), y(y), type(type) {}
};

int map[9][9];
int n, m, zero, cctv_cnt, res;

// 0은 위, 1은 오른쪽, 2는 아래, 3은 왼쪽
int dx[] = {0, 1, 0, -1};
int dy[] = {-1, 0, 1, 0};
int c_idx[4][3]={{0,1,2},{0,1,3},{0,2,3},{1,2,3}};  // 4번 cctv의 모든 방향을 담아둔 배열
cctv c[8];

void dfs(int cnt);
void shoot(int dir, int x, int y);  // cctv의 감시를 받을 수 있는 구역을 맵에 표시하는 함수
void restore(int dir, int x, int y);  // cctv의 방향을 바꿀 때 기존 방향의 감시 구역을 원래대로 되돌려주는 함수
void check();

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(nullptr);
    cin >> n >> m;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            cin >> map[i][j];
            if (map[i][j] == 0)
                zero++;
            if (map[i][j] >= 1 && map[i][j] < 6)
                c[cctv_cnt++] = cctv(i, j, map[i][j]);
        }
    }
    res = zero;
    dfs(cctv_cnt);

    cout << res << '\n';
    return 0;
}

void dfs(int cnt)
{
    // cctv의 갯수가 0이 되면 사각 지대를 카운트
    if (cnt == 0)
    {
        check();
        return;
    }

    int x = c[cnt - 1].x;
    int y = c[cnt - 1].y;
    int type = c[cnt - 1].type;

    switch (type)
    {
    case 1:
        for (int i = 0; i < 4; i++)
        {
            shoot(i, x, y);
            dfs(cnt - 1);
            restore(i, x, y);
        }
        break;
    case 2:
        for (int i = 0; i < 2; i++)
        {
            shoot(i, x, y);
            shoot(i + 2, x, y);
            dfs(cnt - 1);
            restore(i, x, y);
            restore(i + 2, x, y);
        }
        break;
    case 3:
        for (int i = 0; i < 4; i++)
        {
            shoot((0 + i + 4) % 4, x, y);
            shoot((1 + i + 4) % 4, x, y);
            dfs(cnt - 1);
            restore((0 + i + 4) % 4, x, y);
            restore((1 + i + 4) % 4, x, y);
        }
        break;
    case 4:
        for(int i=0;i<4;i++)
        {
            shoot(c_idx[i][0],x,y);
            shoot(c_idx[i][1],x,y);
            shoot(c_idx[i][2],x,y);
            dfs(cnt-1);
            restore(c_idx[i][0],x,y);
            restore(c_idx[i][1],x,y);
            restore(c_idx[i][2],x,y);
        }
        break;
    case 5:
        shoot(0, x, y);
        shoot(1, x, y);
        shoot(2, x, y);
        shoot(3, x, y);
        dfs(cnt - 1);
        restore(0, x, y);
        restore(1, x, y);
        restore(2, x, y);
        restore(3, x, y);
        break;
    }
}

void shoot(int dir, int x, int y)
{
    int nx = x, ny = y;

    while (1)
    {
        nx += dx[dir];
        ny += dy[dir];

        if (nx < 1 || ny < 1 || nx > n || ny > m || map[nx][ny] == 6)
            return;
        if (map[nx][ny] >= 1 && map[nx][ny] <= 5)
            continue;

        // 현재 맵이 0이면 감시 받을 수 있는 구역을 7로 변경
        if(map[nx][ny]==0)
            map[nx][ny]=7;
        
        // 만약 0이 아니라면 다른 cctv의 감시 구역을 의미한다. 그냥 7로 설정해두면 restore 함수에서 해당 구역을 0으로 설정해버릴수 있다.
        // 이를 막기 위해 1을 증가시켜준다.
        else
            map[nx][ny]++;
    }
}

void restore(int dir, int x, int y)
{
    int nx = x, ny = y;

    while (1)
    {
        nx += dx[dir];
        ny += dy[dir];

        if (nx < 1 || ny < 1 || nx > n || ny > m || map[nx][ny] == 6)
            return;

        if (map[nx][ny] >= 1 && map[nx][ny] <= 5)
            continue;

        // 7로 설정되어있다면 cctv 1개만이 감시하고 있음을 나타낸다. 따라서 다시 0으로 되돌려줘도 상관없다.
        if (map[nx][ny] == 7)
            map[nx][ny] = 0;
        // 다른 cctv의 감시를 계속 나타내기 위해 7보다 크면 1을 감소시켜준다.
        else
            map[nx][ny]--;
    }
}

void check()
{
    int cnt = 0;
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++)
            if (map[i][j] == 0)
                cnt++;

    if (cnt < res)
        res = cnt;
}