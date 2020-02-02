#include <bits/stdc++.h>

using namespace std;
int n;
vector<pair<int, int>> v;
pair<int, int> temp[11];

int arr[8];
int answer = 0;

void dfs(int index) {
    if (index == n) {
        bool same = false;
        for (int i = 0 ; i < n ;i++) {
            if (arr[i] == i) {
                same = true;
                break;
            }
        }
        if(same)
            return;
        for (int i = 0 ; i < n ; i++) {
            temp[i] = v[i];
        }
        for (int i = 0 ; i < n ; i++) {
            int target = arr[i];
            if (temp[i].first <= 0)
                continue;
            if (temp[target].first <= 0)
                continue;
            temp[i].first -= temp[target].second;
            temp[target].first -= temp[i].second;
        }
        int k = 0;
        for (int i = 0 ; i < n ; i++) {
            if( temp[i].first <= 0 )
                k++;
        }
        answer = max(answer, k);
        return;
    }
    for (int i = 0 ; i < n ; i++) {
        arr[index] = i;
        dfs(index + 1);
    }
}


int main()
{
    scanf("%d", &n);
    for (int i = 0 ; i < n ;i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        v.push_back(make_pair(x, y));
    }
    dfs(0);
    printf("%d\n", answer);
    
    return 0;
}
