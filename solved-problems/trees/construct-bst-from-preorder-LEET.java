// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal

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
    //  Root is always at start index of sub-array
    static TreeNode getRoot(int start, int end, int[] preorder) {
        // Base case.
        if(start > end) {
            return null;
        }

        int rootVal = preorder[start];

        int rightSubTreeStart;
        // get start of elements which are greater than root.
        for(
            rightSubTreeStart = start + 1; 
            rightSubTreeStart <= end; 
            rightSubTreeStart++
        ) {
            // Found the first element greater than root.
            if(preorder[rightSubTreeStart] > rootVal) {
                break;
            }
        }

        return new TreeNode(
            rootVal,
            getRoot(start + 1, rightSubTreeStart - 1, preorder),
            getRoot(rightSubTreeStart, end, preorder)
        );
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return getRoot(0, preorder.length - 1, preorder);
    }
}