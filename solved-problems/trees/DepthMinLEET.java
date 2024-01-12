// Problem link: https://leetcode.com/problems/minimum-depth-of-binary-tree

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
    public int minDepth(TreeNode root) {

        if(root == null) {
            return 0;
        }

        int depth = Integer.MAX_VALUE;

        int leftDepth = Integer.MAX_VALUE;
        if(root.left != null) {
            leftDepth = minDepth(root.left);

            depth = 1 + leftDepth;
        }
        
        int rightDepth = Integer.MAX_VALUE;
        if(root.right != null) {
            rightDepth = 1 + minDepth(root.right);

            if(depth > rightDepth) {
                depth = rightDepth;
            }
        }

        if(depth == Integer.MAX_VALUE) {
            return 1;
        }

        return depth;

    }
}