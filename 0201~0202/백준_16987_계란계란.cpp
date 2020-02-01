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

	// ���� ���õ� ����� �������� 0�����̸� �ƹ��͵� ���� �ʰ� ���� ������� �Ѿ��.
	if (egg[start].DURABILITY <= 0)
		solve(start + 1);
	else
	{
		bool flag = false;
		// ��� ����� Ž���Ѵ�.
		for (int i = 0; i < n; i++)
		{
			if (start == i)
				continue;

			// ���õ� ����� �������� 0���� ũ�� ������� ����ġ�� �۾��� �����Ѵ�.
			if (egg[i].DURABILITY > 0)
			{
				flag = true;  // ���ġ�⸦ ���������Ƿ� flag�� true�� �ٲ��ش�.
				egg[start].DURABILITY -= egg[i].WEIGHT;
				egg[i].DURABILITY -= egg[start].WEIGHT;
				solve(start + 1);  // ���ġ�⸦ �Ϸ������Ƿ� ���� ������� �Ѿ��.

				// ����� �������� ������ ������ �������ش�.
				egg[start].DURABILITY += egg[i].WEIGHT;
				egg[i].DURABILITY += egg[start].WEIGHT;
			}
		}

		// ���� ���ġ�� �۾��� ������� �ʾҴٸ� ���� ���� ����� ��� ���������� �ǹ��Ѵ�.
		// �׷��� ������ ���� ���� ������ ������� �Ѿ�� ���� �ٷ� ������ ������� �Ѿ�鼭 Ž�� �۾��� ������.
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