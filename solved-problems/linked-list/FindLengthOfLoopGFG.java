// https://www.geeksforgeeks.org/problems/find-length-of-loop/1
// TODO: Make notes

/*
class Node
{
    int data;
    Node next;
    Node(int d) {data = d; next = null; }
}
*/

//Function should return the length of the loop in LL.

class Solution
{
    /*
    ____BRUTEFORCE____
    
    TC: O(N), SC: O(N), but this can be optimized further
    to reduce the extra space we're using.
    */
    // static int countNodesinLoop(Node head) {
    //     if(head == null || head.next == null) return 0;
        
    //     HashMap<Node, Integer> nodeDistances = new HashMap<>();
    //     int nodeDistance = 0;
        
    //     while(head != null) {
    //         if(nodeDistances.containsKey(head)) {
    //             return nodeDistance - nodeDistances.get(head);
    //         }
            
    //         nodeDistances.put(head, nodeDistance++);
    //         head = head.next;
    //     }
        
    //     return 0;
    // }
    
    /*
    ____OPTIMAL____
    
    TC: O(N), SC: O(1)
    */
    static int countNodesinLoop(Node head) {
        if(head == null || head.next == null) return 0;
        
        Node slow = head;
        Node fast = head;
        
        slow = slow.next;
        fast = fast.next.next;
        
        while(slow != null) {
            if(slow == fast) {
                int loopCt = 1;
                
                slow = slow.next;
                
                while(slow != fast) {
                    slow = slow.next;
                    loopCt++;
                }
                
                return loopCt;
            }
            
            slow = slow.next;
            
            if(fast != null) {
                fast = fast.next;
                if(fast != null) {
                    fast = fast.next;
                }
            }
        }
        
        return 0;
    }
}