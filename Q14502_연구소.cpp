#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

int dx[4] = {-1, 0, 0, 1};
int dy[4] = {0, 1, -1, 0};
int n, m;
vector<vector<int>> original(8, vector<int> (8));
vector<vector<int>> board(8, vector<int> (8));
vector<pair<int, int> > zero;
vector<pair<int, int> > virus;

void BFS() {
    queue<pair<int, int>> q;
    for (int i = 0 ; i < virus.size() ; i++) {
        q.push(virus[i]);
    }
    while(!q.empty()) {
        auto p = q.front();
        int cx = p.first;
        int cy = p.second;
        q.pop();
        for (int i = 0 ; i < 4 ; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (board[nx][ny] == 0) {
                    board[nx][ny] = 2;
                    q.push(make_pair(nx, ny));
                }
            }
        }
    }
}

int main() {
    scanf("%d %d", &n, &m);
    for (int i = 0 ; i < n ; i++) {
        for (int j = 0 ; j < m ; j++) {
            scanf("%d", &original[i][j]);
            if (original[i][j] == 0) {
                zero.push_back(make_pair(i, j));
            }
            if (original[i][j] == 2) {
                virus.push_back(make_pair(i, j));
            }
        }
    }
    int answer = 0;
    int count;
    int p = zero.size();
    for (int i = 0 ; i < p ; i++) {
        for (int j = i+1 ; j < p ; j++) {
            for (int k = j+1 ; k < p ; k++) {
                count = 0;
                board = original;
                board[zero[i].first][zero[i].second] = 1;
                board[zero[j].first][zero[j].second] = 1;
                board[zero[k].first][zero[k].second] = 1;
                BFS();
                for (int x = 0 ; x < n ; x++) {
                    for (int y = 0 ; y < m ; y++) {
                        if (board[x][y] == 0) {
                            count++;
                        }
                    }
                }
                answer = max(count, answer);
            }
        }
    }
    printf("%d\n", answer);

    return 0;
}