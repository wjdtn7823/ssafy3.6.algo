#include <iostream>
#include <algorithm>
using namespace std;

struct chicken
{
	int x;
	int y;

	chicken() {}
	chicken(int x, int y) : x(x), y(y) {}
};

struct home
{
	int x;
	int y;

	home() {}
	home(int x, int y) : x(x), y(y) {}
};

int n, m, home_cnt, ch_cnt;
int map[51][51];
chicken c[13];
home h[100];
int idx[13];
int res = 2147000000;

void solve(int cnt)
{
	int dis = 0;
	for (int i = 0; i < home_cnt; i++)
	{
		int m = 2147000000;
		for (int j = 0; j < cnt; j++)
		{
			int x = c[idx[j]].x;
			int y = c[idx[j]].y;
			int tmp = abs(x - h[i].x) + abs(y - h[i].y);
			if (tmp < m)
				m = tmp;
		}
		dis += m;
	}

	if (res > dis)
		res = dis;
}

void comb(int index, int cnt, int limit)
{
	if (cnt == limit)
	{
		solve(limit);
		return;
	}
	for (int i = index; i < ch_cnt; i++)
	{
		idx[cnt] = i;
		comb(i + 1, cnt + 1, limit);
	}
}

int main()
{
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			cin >> map[i][j];
			if (map[i][j] == 1)
				h[home_cnt++] = home(i, j);
			else if (map[i][j] == 2)
				c[ch_cnt++] = chicken(i, j);
		}
	}

    for(int i=1;i<=m;i++)
	    comb(0, 0, i);
	cout << res << '\n';
	return 0;
}