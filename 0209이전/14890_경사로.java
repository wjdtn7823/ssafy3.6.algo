#include <iostream>
#include <math.h>
#include <memory.h>
using namespace std;

int map[100][100];
int N, L;
bool visited[100][100]; //경사로 설치 여부를 확인하기 위함
int main() {
	cin >> N >> L;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> map[i][j];
	int cnt = 0; 
	//가로 모양의 길만 확인한다
	for (int i = 0; i < N; i++) {
		bool suc = true;
		for (int j = 0; j < N - 1; j++) {  
			if (abs(map[i][j + 1] - map[i][j]) > 1) {
				suc = false; break;
			}
			if (map[i][j + 1] == map[i][j])
				continue;
			else {
				// 오르막일 경우
				if (map[i][j + 1] - 1 == map[i][j]) {
					bool ok = true;
					for (int k = 0; k < L; k++)
						if ((j-k)<0 || map[i][j] != map[i][j - k] )
							ok = false;
					if (ok)
						for (int k = 0; k < L; k++) {
							if (visited[i][j - k]) {
								suc = false;
							}
							else
								visited[i][j - k] = true;
						}
					else {
						suc = false; break;
					}
				}
				//내리막일 경우
				if (map[i][j + 1] + 1 == map[i][j]) {
					bool ok = true;
					for (int k = 1; k <= L; k++)
						if (map[i][j + 1] != map[i][j + k])
							ok = false;
					if (ok)
						for (int k = 1; k <= L; k++) {
							if ((j+k)>=N || visited[i][j + k]) {
								suc = false;
							}
							else {
								visited[i][j + k] = true;
							}
						}
					else {
						suc = false; break;
					}
				}
			}
		}
		if (suc) {
			//	cout << "성공한 가로줄은 :" << i << "번째 줄 입니다." << endl;
			cnt++;
		}
	}

	//세로줄만 확인한다
	memset(visited, false, sizeof(visited));
	for (int j = 0; j < N; j++) {
		bool suc = true;
		for (int i = 0; i < N - 1; i++) {  
			if (abs(map[i + 1][j] - map[i][j]) > 1) {
				suc = false; break;
			}
			if (map[i + 1][j] == map[i][j])
				continue;
			else {
				// 오르막일 경우
				if (map[i + 1][j] - 1 == map[i][j]) {
					bool ok = true;
					for (int k = 0; k < L; k++)
						if (map[i][j] != map[i - k][j])
							ok = false;
					if (ok)
						for (int k = 0; k < L; k++) {
							if (visited[i - k][j]) {
								suc = false;
							}
							else
								visited[i - k][j] = true;
						}
					else {
						suc = false; break;
					}
				}
				//내리막일 경우
				if (map[i + 1][j] + 1 == map[i][j]) {
					bool ok = true;
					for (int k = 1; k <= L; k++)
						if (map[i + 1][j] != map[i + k][j])
							ok = false;
					if (ok)
						for (int k = 1; k <= L; k++) {
							if (visited[i + k][j]) {
								suc = false;
							}
							else {
								visited[i + k][j] = true;
							}
						}
					else {
						suc = false; break;
					}
				}
			}
		}
		if (suc) {
			cnt++;
		}
	}
	cout << cnt << endl;
}