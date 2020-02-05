#include <iostream>
#include <stack>
#include <string>
using namespace std;

char map[50][50];
bool check[50][50][4];
int v[50][50][4];

int dx[] = { -1,0,1,0 };
int dy[] = { 0,1,0,-1 };

struct game {
	int x;
	int y;
	int dist;
	int direction;  // 0은 위, 1은 오른쪽, 2는 아래, 3은 왼쪽

	game(int x, int y, int dist, int direction) :x(x), y(y), dist(dist), direction(direction) {};
};

int main()
{
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	int n, m, ans = 0;
	cin >> n >> m;
	string s;

	for (int i = 0; i < n; i++)
	{
		cin >> s;
		for (int j = 0; j < m; j++)
		{
			map[i][j] = s[j];
			for (int k = 0; k < 4; k++)
			{
				if (map[i][j] == 'H')
					continue;
				int tmp = map[i][j] - 48;
				int nx = i + dx[k] * tmp;
				int ny = j + dy[k] * tmp;
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				check[i][j][k] = 1;
			}
		}
	}

	stack<game> st;
	st.push(game(0, 0, 0, 0));
	v[0][0][0] = 1;
	v[0][0][1] = 1;
	v[0][0][2] = 1;
	v[0][0][3] = 1;
	
	bool infinite = false;
	
	while (!st.empty())
	{
		int x = st.top().x;
		int y = st.top().y;
		int d = st.top().dist;
		int direction = st.top().direction;
		int tmp = map[x][y] - 48;
		st.pop();

		printf("nx:%d, ny:%d, direction: %d, v[x][y][direction]: %d\n", x, y, direction,v[x][y][direction]);
		if (v[x][y][direction] >= 2)
		{
			infinite = true;
			break;
		}

		for (int i = 0; i < 4; i++)
		{
			int nx = x + dx[i] * tmp;
			int ny = y + dy[i] * tmp;
			printf("nx:%d, ny:%d\n", nx, ny);
			if (check[x][y][i])
			{
				st.push(game(nx, ny, d + 1,i));
				v[nx][ny][direction]++;
			}
			else
			{
				if (map[x][y] == 'H')
				{
					if (d > ans)
						ans = d;
				}	
				else
					if (d + 1 > ans)
						ans = d + 1;
			}
		}
	}

	if (infinite)
		cout << "-1\n";
	else
		cout << ans << '\n';
	return 0;
}