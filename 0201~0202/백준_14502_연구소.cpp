/*
0�� ��ǥ�� zero ���Ϳ� ��Ƶд�.
�� �� ������ �̿��ؼ� zero ������ �ε����� 3�� �̾��ش�.
�ش� �ε����� �ش��ϴ� ��ǥ���� map ���� 1�� �ٲ��ְ� ���̷����� Ȯ���Ų��(solve �Լ�).


*/

#include <iostream>
#include <queue>
#include <vector>
#include <utility>
#define MAX 9
#define X first
#define Y second
using namespace std;

queue<pair<int, int>> virus;
vector<pair<int, int>> zero;
int map[MAX][MAX], backup[MAX][MAX];
int res = -1, n, m;
int comb[MAX];
int dx[] = { 1,0,-1,0 };
int dy[] = { 0,-1,0,1 };

void count()
{
	int cnt = 0;
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= m; j++)
		{
			if (map[i][j] == 0)
				cnt++;
		}
	}

	if (res < cnt)
		res = cnt;
}

void solve(int idx, int cnt)
{
	if (cnt == 3)
	{
		// 3���� �ε����� �̾��ָ� �ش� ��ǥ�� map�� ���� 1�� �ٲ��ش�.
		for (int j = 0; j < 3; j++)
		{
			int x = zero[comb[j]].X;
			int y = zero[comb[j]].Y;
			map[x][y] = 1;
		}

		// �� �� bfs�� �̿��ؼ� ���̷����� Ȯ���Ų��.
		while (!virus.empty())
		{
			int x = virus.front().X;
			int y = virus.front().Y;
			virus.pop();

			for (int j = 0; j < 4; j++)
			{
				int nx = x + dx[j];
				int ny = y + dy[j];

				if (nx<1 || nx>n || ny<1 || ny>m || map[nx][ny] == 1 || map[nx][ny] == 2)
					continue;

				map[nx][ny] = 2;
				virus.push({ nx,ny });
			}
		}

		// �������� ������ ĭ�� ������ ���� �Լ�
		count();

		// �ٽ� map�� �ʱ� ���·� �������ְ� virus ť�� ������ virus ��ǥ�� �ٽ� �־��ش�.
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
			{
				map[i][j] = backup[i][j];
				if (backup[i][j] == 2)
					virus.push({ i,j });
			}
		return;
	}

	// zero ������ �ε��� 3���� �������� �̾��ش�.
	for (int i = idx; i < zero.size(); i++)
	{
		comb[cnt] = i;
		solve(i + 1, cnt + 1);
	}
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++)
		{
			cin >> map[i][j];
			backup[i][j] = map[i][j];
			if (map[i][j] == 2)
				virus.push({ i,j });  // virus ��ǥ�� queue�� ��Ƶд�.
			if (map[i][j] == 0)
				zero.push_back({ i,j });  // 0�� ��ǥ�� zero ���Ϳ� ��Ƶд�.
		}

	solve(0, 0);
	cout << res << '\n';
	return 0;
}