// Problem link: https://leetcode.com/problems/validate-binary-search-tree

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
    First attempt

    Couldn't handle 
       5
      / \
     4   6
        / \
       3   7
    */
    // public boolean isValidBST(TreeNode root) {
    //     if(root == null) {
    //         return true;
    //     }

    //     if(root.left != null && !(root.left.val < root.val && isValidBST(root.left))) {
    //         return false;
    //     }

    //     if(root.right != null && !(root.right.val > root.val && isValidBST(root.right))) {
    //         return false;
    //     }

    //     return true;
    // }


    /*
    Uses long datatype for min and max. We had to use long because whoever contributed testcases is 
    a **** and used Integer.MIN_VALUE and Integer.MAX_VALUE as node values.

    The min and max values are both inclusive, which means root.val can be equal to min or max.
    */
    public boolean constraintCheck(TreeNode root, long min, long max) {
        if(root.val < min || root.val > max) {
            return false;
        }

        /* 
        We know that root.val is guaranteed to be less than max, so we update
        max for the left sub-tree

        -1 to ensure any value in left sub-tree is surely not equal to root.val
        */
    
        if(root.left != null && !constraintCheck(root.left, min, (long)root.val - 1)) {
            return false;
        }


        /* 
        We know that root.val is guaranteed to be more than min, so we update
        min for the right sub-tree

        +1 to ensure any value in right sub-tree is surely not equal to root.val
        */
        if(root.right != null && !constraintCheck(root.right, (long)root.val + 1, max)) {
            return false;
        }

        return true;
    }


    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }

        // The root node has no conditions that bind it since it has no parents.
        return constraintCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    
    // This approach uses the property of BST that in order traversal has elements in sorted order.
    // public boolean isValidBST(TreeNode root) {
    //     if(root == null) {
    //         return true;
    //     }

    //     // The root node has no conditions that bind it since it has no parents.
    //     // return constraintCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    //     Stack<TreeNode> nodes = new Stack<>();
    //     nodes.push(root);

    //     // This flag specifies whether the last node has been initialized or not
    //     boolean flag = false;
    //     int lastNode = 0;

    //     // Using iterative in-order traversal so that we can easily return false as soon as we encounter a violation of BST
    //     while(!nodes.isEmpty()) {
    //         TreeNode current = nodes.pop();
            
    //         if(current.left != null) {
    //             TreeNode temp = current.left;
    //             current.left = null;
    //             nodes.push(current);
    //             nodes.push(temp);
    //         }
    //         else {
    //             if(!flag) {
    //                 flag = true;
    //             } 
    //             else if(current.val <= lastNode) {
    //                 return false;
    //             }
                
    //             lastNode = current.val;

    //             if(current.right != null) {
    //                 nodes.push(current.right);
    //             }
    //         }
    //     }

    //     return true;        
    // }
}