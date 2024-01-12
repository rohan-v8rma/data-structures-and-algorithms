// Problem link: https://leetcode.com/problems/binary-tree-maximum-path-sum

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
    // The logic of this is similar to how we calculated the DIAMETER OF TREE.
    static int maxSum = Integer.MIN_VALUE;

    static int getBestSubPath(TreeNode root) {
        int leftSubPath = 0;
        int rightSubPath = 0;

        if(root.left != null) {
            leftSubPath = getBestSubPath(root.left);
        }

        if(root.right != null) {
            rightSubPath = getBestSubPath(root.right);
        }

        /*
        In this we keep the current node as the CURVE POINT.

        If the leftSubPath is greater than 0, we keep it in the path, since it increases our path value.
        If NOT, we don't include it.

        Similarly for the rightSubPath.
        */
        int pathSum = root.val + Math.max(leftSubPath, 0) + Math.max(rightSubPath, 0);
        
        // Updating the maxSum if necessary.
        maxSum = Math.max(maxSum, pathSum);

        /* 
        This returns the best sub-path possible

        Either the one that goes to the left of the current node
        OR
        the one that goes to the right of the current node
        OR 
        the one that terminates at the current node (represented by 0 in the max calculation)
        */
        return (root.val + Math.max(Math.max(leftSubPath, 0), rightSubPath));
    }

    public int maxPathSum(TreeNode root) {
        if(root == null) {
            return 0;
        }

        /* 
        Updating the static variable to MIN for each test case so that results are not 
        transferred across test cases.
        */
        maxSum = Integer.MIN_VALUE;

        // Although this function returns a value, we have no need for it at this level.
        getBestSubPath(root);
        return maxSum;
    }
}