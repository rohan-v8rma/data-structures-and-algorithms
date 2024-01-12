// https://practice.geeksforgeeks.org/problems/kth-ancestor-in-a-tree/1 

/*
Structure of Node class is:

class Node {
    int data;
    Node left, right;
    
    public Node(int data){
        this.data = data;
    }
}
*/

class Solution {
    static int kCopy;
    
    public int kthAncestor(Node root, int k, int node) {
        if(root == null) {
            return 0;
        }
        else if(root.data == node) {
            kCopy = k;
            return -1;
        }
        
        int returnVal = kthAncestor(root.left, k, node);
        
        /* 
        If node not found in left sub-tree, only then do we try finding it in right sub-tree.
        
        On the otherhand, if returnVal is already -1, the recursive call does NOT
        need to take place.
        */
        returnVal = (returnVal == 0) ? kthAncestor(root.right, k, node) : returnVal;
        
        /*
        Since the required node has been found, we decrement kCopy UNTIL Kth ancestor
        is found (when kCopy becomes 0.)
        */
        if(returnVal == -1) {
            kCopy--;    
            
            /*
            When kCopy becomes 0, we have found the kth ancestor of `node`, so we 
            return root.data
            */
            if(kCopy == 0) {
                kCopy = -1;
                return root.data;
            }
        }
        
        
        
        /*
        In case, returnVal is -1 or 0, we return it.
        */
        return returnVal;
    }
}