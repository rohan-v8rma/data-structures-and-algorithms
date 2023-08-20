// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal

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
    static HashMap<Integer, Integer> elementIdx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        elementIdx = new HashMap<>();

        return buildTree(
            inorder,
            postorder, 
            inorder.length - 1,
            inorder.length - 1, 
            inorder.length
        );      
    }
    
    public TreeNode buildTree(
        int[] inorder, 
        int[] postorder,
        int inEndIdx,
        int postEndIdx,
        int traversalLen
    ) {
        if(traversalLen == 1) {
            return new TreeNode(inorder[inEndIdx]);
        }          
        else if(traversalLen < 1) {
            return null;
        }

        int rootVal = postorder[postEndIdx];

        int inorderRootIdx = elementIdx.getOrDefault(rootVal, inEndIdx);
        while(inorder[inorderRootIdx] != rootVal) {
            elementIdx.put(inorder[inorderRootIdx], inorderRootIdx);
            inorderRootIdx--;
        }

        /*
        inorderRootIdx currently points to index of root value in the in-order array.
        The right sub-tree starts exactly 1 index after this.
        Hence, we can use it to calculate length of the right sub-traversal.
        */
        int rightSubTraversalLen = inEndIdx - inorderRootIdx;

        return new TreeNode(
            rootVal, 
            buildTree(
                inorder, postorder,
                inorderRootIdx - 1,
                postEndIdx - 1 - rightSubTraversalLen,
                /*
                In order to get the length of the left sub-traversal, subtract the 
                length of the right sub-traversal and 1 for the current root node
                removal.
                */
                traversalLen - rightSubTraversalLen - 1
            ),
            buildTree(
                inorder, postorder,
                inEndIdx, // The in-order end index stays the same for right sub-traversal
                postEndIdx - 1, // Removing the root from the current traversal
                rightSubTraversalLen
            )
        );
    }
}