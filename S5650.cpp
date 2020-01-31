#include <iostream>
#include <vector>
#include <utility>

using namespace std;

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };
int board[105][105];
int answer = 0;
int n;
vector<vector<pair<int, int>>> v;

pair<int, int> other(int number, int x, int y) {
	if (v[number][0].first == x && v[number][0].second == y)
		return v[number][1];
	else return v[number][0];
}


void go(int x, int y, int first_direction) {
	int startx = x;
	int starty = y;
	int dir = first_direction;
	int temp_answer = 0;
	while (true) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if (board[nx][ny] == 1) {
			temp_answer += 1;
			if (dir == 0) {
				dir = 2;
			}
			else if (dir == 1) {
				dir = 0;
			}
			else if (dir == 2) {
				dir = 3;
			}
			else if (dir == 3) {
				dir = 1;
			}
		}
		else if (board[nx][ny] == 2) {
			temp_answer += 1;
			if (dir == 0) {
				dir = 2;

			}
			else if (dir == 1) {
				dir = 3;

			}
			else if (dir == 2) {
				dir = 1;
			}
			else if (dir == 3) {
				dir = 0;
			}
		}
		else if (board[nx][ny] == 3) {
			temp_answer += 1;
			if (dir == 0) {
				dir = 1;
			}
			else if (dir == 1) {
				dir = 3;
			}
			else if (dir == 2) {
				dir = 0;
			}
			else if (dir == 3) {
				dir = 2;
			}
		}
		else if (board[nx][ny] == 4) {
			temp_answer += 1;
			if (dir == 0) {
				dir = 3;

			}
			else if (dir == 1) {
				dir = 2;
			}
			else if (dir == 2) {
				dir = 0;

			}
			else if (dir == 3) {
				dir = 1;

			}
		}
		else if (6 <= board[nx][ny] && board[nx][ny] <= 10) {
			// no additional points
			int kx = other(board[nx][ny], nx, ny).first;
			int ky = other(board[nx][ny], nx, ny).second;
			nx = kx;
			ny = ky;
		}
		else if (board[nx][ny] == -1) {
			break;
		}
		else if (board[nx][ny] == 20 || board[nx][ny] == 5) {
			temp_answer += 1;
			if (dir == 0) {
				dir = 2;
			}
			else if (dir == 1) {
				dir = 3;
			}
			else if (dir == 2) {
				dir = 0;
			}
			else if (dir == 3) {
				dir = 1;
			}
		}
		x = nx;
		y = ny;
		if (x == startx && y == starty)
			break;
	}
	if (temp_answer > answer)
		answer = temp_answer;
}

int main() {
	int testcase;
	scanf("%d", &testcase);
	for (int tc = 1; tc <= testcase; tc++) {
		scanf("%d", &n);
		v.resize(11);
		answer = 0;
		for (int i = 0; i < 105; i++) {
			for (int j = 0; j < 105; j++) {
				board[i][j] = 20;
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				scanf("%d", &board[i][j]);
				if (6 <= board[i][j] && board[i][j] <= 10) {
					v[board[i][j]].push_back(make_pair(i, j));
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (board[i][j] == 0) {
					for (int dir = 0; dir < 4; dir++) {
						go(i, j, dir);
					}
				}
			}
		}

		for (int i = 0; i <= 10; i++)
			v.clear();
		printf("#%d %d\n", tc, answer);
	}

	return 0;
}