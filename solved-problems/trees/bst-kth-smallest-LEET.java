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
        // Stack<TreeNode> nodeStack = new Stack<>();
        /*
        Using ArrayDeque instead of Stack, since Stack is thread-safe;
        which is unnecessary overhead as we are in a single-threaded
        environment
        */
        Deque<TreeNode> nodeStack = new ArrayDeque<>();

        TreeNode currentNode = root;

        // Iterative DFS / In-order traversal that doesn't MUTATE the tree
        while(true) {
            if(currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.left;
            }
            else {
                currentNode = nodeStack.pop();
                
                if(--k == 0) {
                    break;
                }

                currentNode = currentNode.right;
            }

        }

        return currentNode.val;
    }

    // static int currentK;

    // // Using recursive DFS
    // public int kthSmallest(TreeNode root, int k) {
    //     currentK = k;
    //     return dfs(root);
    // }

    // public int dfs(TreeNode node) {
    //     if(node == null) {
    //         return -1;
    //     }

    //     int leftReturn = dfs(node.left);

    //     if(leftReturn != -1) {
    //         return leftReturn;
    //     }

    //     if(--currentK == 0) {
    //         return node.val;
    //     }

    //     int rightReturn = dfs(node.right);

    //     return rightReturn;
    // }

    
}