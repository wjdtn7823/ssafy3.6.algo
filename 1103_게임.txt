#include <bits/stdc++.h>

using namespace std;

int n, m;
char a[50][50];
int dp[50][50];
int visited[50][50];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, 1, -1};
bool check;

void DFS(int x, int y) {
    visited[x][y] = -1;
    if (a[x][y] == 'H') {
        visited[x][y] = 1;
        return;
    }
    for (int i = 0 ; i < 4; i++){
        int k = a[x][y] - '0';
        int nx = x + dx[i]*k;
        int ny = y + dy[i]*k;
        if (0 <= nx && nx < n && 0 <= ny && ny < m) {
            if (visited[nx][ny] == -1) {
                check = true;
                return;
            }
            else if (!visited[nx][ny]) {
                DFS(nx, ny);
            }
        }
    }
    visited[x][y] = 1;
}

int go(int x, int y) {
    if (x < 0 || y < 0 || x >= n || y >= m) {
        return 0;
    }
    if (a[x][y] == 'H') {
        return 0;
    }
    if (dp[x][y] != -1) {
        return dp[x][y];
    }
    int ret = 1;
    int k = a[x][y] - '0';
    for (int i = 0 ; i < 4; i++) {
        int nx = x + dx[i]*k;
        int ny = y + dy[i]*k;
        ret = max(ret, go(nx, ny) + 1);
    }
    return dp[x][y] = ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    scanf("%d %d", &n, &m);
    for (int i = 0 ; i < n ; i++) {
        for (int j = 0 ; j < m ; j++) {
            scanf(" %c", &a[i][j]);
        }
    }
    memset(dp, -1, sizeof(dp));
    check = false;
    DFS(0, 0);
    if (check) {
        printf("-1");
    }
    else {
        printf("%d", go(0, 0));
    }

  return 0;
}
