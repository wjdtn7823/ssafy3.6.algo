#include <iostream>
#include <math.h>
using namespace std;

int main() {
	int N;
	cin >> N;
	if (N == 1) cout << 0;
	else
	cout << 2 * powf(3,(N-2));
}