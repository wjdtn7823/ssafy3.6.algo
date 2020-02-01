/*
0의 좌표를 zero 벡터에 담아둔다.
그 후 조합을 이용해서 zero 벡터의 인덱스를 3개 뽑아준다.
해당 인덱스에 해당하는 좌표들의 map 값을 1로 바꿔주고 바이러스를 확산시킨다(solve 함수).


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
		// 3개의 인덱스를 뽑아주면 해당 좌표의 map의 값을 1로 바꿔준다.
		for (int j = 0; j < 3; j++)
		{
			int x = zero[comb[j]].X;
			int y = zero[comb[j]].Y;
			map[x][y] = 1;
		}

		// 그 후 bfs를 이용해서 바이러스를 확산시킨다.
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

		// 연구소의 안전한 칸의 개수를 세는 함수
		count();

		// 다시 map을 초기 상태로 변경해주고 virus 큐에 기존의 virus 좌표를 다시 넣어준다.
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
			{
				map[i][j] = backup[i][j];
				if (backup[i][j] == 2)
					virus.push({ i,j });
			}
		return;
	}

	// zero 벡터의 인덱스 3개를 조합으로 뽑아준다.
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
				virus.push({ i,j });  // virus 좌표를 queue에 담아둔다.
			if (map[i][j] == 0)
				zero.push_back({ i,j });  // 0의 좌표를 zero 벡터에 담아둔다.
		}

	solve(0, 0);
	cout << res << '\n';
	return 0;
}