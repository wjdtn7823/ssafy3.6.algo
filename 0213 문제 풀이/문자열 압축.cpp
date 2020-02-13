#include <string>
#include <vector>
#include <iostream>
#include <stdio.h>

using namespace std;

int solution(string s) {

	int curr_idx = 0;
	int len = s.length();
	int answer = len;
	
	for (int j = 1; j <= len; j++) {
		curr_idx = 0;
		string extract_string = "";
		bool flag = true;
		while (curr_idx<len) {
			int repeat_count = 1;
			string ps = s.substr(curr_idx, j);
			
			curr_idx += j;
			string next;
			flag = true;
			while (curr_idx<len) {
				next = s.substr(curr_idx, j);
				if (next != ps) {
					
						
					break;
				}
				curr_idx += j;
				repeat_count++;
	
			}
			if (flag == false) break;
			if (repeat_count > 1) extract_string += to_string(repeat_count);
			extract_string += ps;

		}
		if (extract_string.length() < answer) answer = extract_string.length();
	}

	return answer;
}


int main()
{
	cout<<solution("aabbaccc")<<endl;
	cout << "------------------" << endl;
	cout << solution("ababcdcdababcdcd")<<endl;
	cout << "------------------" << endl;
	cout << solution("abcabcdede")<<endl;
	cout << "------------------" << endl;
	cout << solution("abcabcabcabcdededededede")<<endl;
	cout << "------------------" << endl;
	cout << solution("xababcdcdababcdcd") << endl;
	cout << "------------------" << endl;
	cout << solution("ababababc") << endl;
	cout << "------------------" << endl;


	return 0;
}