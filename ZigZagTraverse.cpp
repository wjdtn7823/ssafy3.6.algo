/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> depth;
    int max_level;
    void DFS(TreeNode* root, int level) {
        if (root == NULL)
            return;
        max_level = max(max_level, level);
        depth[level].push_back(root->val);
        DFS(root->left, level + 1);
        DFS(root->right, level + 1);
    }
    
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        depth.resize(1000);
        vector<vector<int>> ret;
        if (root == NULL) {
            return ret;
        }
        DFS(root, 0);
        
        for (int i = 0 ; i <= max_level  ; i++) {
            if (i % 2 == 0) {
                ret.push_back(depth[i]);
            }
            else {
                reverse(depth[i].begin(), depth[i].end());
                ret.push_back(depth[i]);
            }
        }
        return ret;
    }
};