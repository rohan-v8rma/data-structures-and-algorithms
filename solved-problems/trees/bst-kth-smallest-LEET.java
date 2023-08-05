// Problem link: https://leetcode.com/problems/kth-smallest-element-in-a-bst

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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> nodeStack = new Stack<>();

        int current = 1;
        TreeNode currentNode = root;
        TreeNode temp;

        while(true) {
            if(currentNode.left != null) {
                temp = currentNode;
                currentNode = currentNode.left;
                temp.left = null;
                nodeStack.push(temp);
            }
            else {
                if(current == k) {
                    break;
                }
                current++;
                
                if(currentNode.right != null) {
                    currentNode = currentNode.right;
                    continue;
                }

                currentNode = nodeStack.pop();
            }
        }

        return currentNode.val;
    }
}