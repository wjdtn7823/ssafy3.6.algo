#include <iostream>
#include <algorithm>
#include <queue>

#define mp make_pair

using namespace std;

int n, m, answer = 0;
int map[10][10];
const int dx[] = { -1, 1, 0, 0 };
const int dy[] = { 0, 0, -1, 1 };

void dfs(int idx, int idy, int cnt) {
	if (cnt == 3) {
		int tmp = 0, tmp_map[10][10];
		queue<pair<int, int>> q;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 2) q.push(mp(i, j));
				tmp_map[i][j] = map[i][j];
			}
		}
		while (!q.empty()) {
			int cur_x = q.front().first, cur_y = q.front().second;
			q.pop();
			for (int dir = 0; dir < 4; dir++) {
				int new_x = cur_x + dx[dir], new_y = cur_y + dy[dir];
				if (new_x < 0 || new_x >= n || new_y < 0 || new_y >= m) continue;
				if (tmp_map[new_x][new_y] == 1) continue;
				else if (tmp_map[new_x][new_y] == 2) continue;
				tmp_map[new_x][new_y] = 2; q.push(mp(new_x, new_y));
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tmp_map[i][j] == 0) tmp++;
			}
		}

		answer = max(answer, tmp);
		return;
	}

	for (int i = idx; i < n; i++) {
		for (int j = idy; j < m; j++) {
			if (map[i][j] != 0) continue;
			map[i][j] = 1;
			dfs(i, j + 1, cnt + 1);
			map[i][j] = 0;
		}
		idy = 0;
	}
}

int main(void) {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
		}
	}

	dfs(0, 0, 0);
	printf("%d\n", answer);

	return 0;
}