#include <bits/stdc++.h>
#include <map>

using namespace std;

int dx[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
int dy[8] = {-1, 0, 1, -1, 1, -1, 0, 1};
int n;
bool inner(int x, int y) {
    return (0 <= x && x < n && 0 <= y && y < n);
}
vector<vector<int>> v;
map<vector<vector<int>>, int> m;

void printv(vector<vector<int>> t) {
    for (int i  =0 ; i < n ;i++) {
        for (int j = 0 ; j < n ;j++) {
            printf("%d ", t[i][j]);
        } 
        printf("\n");
    }
}

bool possible = false;

void BFS() {
    m[v] = 1;
    queue<vector<vector<int>>> q;
    q.push(v);
    while(!q.empty()) {
        auto c = q.front();
        q.pop();
        int cnt = 0;
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (c[i][j] == 2) {
                    cnt += 1;
                    for (int dir = 0 ; dir < 8 ;dir++) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        int nx2 = i + dx[dir]*2;
                        int ny2 = j + dy[dir]*2;
                        if (inner(nx, ny) && inner(nx2, ny2)) {
                            if (c[nx][ny] == 2 && c[nx2][ny2] == 0) {
                                vector<vector<int>> next = c;
                                next[i][j] = 0;
                                next[nx][ny] = 0;
                                next[nx2][ny2] = 2;
                                //printv(next);
                                if (m[next] == 0) {
                                    m[next] = 1;
                                    q.push(next);
                                }
                            }
                        } 
                    }
                }
            }
        }
        if (cnt == 1) {
            possible = true;
            return;
        }
    }
    

}

int main() {

    cin >>n;
    
    
    v.resize(n, vector<int> (n));
    for (int i = 0 ; i < n ; i++) {
        for (int j = 0 ; j < n ; j++) {
            cin >> v[i][j];
        }
    }
    BFS();
    if (possible) {
        printf("Possible\n");
    }
    else printf("Impossible\n");

    return 0;
}