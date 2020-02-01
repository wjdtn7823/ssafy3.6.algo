#include <iostream>
#include <queue>
#include <deque>
#include <utility>
#define MAX 11
using namespace std;

int map[MAX][MAX];
int A[MAX][MAX];
deque<pair<int, int>> d[11];
deque<pair<int, int>> tmp[11];
deque<pair<int, int>> dead[11];
int dx[] = { -1,-1,-1,0,0,1,1,1 };
int dy[] = { -1,0,1,-1,1,-1,0,1 };

void spring_summer(int n)
{
	int y, age;
	for (int i = 1; i <= n; i++)
	{
		// spring 양분 먹는 과정
		while (!d[i].empty())
		{
			y = d[i].front().first;
			age = d[i].front().second;
			if (map[i][y] >= age)
			{
				map[i][y] -= age;
				age++;
				tmp[i].push_back(make_pair(y, age));
				d[i].pop_front();
			}
			else
			{
				d[i].pop_front();
				dead[i].push_back({ y,age });
			}
		}
		//summer 양분 추가
		while (!dead[i].empty())
		{
			y = dead[i].front().first;
			age = dead[i].front().second;
			map[i][y] += age / 2;
			dead[i].pop_front();
		}
	}
}

void autumn(int n)
{
	int y, age, nx, ny;
	for (int i = 1; i <= n; i++)
	{
		while (!tmp[i].empty())
		{
			y = tmp[i].front().first;
			age = tmp[i].front().second;
			tmp[i].pop_front();
			d[i].push_back({ y, age });
			if (age % 5 == 0)
			{
				for (int j = 0; j < 8; j++)
				{
					nx = i + dx[j];
					ny = y + dy[j];
					if (nx <= 0 || nx > n || ny <= 0 || ny > n)
						continue;
					d[nx].push_front({ ny, 1 });
				}
			}

		}
	}
}


void winter(int n)
{
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			map[i][j] += A[i][j];
}

void answer(int n)
{
	int ans = 0;
	for (int i = 1; i <= n; i++)
		ans += d[i].size();
	cout << ans << '\n';
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m, k, x, y, z, year = 0;

	cin >> n >> m >> k;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
		{
			cin >> A[i][j];
			map[i][j] = 5;
		}

	for (int i = 0; i < m; i++)
	{
		cin >> x >> y >> z;
		d[x].push_back(make_pair(y, z));
	}

	while (year < k)
	{
		spring_summer(n);
		autumn(n);
		winter(n);
		year++;
	}
	answer(n);
	return 0;
}