#include <iostream>
#include <math.h>

using namespace std;

int board[103][103];
int n, l;
bool bridge[103][103];
int temp[103][103];

int main() {
	cin >> n >> l;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> board[i][j];
		}
	}
	int answer = 0;
	for (int i = 0; i < n; i++) {
		bool possible = true;
		for (int j = 1; j < n; j++) {
			if (abs(board[i][j] - board[i][j - 1]) >= 2) {
				possible = false;
				break;
			}
		}
		if (!possible)
			continue;
		
		for (int j = 1; j < n; j++) {
			if (board[i][j - 1] + 1 == board[i][j]) {
				int target = board[i][j - 1];
				for (int k = 0; k < l; k++) {
					if (j - 1 - k >= 0 && board[i][j - 1 - k] == target && bridge[i][j - 1 - k] == false) {
						bridge[i][j - 1 - k] = true;
					}
					else possible = false;
				}
			}
			else if (board[i][j - 1] == board[i][j] + 1) {
				int target = board[i][j];
				for (int k = 0; k < l; k++) {
					if (j + k < n && board[i][j + k] == target && bridge[i][j + k] == false) {
						bridge[i][j + k] = true;
					}
					else possible = false;
				}
			}
		}
		if (possible) {
			answer += 1;
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			temp[i][j] = board[i][j];
			bridge[i][j] = false;
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			board[i][j] = temp[j][i];
		}
	}

	//printf("%d\n", answer);


	for (int i = 0; i < n; i++) {
		bool possible = true;
		for (int j = 1; j < n; j++) {
			if (abs(board[i][j] - board[i][j - 1]) >= 2) {
				possible = false;
				break;
			}
		}
		if (!possible)
			continue;
		for (int j = 1; j < n; j++) {
			if (board[i][j - 1] + 1 == board[i][j]) {
				int target = board[i][j - 1];
				for (int k = 0; k < l; k++) {
					if (j - 1 - k >= 0 && board[i][j - 1 - k] == target && bridge[i][j - 1 - k] == false) {
						bridge[i][j-1-k] = true;
					}
					else possible = false;
				}
			}
			else if (board[i][j - 1] == board[i][j] + 1) {
				int target = board[i][j];
				for (int k = 0; k < l; k++) {
					if (j + k < n && board[i][j + k] == target && bridge[i][j + k] == false) {
						bridge[i][j + k] = true;
					}
					else possible = false;
				}
			}
		}
		if (possible) {
			answer += 1;
		}
	}

	printf("%d\n", answer);


	return 0;
}