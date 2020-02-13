#include <iostream>
using namespace std;

int d[25], c[25], p[25], s[25];
int n, m;
int res, res_bit;

void comb(int idx, int bitmask, int price, int sat)
{
    if (res < sat)
    {
        res = sat;
        res_bit = bitmask;
    }
    for (int i = idx; i < m; i++)
    {
        int tmp = price + p[i];
        if (tmp > n)
            continue;
        comb(i + 1, bitmask | (1 << i), price + p[i], sat + s[i]);
    }
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    freopen("input.txt", "r", stdin);

    int t;
    cin >> t;
    for (int tc = 1; tc <= t; tc++)
    {
        res = 0;
        res_bit=0;
        cin >> n >> m;
        for (int i = 0; i < m; i++)
            cin >> p[i] >> s[i];

        comb(0, 0, 0, 0);

        cout << "#" << tc << " ";
        for (int j = 0; j < m; j++)
        {
            if (res_bit & (1 << j))
                cout << j << " ";
        }
        cout << res << '\n';
    }
    return 0;
}