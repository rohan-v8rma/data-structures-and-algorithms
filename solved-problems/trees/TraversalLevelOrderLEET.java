// Problem link: https://leetcode.com/problems/binary-tree-level-order-traversal

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
    // Approach 1: Uses 2 queues (1 global and 1 for different levels)
    // public List<List<Integer>> levelOrder(TreeNode root) {
    //     List<List<Integer>> levelOrderMatrix = new ArrayList<>();
    //     Queue<TreeNode> bfs = new LinkedList<>();

    //     if(root != null) {
    //         bfs.offer(root);
    //     }
        

    //     while(!bfs.isEmpty()) {
    //         ArrayList<Integer> levelArr = new ArrayList<>();

    //         Queue<TreeNode> nextLevel = new LinkedList<>();

    //         while(!bfs.isEmpty()) {
    //             TreeNode currentNode = bfs.poll();
                
    //             levelArr.add(currentNode.val);
            
    //             if(currentNode.left != null) {
    //                 nextLevel.offer(currentNode.left);
    //             };

    //             if(currentNode.right != null) {
    //                 nextLevel.offer(currentNode.right);
    //             };
    //         }

    //         levelOrderMatrix.add(levelArr);

    //         bfs.addAll(nextLevel);
    //     }
        
    //     return levelOrderMatrix;
    // }

    // Approach 2: Uses 1 queue by limiting the number of elements processed at start of each while-loop iteration
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderMatrix = new ArrayList<>();
        Queue<TreeNode> bfs = new LinkedList<>();

        if(root != null) {
            bfs.offer(root);
        }
        

        while(!bfs.isEmpty()) {
            ArrayList<Integer> levelArr = new ArrayList<>();

            int nodesInCurrentLevel = bfs.size();
            
            for(int node = 1; node <= nodesInCurrentLevel; node++) {
                TreeNode currentNode = bfs.poll();
                
                levelArr.add(currentNode.val);
            
                if(currentNode.left != null) {
                    bfs.offer(currentNode.left);
                }

                if(currentNode.right != null) {
                    bfs.offer(currentNode.right);
                }
            }

            levelOrderMatrix.add(levelArr);
        }
        
        return levelOrderMatrix;
    }
}