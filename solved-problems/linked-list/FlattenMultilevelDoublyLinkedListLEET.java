// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list
// TODO: Make notes

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if(head == null) {
            return null;
        }

        if(head.child != null) {
            // We just store the NEXT LL for now. We will flatten it later (reason below)
            Node nextLL = head.next;
            Node childLL = flatten(head.child);
            
            // Removing the child from head.
            head.child = null;

            /* 
            Adding the CHILD LL to next of head.
            
            At the same time, updating the previous pointer of the CHILD LL.
            */
            head.next = childLL;
            childLL.prev = head;

            /* 
            This is the last node of the flattened CHILD LL.

            We copy the value to a local variable, instead of relying on the static
            global variable, because flattening NEXT LL might update the global
            variable.
            */
            Node localLast = lastInFlattened;

            /* 
            Assigning the nodes that were previously after head, 
            to a position after the flattened CHILD LL. 
            */
            if(nextLL != null) {
                /* 
                This is working because last flatten call is on NEXT LL.

                If the child flatten call was the last one, the lastInFlattened would
                have been according to the child of the node, which is NOT desirable.

                We want lastInFlattened to be set according to the last node of the linked
                list, which would be the last child of the NEXT linked list, because:

                HEAD -> CHILD LL -> NEXT LL -> Need to assign stuff here.

                We need to assign pointers from last of NEXT LL in upper recursive calls.
                */
                nextLL = flatten(nextLL);
                
                localLast.next = nextLL;
                nextLL.prev = localLast;
            }
            
        }
        else if(head.next != null) {
            Node flattenedNext = flatten(head.next);
            flattenedNext.prev = head;
            head.next = flattenedNext;
        }
        // This is for the base case when a node has neither a child nor a next node.
        else {
            lastInFlattened = head;
        }
        
        return head;
    }

    static Node lastInFlattened;
}