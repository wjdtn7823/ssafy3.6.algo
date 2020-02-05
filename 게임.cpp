#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;
#define MAX 51
const int dx[] = {0,0,1,-1};
const int dy[] = {1,-1,0,0};
int N,M;
string board[MAX];
bool visited[MAX][MAX];
int dp[MAX][MAX];
bool infinite;

bool outOfBound(int x, int y){
    return x<0||x>=N||y<0||y>=M;
}

int dfs(int x, int y){
    if(visited[x][y]) {
        infinite = true;
        return 0;
    }
    int &ret = dp[x][y];
    if(ret!=-1) return ret; //저장된 값이 있을 경우
    ret = 0;
    for (int i = 0; i < 4; ++i) {
        int value = board[x][y] - '0';
        int nx = x + value * dx[i];
        int ny = y + value * dy[i];
        if (outOfBound(nx, ny) || board[nx][ny] == 'H') continue;
        visited[x][y] = true;
        ret = max(ret, dfs(nx, ny) + 1);
        visited[x][y] = false;
    }
    return ret;
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin>>N>>M;
    for (int i = 0; i < N; ++i) {
        cin>>board[i];
    }
    memset(dp, -1, sizeof(dp));
    int tmp = dfs(0,0);
    if(infinite){
        cout<<-1;
    }else{
        cout<<tmp+1;
    }
    return 0;
}
