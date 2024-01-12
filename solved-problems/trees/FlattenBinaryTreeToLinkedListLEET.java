// https://leetcode.com/problems/flatten-binary-tree-to-linked-list

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
    Since the linked list should be in the same order as a pre-order traversal
    of the binary tree, we know we have to EXECUTE pre-order traversal.
    */
    public void flatten(TreeNode root) {
        // This is for the tree having 0 nodes.
        if(root == null) {
            return;
        }

        Stack<TreeNode> preOrderStack = new Stack<>();
        preOrderStack.push(root);

        TreeNode prevNodeInLL = null;
        TreeNode currNodeInLL;

        // This while-loop runs as long as nodes are left to process.
        while(!preOrderStack.isEmpty()) {

            // This is the current node according to in order traversal.
            currNodeInLL = preOrderStack.pop();
            
            // Since pre-order is ROOT LEFT RIGHT, first add RIGHT to stack
            if(currNodeInLL.right != null) {
                preOrderStack.push(currNodeInLL.right);
                
                /* 
                No need to do this since it is done when the next node
                of linked list is pointed to.
                */
                // currNodeInLL.right = null;
            }

            // Next, add LEFT node.
            if(currNodeInLL.left != null) {
                preOrderStack.push(currNodeInLL.left);
                
                // Step for flattening the tree.
                currNodeInLL.left = null;
            }

            /* 
            This check is for bypassing the first iteration, when
            there is no PREVIOUS NODE
            */
            if(prevNodeInLL != null) {
                /* 
                Adding a node to the linked list
                (as right child of the previous node in the linked list)
                */
                prevNodeInLL.right = currNodeInLL;
            }

            /* 
            Setting the current node of linked list as the previous node
            for setting the right pointer in the next iteration.
            */
            prevNodeInLL = currNodeInLL;
        }
    }
}