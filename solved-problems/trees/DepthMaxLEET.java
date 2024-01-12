// Problem link: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/

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

 // Implemented both BFS and DFS approaches
class Solution {
    // DFS approach
    public int maxDepth(TreeNode root) {
        if(root == null) {
            // If null, it takes up no height, so 0.
            return 0;
        }

        // Height of a single node.
        int leftDepth = 1 + maxDepth(root.left);
        int rightDepth = 1 + maxDepth(root.right);

        return(leftDepth > rightDepth ? leftDepth : rightDepth);
    }

    // BFS approach (level order traversal)
    // public int maxDepth(TreeNode root) {
    //     int height = 0;

    //     Queue<TreeNode> levelOrderQ = new LinkedList<>();

    //     if(root != null) {
    //         levelOrderQ.offer(root);
    //     }

    //     while(!levelOrderQ.isEmpty()) {
    //         height++;
    //         int nodesInLevel = levelOrderQ.size();
    //         TreeNode current;
    //         for(int node = 1; node <= nodesInLevel; node++) {
    //             current = levelOrderQ.poll();
                
    //             if (current.left != null) { 
    //                 levelOrderQ.offer(current.left);
    //             }

    //             if (current.right != null) { 
    //                 levelOrderQ.offer(current.right);
    //             }

    //         }
    //     }

    //     return height;
    // }
}