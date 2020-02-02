#include <cstdio>
#include <cstdlib>
#include <cstring>
#define WALL_NUM 3
int N,M;
int *map, *cpy_map;

int count(int *map){
    int sum = 0;
    for(int i=0;i<N*M;i++) if(!*(map+i)) sum++;
    return sum;
}

void infect(int *map){
    for(int y=0;y<N;y++){
        for(int x=0;x<M;x++){
            int *cell = map+y*M+x;
            if(*cell==2){
                int up = 0;
                int left = 0;
                if(x!=0&&*(cell-1)==0){
                    *(cell-1)=2;
                    left = 1;
                }
                if(y!=0&&*(cell-M)==0){
                    *(cell-M)=2;
                    up = 1;
                }
                if(x!=M-1&&*(cell+1)==0){
                    *(cell+1)=2;
                }
                if(y!=N-1&&*(cell+M)==0){
                    *(cell+M)=2;
                }
                if(up){
                    y--;
                    x--;
                }
                if(left){
                    x-=2;
                }
            }
        }
    }
}

//벽 세우기
int solve(int pos, int cnt){
    if(cnt==WALL_NUM){
        memcpy(cpy_map,map, sizeof(int)*N*M);
        infect(cpy_map);
        return count(cpy_map);
    }
    if(pos>=N*M-1) return 0;
    int res = 0;
    int max = 0;
    for(int i=pos;i<N*M;i++){
        if(!*(map+i)){
            *(map+i)=1;
            res = solve(i+1, cnt+1);
            max = max<res?res:max;
            *(map+i)=0;
        }
    }
    return max;
}

int main(int argc, char const *argv[])
{
    scanf("%d %d", &N, &M);
    map = (int*)malloc(sizeof(int)*N*M);
    for(int i=0;i<N*M;i++) scanf("%d", map+i);
    cpy_map = (int*)malloc(sizeof(int)*N*M);
    printf("%d", solve(0,0));
    return 0;
}
