class Solution {
public:
	vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
		vector<vector<int>> v;
		vector<int> v2[500];
		queue<pair<TreeNode*, int>> q; stack<pair<TreeNode*, int>> s;

		if (root != 0) { q.push({ root, 0 }); }

		while (!q.empty()) {
			TreeNode *cur = q.front().first;
			int depth = q.front().second; q.pop();
			v2[depth].push_back(cur->val);
			if (cur->left != 0) q.push({ cur->left, depth + 1 });
			if (cur->right != 0) q.push({ cur->right, depth + 1 });
		}

		for (int i = 0; i < 500; i++) {
			if (v2[i].size() == 0) break;
			if (i & 1) {
				reverse(v2[i].begin(), v2[i].end());
				v.push_back(v2[i]);
			}
			else {
				v.push_back(v2[i]);
			}
		}
		return v;
	}
};