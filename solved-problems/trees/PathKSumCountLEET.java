// https://leetcode.com/problems/path-sum-iii

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
    This function calculates sums involving the current node, by add the value 
    of the current node, to the sums uptil its parent

    BETTER than bruteforce

    TC: O(N^2), SC: O(N^2). See notebook for further explanation.
    */
    // public void calculateSumsTillCurrentNode(
    //     TreeNode node, 
    //     int targetSum, 
    //     ArrayList<Long> sumsTillParent
    // ) {
    //     if(node == null) {
    //         return;
    //     }
        
    //     ArrayList<Long> sumsTillCurrentNode = new ArrayList<>();

    //     // The node by itself is equal to the target sum, so we increment count.
    //     if(node.val == targetSum) {
    //         count++;
    //     }

    //     for(long sumTillParent: sumsTillParent) {
    //         // Calculating the new sum
    //         sumTillParent += node.val;
            
    //         // Adding the new sum to sumsTillCurrentNode
    //         sumsTillCurrentNode.add(sumTillParent);
            
    //         // If the calculated sum is equal to target sum, we increment count.
    //         if(sumTillParent == targetSum) {
    //             count++;
    //         }
    //     }

    //     // A sum that starts at the current node itself.
    //     sumsTillCurrentNode.add((long)node.val);

    //     // Calculates the sums including node.left. Increments count if necessary.
    //     calculateSumsTillCurrentNode(node.left, targetSum, sumsTillCurrentNode);

    //     // Calculates the sums including node.right. Increments count if necessary.
    //     calculateSumsTillCurrentNode(node.right, targetSum, sumsTillCurrentNode);
    // }


    /*
    OPTIMAL:

    TC: O(N^2)
    It is unchanged since L sums are calculated at each level L. (WORST CASE: N LEVELS)

    SC: O(N)
    Improved because instead of having L length arrays at each level L,
    we now use a common path array having max length of N.
    */
    static void calculateSumsTillEndPoint(TreeNode endPoint, int targetSum) {
        // base case.
        if(endPoint == null) {
            return;
        }

        // Adding the current node to the current path.
        currentPath.add(endPoint.val);

        /* 
        This sum rolls upwards.

        First it includes endPoint, 
        then it includes parent of endPoint as well
        then it includes parent of parent of endPoint as well, and so on...

        We use LONG because the values of the nodes can cause overflow.
        */
        long rollingSum = 0;

        for(int index = currentPath.size() - 1; index >= 0; index--) {
            rollingSum += currentPath.get(index);

            /* 
            If at any point the rolling sum becomes equal to target sum, 
            count IS incremented.
            */
            if(rollingSum == targetSum) {
                count++;
            }
        }
        
        calculateSumsTillEndPoint(endPoint.left, targetSum);
        calculateSumsTillEndPoint(endPoint.right, targetSum);

        // Removing the current node from path as a backtracking step.
        currentPath.remove(currentPath.size() - 1);
    }



    // Used in both approaches
    static int count;
    
    /*
    Used only in second approach
    
    Since we are storing path values in the array, instead of sums
    we only need to use Integer instead of Long since 
    1 node value can be contained in the bounds of Integer
    */
    static ArrayList<Integer> currentPath;



    public int pathSum(TreeNode root, int targetSum) {
        count = 0;
        
        // First approach
        // calculateSumsTillCurrentNode(root, targetSum, new ArrayList<>());

        // Second approach
        currentPath = new ArrayList<>();
        calculateSumsTillEndPoint(root, targetSum);

        return count;
    }
}