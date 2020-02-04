#include <iostream>
#include <vector>
#include <utility>
#include <queue>
#include <algorithm>
#include <string.h>
using namespace std;

char map[20][20];
int dist[20][20]; // 청소기에서 더러운 칸까지의 거리 + 각 더러운 칸에서 다른 더러운 칸까지의 거리
bool v[20][20];

int dx[] = { 0,1,0,-1 };
int dy[] = { -1,0,1,0 };

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m, min;
	bool res;  // res가 false이면 -1을 출력

	while (1)
	{
		cin >> m >> n;

		if (m == 0 && n == 0)
			break;

		deque<pair<int, int>> dq;  // 청소기와 더러운 칸을 저장해두는 덱
		res = true;

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				cin >> map[i][j];
				if (map[i][j] == 'o')
					dq.push_front({ i,j });  // 청소기가 있는 곳이 항상 0번쨰 인덱스가 되도록 앞에 넣어준다.
				if (map[i][j] == '*')
					dq.push_back({ i,j });
			}
		}

		// dist 배열을 채워주기 위한 과정
		for (int i = 0; i < dq.size() - 1; i++)
		{
			int start_x = dq[i].first;
			int start_y = dq[i].second;
			for (int j = i + 1; j < dq.size(); j++)
			{
				queue<pair<pair<int, int>, int>> qu;
				qu.push({ {start_x,start_y},0 });
				v[start_x][start_y] = 1;
				bool flag = false;

				while (!qu.empty())
				{
					int xx = qu.front().first.first;
					int yy = qu.front().first.second;
					int dd = qu.front().second;
					qu.pop();

					for (int k = 0; k < 4; k++)
					{
						int nx = xx + dx[k];
						int ny = yy + dy[k];

						if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 'x' || v[nx][ny])
							continue;


						if (nx == dq[j].first && ny == dq[j].second)
						{
							flag = true;
							dist[i][j] = dd + 1;
							dist[j][i] = dd + 1;
							break;
						}

						qu.push({ { nx,ny }, dd + 1 });
						v[nx][ny] = 1;
					}
					if (flag)
						break;
				}
				
				// 청소기가 더러운 칸에 도달할 수 없는 경우 999로 설정해두고  res를 false로 설정
				if (!flag)
				{
					dist[i][j] = dist[j][i] = 999;
					res = false;
				}

				for (int i = 0; i < 20; i++)
					memset(v[i], 0, sizeof(bool) * 20);
			}
		}

		if (!res)
			cout << "-1\n";
		else
		{
			min = 2147000000;
			int idx = dq.size();
			int *p = new int[idx - 1];
			for (int k = 1; k < idx; k++)
				p[k - 1] = k;

			// 0으로 시작하는 순열들을 전부 체크해서 최소값을 구해준다.
			do {
				int sum = dist[0][p[0]];

				for (int k = 0; k < idx - 2; k++)
				{
					sum += dist[p[k]][p[k + 1]];
				}
				if (sum < min)
					min = sum;
			} while (next_permutation(p, p + idx - 1));

			delete[] p;
			cout << min << '\n';
		}
	}
	return 0;
}