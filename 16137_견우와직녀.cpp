#include <iostream>
#include <vector>
#include <utility>
#include <queue>
#include <cstring>


using namespace std;
int n, m;
bool inner(int x, int y) {
	return (0 <= x && x < n && 0 <= y && y < n);
}
int dx[4] = { -1, 0, 0, 1 };
int dy[4] = { 0, -1, 1, 0 };
int board[10][10];
int dist[10][10];
int inf = 987654321;

vector<pair<int, int>> zero;

void dijkstra() {

	priority_queue<pair<int, pair<int, int> > > q;
	dist[0][0] = 0;
	q.push({ 0, {0, 0} });
	while (!q.empty()) {
		int cx = q.top().second.first;
		int cy = q.top().second.second;
		int cd = q.top().first;
		q.pop();
		if (dist[cx][cy] < -cd)
			continue;

		for (int i = 0; i < 4; i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];
			if (inner(nx, ny) && board[nx][ny] != 0) {
				if (board[nx][ny] == 1) {
					if (dist[nx][ny] > dist[cx][cy] + 1) {
						dist[nx][ny] = dist[cx][cy] + 1;
						q.push({ -dist[nx][ny], {nx, ny} });
					}
				}
				else{
					int d = (dist[cx][cy] / board[nx][ny] + 1) * board[nx][ny];
					if (board[cx][cy] == 1 && dist[nx][ny] > d) {
						dist[nx][ny] = d;
						q.push({ -dist[nx][ny], {nx, ny} });
					}
				}
			}

		}
	}

}



int main() {
	

		scanf("%d %d", &n, &m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				scanf("%d", &board[i][j]);
				if (board[i][j] == 0) {
					zero.push_back(make_pair(i, j));
				}
			}
		}
		int answer = 987654321;
		for (auto k : zero) {
			int cx = k.first;
			int cy = k.second;
			board[cx][cy] = m;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dist[i][j] = inf;
				}
			}
			dijkstra();
			answer = min(answer, dist[n - 1][n - 1]);
			board[cx][cy] = 0;
		}
		

		printf("%d\n", answer);
		zero.clear();

	return 0;
}