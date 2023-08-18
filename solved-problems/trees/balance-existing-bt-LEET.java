// https://leetcode.com/problems/balance-a-binary-search-tree

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
    // static HashMap<TreeNode, Integer> storedHeights;
    
    // static int getHeight(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }

    //     return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    // }
    // // static int returnedHeight = 0;
    
    // static TreeNode leftRotate(TreeNode root) {
    //     TreeNode newRoot = root.right;
    //     root.right = newRoot.left;
    //     newRoot.left = root;
    //     // root = newRoot;

    //     return newRoot;
    // }
    
    // static TreeNode rightRotate(TreeNode root) {
    //     TreeNode newRoot = root.left;
    //     root.left = newRoot.right;
    //     newRoot.right = root;
    //     // root = newRoot;    

    //     return newRoot;
    // }

    // /* 
    // Failed ATTEMPT

    // Regular BST cannot be converted to AVL tree by just applying valid
    // rotations on every node.

    // SEE NOTEBOOK FOR ILLUSTRATION OF WHY IT DOES NOT WORK.
    // */

    // public TreeNode balanceAndUpdateHeightPassively(TreeNode root) {
    //     if(root == null) {
    //         return null;
    //     }

    //     // TreeNode temp;

    //     // balance left sub-tree
    //     root.left = balanceAndUpdateHeightPassively(root.left);
    //     // int leftHeight = storedHeights.get(root.left);
    //     int leftHeight = getHeight(root.left);

    //     // balance right sub-tree
    //     root.right = balanceAndUpdateHeightPassively(root.right);
    //     // int rightHeight = storedHeights.get(root.right);
    //     int rightHeight = getHeight(root.right);
        
    //     // left sub-tree is greater than right sub-tree.
    //     if(leftHeight - rightHeight > 1) {
    //         /* 
    //         Since height of left sub-tree is greater, it obviously means 
    //         that root.left is NOT null, so we can reference 
    //         root.left.left AND root.left.right.
    //         */
            
    //         if(getHeight(root.left.left) < getHeight(root.left.right)) {
    //             /*
    //             root.left.right is taller.

    //             So, in order to actually balance the tree, a left rotation
    //             is required, before we perform the right rotation.
    //             */
    //             root.left = leftRotate(root.left);
    //         }

    //         /* 
    //         Performing the final right rotation to balance the tree.
    //         A left rotation could have taken place before this.
    //         */
    //         root = rightRotate(root);
            
    //         // leftHeight--;
    //         // rightHeight++;
    //     }
    //     // right sub-tree is taller than left sub-tree
    //     else if(leftHeight - rightHeight < -1) {
    //         /*
    //         Since height of right sub-tree is greater, it obviously means
    //         that root.right is NOT null, so we can reference 
    //         root.right.left AND root.right.right.
    //         */
    //         if(getHeight(root.right.left) > getHeight(root.right.right)) {
    //             /*
    //             root.right.left is taller.

    //             So, in order to actually balance the tree, a right rotation
    //             is required, before we perform the left rotation.
    //             */
    //             root.left = rightRotate(root.right);
    //         }

    //         /*
    //         Performing the final left rotation to balance the tree.
    //         A right rotation could have taken place before this.
    //         */
    //         root = leftRotate(root);
    //         // leftHeight++;
    //         // rightHeight--;
    //     }

    //     // storedHeight.put(root, Math.max(leftHeight, rightHeight));
    //     return root;
    // }

    static ArrayList<Integer> inorderSeq;

    static void inorderRecursive(TreeNode root) {
        if(root == null) {
            return;
        }

        inorderRecursive(root.left);
        inorderSeq.add(root.val);
        inorderRecursive(root.right);
    }

    static TreeNode getRoot(int startIndex, int endIndex) {
        // Base case when no elements left to insert in this branch of recursion
        if(startIndex > endIndex) {
            return null;
        }
        
        int mid = (startIndex + endIndex) / 2;

        return new TreeNode(
            inorderSeq.get(mid),
            
            // This call gives us the root of left sub-tree of current node.
            getRoot(startIndex, mid - 1),

            // This call gives us the root of right sub-tree of current node.
            getRoot(mid + 1, endIndex)
        );
    }


    public TreeNode balanceBST(TreeNode root) {
        inorderSeq = new ArrayList<>();
        inorderRecursive(root);

        return getRoot(0, inorderSeq.size() - 1);
    }
}