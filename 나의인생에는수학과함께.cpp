#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;
int dx[] = { 0,1 };
int dy[] = { 1,0 };
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	int N;
	char map[5][5];
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			cin >> map[i][j];
		}
	}

	int max = INT_MIN;
	int min = INT_MAX;
	vector<int> dir;
	for (int i = 0; i < N-1; i++)
	{
		dir.push_back(0);
		dir.push_back(1);
	}
	sort(dir.begin(), dir.end());
	do {
		int result = map[0][0] - '0';
		int x = 0;
		int y = 0;
		char op = '+';
		for (int i = 0; i < 2*N-2; i++) {
			x += dx[dir[i]];
			y += dy[dir[i]];
			if (i % 2 == 0) {
				op = map[x][y];
			}
			else {
				int num = map[x][y] - '0';
				switch (op) {
				case '+':
					result += num;
					break;
				case '-':
					result -= num;
					break;
				case '*':
					result *= num;
					break;
				default:
					break;
				}
			}
		}
		max = max < result ? result : max;
		min = min > result ? result : min;
	} while (next_permutation(dir.begin(), dir.end()));
	cout << max << " " << min;
	return 0;
}
