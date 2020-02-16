#include <iostream>

using namespace std;

int D, N; //³¯¾¾, ¿Ê°³¼ö

struct Cloth {
	int start;
	int end;
	int sati;
};

int weather[201];
Cloth cloth[201];
int dp[201][201];

int max(int a, int b) {
	if (a > b) return a;
	return b;
}

void init() {
	cin >> D >> N;
	for (int i = 1; i <= D; i++) {
		cin >> weather[i];
		for (int j = 1; j <= N; j++) {
			dp[i][j] = 0;
		}
	}
	for (int i = 1; i <= N; i++) {
		cin >> cloth[i].start >> cloth[i].end >> cloth[i].sati;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	init();
	for (int i = 2; i <= D; i++) {
		for (int j = 1; j <= N; j++) {
			for (int k = 1; k <= N; k++) {
				if (cloth[j].start <= weather[i] && cloth[j].end >= weather[i]) {
					if(cloth[k].start <= weather[i-1] && cloth[k].end >= weather[i-1])
						dp[i][j] = max(dp[i][j], dp[i-1][k] + abs(cloth[k].sati-cloth[j].sati));
					else {
						dp[i][j] = max(dp[i][j], dp[i - 1][k]);
					}
				}
				else {
					dp[i][j] = max(dp[i - 1][k], dp[i][j]);
				}
			}
		}
	}
	int ans = 0;
	for (int i = 1; i <= N; i++) {
		ans = max(ans, dp[D][i]);
	}
	cout << ans;
}