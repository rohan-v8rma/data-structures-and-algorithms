// https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree

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
    // public long kthLargestLevelSum(TreeNode root, int k) {
    //     List<Long> sums = new ArrayList<>();

    //     Queue<TreeNode> bfsQueue = new LinkedList<>();
    //     bfsQueue.offer(root);
    
    //     // Level-order traversal
    //     while(!bfsQueue.isEmpty()) {
    //         int nodesInThisLevel = bfsQueue.size();
    //         long levelSum = 0;
            
    //         for(int nodeNum = 0; nodeNum < nodesInThisLevel; nodeNum++) {
    //             TreeNode currentNode = bfsQueue.poll();
                
    //             levelSum += currentNode.val;
                
    //             if(currentNode.left != null) {
    //                 bfsQueue.offer(currentNode.left);
    //             }

    //             if(currentNode.right != null) {
    //                 bfsQueue.offer(currentNode.right);
    //             }
    //         }

    //         sums.add(levelSum);
    //     }

    //     if(sums.size() < k) {
    //         return -1;
    //     }

    //     Collections.sort(sums, (a, b) -> Long.compare(b, a));
        
    //     return sums.get(k - 1);
    // }

    public long kthLargestLevelSum(TreeNode root, int k) {
        PriorityQueue<Long> sums = new PriorityQueue<>();

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);
    
        // Level-order traversal
        while(!bfsQueue.isEmpty()) {
            int nodesInThisLevel = bfsQueue.size();
            long levelSum = 0;
            
            for(int nodeNum = 0; nodeNum < nodesInThisLevel; nodeNum++) {
                TreeNode currentNode = bfsQueue.poll();
                
                levelSum += currentNode.val;
                
                if(currentNode.left != null) {
                    bfsQueue.offer(currentNode.left);
                }

                if(currentNode.right != null) {
                    bfsQueue.offer(currentNode.right);
                }
            }

            /* 
            We haven't reached k number of sums yet, so directly add the 
            sum to the min-heap
            */
            if(sums.size() < k) {
                sums.offer(levelSum);
            }
            /*
            We have k sums in the min-heap, but the current sum is 
            greater than the smallest sum in the min-heap.

            This means the smallest sum of the min-heap is actually the
            (K-1)th largest sum, which we have no need for.

            So, we remove smallest sum, and add the current sum to the 
            heap.
            */
            else if(levelSum > sums.peek()) {
                sums.poll();
                sums.offer(levelSum);
            }
        }

        if(sums.size() < k) {
            return -1;
        }
        

        return sums.poll();
    }
    
}