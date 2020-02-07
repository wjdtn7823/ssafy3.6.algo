#include <bits/stdc++.h>

using namespace std;

char board[12][6];
int dx[4] = { -1, 0, 0, 1 };
int dy[4] = { 0, -1, 1, 0 };
bool visited[12][6];
vector<pair<int, int>> v;

bool inner(int x, int y) {
	return (0 <= x && x < 12 && 0 <= y && y < 6);
}

void DFS(int x, int y) {
	visited[x][y] = true;
	v.push_back(make_pair(x, y));
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (inner(nx, ny) && !visited[nx][ny] && board[nx][ny] == board[x][y]) {
			DFS(nx, ny);
		}
	}
}

void _swap(int a, int b, int c, int d) {
	char k = board[a][b];
	board[a][b] = board[c][d];
	board[c][d] = k;
}

int main() {
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 6; j++) {
			scanf(" %c", &board[i][j]);
		}
	}
	bool found = false;
	int answer = 0;
	while (true) {
		found = false;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (board[i][j] != '.' && !visited[i][j]) {
					DFS(i, j);
					
					if (v.size() >= 4) {
						found = true;
						for (auto k : v) {
							board[k.first][k.second] = '.';
						}
					}
					v.clear();
				}
			}
		}
		memset(visited, false, sizeof(visited));

		if (!found)
			break;

		answer += 1;
		for (int i = 11; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (board[i][j] == '.')
					continue;
				int k;
				for (k = i; ; k++) {
					if (k + 1 >= 12 || board[k + 1][j] != '.')
						break;
				}
				_swap(i, j, k, j);

			}
		}

	}
	printf("%d\n", answer);

	return 0;
}