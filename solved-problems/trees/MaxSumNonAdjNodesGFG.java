// https://practice.geeksforgeeks.org/problems/maximum-sum-of-non-adjacent-nodes/1

/*class Node
{
    int data;
    Node left, right;
    
    Node(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}*/

class Solution
{
    
    /* 
    This solution will cause TLE because it is O(2^N)
    since there is repetition of recursive calls.
    */
    // static int choose(Node currentNode, boolean isChosen) {
    //     if(currentNode == null) {
    //         return 0;
    //     }
        
    //     int sum = 0;
    //     /*
    //     If the current node is chosen, we have no option
    //     other than to NOT choose its children
    //     */
    //     if(isChosen) {
    //         sum = 
    //         currentNode.data 
    //         + 
    //         choose(currentNode.left, false) 
    //         + 
    //         choose(currentNode.right, false);
    //     }
    //     /*
    //     If the current node is not chosen, we can
    //     either choose or NOT choose the children of current node.
        
    //     So, we call maxSum on both the children
    //     */
    //     else {
    //         sum = getMaxSum(currentNode.left) + getMaxSum(currentNode.right);
    //     }
        
    //     return sum;
    // }
    
    // static int getMaxSum(Node root) {
    //     if(root == null) {
    //         return 0;
    //     }
        
    //     return Math.max(choose(root, true), choose(root, false));
    // }
    
    /*
    TC: O(N * 2), since 2 hashmaps with atmost N entries each need to be calculated.
    */
    static int choose(Node currentNode, boolean isChosen) {
        if(currentNode == null) {
            return 0;
        }
        
        int sum;
        
        if(isChosen) {
            if(trueSum.containsKey(currentNode)) {
                // We don't perform recursive calls if the sum has already been calculated once
                return trueSum.get(currentNode);
            }
            
            /*
            Since the current node has been chosen, we don't choose the children of
            the current node.
            */
            sum 
            = 
            currentNode.data 
            + 
            choose(currentNode.left, false) 
            + 
            choose(currentNode.right, false)
            ;
    
            // Storing the sum for future reference
            trueSum.put(currentNode, sum);
            
            return sum;
        }
        
        /*
        If control of program is able to reach till this point, it means the node was NOT
        chosen to be added to the sum.
        */
        
        if(falseSum.containsKey(currentNode)) {
            /*
            We check if the sum has been calculated previously. If yes, 
            */
            return falseSum.get(currentNode);
        }
        
        
        /*
        If the current node is not chosen, we can
        either choose or NOT choose the children of current node.
        
        So, we call maxSum on both the children
        */
        sum 
        = 
        Math.max(choose(currentNode.left, true), choose(currentNode.left, false))
        +
        Math.max(choose(currentNode.right, true), choose(currentNode.right, false));
        
        falseSum.put(currentNode, sum);
        
        return sum;
    }
    
    
    static HashMap<Node, Integer> trueSum;
    static HashMap<Node, Integer> falseSum;
    
    
    static int getMaxSum(Node root) {
        trueSum = new HashMap<>();
        falseSum = new HashMap<>();
        
        return Math.max(choose(root, true), choose(root, false));
    }
}
