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
    vector <vector <int>> ans;
    int max_level = -1 ;
public:
    void dfs(TreeNode* node, int level){
        if(node==NULL) return;
        if(max_level<level){ 
            ans.resize(level+1);
            max_level = level;               
        }
        ans[level].push_back(node->val);
        
       
        if(node->right!=NULL) dfs(node->right,level+1);
        if(node->left!=NULL) dfs(node->left,level+1);
       
        
        
        return;
    }
    
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        
        dfs(root,0);
        for(int i= 0;i <ans.size();i++){
            if(i%2==0) reverse(ans[i].begin(),ans[i].end());
        }
        return ans;
        
    }
};