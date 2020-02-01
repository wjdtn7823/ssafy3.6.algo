#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

// 0: ÆÐ¹è, 1: ¹«½ÂºÎ, 2: ½Â¸®
// ³ª, °æÈñ, ¹ÎÈ£
short rule[10][10], k, ans, n;
vector<int> kyung(20);
vector<int> minho(20);
vector<int> me;

void dfs(int cnt, int game, int me_win, int k_win, int min_win, int kyung_idx, int minho_idx)
{
	if (cnt > n)
	{
		ans = 0;
		return;
	}

	if (me_win == k || k_win == k || min_win == k)
	{
		if (me_win != k)
			ans = 0;
		else
			ans = 1;
		return;
	}

	// ³ª, °æÈñ
	if (game == 0)
	{
		if (rule[me[cnt]][kyung[kyung_idx]] == 2)
			dfs(cnt + 1, 1, me_win + 1, k_win, min_win, kyung_idx + 1, minho_idx);
		else
			dfs(cnt + 1, 2, me_win, k_win + 1, min_win, kyung_idx + 1, minho_idx);
	}

	// ³ª, ¹ÎÈ£
	else if (game == 1)
	{
		if (rule[me[cnt]][minho[minho_idx]] == 2)
			dfs(cnt + 1, 0, me_win + 1, k_win, min_win, kyung_idx, minho_idx + 1);
		else
			dfs(cnt + 1, 2, me_win, k_win, min_win + 1, kyung_idx, minho_idx + 1);
	}

	// °æÈñ, ¹ÎÈ£
	else if (game == 2)
	{
		if (rule[kyung[kyung_idx]][minho[minho_idx]] == 2)
			dfs(cnt, 0, me_win, k_win + 1, min_win, kyung_idx + 1, minho_idx + 1);
		else
			dfs(cnt, 1, me_win, k_win, min_win + 1, kyung_idx + 1, minho_idx + 1);
	}
}


int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int tmp;
	cin >> n >> k;

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
			cin >> rule[i][j];
		me.push_back(i);
	}


	for (int i = 0; i < 20; i++)
	{
		cin >> tmp;
		kyung[i] = tmp;
	}
	for (int i = 0; i < 20; i++)
	{
		cin >> tmp;
		minho[i] = tmp;
	}

	do {
		dfs(0, 0, 0, 0, 0, 0, 0);
		if (ans == 1)
			break;
	} while (next_permutation(me.begin(), me.end()));

	cout << ans << '\n';
	return 0;
}