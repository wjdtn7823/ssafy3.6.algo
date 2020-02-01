#include <iostream>
#include <vector>
#include <utility>
#define DURABILITY first
#define WEIGHT second
using namespace std;

int n, res = 0;
vector<pair<int, int>> egg;

void solve(int start)
{
	if (start >= n)
	{
		int cnt = 0;
		for (int i = 0; i < n; i++)
			if (egg[i].DURABILITY <= 0)
				cnt++;
		if (cnt > res)
			res = cnt;
		return;
	}

	// 현재 선택된 계란의 내구도가 0이하이면 아무것도 하지 않고 다음 계란으로 넘어간다.
	if (egg[start].DURABILITY <= 0)
		solve(start + 1);
	else
	{
		bool flag = false;
		// 모든 계란을 탐색한다.
		for (int i = 0; i < n; i++)
		{
			if (start == i)
				continue;

			// 선택된 계란의 내구도가 0보다 크면 계란으로 내려치기 작업을 수행한다.
			if (egg[i].DURABILITY > 0)
			{
				flag = true;  // 계란치기를 수행했으므로 flag를 true로 바꿔준다.
				egg[start].DURABILITY -= egg[i].WEIGHT;
				egg[i].DURABILITY -= egg[start].WEIGHT;
				solve(start + 1);  // 계란치기를 완료했으므로 다음 계란으로 넘어간다.

				// 계란의 내구도를 원래의 값으로 복원해준다.
				egg[start].DURABILITY += egg[i].WEIGHT;
				egg[i].DURABILITY += egg[start].WEIGHT;
			}
		}

		// 만약 계란치기 작업이 수행되지 않았다면 현재 남은 계란이 모두 깨져있음을 의미한다.
		// 그렇기 때문에 굳이 다음 오른쪽 계란으로 넘어가지 말고 바로 마지막 계란으로 넘어가면서 탐색 작업을 끝낸다.
		if (!flag)
			solve(n);
	}
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	int x, y;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> x >> y;
		egg.push_back({ x,y });
	}

	solve(0);
	cout << res << '\n';
	return 0;
}