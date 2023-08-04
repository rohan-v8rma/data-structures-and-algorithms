// Problem link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagLevels = new ArrayList<>();
        
        Deque<TreeNode> bfs = new LinkedList<>();
        
        if(root != null) {
            bfs.add(root);
        }

        boolean forwardTraversal = true;

        while(!bfs.isEmpty()) {

            int nodesInCurrentLevel = bfs.size();
            
            List<Integer> levelArr = new ArrayList<>();

            if(forwardTraversal) {
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
            }
            else {
                for(int node = 1; node <= nodesInCurrentLevel; node++) {
                    TreeNode currentNode = bfs.pollLast();

                    levelArr.add(currentNode.val);

                    if(currentNode.right != null) {
                        bfs.offerFirst(currentNode.right);
                    }    

                    if(currentNode.left != null) {
                        bfs.offerFirst(currentNode.left);
                    }
                }
            }

            zigzagLevels.add(levelArr);
            forwardTraversal = !forwardTraversal;
        }
        
        return zigzagLevels;
    }
}