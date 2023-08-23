// https://practice.geeksforgeeks.org/problems/is-binary-tree-heap

/*
Node defined as
class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
*/

// See notebook for complete explanation

class Solution {
    boolean isHeap(Node tree) {
        
        Queue<Node> bfsQueue = new LinkedList<>();
        bfsQueue.offer(tree);
        
        boolean noMoreChildren = false;
        
        while(!bfsQueue.isEmpty()) {
            Node currentNode = bfsQueue.poll();
            
            // In the case that LEFT child of current node is PRESENT.
            if(currentNode.left != null) {
                if(
                    noMoreChildren
                    ||
                    currentNode.data < currentNode.left.data
                ) {
                    return false;
                }
                
                
                bfsQueue.offer(currentNode.left);
            }
            else {
                /* 
                Since left child of current node is NOT present, we mark 
                the noMoreChildren flag as true, 
                
                So, even the right child of the current node CANNOT be present.
                */
                noMoreChildren = true;
            }
            
            // In the case that RIGHT child of current node is PRESENT.
            if(currentNode.right != null) {
                if(
                    noMoreChildren
                    ||
                    currentNode.data < currentNode.right.data
                ) {
                    return false;
                }
                
                bfsQueue.offer(currentNode.right);
            }
            else {
                /* 
                Since right child of current node is NOT present, we mark 
                the noMoreChildren flag as true, 
                
                So, no subsequent de-queued node can be found to have a child.
                */
                noMoreChildren = true;
            }
        }
        
        return true;
    }
}