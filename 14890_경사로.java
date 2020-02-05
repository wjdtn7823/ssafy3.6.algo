#include <iostream>
#include <math.h>
#include <memory.h>
using namespace std;

int map[100][100];
int N, L;
bool visited[100][100]; //���� ��ġ ���θ� Ȯ���ϱ� ����
int main() {
	cin >> N >> L;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> map[i][j];
	int cnt = 0; 
	//���� ����� �游 Ȯ���Ѵ�
	for (int i = 0; i < N; i++) {
		bool suc = true;
		for (int j = 0; j < N - 1; j++) {  
			if (abs(map[i][j + 1] - map[i][j]) > 1) {
				suc = false; break;
			}
			if (map[i][j + 1] == map[i][j])
				continue;
			else {
				// �������� ���
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
				//�������� ���
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
			//	cout << "������ �������� :" << i << "��° �� �Դϴ�." << endl;
			cnt++;
		}
	}

	//�����ٸ� Ȯ���Ѵ�
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
				// �������� ���
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
				//�������� ���
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