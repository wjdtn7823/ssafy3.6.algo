#include <iostream>
using namespace std;

struct badook
{
    int x;
    int y;
    bool live;

    badook(){}
    badook(int x, int y, bool live) : x(x), y(y), live(live) {}
};

int n, map[10][10], dook_cnt;
badook b[10];
bool flag;

int dx[] = {0, 0, 1, -1, -1, 1, 1, -1}; // 0, 1은 가로 2, 3은 세로 4,5,6,7은 대각선
int dy[] = {1, -1, 0, 0, 1, 1, -1, -1};

void dfs(int cnt)
{
    if (cnt == 1)
    {
        flag = true;
        return;
    }

    for (int i = 2; i < dook_cnt + 2; i++)
    {
        if (!b[i].live)
            continue;
        int x = b[i].x;
        int y = b[i].y;

        for (int j = 0; j < 8; j++)
        {
            int nx = x + dx[j];
            int ny = y + dy[j];

            if (nx <= 0 || ny <= 0 || nx >= n - 1 || ny >= n - 1 || map[nx][ny] < 2)
                continue;

            int nnx = nx + dx[j];
            int nny = ny + dy[j];

            if (nnx <= 0 || nny <= 0 || nnx >= n - 1 || nny >= n - 1 || map[nnx][nny] != 0)
                continue;

            int tmp = map[nx][ny];
            b[map[nx][ny]].live = false;
            b[map[x][y]].x = nnx;
            b[map[x][y]].y = nny;
            map[nnx][nny] = map[x][y];
            map[x][y] = 0;
            map[nx][ny] = 0;

            dfs(cnt - 1);

            map[x][y] = map[nnx][nny];
            map[nx][ny] = tmp;
            map[nnx][nny] = 0;
            b[map[x][y]].x = x;
            b[map[x][y]].y = y;
            b[map[nx][ny]].live = true;
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> map[i][j];
            if (map[i][j] == 2)
            {
                map[i][j] += dook_cnt;
                b[map[i][j]] = badook(i, j, true);
                dook_cnt++;
            }
        }
    }

    dfs(dook_cnt);

    if (flag)
        cout << "Possible\n";
    else
        cout << "Impossible\n";

    return 0;
}