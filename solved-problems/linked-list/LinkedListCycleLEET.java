// https://leetcode.com/problems/linked-list-cycle/
// TODO: Make notes

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {
    /*
    Fast and Slow pointers

    TC: O(N) and SC: O(1)
    */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while(slow != null) {
            if(slow == fast) return true;

            slow = slow.next;

            if(fast != null) {
                fast = fast.next;
                if(fast != null) {
                    fast = fast.next;
                }
            }
        }

        return false;
    }
}