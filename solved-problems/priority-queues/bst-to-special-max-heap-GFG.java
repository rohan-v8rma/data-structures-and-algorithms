// https://practice.geeksforgeeks.org/problems/bst-to-max-heap

/*class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}*/

class Solution {
    public static void convertToMaxHeapUtil(Node root) {
    
        List<Integer> inorder = new ArrayList<>();
    
        Deque<Node> stack = new ArrayDeque<>();
    
        Node copyOfRoot = root;
        
        // Iterative in-order traversal.
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            
            if(stack.isEmpty()) {
                break;
            }
            
            root = stack.pop();
            inorder.add(root.data);
            
            // Going to right of root, whether it be an element or be null.
            root = root.right;
        }
        
        
        root = copyOfRoot;
        
        /* 
        Post Order Traversal on the tree for writing values.
        
        This takes the sorted array (inorder traversal) we have and goes to left
        sub-tree to change their values using the first part of the array.
        
        The first part of the array obviously has less values due to the array being sorted.
        
        Then, it goes to the right sub-tree to change their values using the second part
        of the array.
        
        The second part has larger values than the first part, because array is sorted.
        This helps satisfy the SPECIAL property that we want.
        
        Finally, to make it a max heap, the last element is written to root
        (last element is greatest of all)
        */
        int currentIdx = 0;
        Node lastProcessedNode = null;
        
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }    
            
            // No more nodes left to process.
            if(stack.isEmpty()) {
                break;
            }
            
            root = stack.pop();
            
            
            if(
                root.right == null // There is NO right child
                ||
                lastProcessedNode == root.right // The right child was just processed
            ) {
                lastProcessedNode = root;
                root.data = inorder.get(currentIdx++);   
                // Removing the processed node.
                root = null;
            }
            // There is a right child that hasn't been processed, so we go to process it.
            else {
                // Saving the current root to stack.
                stack.push(root);
                root = root.right;
            }
        }
        
        
    }
}