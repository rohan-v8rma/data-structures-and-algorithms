// Problem link: https://leetcode.com/problems/binary-tree-postorder-traversal

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
    // Iterative implementation of post-order traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorderArr = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();       

        if(root != null) {
            stack.add(root);
        }

        TreeNode current, temp;
        while(!stack.isEmpty()) {
            current = stack.pop();
            if(current.left != null) {
                temp = current.left;
                current.left = null;
                stack.push(current);
                stack.push(temp);
            }
            else if(current.right != null) {
                temp = current.right;
                current.right = null;
                stack.push(current);
                stack.push(temp);
            }
            else {
                postorderArr.add(current.val);
            }
        }

        return postorderArr;
    }
}