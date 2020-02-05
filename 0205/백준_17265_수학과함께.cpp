#include <iostream>
#include <queue>
#include <utility>
using namespace std;

char map[5][5];

// 두 방향으로만 진행
int dx[] = { 0, 1 };
int dy[] = { 1, 0 };

struct s
{
	int x;
	int y;
	int num;  // 현재까지의 진행 결과값을 저장
	int op;  // 0은 +, 1은 -, 2는 *

	s(int x, int y, int num, int op)
	{
		this->x = x;
		this->y = y;
		this->num = num;
		this->op = op;
	}
};

int main()
{
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	//freopen("input.txt", "r", stdin);
	int n;
	int max = -2147000000;
	int min = 2147000000;
	cin >> n;

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			cin >> map[i][j];

	queue<s> qu;
	int tmp = map[0][0] - 48;
	qu.push(s(0, 0, tmp, 0));  // 처음 숫자를 저장해둔다.

	while (!qu.empty())
	{
		int x = qu.front().x;
		int y = qu.front().y;
		int num = qu.front().num;
		int op = qu.front().op;
		qu.pop();

		if (x == n - 1 && y == n - 1)
		{
			if (num > max)
				max = num;

			if (min > num)
				min = num;

			continue;
		}

		for (int i = 0; i < 2; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= n || ny >= n)
				continue;

			// 맵이 숫자인 경우
			if (((nx + ny) % 2) == 0)
			{
				int tmp = map[nx][ny] - 48;
				switch (op)  // 0이면 덧셈, 1이면 뺼셈, 2면 곱셈
				{
				case 0:
					tmp = num + tmp;
					break;
				case 1:
					tmp = num - tmp;
					break;
				case 2:
					tmp = num * tmp;
					break;
				}
				qu.push(s(nx, ny, tmp, op));
			}
			else
			{
				char c = map[nx][ny];
				int tmp;
				switch (c)
				{
				case '+':
					tmp = 0;
					break;
				case '-':
					tmp = 1;
					break;
				case '*':
					tmp = 2;
					break;
				}
				qu.push(s(nx, ny, num, tmp));
			}
		}
	}

	cout << max << " " << min << '\n';
	return 0;
}