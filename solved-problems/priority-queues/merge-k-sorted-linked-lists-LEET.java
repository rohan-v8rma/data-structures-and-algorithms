// https://leetcode.com/problems/merge-k-sorted-lists

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /* 
    Similar to Merge K sorted arrays questions. 
    
    Only difference is regular sorting algorithms (BRUTEFORCE) 
    won't work here as easily.
    */

    // OPTIMAL SOLUTION: O(NK.log(K))
    public ListNode mergeKLists(ListNode[] lists) {

        /* 
        PQ that will store at most K nodes of K linked-lists.

        Since it is not possible to pass both a custom comparator,
        and a collection for building the priority queue;
        we won't be able to build the PQ in O(K) using bottom up technique
        */
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (node1, node2) -> Integer.compare(node1.val, node2.val)
        );

        // Top down approach for creating heap: O(K.log(K))
        for(ListNode list: lists) {
            if(list != null) {
                minHeap.offer(list);
            }
            
        }

        ListNode head = null;
        ListNode currentInLL = null;

        ListNode nextMinNode;

        while(!minHeap.isEmpty()) {
            // Getting the next minimum node from heap.
            nextMinNode = minHeap.poll();
            
            if(head == null) {
                head = nextMinNode;
                currentInLL = head;
            }
            else {
                currentInLL.next = nextMinNode;
                currentInLL = currentInLL.next;
            }

            /* 
            If this linked list has more elements,
            we add it back to the heap.
            */
            if(nextMinNode.next != null) {
                minHeap.offer(nextMinNode.next);
            }
        
        }
        
        return head;
    }
}