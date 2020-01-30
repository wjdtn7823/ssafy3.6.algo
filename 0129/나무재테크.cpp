#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef struct {
	int x;
	int y;
	int age;
}tt;
int dx[] = { -1,-1,-1,0,0,1,1,1 };
int dy[] = { -1,1,0,1,-1,-1,0,1 };

void print_trees( );
void print_nutr();
int n, m, k;
int nutr[11][11];
int A[11][11];
vector <int> trees[11][11];
int main() {

	cin >> n >> m >> k;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> A[i][j];
			nutr[i][j] = 5;
		}
	}

	for (int i = 0; i < m; i++) {
		int x, y,z;
		cin >> x >> y>>z;
		trees[x][y].push_back(z);
	}

	while (k--) {
		//printf("k=%d\n", k);
		//봄
		vector <tt> dead_trees;
		vector <tt> grow_trees;
		//print_nutr();
		//print_trees();
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++) {
				vector <int> vec;
				if (trees[i][j].empty()) continue;
				sort(trees[i][j].begin(), trees[i][j].end());
				for (int k = 0; k < trees[i][j].size(); k++) {
					int age = trees[i][j][k];
					if (age > nutr[i][j]) {
						dead_trees.push_back({ i,j,age });
					}
					else {
						nutr[i][j] -= age;

						if ((age + 1) % 5 == 0) grow_trees.push_back({ i,j,1 });
						vec.push_back(age + 1);
					}
				}
				trees[i][j] = vec;
			}

		}
		//print_trees();
		//여름
		
		for (int k = 0; k < dead_trees.size(); k++) {
			int x = dead_trees[k].x;
			int y = dead_trees[k].y;
			nutr[x][y] += dead_trees[k].age / 2;
		}
		


		//가을
			
		for (int k = 0; k < grow_trees.size(); k++) {
			int cx = grow_trees[k].x;
			int cy = grow_trees[k].y;
			for (int j = 0; j < 8; j++) {
				int nx = cx + dx[j];
				int ny = cy + dy[j];
				if (nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
				trees[nx][ny].push_back(1);
			}

		}
			
		//겨울
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++) {
				nutr[i][j] += A[i][j];
			}
		}

		//print_trees();

	}



	int answer = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			answer += trees[i][j].size();
		}
	}
	cout << answer << endl;

}
void print_nutr() {

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			printf("%d ", nutr[i][j]);
		}
		printf("\n");
	}
}

void print_trees() {
	//printf("%s\n", __func__);
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (trees[i][j].size() == 0) continue;
			printf("i=%d j=%d ", i, j);
			for (int k = 0; k < trees[i][j].size(); k++) {
				printf("%d ", trees[i][j][k]);
			}
			printf("\n");
		}
		//printf("\n");
	}
}