#include <iostream>
#include <queue>
using namespace std;

struct location
{
    int x, y, size;

    location(int x, int y, int size) : x(x), y(y), size(size) {}
};

char map[101][101];
bool ch[101][101];
int n, m;
queue<location> qu;
bool res = true;

void x_check(int x, int y, int size)
{
    if (x - size < 1 || x + size > n)
        return;

    // 중심에서 위쪽으로 size만큼 아래쪽으로 size만큼 있는지 체크한다.
    for (int i = 1; i <= size; i++)
    {
        if (map[x - i][y] != '*' || map[x + i][y] != '*')
            return;
    }

    // 존재한다면 해당 사이즈의 십자가가 있음을 나타낸다.
    qu.push(location(x, y, size));

    // 해당 십자가의 체크 배열을 1로 설정해준다.
    for (int i = 1; i <= size; i++)
    {
        ch[x - i][y] = 1;
        ch[x + i][y] = 1;
        ch[x][y - i] = 1;
        ch[x][y + i] = 1;
    }
    ch[x][y] = 1;
}

void y_check(int x, int y)
{
    int y_cnt = 1;
    while (1)
    {
        if (y + 1 > m || map[x][y + 1] != '*')
            break;
        y++;
        y_cnt++;
        if (y_cnt % 2 == 1)
        {
            // 예를 들어 y의 갯수가 3이면 해당 십자가의 사이즈는 1이 된다. 중간 좌표에서 x의 갯수가 3이 되는지를 체크해준다.
            int size = (y_cnt - 1) / 2;
            x_check(x, y - size, size);
        }
    }
}

void check()
{
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            // 만약 체크가 되지 않은 *가 존재한다면 십자가만을 이용해서 그래프를 만들 수 없음을 나타낸다.
            if (map[i][j] == '*' && !ch[i][j])
            {
                res = false;
                return;
            }
        }
    }
}
int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            cin >> map[i][j];
        }
    }

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            if (map[i][j] != '*')
                continue;
            // *를 만나면 y먼저 체크해준다.
            y_check(i, j);
        }
    }

    check();
    if (qu.empty() || !res)
        cout << "-1\n";
    else
    {
        cout << qu.size() << '\n';
        while (!qu.empty())
        {
            cout << qu.front().x << " " << qu.front().y << " " << qu.front().size << '\n';
            qu.pop();
        }
    }

    return 0;
}
