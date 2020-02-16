#include <iostream>
#include <queue>
#include <memory.h>
using namespace std;

int N, M;
char board[12][6];
bool visited[12][6];
int dy[4] = { -1,0,1,0 };
int dx[4] = { 0,1,0,-1 };

struct Node {
	int y;
	int x;
	char block;
};

int dfs(int y, int x, char block) {
	visited[y][x] = true;
	int ret = 0;
	for (int i = 0; i < 4; i++) {
		int ny = dy[i] + y;
		int nx = dx[i] + x;
		if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
		if (visited[ny][nx] || board[ny][nx] != block) continue;
		ret += dfs(ny, nx, block);
	}
	return 1 + ret;
}

int main() {
	N = 12;
	M = 6;
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 6; j++) {
			cin >> board[i][j];
		}
	}
	int ans = 0;
	while (true) {
		bool flag = false;
		//뿌요를 터트린 후 내려갈 놈들을 내림
		for (int i = 0; i < 6; i++) {
			int empty_space = -1;
			for (int j = 11; j >= 0; j--) {
				if (board[j][i] == '.') {
					empty_space = j;
					break;
				}
			}
			if (empty_space != -1) {
				for (int j = empty_space - 1; j >= 0; j--) {
					if (board[j][i] != '.') {
						board[empty_space][i] = board[j][i];
						board[j][i] = '.';
						empty_space--;
					}
				}
			}
		}


		//for (int j = 0; j < 6; j++) {
		//	for (int i = 11; i >= 0; i--) {
		//		if (board[i][j] != '.') {				// i = 9;
		//			for (int k = 11; k > i; k--) { // k = 11;
		//				if (board[k][j] == '.') {
		//					swap(board[i][j], board[k][j]); // 
		//					break; // k for문을 벗어나도록 합니다. 새로운 아이를 찾도록 합니다. 2 그럼 다시 포문에 들어옵니다. 
		//				}
		//			}
		//		}
		//	}
		//}


		////뿌요를 내린 후 
		//for (int i = 0; i < N; i++) {
		//	for (int j = 0; j < M; j++) {
		//		cout << board[i][j];
		//	}
		//	cout << endl;
		//}
		//cout << endl;

		//뭉친애들을 저장 해두었다가 한 번에 터트림
		queue<Node> q;
		memset(visited, false, sizeof(visited));
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (board[i][j] == '.')continue;
				int k = dfs(i, j, board[i][j]);
				if (k >= 4) {
					flag = true;
					q.push({ i,j,board[i][j] });
				}
			}
		}
		memset(visited, false, sizeof(visited));
		while (!q.empty()) {
			Node cur = q.front(); q.pop();
			board[cur.y][cur.x] = '.';
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if (visited[ny][nx] || board[ny][nx] != cur.block) continue;
				visited[ny][nx] = true;
				q.push({ ny,nx,cur.block });
			}
		}

		if (flag == false)
		{
			break;
		}
		ans++;
		////맵 확인2
		//for (int i = 0; i < N; i++) {
		//	for (int j = 0; j < M; j++) {
		//		cout << board[i][j];
		//	}
		//	cout << endl;
		//}
		//cout << endl;
	}
	cout << ans;
}



