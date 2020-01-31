#include <iostream>

using namespace std;

int n, k, a[13][13], ans;
int kyung[22], minho[22], p[11];

void dfs(int game, int kcnt, int mcnt, int my_win, int k_win, int m_win) {
	if (ans) return;
	if (my_win == k || k_win == k || m_win == k) {
		if (my_win == k) {
			ans = 1; return;
		}
		return;
	}
	if (game == 0) {
		// me and kyung
		for (int i = 1; i <= n; i++) {
			if (p[i] == 0) {
				p[i] = 1; 
				if (a[i][kyung[kcnt]] == 2) dfs(2, kcnt + 1, mcnt, my_win + 1, k_win, m_win);
				else dfs(1, kcnt + 1, mcnt, my_win, k_win + 1, m_win);
				p[i] = 0; 
			}
		}
	}
	else if (game == 1) {
		// kyung and minho
		if (a[kyung[kcnt]][minho[mcnt]] == 2) dfs(0, kcnt + 1, mcnt + 1, my_win, k_win + 1, m_win);
		else dfs(2, kcnt + 1, mcnt + 1, my_win, k_win, m_win + 1);
	}
	else if (game == 2) {
		// me and minho
		for (int i = 1; i <= n; i++) {
			if (p[i] == 0) {
				p[i] = 1;
				if (a[i][minho[mcnt]] == 2) dfs(0, kcnt, mcnt + 1, my_win + 1, k_win, m_win);
				else dfs(1, kcnt, mcnt + 1, my_win, k_win, m_win + 1);
				p[i] = 0;
			}
		}
	}
}

int main(void) {
	ios::sync_with_stdio(0); cin.tie(nullptr);
	cin >> n >> k;
	for (int i = 1; i <= n; i++) for (int j = 1; j <= n; j++) cin >> a[i][j];
	for (int i = 0; i < 20; i++) cin >> kyung[i];
	for (int i = 0; i < 20; i++) cin >> minho[i];

	dfs(0, 0, 0, 0, 0, 0);
	printf("%d\n", ans);
}