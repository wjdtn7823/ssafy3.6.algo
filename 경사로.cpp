#include <iostream>
#include <algorithm>
#include <stdlib.h>
using namespace std;
#define MAX 101
int map[MAX][MAX];

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N,L;
    cin>>N>>L;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            cin>>map[i][j];
        }
    }

    int ans = 0;

    //가로
    for (int i = 0; i < N; ++i) {
        int dist = 1;
        for (int j = 1; j < N; ++j) {
            if(abs(map[i][j-1]-map[i][j])>=2) break;
            if(map[i][j-1]==map[i][j]) dist++;
            else if(map[i][j-1]>map[i][j]) {        //step down
                if(dist>=0) dist = 1-L;
                else break;
            }
            else if(map[i][j-1]<map[i][j]) {        //step up
                if(dist>=L) dist = 1;
                else break;
            }
            if(j==N-1&&dist>=0) {
                ans++;
                //cout<<i<<' ';
            }
        }
    }

    //세로
    for (int j = 0; j < N; ++j) {
        int dist = 1;
        for (int i = 1; i < N; ++i) {
            if(abs(map[i-1][j]-map[i][j])>=2) break;
            if(map[i-1][j]==map[i][j]) dist++;
            else if(map[i-1][j]>map[i][j]) {
                if(dist>=0) dist = 1-L;
                else break;
            }
            else if(map[i-1][j]<map[i][j]) {
                if(dist>=L) dist = 1;
                else break;
            }
            if(i==N-1&&dist>=0) {
                ans++;
                //cout<<j<<' ';
            }
        }
    }
    cout<<ans;
    return 0;
}
