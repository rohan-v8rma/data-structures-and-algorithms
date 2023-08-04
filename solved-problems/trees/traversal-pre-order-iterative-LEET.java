// Problem : https://leetcode.com/problems/binary-tree-preorder-traversal

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
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> preOrderStack = new Stack<>();
        List<Integer> preOrderArr = new ArrayList<>();

        if(root != null) {
            preOrderStack.push(root);
        }

        while(!preOrderStack.isEmpty()) {
            TreeNode currentNode = preOrderStack.pop();

            preOrderArr.add(currentNode.val);

            if(currentNode.right != null) {
                preOrderStack.push(currentNode.right);
            }

            if(currentNode.left != null) {
                preOrderStack.push(currentNode.left);
            }
        }

        return preOrderArr;
    }
}