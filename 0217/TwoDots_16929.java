#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;

int N, M;
char board[51][51];
bool visited[51][51][4];
int dy[4] = { -1,0,1,0 };
int dx[4] = { 0,1,0,-1 };
bool suc;
void dfs(int y,int x, char var,int dir) {
	if (suc == true) {
		return;
	}
	if (visited[y][x][(dir + 2) % 4]) 
		return;
	if (visited[y][x][(dir+1)] || visited[y][x][(dir + 3)] || visited[y][x][(dir)]) {
		suc = true;
		return;
	}

	for (int i = 0; i < 4; i++) {
		int ny = dy[i] + y;
		int nx = dx[i] + x;
		if (ny < 0 || nx < 0 || ny >= N || nx >= M ) continue;
		if (board[ny][nx] == var) {
			visited[y][x][i] = true;
			dfs(ny, nx, var,i);
			visited[y][x][i] = false;
		}
	}

}

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			suc = false;
			for (int k = 0; k < 4; k++) {
				if (visited[i][j][k])
					continue;
				dfs(i, j, board[i][j], k);
				if (suc) {
					cout << "Yes";
					return 0;
				}
			}
			
			
		}
	}
	
	cout << "No";
}