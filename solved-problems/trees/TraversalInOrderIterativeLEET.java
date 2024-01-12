// Problem link: https://leetcode.com/problems/binary-tree-inorder-traversal

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
    In the recursive version, call stack space is used to keep track of nodes.

    This is an ITERATIVE IMPLEMENTATION that MUTATES THE TREE

    This implementation is not ideal, because it mutates the tree
    (see how the left and right variables of `current` node
    are assigned null).

    This can cause problems when more than 1 transversal is being
    run in the same algorithm, leading to nodes disappearing.
    */
    // public List<Integer> inorderTraversal(TreeNode root) {

    //     List<Integer> inorderArr = new ArrayList<>();

    //     Stack<TreeNode> nodeStack = new Stack<>();

    //     if(root != null) {
    //         nodeStack.push(root);
    //     }

    //     TreeNode current;
    //     while(!nodeStack.isEmpty()) {
            
    //         current = nodeStack.pop(); 
            
    //         if(current.left != null) {
    //             TreeNode temp = current.left;

    //             // Tree getting mutated.
    //             current.left = null;

    //             nodeStack.push(current);
    //             nodeStack.push(temp);
    //         }
    //         else {
    //             inorderArr.add(current.val);
    //             if(current.right != null) {
    //                 nodeStack.push(current.right);
    //             }
    //         }
    //     }

    //     return inorderArr;
    
    // }

    // Iterative implementation that doesn't MUTATE THE TREE
    // public List<Integer> inorderTraversal(TreeNode root) {
    //     List<Integer> inorderArr = new ArrayList<>();

    //     Stack<TreeNode> nodeStack = new Stack<>();

    //     TreeNode currentNode = root;

    //     while(true) {
    //         /* 
    //         This is the portion where we repeatedly go to left sub-tree
    //         to properly execute in-order traversal.
    //         */
    //         while(currentNode != null) {
    //             nodeStack.push(currentNode);
    //             // See how the tree is not being mutated
    //             currentNode = currentNode.left;
    //         }

    //         if(nodeStack.isEmpty()) {
    //             break;
    //         }

    //         currentNode = nodeStack.pop();
    //         inorderArr.add(currentNode.val);
    
    //         currentNode = currentNode.right;
    //     }
        
    //     return inorderArr;
    // }

    /*
    Striver's method: 
    Iterative implementation that doesn't mutate the tree
    AND 
    is easily customizable to be used for different problems.

    This is because only 1 change of currentNode value occurs
    per iteration of while-loop.
    */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderArr = new ArrayList<>();

        if(root == null) {
            return inorderArr;
        }

        Stack<TreeNode> nodeStack = new Stack<>();

        TreeNode current = root;

        while(true) {
            if(current != null) {
                nodeStack.push(current);
                current = current.left;
            }
            else if(nodeStack.isEmpty()) {
                break;
            }
            else {
                current = nodeStack.pop();
                inorderArr.add(current.val);
                current = current.right;
            }
        }

        return inorderArr;
    }
}