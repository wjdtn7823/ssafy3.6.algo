#include <cstdio>
#define MAX 8
int N;
int S[MAX], W[MAX];
int answer;

void dfs(int idx){
    if(idx>=N){
        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            if(S[i]<=0) cnt++;
        }
        answer=answer<cnt?cnt:answer;
        return;
    }
    if(S[idx]<=0) dfs(idx+1);
    else{
        bool flag = false;
        for (int i = 0; i < N; ++i) {
            if(i==idx||S[i]<=0) continue;
            else flag = true;
            S[idx]-=W[i];
            S[i]-=W[idx];
            dfs(idx+1);
            S[idx]+=W[i];
            S[i]+=W[idx];
        }
        if(!flag) dfs(N);
    }
}

int main(int argc, char const *argv[]){
    answer = 0;
    scanf("%d", &N);
    for(int i=0;i<N;i++) scanf("%d %d", &S[i], &W[i]);
    dfs(0);
    printf("%d", answer);
    return 0;
}
