// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // // SELF DEVELOPED ALGO (BETTER than BRUTEFORCE)
    // public boolean inThisTree(TreeNode root, TreeNode n, Set<TreeNode> ancestors) {
    //     if(root == null) {
    //         return false;
    //     }

    //     if(
    //         ancestors.contains(root)
    //         ||
    //         root == n
    //     ) {
    //         return true;
    //     }
        

    //     if(
    //         inThisTree(root.left, n, ancestors) 
    //         || 
    //         inThisTree(root.right, n, ancestors)
    //     ) {
    //         ancestors.add(root);
    //         return true;
    //     }

    //     return false;
    // }

    
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     Set<TreeNode> ancestorsOfP = new HashSet<>();
    //     Set<TreeNode> ancestorsOfQ = new HashSet<>();

    //     while(true) {
    //         if(
    //             inThisTree(root.left, p, ancestorsOfP) 
    //             && 
    //             inThisTree(root.left, q, ancestorsOfQ) 
    //         ) {
    //             root = root.left;
    //         }
    //         else if(
    //             inThisTree(root.right, p, ancestorsOfP) 
    //             && 
    //             inThisTree(root.right, q, ancestorsOfQ) 
    //         ) {
    //             root = root.right;
    //         }
    //         else {
    //             break;
    //         }
    //     }

    //     return root;
    // }

    // OPTIMAL V1: See notebook for complete explanation
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /* 
        This is the base case, when either p or q is found.

        Observe if the tree is 
        
        5 
         \
          2

        where p = 5, and q = 2. As soon as LCA is called on root, which is 5;
        root(5) is returned which is actually LCA.
        */
        if(root == p || root == q || root == null) {
            return root;
        }

        TreeNode nodeFoundInLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode nodeFoundInRight = lowestCommonAncestor(root.right, p, q);

        if(nodeFoundInLeft == null && nodeFoundInRight == null) {
            return null;
        }
        /*
        Nodes were found in both the left and right sub-tree.

        This means the current node is the lowest common ancestor of p and q,
        because one node is in left and one node is in right.

        Not possible to go lower for the ANCESTOR node, because then either 
        p or q will become unreachable.
        */
        else if(nodeFoundInLeft != null && nodeFoundInRight != null) {
            return root;
        }

        /*
        This is for when 1 of the sub-trees returned a NULL node, while 
        the other did not.

        This tells us that either p or q was found, in the left/right sub-tree.
        */
        return (nodeFoundInLeft != null ? nodeFoundInLeft : nodeFoundInRight);
    }


    // // OPTIMAL V2: Faster runtime due to reduced checks
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     /* 
    //     This is the base case, when either p or q is found.

    //     Observe if the tree is 
        
    //     5 
    //      \
    //       2

    //     where p = 5, and q = 2. As soon as LCA is called on root, which is 5;
    //     root(5) is returned which is actually LCA.
    //     */
    //     if(root == p || root == q || root == null) {
    //         return root;
    //     }

    //     TreeNode nodeFoundInLeft = lowestCommonAncestor(root.left, p, q);
    //     TreeNode nodeFoundInRight = lowestCommonAncestor(root.right, p, q);

    //     if(nodeFoundInLeft != null && nodeFoundInRight != null) {
    //         return root;
    //     }

    //     /*
    //     If nodeFoundInLeft is not null, nodeFoundInLeft is returned

    //     Else nodeFoundInRight, which can be NULL or NOT NULL
    //     */
    //     return (nodeFoundInLeft != null ? nodeFoundInLeft : nodeFoundInRight);
    // }
}