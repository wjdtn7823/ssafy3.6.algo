#include <bits/stdc++.h>

using namespace std;

int inf = 987654321;
int n;
char board[5][5];
int dx[2] = {1, 0};
int dy[2] = {0, 1};
vector<int> v;



int main () {
    scanf("%d", &n);
    for (int i = 0 ; i < n ; i++) {
        for (int j = 0 ; j < n ; j++) {
            scanf(" %c", &board[i][j]);
        }
    }
    for (int i = 0 ; i < n -1; i++) {
        v.push_back(0);
    }
    for (int i = 0 ; i < n -1; i++) {
        v.push_back(1);
    }
    int maxanswer = -inf;
    int minanswer = inf;

    do {
        int x = 0;
        int y = 0;
        int value = board[0][0] - '0';
        
        for (int i = 0 ; i < 2*n  - 2; i += 2) {
            int dir = v[i];
            int next_dir = v[i+1];
            int cx = x + dx[dir];
            int cy = y + dy[dir];
            int nx = cx + dx[next_dir];
            int ny = cy + dy[next_dir];
            if (board[cx][cy] == '*') {
                value = value * (board[nx][ny] - '0');
                
            } 
            else if (board[cx][cy] == '+') {
                value = value + (board[nx][ny] - '0');
            }
            else if (board[cx][cy] == '-') {
                value = value - (board[nx][ny] - '0');
            }
            x = nx;
            y = ny;
            
        }
        maxanswer = max(maxanswer ,value);
        minanswer = min(minanswer, value);
    }
    while(next_permutation(v.begin(), v.end()));
    printf("%d %d\n", maxanswer, minanswer);
    return 0;
}