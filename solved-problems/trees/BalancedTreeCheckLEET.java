// https://leetcode.com/problems/balanced-binary-tree

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
    // public int height(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }

    //     int leftHeight = 1 + height(root.left);
    //     int rightHeight = 1 + height(root.right);

    //     return(leftHeight > rightHeight ? leftHeight : rightHeight);
    // }

    // Version 1: BOth versions giving same performance
    // public boolean isBalanced(TreeNode root) {
    //     if(root == null) {
    //         return true;
    //     }   

    //     if(Math.abs(height(root.left) - height(root.right)) <= 1) {
    //         // Recursive check for whether left and right sub-trees are balanced or not.
    //         return(isBalanced(root.left) && isBalanced(root.right));
    //     }

    //     return false;
    // }

    /* Version 2
    
    Should technically be faster because recursive calls are made first 
    which have shorter height checks than the root node.
    */
    // public boolean isBalanced(TreeNode root) {
    //     if(root == null) {
    //         return true;
    //     }   

    //     if( 
    //         !(isBalanced(root.left) && isBalanced(root.right)) 
    //         ||
    //         !(Math.abs(height(root.left) - height(root.right)) <= 1)  
    //     ) {
    //         return false;
    //     }

    //     return true;
    // }

    /* 
    OPTIMAL: 
    
    Recursive function returns height and at the same time, passively modifies the 
    isBalanced static variable.

    Slight optimization - As soon as the tree is found to be unbalanced, 
    return from all the recursive calls without serving any other height function calls.
    */

    static boolean isBalanced;

    public int returnHeightAndCheckBalancingPassively(TreeNode root) {
        if(
            root == null 
            || 
            /* 
            Once it is found that one of the sub-trees is unbalanced, 
            no need to process further recursive calls.

            This OR condition improved the memory utilization of the solution.
            */
            !isBalanced
        ) {
            return 0;
        }
        
        int leftHeight = returnHeightAndCheckBalancingPassively(root.left);
        int rightHeight = returnHeightAndCheckBalancingPassively(root.right);

        if(Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced = false;
        }

        return (1 + Math.max(leftHeight, rightHeight));
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }   

        isBalanced = true;

        returnHeightAndCheckBalancingPassively(root);

        return isBalanced;
    }
}