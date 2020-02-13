#include <iostream>
#include <queue>
#include <utility>
#include <string.h>
using namespace std;

int n, m, cnt, res;
int map[101][101];
int v[101][101];
queue<pair<int, int>> qu;

int dx[] = {0, 1, 0, -1};
int dy[] = {-1, 0, 1, 0};

void bfs(int x, int y)
{
    qu.push({x, y});
    v[x][y] = 1;

    while (!qu.empty())
    {
        int x = qu.front().first;
        int y = qu.front().second;
        qu.pop();

        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > m || (map[nx][ny] == 0 && v[nx][ny] == 1))
                continue;

            if (map[nx][ny] == 1)
            {
                v[nx][ny]++;
                continue;
            }

            qu.push({nx, ny});
            v[nx][ny] = 1;
        }
    }
}

void check()
{
    for (int i = 2; i < n; i++)
        for (int j = 2; j < m; j++)
            if (v[i][j] >= 2)
            {
                cnt--;
                map[i][j] = 0;
            }
}

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
            if (map[i][j] == 1)
                cnt++;
        }
    }

    while (1)
    {
        res++;
        memset(v, 0, sizeof(v));
        bfs(1, 1);
        check();
        if (cnt == 0)
            break;
    }

    cout << res << '\n';
    return 0;
}