// Problem link: https://leetcode.com/problems/diameter-of-binary-tree

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
    // // BRUTEFORCE: Makes redundant recursive calls.
    
    // // This function recursively gets the max depth of the node.
    // static int getMaxDepth(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }
        
    //     int leftDepth = 1 + getMaxDepth(root.left);
    //     int rightDepth = 1 + getMaxDepth(root.right);
        
    //     return(leftDepth > rightDepth ? leftDepth : rightDepth);
    // }

    // // This is the main function of the BRUTEFORCE solution.
    // public int diameterOfBinaryTree(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }

    //     int diameter = -1;

    //     int chordThroughLeftSubTree = diameterOfBinaryTree(root.left);
    //     int chordThroughRightSubTree = diameterOfBinaryTree(root.right);
    //     int chordThroughRoot = getMaxDepth(root.left) + getMaxDepth(root.right);

    //     diameter = chordThroughLeftSubTree;
    //     diameter = (chordThroughRightSubTree > diameter) ? chordThroughRightSubTree : diameter;
    //     diameter = (chordThroughRoot > diameter) ? chordThroughRoot : diameter;

    //     return diameter;
    // }

    // /* 
    // BETTER: 
    // Instead of calculating the heights again and again for figuring out the length of the chord
    // through a particular point, we calculate them once using a complementary function.
    // */

    // /* 
    // This function is run as part of the setup for our BETTER solution

    // This function does make recursive calls the find height of right and left subtree
    // but along with that, it stores the height of node in its `val` property.

    // The height of the sub-tree can easily be retrieved in our main solution, by 
    
    // */
    // static void findHeights(TreeNode root) {
    //     int leftHeight = 0;
    //     int rightHeight = 0;

    //     if(root.left != null) {
    //         findHeights(root.left);
    //         leftHeight = root.left.val;
    //     }

    //     if(root.right != null) {
    //         findHeights(root.right);
    //         rightHeight = root.right.val;
    //     }

    //     root.val = 1 + Math.max(leftHeight, rightHeight);
    // }

    // /* 
    // We are still checking chords through all nodes using this function, so O(N), but the calculation
    // of the 3 values that needs to be compared in the case of each nodes takes O(1) time.
    // */
    // public int chordThroughThisNode(TreeNode root) {

    //     int chordThroughLeftSubTree = 0;
    //     int chordThroughRightSubTree = 0;
    //     int chordThroughRoot = 0;
        
    //     if(root.left != null) {
    //         chordThroughLeftSubTree = chordThroughThisNode(root.left);

    //         // Adding
    //         chordThroughRoot += root.left.val;
    //     }

    //     if(root.right != null) {
    //         chordThroughRightSubTree = chordThroughThisNode(root.right);
    //         chordThroughRoot += root.right.val;
    //     }

    //     int diameter = Math.max(chordThroughLeftSubTree, chordThroughRightSubTree);
    //     diameter = Math.max(chordThroughRoot, diameter);

    //     return diameter;
    // }

    // // Main function of our BETTER solution.
    // public int diameterOfBinaryTree(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }

    //     findHeights(root);
    //     return chordThroughThisNode(root);
    // }

    /* 
    ____OPTIMAL solution: Marginally faster than BETTER solution____
    */
    // static int diameter;

    // static int returnHeightAndFindDiameterPassively(TreeNode root) {
    //     // Finding lh and rh as usual.
    //     int leftHeight = 0;
    //     int rightHeight = 0;
        
    //     if(root.left != null) {
    //         leftHeight = returnHeightAndFindDiameterPassively(root.left);
    //     }
        
    //     if(root.right != null) {
    //         rightHeight = returnHeightAndFindDiameterPassively(root.right);
    //     }

    //     /* 
    //     While finding the heights, we check whether the current node is the CURVE POINT
    //     for the diameter of the tree.

    //     We use the calculated values of leftHeight and rightHeight to perform this check.
    //     */
    //     diameter = Math.max(diameter, leftHeight + rightHeight);

    //     // Returning height as usual.
    //     return 1 + Math.max(leftHeight, rightHeight);
    // }

    // public int diameterOfBinaryTree(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }

    //     diameter = 0;
    //     returnHeightAndFindDiameterPassively(root);
    //     return diameter;
    // }

    /*
    ___SMALLER CODE, BETTER BASE CASE
    */
    int getHeight(TreeNode root) {
        /* 
        Since we've reached a node beyond the leaf, 
        it technically has -1 height because it doesn't exist

        When leaf adds 1 to this, it gets correct height of 0.
        */
        if(root == null) return -1;

        int leftHeight = 1 + getHeight(root.left);
        int rightHeight = 1 + getHeight(root.right);

        diameter = Math.max(diameter, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight);
    }

    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = -1;
        
        getHeight(root);
        
        return diameter;
    }
}