#include <iostream>
#include <algorithm>
using namespace std;

int map[101][101], n, l, res = 0, tmp[101][101];
bool v[101][101];

void solve()
{
    for (int i = 1; i <= n; i++)
    {
        bool flag = true;
        for (int j = 1; j < n; j++)
        {
            if (map[i][j] == map[i][j + 1])
                continue;
            if (abs(map[i][j] - map[i][j + 1]) >= 2)
            {
                flag = false;
                break;
            }

            if (map[i][j] - map[i][j + 1] == 1)
            {
                if (j + l > n)
                {
                    flag = false;
                    break;
                }
                bool r = true;
                if (l != 1)
                {
                    for (int k = j + 1; k < j + l; k++)
                    {
                        if (map[i][k] != map[i][k + 1] || v[i][k] || v[i][k + 1])
                        {
                            flag = false;
                            r = false;
                        }
                    }
                }
                else
                {
                    if (v[i][j + 1])
                    {
                        flag = false;
                        r = false;
                    }
                }

                if (r)
                {
                    for (int k = j + 1; k <= j + l; k++)
                    {
                        v[i][k] = true;
                    }
                }
            }
            else if (map[i][j] - map[i][j + 1] == -1)
            {
                if (j - l + 1 < 1)
                {
                    flag = false;
                    break;
                }
                bool r = true;
                if (l != 1)
                {
                    for (int k = j; k > j - l + 1; k--)
                    {
                        if (map[i][k] != map[i][k - 1] || v[i][k] || v[i][k - 1])
                        {
                            flag = false;
                            r = false;
                        }
                    }
                }
                else
                {
                    if (v[i][j])
                    {
                        flag = false;
                        r = false;
                    }
                }

                if (r)
                {
                    for (int k = j; k >= j - l + 1; k--)
                    {
                        v[i][k] = true;
                    }
                }
            }
        }
        if (flag)
        {
            // cout<<i<<'\n';
            res++;
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    freopen("input.txt", "r", stdin);
    cin >> n >> l;

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
        {
            cin >> map[i][j];
            tmp[i][j] = map[i][j];
        }

    solve();
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
        {
            map[i][j] = tmp[j][i];
            v[i][j] = 0;
        }

    solve();

    cout << res << '\n';
    return 0;
}