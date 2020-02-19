#include <iostream>
#include <algorithm>
#include <memory.h>
using namespace std;

int n, ans;
int batting[10] = {0, 2, 3, 4, 5, 6, 7, 8, 9, 1}; // 배팅 순서 담아두는 배열
int s[51][10];                                    // 각 타자들의 기록을 담아두는 배열
int basement[5];                                  // 루 상황을 나타내는 배열

// hitter는 이번 이닝의 시작 순번을 의미함.
void dfs(int innings, int score, int hitter)
{
    memset(basement, 0, sizeof(basement));
    if (innings == n + 1)
    {
        ans = max(score, ans);
        return;
    }

    int out_count = 0;
    while (out_count != 3)
    {
        basement[0] = 1;
        if (s[innings][batting[hitter]] == 0)
        {
            out_count++;
            hitter++;
            if (hitter > 9)
                hitter = 1;
            continue;
        }

        int hit = s[innings][batting[hitter]];
        for (int i = 3; i >= 0; i--)
        {
            int next = i + hit;
            if (next >= 4)
            {
                basement[4] += basement[i];
                basement[i] = 0;
                continue;
            }
            basement[next] = basement[i];
            basement[i] = 0;
        }
        hitter++;
        if (hitter > 9)
            hitter = 1;
    }

    dfs(innings + 1, score + basement[4], hitter);
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(nullptr);

    cin >> n;
    batting[9] = 1;

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= 9; j++)
            cin >> s[i][j];

    do
    {
        batting[9] = batting[4];
        batting[4] = 1;
        dfs(1, 0, 1);
        batting[4] = batting[9];
        batting[9] = 1;
    } while (next_permutation(batting + 1, batting + 9));

    cout << ans << '\n';
    return 0;
}