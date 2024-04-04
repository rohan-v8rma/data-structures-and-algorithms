// https://leetcode.com/problems/recover-binary-search-tree
// TODO: Make notes

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
    /*_____O(N) space complexity solution_____*/

    // void getInorder(TreeNode root) {
    //     if(root == null) return;
        
    //     getInorder(root.left);

    //     inorder.add(root.val);

    //     getInorder(root.right);
    // }

    // void swap(TreeNode root, int a, int b) {
    //     if(root == null) return;

    //     if(root.val == a) root.val = b;
    //     else if(root.val == b) root.val = a;

    //     swap(root.left, a, b);
    //     swap(root.right, a, b);
    // }

    // ArrayList<Integer> inorder;

    // public void recoverTree(TreeNode root) {
    //     inorder = new ArrayList<>();
    //     getInorder(root);

    //     int first = -1;
    //     int second = -1;

    //     for(int i = 1; i < inorder.size(); i++) {
    //         // Decrement point seen.
    //         if(inorder.get(i - 1) > inorder.get(i)) {
    //             if(first == -1) {
    //                 first = i - 1;
    //                 second = i;   
    //             }
    //             else {
    //                 second = i;
    //             }
    //         }
    //     }

    //     swap(root, inorder.get(first), inorder.get(second));
    // }

    /*_____O(1) space complexity solution_____*/

    static long prev;
    static boolean firstValueFound;
    static int firstValue;
    static int secondValue;

    void doInorder(TreeNode root) {
        if(root == null) return;

        doInorder(root.left);

        // Decrement point found. There will be either 1 or 2 decrement points.
        if(prev > root.val) {
            if(firstValueFound) {
                secondValue = root.val;
            }
            else {
                firstValue = (int)prev;
                secondValue = root.val;
                firstValueFound = true;
            }
        }

        prev = root.val;

        doInorder(root.right);
    }

    void swap(TreeNode root) {
        if(root == null) return;

        if(root.val == firstValue) root.val = secondValue;
        else if(root.val == secondValue) root.val = firstValue;

        swap(root.left);
        swap(root.right);
    }

    public void recoverTree(TreeNode root) {
        prev = Long.MIN_VALUE;
        firstValueFound = false;

        doInorder(root);
        
        swap(root);
    }
}