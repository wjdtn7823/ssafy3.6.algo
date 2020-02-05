#include <iostream>
#include <memory.h>
#include <string>
#include <algorithm>
using namespace std;

char map[51][51];
int dp[51][51], n, m;
bool v[51][51];
int dx[] = { 0,1,0,-1 };
int dy[] = { -1,0,1,0 };
bool infinite;

int dfs(int x, int y)
{
	if (infinite) return 0;
	if (v[x][y])
	{
		infinite = true;
		return 0;
	}
	int &ret = dp[x][y];
	if (ret != -1)
		return ret;
	ret = 0;

	int tmp = map[x][y] - 48;

	for (int i = 0; i < 4; i++)
	{
		int nx = x + dx[i] * tmp;
		int ny = y + dy[i] * tmp;
		if (nx<1 || nx>n || ny<1 || ny>m) continue;
		if (map[nx][ny] == 'H')
			continue;
		v[x][y] = 1;
		ret = max(ret, dfs(nx, ny) + 1);
		v[x][y] = 0;
	}

	return ret;
}

int main()
{
	string s;
	cin >> n >> m;
	for (int i = 1; i <= n; i++)
	{
		cin >> s;
		for (int j = 1; j <= m; j++)
		{
			map[i][j] = s[j - 1];
		}
	}

	memset(dp, -1, sizeof(dp));
	int tmp = dfs(1, 1);
	if (infinite)
		cout << "-1\n";
	else
		cout << tmp + 1 << '\n';
	return 0;
}