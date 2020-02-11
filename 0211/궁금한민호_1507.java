#include <iostream>
#include <algorithm>
using namespace std;


int N;
int city[21][21];
int bridge[21][21];
bool visited[21][21];
int main() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> city[i][j];
			bridge[i][j] = city[i][j];
		}
	}
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			for (int k = 1; k <= N; k++) {
				if (i == k || j == i) continue;
				if (city[j][i] + city[i][k] == city[j][k]) {
					bridge[j][k] = 0;
				}
				else if (city[j][k] > city[j][i] + city[i][k]) {
					cout << -1;
					return 0;
				}
			}
		}
	}
	int ans = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (visited[i][j] || bridge[i][j] == 0) continue;
			ans += bridge[i][j];
			visited[i][j] = true;
			visited[j][i] = true;
		}
	}
	cout << ans;
}