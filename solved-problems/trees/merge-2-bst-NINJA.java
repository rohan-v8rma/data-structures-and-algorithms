public class Solution {
    /*
    FASTEST TO WRITE SOLUTION. Use this in test.

    TC: O(2*(M + N))
    SC: O(2*(M + N)) + O(h1 + h2)
    */
    // public static List<Integer> mergeBST(TreeNode root1, TreeNode root2) {
    //     List<Integer> mergedBst = new ArrayList<>();
        
    //     List<Integer> inorder1 = new ArrayList<>();
    //     List<Integer> inorder2 = new ArrayList<>();

    //     inorderTraversal(root1, inorder1);
    //     inorderTraversal(root2, inorder2);

    //     int len1 = inorder1.size();
    //     int len2 = inorder2.size();

    //     int idx1 = 0, idx2 = 0;

    //     while(idx1 < len1 && idx2 < len2) {
    //         int current1 = inorder1.get(idx1);
    //         int current2 = inorder2.get(idx2);
    //         if(current1 < current2) {
    //             mergedBst.add(current1);
    //             idx1++;
    //         }
    //         else {
    //             mergedBst.add(current2);
    //             idx2++;
    //         }
    //     }

    //     while(idx1 < len1) {
    //         mergedBst.add(inorder1.get(idx1++));
    //     }

    //     while(idx2 < len2) {
    //         mergedBst.add(inorder2.get(idx2++));
    //     }

    //     return mergedBst;
    // }

    // static void inorderTraversal(TreeNode root, List<Integer> inorderArr) {
    //     if(root == null && !inorder.isEmpty()) {
    //         return;
    //     }

    //     Deque<TreeNode> stack = new ArrayDeque<>();

    //     while(true) {
    //         while(root != null) {
    //             stack.push(root);
    //             root = root.left;
    //         }

    //         if(stack.isEmpty()) {
    //             break;
    //         }

    //         root = stack.pop();
    //         inorderArr.add(root.data);

    //         if(root.right != null) {
    //             root = root.right;
    //         }
    //         else {
    //             root = null;
    //         }
    //     }
    // }

    /*
    Flatten the BSTs to linked list, then merge the linked lists.

    TC: O(M + N) + O(M + N)
    SC: O(M + N) + O(h1 + h2)
    */
    // public static List<Integer> mergeBST(TreeNode root1, TreeNode root2) {
    //     root1 = bstToLinkedList(root1);
    //     root2 = bstToLinkedList(root2);

    //     List<Integer> merged = new ArrayList<>();

    //     while(root1 != null && root2 != null) {
    //         if(root1.data < root2.data) {
    //             merged.add(root1.data);
    //             root1 = root1.right;           
    //         }
    //         else {
    //             merged.add(root2.data);
    //             root2 = root2.right;
    //         }
    //     }

    //     while(root1 != null) {
    //         merged.add(root1.data);
    //         root1 = root1.right;            
    //     }

    //     while(root2 != null) {
    //         merged.add(root2.data);
    //         root2 = root2.right;            
    //     }

    //     return merged;
    // }

    // static TreeNode bstToLinkedList(TreeNode root) {
    //     Deque<TreeNode> stack = new ArrayDeque<>();

    //     TreeNode llHead = null, currentPtr = null;

    //     while(true) {
    //         if(root != null) {
    //             stack.push(root);
    //             root = root.left;
    //         }
    //         else {
    //             if(stack.isEmpty()) {
    //                 break;
    //             }

    //             root = stack.pop();
                
    //             if(llHead == null) {
    //                 llHead = root;
    //                 llHead.left = null;
    //                 currentPtr = llHead;
    //             }
    //             else {
    //                 currentPtr.right = root;
    //                 currentPtr = currentPtr.right;
    //             }

    //             root = root.right;
    //         }
    //     }

    //     return llHead;
    // }

    /*
    Reduced TC and SC 

    TC: O(M + N)
    SC: O(M + N) + O(h1 + h2)
    */
    public static List<Integer> mergeBST(TreeNode root1, TreeNode root2) {
        List<Integer> mergedBST = new ArrayList<>();

        Deque<TreeNode> inorder1 = new ArrayDeque<>();
        Deque<TreeNode> inorder2 = new ArrayDeque<>();

        List<TreeNode> nextInorder;

        TreeNode current1, current2;

        nextInorder = nextInorderNode(root1, inorder1);
        root1 = nextInorder.get(0);
        current1 = nextInorder.get(1);

        nextInorder = nextInorderNode(root2, inorder2);
        root2 = nextInorder.get(0);
        current2 = nextInorder.get(1);

        while(current1 != null && current2 != null) {
            if(current1.data < current2.data) {
                mergedBST.add(current1.data);

                nextInorder = nextInorderNode(root1, inorder1);
                root1 = nextInorder.get(0);
                current1 = nextInorder.get(1);
            }
            else {
                mergedBST.add(current2.data);
                
                nextInorder = nextInorderNode(root2, inorder2);
                root2 = nextInorder.get(0);
                current2 = nextInorder.get(1);
            }
        }


        while(current1 != null) {
            mergedBST.add(current1.data);

            nextInorder = nextInorderNode(root1, inorder1);
            root1 = nextInorder.get(0);
            current1 = nextInorder.get(1);
        }

        while(current2 != null) {
            mergedBST.add(current2.data);

            nextInorder = nextInorderNode(root2, inorder2);
            root2 = nextInorder.get(0);
            current2 = nextInorder.get(1);
        }

        return mergedBST;
    }

    static List<TreeNode> nextInorderNode(TreeNode root, Deque<TreeNode> inorderStack) {
        /* 
        We check at the top itself, if no nodes are left to traverse.

        If root == null, we would need a node from stack to traverse,
        but if stack is also EMPTY, we don't have nodes left to traverse.
        */
        if(root == null) { 
            if(inorderStack.isEmpty()) {
                // No more nodes left to traverse.
                return Arrays.asList(null, null);
            }
            else {
                // We can safely do this without checking since stack is guaranteed to have an element
                root = inorderStack.pop();      
            }
        }
        else {
            /*
            By changing the condition from root != null, root.left != null
            we save an extra stack push and pop every time,
            */
            while(root.left != null) {
                inorderStack.push(root);
                root = root.left;
            }
        }
        
        // This is the current node in the in-order traversal.
        TreeNode currentNode = root;
        
        /* 
        If the node has a right child, we go to it for the next iteration, 
        which recursively goes to left of the right child.

        Otherwise, set root to null (root.right is null), so that it 
        doesn't again recursively go to left children of this node 
        (which it already did previously).
        */
        root = root.right;


        return Arrays.asList(root, currentNode);
    }


    
}