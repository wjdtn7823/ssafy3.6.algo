#include <iostream>
#include <math.h>
using namespace std;

const int Mod = 1000000009;
long long dp[33334];

int main() {
	int N;
	cin >> N;
	dp[1] = 0;
	dp[2] = 2;
	for (int i = 3; i <= N; i++) {
		dp[i] = (dp[i-1] * 3)%Mod;
	}
	cout << dp[N];
}