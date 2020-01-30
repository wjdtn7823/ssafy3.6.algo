#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
#define MAX 11

int N, M, K;
vector<int> tree[MAX][MAX];
int supplement[MAX][MAX];
int nutrient[MAX][MAX];

int dx[] = {0,0,1,1,1,-1,-1,-1};
int dy[] = {1,-1,1,0,-1,1,0,-1};

void spring_and_summer(){
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            if(tree[i][j].size()==0) continue;
            int legacy = 0;
            sort(tree[i][j].begin(), tree[i][j].end());
            vector<int> temp;
            for (int k = 0; k < tree[i][j].size(); ++k) {
                int age = tree[i][j][k];
                if (nutrient[i][j]<age){
                    legacy+=age/2;
                }
                else{
                    temp.push_back(age+1);
                    nutrient[i][j]-=age;
                }
            }

            tree[i][j].clear();
            for (int k = 0; k < temp.size(); ++k) {
                tree[i][j].push_back(temp[k]);
            }
            nutrient[i][j] += legacy;
        }
    }
}

void autumn(){
    for (int x = 1; x <= N; x++)
    {
        for (int y = 1; y <= N; y++)
        {
            if(tree[x][y].size()==0) continue;
            for (int k = 0; k < tree[x][y].size(); ++k) {
                int age = tree[x][y][k];
                if(age%5==0){
                    for (int dir = 0; dir < 8; ++dir) {
                        int nx = x+dx[dir];
                        int ny = y+dy[dir];
                        if(nx<1||nx>=(N+1)||ny<1||ny>=(N+1)) continue;
                        tree[nx][ny].push_back(1);
                    }
                }
            }
        }
    }
}

void winter(){
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            nutrient[i][j] += supplement[i][j];
        }
    }
}

void solve(){
    for (int i = 0; i < K; ++i) {
        spring_and_summer();
        autumn();
        winter();
    }
}

void input(){
    cin>>N>>M>>K;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            nutrient[i][j] = 5;
            cin>>supplement[i][j];
        }
    }

    for (int i = 0; i < M; ++i) {
        int x,y,age;
        cin>>x>>y>>age;
        tree[x][y].push_back(age);
    }
}

void output(){
    int answer = 0;
    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= N; ++j) {
            answer+=tree[i][j].size();
        }
    }
    cout<<answer<<'\n';
}

int main(void) {
    cin.tie(NULL);
    ios::sync_with_stdio(false);
    input();
    solve();
    output();
    return 0;
}
