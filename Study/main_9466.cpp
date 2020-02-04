#include <iostream>
using namespace std;

int tes, n, arr[100001], tmp;
bool finished[100001], visited[100001], fail[100001];

void dfs_true(int idx) {
	if (!finished[arr[idx]]) {
		finished[arr[idx]] = true; visited[arr[idx]] = true;
		dfs_true(arr[idx]);
	}
}


bool dfs(int idx, int find_num) {
	bool temp = false;
	if (arr[idx] == find_num) {
		finished[idx] = true; visited[idx] = true; 
		dfs_true(idx);
		return true;
	}
	
	if (!visited[arr[idx]]) {
		visited[arr[idx]] = true;
		temp = dfs(arr[idx], find_num);
		if(!temp)visited[arr[idx]] = false;
	}
	return temp;
}

int main(void) {
	ios::sync_with_stdio(0); cin.tie(nullptr);
	cin >> tes;
	for (int test_case = 1; test_case <= tes; test_case++) {
		cin >> n;
		for (int i = 1; i <= n; i++) {
			cin >> arr[i];
			if (i == arr[i]) { visited[i] = true; finished[i] = true; }
		}
		
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				visited[i] = true; bool tmp = dfs(i, i);
			}
		}
		int res = 0;
		for (int i = 1; i <= n; i++) {
			if (!finished[i]) res++;
			visited[i] = false; finished[i] = false;
		}

		printf("%d\n", res);
	}



	return 0;
}