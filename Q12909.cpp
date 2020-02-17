#include <iostream>

using namespace std;

int score[52];
int n;
int answer = 0;


int dp[52][100][200];
int max(int a, int b) {
    return a < b ? b : a;
}

// 현재 바라보고 있는 아이템: index, 반드시 x 개의 item을 골라야 하고, 지출해야 하는 금액은 반드시 y원
int go(int index, int x, int y) {
    if (x > y) {
        return -987654321;
    }
    if (x == y) {
        return score[1]*x;
    }
    if (index == 1 && x < y)
        return -987654321;
    if (dp[index][x][y] != -1)
        return dp[index][x][y];
    int ret = 0;
    for (int k = 0 ; k < n ; k++) {
        ret = max(ret, go(index - 1, x - k, y - index*k) + score[index]* k);
    }
    return dp[index][x][y] = ret;
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cin >> n;
    for (int i = 1 ; i < n ; i++) {
        cin >> score[i];
    }

    for (int i = 0 ; i < 52 ; i++) {
        for (int j = 0 ; j < 100 ; j++) {
            for (int k = 0 ; k < 200 ;k++) {
                dp[i][j][k] = -1;
            }
        }
    }
    printf("%d\n", go(n, n, 2*n-2));

    return 0;
}