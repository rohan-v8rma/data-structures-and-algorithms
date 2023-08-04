// Problem link: https://leetcode.com/problems/binary-tree-inorder-traversal

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /* 
    Iterative implementation

    In the recursive version, call stack space is used to keep track of nodes
    */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> inorderArr = new ArrayList<>();

        Stack<TreeNode> nodeStack = new Stack<>();

        if(root != null) {
            nodeStack.push(root);
        }

        TreeNode current;
        while(!nodeStack.isEmpty()) {
            
            current = nodeStack.pop(); 
            
            if(current.left != null) {
                TreeNode temp = current.left;
                current.left = null;
                nodeStack.push(current);
                nodeStack.push(temp);
            }
            else {
                inorderArr.add(current.val);
                if(current.right != null) {
                    nodeStack.push(current.right);
                }
            }
        }

        return inorderArr;
    
    }
}