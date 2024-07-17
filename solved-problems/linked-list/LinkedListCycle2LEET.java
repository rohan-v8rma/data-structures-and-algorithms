// https://leetcode.com/problems/linked-list-cycle-ii
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
    TC: O(N) but SC: O(N) solution.

    This has to be reduced, to something more optimal.
    */
    // public ListNode detectCycle(ListNode head) {
    //     if(head == null || head.next == null) return null;

    //     HashSet<ListNode> visited = new HashSet<>();

    //     visited.add(head);

    //     head = head.next;

    //     while(head != null) {
    //     if(visited.contains(head)) {
    //             return head;
    //         }

    //     visited.add(head);

    //         head = head.next;
    //     }

    //     return null;
    // }

    /*
    TC: O(N) and SC:O(1)

    https://leetcode.com/problems/linked-list-cycle-ii/solutions/1701128/c-java-python-slow-and-fast-image-explanation-beginner-friendly

    - slow moves 1 step at a time, fast moves 2 steps at a time.
    - when slow and fast meet each other, they must be on the cycle
        - x denotes the length of the linked list before starting the circle
        - y denotes the distance from the start of the cycle to where slow and fast met
        - C denotes the length of the cycle
        - when they meet, slow traveled (x + y) steps while fast traveled 2 * (x + y) steps, and the extra distance (x + y) must be a multiple of the circle length C
            - note that x, y, C are all lengths or the number of steps need to move.
            - head, slow, fast are pointers.
            - head moves x steps and arrives at the start of the cycle.
    - so we have x + y = N * C. Slow pointer has already travelled y steps into the circle. If it travels x more steps, it will reach beginning of the circle, 
      since x and y add up to a multiple of the length of the cycle
    - At the same time, according to the definition of x, head will also reach the start of the cycle after moving x steps.
    - so if head and slow start to move at the same time, they will meet at the start of the cycle, that is the answer.
    */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode slow = head.next;
        ListNode fast = head.next.next; 

        // Distance travelled by slow pointer.
        int slowDist = 1;

        // Distance travelled by fast pointer.
        int fastDist = 2;

        while(slow != null) {
            // Slow travelled (x + y) steps, and fast travelled 2 * (x + y)
            if(slow == fast) {
                // Travelling of x steps by both slow and head.
                while(slow != head) {
                    head = head.next;
                    slow = slow.next;
                }

                return slow;
            }

            slow = slow.next;
            slowDist++;

            if(fast != null) {
                fast = fast.next;
                if(fast != null) {
                    fast = fast.next;
                    fastDist += 2;
                }
            }
        }

        

        return null;
    }
}