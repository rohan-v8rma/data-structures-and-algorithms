// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal 

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
    // HashMap could be added to improve TC to a faster O(N), from slower O(N)
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructFromPrePost(
            preorder, postorder, 
            0, 0,
            postorder.length
        );
    }

    // See full explanation in notebook
    public TreeNode constructFromPrePost(
        int[] preorder,
        int[] postorder,
        int preStartIdx, 
        int postStartIdx,
        int traversalLen
    ) {
        if(traversalLen == 1) {
            return new TreeNode(preorder[preStartIdx]);
        }
        else if(traversalLen < 1) {
            return null;
        }

        int rootVal = preorder[preStartIdx];
        /*
        For constructing the tree, we are assuming each node has a left child,
        before it has a right child.

        This is why we are considering the node just after the current node
        to be the root of the left sub-tree, rather than the root of the right
        sub-tree.

        So, in certain cases where a node has only 1 child, we will always
        be considering the child to be the left one.
        */
        int leftRootVal = preorder[preStartIdx + 1];

        // HashMap could be added here to cache the positions of elements.
        int idxOfLeftRootInPostOrder = postStartIdx;
        while(postorder[idxOfLeftRootInPostOrder] != leftRootVal) {
            idxOfLeftRootInPostOrder++;
        }

        /*
        The length of the left sub-traversal is fetched by subtracting the 
        starting index of the current post-order traversal from the 
        index of the root of the left sub-tree in the post-order traversal
        and adding 1 to get true length.
        */
        int leftSubTraversalLen = idxOfLeftRootInPostOrder - postStartIdx + 1;
        /* 
        Getting length of right sub-traversal 
        by subtracting length of left sub-traversal
        as well as 1 for removing the current root element.
        */
        int rightSubTraversalLen = traversalLen - 1 - leftSubTraversalLen;

        return new TreeNode(
            rootVal,
            constructFromPrePost(
                preorder,                
                postorder,
                preStartIdx + 1, // Removing the root from pre-order sub-traversal
                postStartIdx,
                leftSubTraversalLen
            ),
            constructFromPrePost(
                preorder,                
                postorder,
                preStartIdx + 1 + leftSubTraversalLen,
                postStartIdx + leftSubTraversalLen,
                rightSubTraversalLen
            )
        );
    }
}