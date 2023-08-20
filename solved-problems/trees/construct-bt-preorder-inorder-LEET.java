// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal

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
    
    public TreeNode buildTree(
        int[] preorder, 
        int[] inorder
    ) {
        // Use of hashmap 
        elementIdx = new HashMap<>();
        
        // NO NEED TO INITIALIZE IT IN THE BEGINNING. ADD VALUES AS NEW INDICES ARE TRAVERSED
        // for(int i = 0; i < inorder.length; i++) {
        //     elementIdx.put(inorder[i], i);
        // }

        return buildTree(preorder, inorder, 0, 0, inorder.length);
    }
    
    public TreeNode buildTree(
        int[] preorder, 
        int[] inorder,
        int preStart,
        int inStart,
        int lenOfTraversal
    ) {
        if(lenOfTraversal == 1) {
            return new TreeNode(preorder[preStart]);
        }
        else if(lenOfTraversal < 1) {
            return null;
        }

        int rootVal = preorder[preStart];
        
        int inorderRootIdx = elementIdx.getOrDefault(rootVal, inStart);
        
        while(inorder[inorderRootIdx] != rootVal) {
            // Using the hashmap to cache indices improved runtime.
            elementIdx.put(inorder[inorderRootIdx], inorderRootIdx);
         
            inorderRootIdx++;
        }

        /*
        inorderRootIdx points to where the current root element is, in the in-order array 
        (which is exactly one index after the end of left of in-order sub-array),
        so it can be used to calculate the length of each of the left sub-traversal.
        */
        int lenOfLeftSubTraversal = inorderRootIdx - inStart;

        return new TreeNode(
            rootVal,
            buildTree(
                preorder, inorder, 
                /*
                The starting of the pre-order is 1 after the the current starting,
                to exclude the current root element from the left pre-order traversal.
                */
                preStart + 1,
                inStart,
                lenOfLeftSubTraversal
            ),
            buildTree(
                preorder, inorder, 
                preStart + 1 + lenOfLeftSubTraversal,
                inorderRootIdx + 1,
                /*
                Removing 1 for the root, as well as removing the length of left 
                sub-traversal.
                */
                lenOfTraversal - lenOfLeftSubTraversal - 1
            )
        );

    }
}