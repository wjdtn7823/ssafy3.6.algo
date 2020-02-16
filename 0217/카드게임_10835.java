#include <iostream>
#include <algorithm>
#include <memory.h>
using namespace std;

int n;
int dp[2001][2001];
int first[2001];
int second[2001];

int dfs(int y, int x) {
	if (y == n+1 || x == n+1) return 0;
	int &ret = dp[y][x];
	if (ret != -1) {
		return ret;
	}
	ret = 0;
	ret = max(ret, dfs(y + 1, x));
	ret = max(ret, dfs(y + 1, x + 1));
	if (first[y] > second[x]) {
		ret = max(ret, dfs(y, x + 1) + second[x]);
	}
	return ret;
}

int main() {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> first[i];
	}
	for (int i = 1; i <= n; i++) {
		cin >> second[i];
	}
	memset(dp, -1, sizeof(dp));
	cout << dfs(1, 1);
	
}