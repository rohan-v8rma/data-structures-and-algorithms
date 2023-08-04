// Problem link: https://leetcode.com/problems/binary-tree-level-order-traversal-ii

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
    // Approach 1: Uses code for regular level order, but reverses the array at the end
    // public List<List<Integer>> levelOrderBottom(TreeNode root) {
    //     List<List<Integer>> levelOrderMatrix = new ArrayList<>();
    //     Queue<TreeNode> bfs = new LinkedList<>();

    //     if(root != null) {
    //         bfs.offer(root);
    //     }
        

    //     while(!bfs.isEmpty()) {
    //         ArrayList<Integer> levelArr = new ArrayList<>();

    //         int nodesInCurrentLevel = bfs.size();
            
    //         for(int node = 1; node <= nodesInCurrentLevel; node++) {
    //             TreeNode currentNode = bfs.poll();
                
    //             levelArr.add(currentNode.val);
            
    //             if(currentNode.left != null) {
    //                 bfs.offer(currentNode.left);
    //             }

    //             if(currentNode.right != null) {
    //                 bfs.offer(currentNode.right);
    //             }
    //         }

    //         levelOrderMatrix.add(levelArr);
    //     }
        
    //     Collections.reverse(levelOrderMatrix);

    //     return levelOrderMatrix;
    // }


    // Approach 2: Used linkedlist for level order matrix, and executed addFirst operation after getting the levelArr of each level.
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> levelOrderMatrix = new LinkedList<>();
        Queue<TreeNode> bfs = new LinkedList<>();

        if(root != null) {
            bfs.offer(root);
        }

        while(!bfs.isEmpty()) {
            List<Integer> levelArr = new ArrayList<>();

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

            levelOrderMatrix.addFirst(levelArr);
        }

        return levelOrderMatrix;
    }
}