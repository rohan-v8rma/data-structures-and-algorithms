// Problem link: https://leetcode.com/problems/binary-tree-postorder-traversal

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
    In the recursive version, call stack space is used to keep track of nodes.

    This is an ITERATIVE IMPLEMENTATION that MUTATES THE TREE

    This implementation is not ideal, because it mutates the tree
    (see how the left and right variables of `current` node
    are assigned null).

    This can cause problems when more than 1 transversal is being
    run in the same algorithm, leading to nodes disappearing.
    */
    // public List<Integer> postorderTraversal(TreeNode root) {
    //     List<Integer> postorderArr = new ArrayList<>();
    //     Stack<TreeNode> stack = new Stack<>();       

    //     if(root != null) {
    //         stack.add(root);
    //     }
 
    //     TreeNode current, temp;
    //     while(!stack.isEmpty()) {
    //         current = stack.pop();
    //         if(current.left != null) {
    //             temp = current.left;
    //             current.left = null;
    //             stack.push(current);
    //             stack.push(temp);
    //         }
    //         else if(current.right != null) {
    //             temp = current.right;
    //             current.right = null;
    //             stack.push(current);
    //             stack.push(temp);
    //         }
    //         else {
    //             postorderArr.add(current.val);
    //         }
    //     }

    //     return postorderArr;
    // }

    /*
    BETTER 1:

    ITERATIVE IMPLEMENTATION that does NOT mutate the tree.

    Uses 2 stacks, 1 for reversing the traversal obtained.

    See notebook for complete explanation of how it works. 

    Although this is OK, but it is not really useful in questions
    where the post-order traversal needs to be generated element-by-element
    */
    // public List<Integer> postorderTraversal(TreeNode root) {
    //     if(root == null) {
    //         return new ArrayList<>();   
    //     }

    //     Stack<TreeNode> nodesToBeProcessed = new Stack<>();
    //     Stack<Integer> reversePostOrder = new Stack<>();

    //     TreeNode currentNode;
    //     nodesToBeProcessed.add(root);

    //     while(!nodesToBeProcessed.isEmpty()) {
    //         currentNode = nodesToBeProcessed.pop();

    //         // Computing ROOT RIGHT LEFT
    //         reversePostOrder.push(currentNode.val);

    //         /* 
    //         Pushing LEFT in stack first, 
    //         so that RIGHT is popped and processed first

    //         (LIFO nature of stack)
    //         */
    //         if(currentNode.left != null) {
    //             nodesToBeProcessed.push(currentNode.left);
    //         }

    //         if(currentNode.right != null) {
    //             nodesToBeProcessed.push(currentNode.right);
    //         }
    //     }

    //     // Reversing ROOT RIGHT LEFT to LEFT RIGHT ROOT
    //     // List<Integer> postorderArr = new ArrayList<>();
    //     // while(!reversePostOrder.isEmpty()) {
    //     //     postorderArr.add(reversePostOrder.pop());
    //     // }

    //     // reversal using collections
    //     List<Integer> postorderArr = new ArrayList<>(reversePostOrder);
    //     Collections.reverse(postorderArr);
        
    //     return postorderArr;
    // }

    // /*
    // BETTER 2:

    // ITERATIVE IMPLEMENTATION that does NOT mutate the tree.

    // Replaces 2 stack with 1 stack AND 1 linked list.

    // No reversal required.

    // Although this is OK, but it is not really useful in questions
    // where the post-order traversal needs to be generated element-by-element
    // */
    // public List<Integer> postorderTraversal(TreeNode root) {
    //     LinkedList<Integer> traversalFrontInsert = new LinkedList<>();

    //     if(root == null) {
    //         return traversalFrontInsert;   
    //     }

    //     Stack<TreeNode> nodesToBeProcessed = new Stack<>();

    //     TreeNode currentNode;
    //     nodesToBeProcessed.add(root);

    //     while(!nodesToBeProcessed.isEmpty()) {
    //         currentNode = nodesToBeProcessed.pop();

    //         // Computing ROOT RIGHT LEFT
    //         traversalFrontInsert.addFirst(currentNode.val);

    //         /* 
    //         Pushing LEFT in stack first, 
    //         so that RIGHT is popped and processed first

    //         (LIFO nature of stack)
    //         */
    //         if(currentNode.left != null) {
    //             nodesToBeProcessed.push(currentNode.left);
    //         }

    //         if(currentNode.right != null) {
    //             nodesToBeProcessed.push(currentNode.right);
    //         }
    //     }
        
    //     // Directly returning the linked list.
    //     return traversalFrontInsert;
    // }

    /*
    OPTIMAL:

    ITERATIVE implementation that does NOT mutate the tree.

    Uses 1 stack.

    Ideal because it generates post-order traversal from start
    to end and NOT in reverse-order, like in the previous 2 approaches.
    */
    public List<Integer> postorderTraversal(TreeNode root) {
        
        List<Integer> postorderArr = new ArrayList<>();
        
        Stack<TreeNode> postorderStack = new Stack<>();

        TreeNode currentNode = root;
        TreeNode lastProcessedNode = null;

        while(currentNode != null || !postorderStack.isEmpty()) {
            
            /* 
            Doing this until all left nodes are added into stack

            This ensures LEFT RIGHT ROOT order by continuously
            expanding left child.
            */
            if(currentNode != null) {
                postorderStack.push(currentNode);
                currentNode = currentNode.left;
            }
            
            // This is for when no more LEFT children are there to expand.
            else if(!postorderStack.isEmpty()) {
                currentNode = postorderStack.pop();
                
                if(
                    /* 
                    The node doesn't have a right child, so we can directly
                    add the node to our post order traversal.
                    */
                    currentNode.right == null
                    
                    ||
                    
                    /*
                    The right node of the currentNode has already been processed
                    and added to the post order array.

                    So, we need not process it again, and can add currentNode
                    to the post order array.

                    This works because the right child of the node is just above
                    it in the stack so it gets processed just before we again
                    come to currentNode.

                    SEE NOTEBOOK FOR ILLUSTRATION.
                    */
                    currentNode.right == lastProcessedNode
                
                ) {
                    postorderArr.add(currentNode.val);
                    
                    /* 
                    Updating the last processed node to the node we just
                    processed.
                    */
                    lastProcessedNode = currentNode; 

                    /* 
                    Removing the currentNode so that it is NOT processed again
                    in the next iteration
                    */
                    currentNode = null;
                }
                /* 
                The currentNode has a right node that is NOT yet processed.
                
                So, we set that as our currentNode.

                If the right node further has left children, they will be
                expanded and added to stack.
                */
                else {
                    // Pushing current node to stack again
                    postorderStack.push(currentNode);
                    currentNode = currentNode.right;
                }
            }
        }
        
        return postorderArr;
    }
}