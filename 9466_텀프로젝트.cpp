#include <bits/stdc++.h>

using namespace std;
int v[100001];
int group[100001];
int visited[100001];
int cnt, cycle_cnt;

void DFS(int here, int groupnum) {
	visited[here] = ++cnt;
	group[here] = groupnum;
	int next = v[here];
	if (visited[next] == -1) {
		DFS(next, groupnum);
	}
	else {
		if (group[next] == groupnum) {
			cycle_cnt = cnt - visited[next] + 1; 
		}
	}
}

int main() {
	int testcase;
	cin >> testcase;
	while(testcase--) {
		int n;
		cin >> n;
		memset(v, 0, sizeof(v));
		memset(group, 0, sizeof(group));
		for (int i = 1 ; i <= n ; i++) {
			cin >> v[i];
		}
		int groupnum = 1;
		int ans = 0;
		memset(visited, -1, sizeof(visited));
		for (int i = 1; i <= n ; i++) {
			if (visited[i] == -1) {
				cnt = 0;
				cycle_cnt = 0;
				DFS(i, groupnum++);
				ans += cycle_cnt;
			}
		}
		printf("%d\n", n - ans);
	}

	return 0;
}